package com.java.java.controller;

import com.java.java.model.Product;
import com.java.java.service.ProductService;
import com.java.java.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    private final SearchService searchService;
    private final ProductService productService;

    public ProductController(SearchService searchService, ProductService productService) {
        this.searchService = searchService;
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestParam MultipartFile file, @RequestParam String name, @RequestParam String color, @RequestParam Double price, @RequestParam String description) {
        return productService.create(file, name, color, price, description);
    }

    @PutMapping
    public Product updateProduct(@RequestParam MultipartFile file, @RequestParam String id, @RequestParam String name, @RequestParam String color, @RequestParam Double price, @RequestParam String description) {
        return productService.update(file, Long.valueOf(id), name, color, price, description);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct (@PathVariable Long id){
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct (@PathVariable Long id){
        return productService.delete(id);
    }


    @GetMapping("/search")
    public Page<Product> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        Pageable pageable = PageRequest.of(page - 1, size);
        return searchService.searchProducts(name, color, minPrice, maxPrice, pageable);
    }

}
