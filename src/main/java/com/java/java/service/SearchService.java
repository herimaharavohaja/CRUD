package com.java.java.service;

import com.java.java.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SearchService {
    Page<Product> searchProducts(String name, String color, Double minPrice, Double maxPrice, Pageable pageable);

}
