package com.java.java.service.impl;

import com.java.java.exception.NotFoundException;
import com.java.java.file.FileService;
import com.java.java.model.Product;
import com.java.java.repository.ProductRepository;
import com.java.java.service.ProductService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final FileService fileService;
    private final ProductRepository productRepository;

    public ProductServiceImpl(FileService fileService, ProductRepository productRepository) {
        this.fileService = fileService;
        this.productRepository = productRepository;
    }


    @Override
    public Product create(MultipartFile file, String name, String description) {
        String url = fileService.saveFile(file);
        Product product = new Product();
        product.setDescription(description);
        product.setImage(url);
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product update(MultipartFile file, Long productId, String name, String description) {
        Optional<Product> optionalProduct = this.productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            String url = product.getImage();
            if (url != null) {
                this.fileService.deleteFile(url);
            }
            url = fileService.saveFile(file);

            product.setDescription(description);
            product.setImage(url);
            product.setName(name);
            return productRepository.save(product);
        }
        throw new NotFoundException("product not found");

    }

    @Transactional
    @Override
    public Product delete(Long productId) {
        Product product = this.findById(productId);
        String url = product.getImage();
        if (url != null) {
            this.fileService.deleteFile(url);
        }
        productRepository.deleteById(productId);
        return product;
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("product not found"));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
