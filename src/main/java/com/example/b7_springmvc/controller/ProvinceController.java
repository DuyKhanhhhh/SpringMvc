package com.example.b7_springmvc.controller;

import com.example.b7_springmvc.model.Customer;
import com.example.b7_springmvc.model.Province;
import com.example.b7_springmvc.service.ICustomerService;
import com.example.b7_springmvc.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ICustomerService customerService;
    @GetMapping()
    public String listProvince(Model model){
        Iterable<Province> provinces = provinceService.findByAll();
        model.addAttribute("province",provinces);
        return "province/list";
    }
    @GetMapping("/create")
    public String createFrom(Model model){
        model.addAttribute("province",new Province());
        return "province/create";
    }
    @PostMapping("/create")
    public String createProvince(@ModelAttribute Province province, RedirectAttributes redirect){
        provinceService.save(province);
        redirect.addAttribute("message","Create new province successfully");
        return "redirect:/provinces";
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/province/update");
            modelAndView.addObject("province", province.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("province") Province province,
                         RedirectAttributes redirect) {
        provinceService.save(province);
        redirect.addFlashAttribute("message", "Update province successfully");
        return "redirect:/provinces";
    }


    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Province> provinceOptional = provinceService.findById(id);
        if(!provinceOptional.isPresent()){
            return new ModelAndView("/error_404");
        }
        Iterable<Customer> customers = customerService.findAllByProvince(provinceOptional.get());

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}

