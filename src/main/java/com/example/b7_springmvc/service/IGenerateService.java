package com.example.b7_springmvc.service;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findByAll();
    void save(T t);
    Optional<T> findById(Long id);
    void remove(Long id);
}
