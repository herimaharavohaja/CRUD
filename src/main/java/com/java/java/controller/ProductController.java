package com.java.java.controller;

import com.java.java.model.Product;
import com.java.java.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestParam MultipartFile file, @RequestParam String name, @RequestParam String description) {
        return productService.create(file, name, description);
    }

    @PutMapping
    public Product updateProduct(@RequestParam MultipartFile file, @RequestParam String id, @RequestParam String name, @RequestParam String description) {
        return productService.update(file, Long.valueOf(id) ,name, description);
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
}
