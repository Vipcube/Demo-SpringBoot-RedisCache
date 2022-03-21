package org.vipcube.spring.redis.service;

import org.vipcube.spring.redis.entity.Product;

import java.util.List;

public interface IProductService {
	List<Product> findAll();

	Product findById( Long id );

	Product create( Product product );

	Product update( Long id, Product product );
}
