package com.example.datn_teehaven.controller;

import com.example.datn_teehaven.entyti.KichCo;
import com.example.datn_teehaven.entyti.TayAo;
import com.example.datn_teehaven.service.TayAoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/admin/tay-ao")
public class TayAoController {
    @Autowired
    private TayAoService tayAoService;

    @GetMapping()
    public String hienThi(Model model) {
        model.addAttribute("listTayAo", tayAoService.findAll());
        model.addAttribute("tayAo", new KichCo());
        return "/admin-template/tay_ao/tay-ao";
    }

    @GetMapping("/dang-hoat-dong")
    public String hienThiDangHoatDong(Model model) {
        model.addAttribute("listKichCo", tayAoService.getAllDangHoatDong());
        model.addAttribute("tayAo", new TayAo());
        return "/admin-template/tay_ao/tay-ao";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(Model model) {
        model.addAttribute("listKichCo", tayAoService.getAllNgungHoatDong());
        model.addAttribute("tayAo", new TayAo());
        return "/admin-template/tay_ao/tay-ao";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") Long id) {
        TayAo tayAo = tayAoService.getById(id);
        model.addAttribute("listTayAo", tayAoService.findAll());
        model.addAttribute("tayAo", tayAo);
        return "/admin-template/tay_ao/sua-tay-ao";
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("tayAo") TayAo tayAo,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        // Kiểm tra trường trống
        if (tayAo.getTen() == null || tayAo.getTen().trim().isEmpty()) {
            result.rejectValue("ten", "error.tayAo", "Tên tay áo không được để trống.");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTayAo", tayAoService.findAll());
            return "/admin-template/tay_ao/sua-tay-ao"; // Giữ nguyên trang
        }

        // Kiểm tra lỗi trong quá trình nhập dữ liệu
        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTayAo", tayAoService.findAll());
            return "/admin-template/tay_ao/sua-tay-ao"; // Giữ nguyên trang
        }

        // Kiểm tra ký tự đặc biệt trong tên kích cỡ
        if (!tayAoService.isTenValid(tayAo.getTen())) {
            result.rejectValue("ten", "error.tayAo", "Tên tay áo không được chứa ký tự đặc biệt.");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTayAo", tayAoService.findAll());
            return "/admin-template/tay_ao/sua-tay-ao"; // Giữ nguyên trang
        }

        // Kiểm tra trùng tên khi cập nhật
        if (!tayAoService.checkTenTrungSua(tayAo.getId(), tayAo.getTen())) {
            model.addAttribute("checkTenTrung", "Tay áo đã tồn tại");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTayAo", tayAoService.findAll());
            return "/admin-template/tay_ao/sua-tay-ao"; // Giữ nguyên trang
        }

        // Cập nhật dữ liệu kích cỡ
        tayAo.setNgaySua(new Date());
        tayAoService.update(tayAo);

        // Lưu thông báo và chuyển hướng
        redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
        return "redirect:/admin/tay-ao"; // Chuyển hướng
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("tayAo", new KichCo());
        return "/admin-template/tay_ao/them-tay-ao"; // Đường dẫn tới template cho form thêm
    }

    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("tayAo") TayAo tayAo,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        // Kiểm tra trường trống
        if (tayAo.getTen() == null || tayAo.getTen().trim().isEmpty()) {
            result.rejectValue("ten", "error.tayAo", "Tên tay áo không được để trống.");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTayAo", tayAoService.findAll());
            return "/admin-template/kich_co/tay-ao"; // Giữ nguyên trang
        }

        // Kiểm tra lỗi trong quá trình nhập dữ liệu
        if (result.hasErrors()) {
            model.addAttribute("listTayAo", tayAoService.findAll());
            model.addAttribute("checkThongBao", "thaiBai");
            return "/admin-template/tay_ao/tay-ao"; // Giữ nguyên trang
        }

        // Kiểm tra ký tự đặc biệt trong tên kích cỡ
        if (!tayAoService.isTenValid(tayAo.getTen())) {
            result.rejectValue("ten", "error.tayAo", "Tên tay áo không được chứa ký tự đặc biệt.");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTayAo", tayAoService.findAll());
            return "/admin-template/tay_ao/tay-ao"; // Giữ nguyên trang
        }

        // Kiểm tra trùng tên khi thêm
        if (!tayAoService.checkTenTrung(tayAo.getTen())) {

            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tay áo đã tồn tại");
            model.addAttribute("listTayAo", tayAoService.findAll());
            return "/admin-template/tay_ao/them-tay-ao"; // Giữ nguyên trang
        }

        // Lưu thông báo và thêm kích cỡ
        tayAo.setNgayTao(new Date());
        tayAo.setNgaySua(new Date());
        tayAo.setTrangThai(0);
        tayAoService.save(tayAo);

        redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
        return "redirect:/admin/tay-ao";
    }
}
