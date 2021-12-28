package com.vyatsu.lab.services;

import com.vyatsu.lab.entities.Product;
import com.vyatsu.lab.entities.ProductSpecification;
import com.vyatsu.lab.repositories.Filter;
import com.vyatsu.lab.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getFilterProducts(Filter filter, Pageable pageable){
        int min = filter.getMinPrice();
        int max = filter.getMaxPrice();
        String text = filter.getText();
        return productRepository.findAll(Specification.where(ProductSpecification.minValue(min)).and(ProductSpecification.maxValue(max)).and(ProductSpecification.hasText(text.toLowerCase(Locale.ROOT))), pageable);
    }

    public int maxPrice(){
        return productRepository.findDistinctFirstByPriceGreaterThanOrderByPriceDesc(Integer.MIN_VALUE).getPrice();
    }

    public int minPrice(){
        return productRepository.findDistinctFirstByPriceLessThanOrderByPrice(Integer.MAX_VALUE).getPrice();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public void deleteByID(Long ID) {
        productRepository.deleteById(ID);
    }
}
