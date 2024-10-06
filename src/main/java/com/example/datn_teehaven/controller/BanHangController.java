package com.example.datn_teehaven.controller;

import com.example.datn_teehaven.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ban-hang-tai-quay")
public class BanHangController {

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/hoa-don")
    public String home(Model model) {
        model.addAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        return "admin-template/ban-hang";
    }

}
