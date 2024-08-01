package com.java.java.service;

import com.java.java.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProductService {
    Product create(MultipartFile file, String name, String color, Double price, String description);

    Product update(MultipartFile file, Long productId, String name, String color, Double price, String description);

    Product delete(Long productId);

    Product findById(Long productId);

    List<Product> findAll();
}
