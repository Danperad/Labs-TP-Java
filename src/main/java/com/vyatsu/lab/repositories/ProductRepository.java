package com.vyatsu.lab.repositories;

import com.vyatsu.lab.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor {
    Optional<Product> findById(Long ID);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAll(Specification specification, Pageable pageable);
    Product findDistinctFirstByPriceLessThanOrderByPrice(int price);
    Product findDistinctFirstByPriceGreaterThanOrderByPriceDesc(int price);
}
