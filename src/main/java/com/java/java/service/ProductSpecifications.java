package com.java.java.service;

import com.java.java.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> hasColor(String color) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("color")), color.toLowerCase());
    }

    public static Specification<Product> priceBetween(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            return null;
        }
        return (root, query, cb) -> cb.between(root.get("price"), minPrice, maxPrice);
    }
}
