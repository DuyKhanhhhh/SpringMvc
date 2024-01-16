package com.example.b7_springmvc.service.impl;

import com.example.b7_springmvc.model.Customer;
import com.example.b7_springmvc.model.Province;
import com.example.b7_springmvc.repository.IProvinceRepository;
import com.example.b7_springmvc.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private IProvinceRepository iProvinceRepository;

    @Override
    public Iterable findByAll() {
        return iProvinceRepository.findAll();
    }

    @Override
    public void save(Province province) {
        iProvinceRepository.save(province);
    }

    @Override
    public Optional findById(Long id) {
        return iProvinceRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iProvinceRepository.deleteById(id);
    }

//    @Override
//    public Page<Customer> findAll(Pageable pageable) {
//        return iProvinceRepository.findAll(pageable);
//    }
}
