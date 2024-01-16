package com.example.b7_springmvc.controller;

import com.example.b7_springmvc.model.Product;
import com.example.b7_springmvc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
public class ProductCustomer {
    @Autowired
    private IProductService productService;
    @GetMapping("")
    public String findAll(@PageableDefault(value = 5)Pageable pageable, Model model){
        Page<Product> products = productService.findAll(pageable);
        model.addAttribute("product", products);
        return "views/list";
    }
}
