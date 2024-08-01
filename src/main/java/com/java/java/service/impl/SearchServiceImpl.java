package com.java.java.service.impl;

import com.java.java.model.Product;
import com.java.java.repository.ProductSearchRepository;
import com.java.java.service.ProductSpecifications;
import com.java.java.service.SearchService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SearchServiceImpl implements SearchService {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<Product> searchProducts(String name, String color, Double minPrice, Double maxPrice, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(cb.like(productRoot.get("name"), "%" + name.toLowerCase() + "%"));
        }

        if (color != null ) {
            predicates.add(cb.like(cb.lower(productRoot.get("color")), color.toLowerCase()));
        }

        if (minPrice != null ) {
            predicates.add(cb.greaterThanOrEqualTo(productRoot.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(productRoot.get("price"), maxPrice));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        List<Product> result = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Product.class)))
                .where(predicates.toArray(new Predicate[0]));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(result, pageable, count);
    }
}
