package com.info.cybercafe.repository;

import com.info.cybercafe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
