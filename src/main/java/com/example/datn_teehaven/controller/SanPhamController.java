package com.example.datn_teehaven.controller;

import com.example.datn_teehaven.entyti.HinhAnhSanPham;
import com.example.datn_teehaven.entyti.SanPham;
import com.example.datn_teehaven.entyti.ThuongHieu;
import com.example.datn_teehaven.service.HinhAnhSanPhamSerivce;
import com.example.datn_teehaven.service.SanPhamSerivce;
import com.example.datn_teehaven.service.ThuongHieuService;
import com.google.firebase.cloud.StorageClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamSerivce sanPhamService;

    @Autowired
    private ThuongHieuService thuongHieuService;


    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("listSP",sanPhamService.getAll());
        model.addAttribute("sanPham",new SanPham());
        model.addAttribute("listTH",thuongHieuService.getAll());
        return "admin-template/san_pham/san-pham";
    }
    @GetMapping("/dang-hoat-dong")
    public String hienThiDangHoatDong(
            Model model
    ) {
        model.addAttribute("listSP", sanPhamService.getAllDangHoatDong());
        model.addAttribute("sanPham", new SanPham());
        return "admin-template/san_pham/san-pham";
    }
    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(
            Model model
    ) {
        model.addAttribute("listSP", sanPhamService.getAllNgungHoatDong());
        model.addAttribute("sanPham", new SanPham());
        return "admin-template/san_pham/san-pham";
    }

    @GetMapping("/detail/{id}")
    public String Detail(@PathVariable("id")Long id,
                         Model model){
        SanPham sanPham = sanPhamService.getById(id);
        model.addAttribute("sanPham",sanPham);
        model.addAttribute("listTH",thuongHieuService.getAll());
        return "admin-template/san_pham/sua-san-pham";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("sanPham")@Valid SanPham sanPham,
                      BindingResult result,
                      Model model,
                      RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listSP",sanPhamService.getAll());
            model.addAttribute("listTH",thuongHieuService.getAll());
            return "admin-template/san_pham/san-pham";
        } else if (!sanPhamService.checkTenTrung(sanPham.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Sản Phẩm đã tồn tại");
            model.addAttribute("listSP",sanPhamService.getAll());
            model.addAttribute("listTH",thuongHieuService.getAll());
            return "admin-template/san_pham/san-pham";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            sanPham.setSoLuongTon(0);
            sanPham.setNgayTao(Date.valueOf(LocalDate.now()));
            sanPham.setTrangThai(0);

            sanPhamService.add(sanPham);

            return "redirect:/admin/san-pham";
        }
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("sanPham") @Valid SanPham sanPham,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listSP",sanPhamService.getAll());
            model.addAttribute("listTH",thuongHieuService.getAll());
            return "admin-template/san_pham/sua-san-pham";
        } else if (!sanPhamService.checkTenTrungSua(sanPham.getId(), sanPham.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Sản phẩm đã tồn tại");
            model.addAttribute("listSP",sanPhamService.getAll());
            model.addAttribute("listTH",thuongHieuService.getAll());
            return "admin-template/san_pham/sua-san-pham";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");

            SanPham sanPham1 = sanPhamService.getById(sanPham.getId());

            sanPham.setMa(sanPham1.getMa());
            sanPham.setNgaySua(new java.util.Date());
            sanPham.setSoLuongTon(sanPham1.getSoLuongTon());

            sanPhamService.update(sanPham);

            return "redirect:/admin/san-pham";
        }
    }

}
