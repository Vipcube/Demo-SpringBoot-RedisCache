package org.vipcube.spring.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * Spring Boot will auto-configure a RedisCacheManager with default cache configuration.
 * However, we can modify this configuration prior to cache manager initialization to do more control over the default configuration.
 *
 * @author Brad Chen
 *
 */
@Configuration
public class RedisCustomConfig {
	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl( Duration.ofMinutes( 60 ) )
				.disableCachingNullValues()
				.serializeValuesWith( RedisSerializationContext.SerializationPair.fromSerializer(
						new GenericJackson2JsonRedisSerializer() ) );
	}

	//@Bean
	//public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
	//	return ( builder ) -> builder
	//			.withCacheConfiguration( "itemCache", RedisCacheConfiguration.defaultCacheConfig()
	//					.entryTtl( Duration.ofMinutes( 10 ) ) )
	//			.withCacheConfiguration( "customerCache", RedisCacheConfiguration.defaultCacheConfig()
	//					.entryTtl( Duration.ofMinutes( 5 ) ) );
	//}
}
