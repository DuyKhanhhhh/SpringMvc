package com.example.b7_springmvc.controller;

import com.example.b7_springmvc.model.Customer;
import com.example.b7_springmvc.model.Province;
import com.example.b7_springmvc.service.ICustomerService;
import com.example.b7_springmvc.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;
    @GetMapping("provinces")
    public Iterable<Province> listProvinces(){
        return provinceService.findByAll();
    }

    @GetMapping("")
    public String listCustomer(@PageableDefault(value = 3) Pageable pageable, Model model){
        Page<Customer> customers = customerService.findAll(pageable);
        model.addAttribute("customer", customers);
        return "customer/list";
    }
    @GetMapping("/search")
    public String listCustomersSearch(@PageableDefault(value = 3) Pageable pageable, @RequestParam("search") Optional<String> search, Model model){
        Page<Customer> customers;
        if(search.isPresent()){
            customers = customerService.findAllByFirstNameContaining(pageable, search.get());
        } else {
            customers = customerService.findAll(pageable);
        }
        model.addAttribute("customer", customers);
        return "customer/search";
    }
    @GetMapping("create")
    public String fromCreate(Model model){
        model.addAttribute("customer", new Customer());
        model.addAttribute("province", provinceService.findByAll());
        return "customer/create";
    }
    @PostMapping("create")
    public String createCustomer(@ModelAttribute("Customer") Customer customer, RedirectAttributes redirectAttributes){
        customerService.save(customer);
        redirectAttributes.addAttribute("message", "Create customer successfully");
        return "redirect:/customers";
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/update");
            modelAndView.addObject("customer", customer.get());
            modelAndView.addObject("province", provinceService.findByAll());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("customer") Customer customer,RedirectAttributes redirect) {
        customerService.save(customer);
        redirect.addFlashAttribute("message", "Update customer successfully");
        return "redirect:/customers";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        customerService.remove(id);
        redirect.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/customers";
    }
}
