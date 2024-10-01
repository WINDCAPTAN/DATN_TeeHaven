package com.example.datn_teehaven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {

    @GetMapping
    public String hienThi(){
        return "admin-template/san_pham/san-pham";
    }
}
