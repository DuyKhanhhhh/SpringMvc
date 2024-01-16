package com.example.b7_springmvc.repository;

import com.example.b7_springmvc.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
}
