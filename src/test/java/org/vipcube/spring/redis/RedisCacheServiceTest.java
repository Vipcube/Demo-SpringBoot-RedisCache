package org.vipcube.spring.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.vipcube.spring.redis.entity.Product;
import org.vipcube.spring.redis.repository.ProductRepository;
import org.vipcube.spring.redis.service.IProductService;
import org.vipcube.spring.redis.service.impl.ProductServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@EnableCaching
@SpringBootTest( classes = { ProductServiceImpl.class, CacheAutoConfiguration.class, RedisAutoConfiguration.class } )
public class RedisCacheServiceTest {
	@MockBean
	private ProductRepository repository;

	@Autowired
	private IProductService productService;

	@Autowired
	private CacheManager cacheManager;

	@Test
	public void testCache() {
		Product product = Product.builder()
				.id( 1L )
				.name( "Product_1" )
				.number( 100 )
				.build();

		given( this.repository.findById( 1L ) )
				.willReturn( Optional.of( product ) );

		Product entity = this.productService.findById( 1L );
		Product cache = this.productService.findById( 1L );
		assertThat( entity ).isEqualTo( product );
		assertThat( cache ).isEqualTo( product );

		verify( this.repository, times( 1 ) )
				.findById( 1L );
		assertThat( this.getFromCache()).isEqualTo( product );
	}

	private Product getFromCache(){
		return (Product) this.cacheManager.getCache( "product" ).get( 1L ).get();
	}
}
