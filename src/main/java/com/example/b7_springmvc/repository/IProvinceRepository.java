package com.example.b7_springmvc.repository;

import com.example.b7_springmvc.model.Customer;
import com.example.b7_springmvc.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IProvinceRepository extends CrudRepository<Province,Long> {
//    Page<Customer> findAll(Pageable pageable);
}
