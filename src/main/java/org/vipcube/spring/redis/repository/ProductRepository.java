package org.vipcube.spring.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vipcube.spring.redis.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
