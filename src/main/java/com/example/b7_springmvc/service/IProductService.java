package com.example.b7_springmvc.service;

import com.example.b7_springmvc.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    void save(Product product);
    Product findById(Long id);
    Page<Product> findAll(Pageable pageable);
}
