package com.java.java.repository;

import com.java.java.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ProductSearchRepository extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
