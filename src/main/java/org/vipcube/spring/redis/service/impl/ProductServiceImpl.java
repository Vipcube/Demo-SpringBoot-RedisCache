package org.vipcube.spring.redis.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.vipcube.spring.redis.entity.Product;
import org.vipcube.spring.redis.repository.ProductRepository;
import org.vipcube.spring.redis.service.IProductService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
	private final ProductRepository repository;

	public ProductServiceImpl( ProductRepository repository ){
		this.repository = repository;
	}

	@Cacheable( value = "products" )
	@Override
	public List<Product> findAll() {
		return this.repository.findAll();
	}

	@Cacheable( value = "product" )
	@Override
	public Product findById( Long id ) {
		return this.repository.findById( id )
				.orElseThrow( EntityNotFoundException::new );
	}

	@CacheEvict( value = { "product", "products" }, allEntries = true )
	@Override
	public Product create( Product product ) {
		return this.repository.save( product );
	}

	@CachePut( value = "product", key = "#product.id" )
	@Override
	public Product update( Product product ) {
		Product entity = this.findById( product.getId() );
		BeanUtils.copyProperties( product, entity, "id" );
		return this.repository.save( entity );
	}
}
