package com.example.datn_teehaven.controller;

import com.example.datn_teehaven.entyti.DiaChi;
import com.example.datn_teehaven.entyti.GioHang;
import com.example.datn_teehaven.entyti.TaiKhoan;
import com.example.datn_teehaven.entyti.VaiTro;
import com.example.datn_teehaven.service.DiaChiService;
import com.example.datn_teehaven.service.GioHangService;
import com.example.datn_teehaven.service.KhachHangService;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {
    @Autowired
    KhachHangService taiKhoanService;


    @Autowired
    DiaChiService diaChiService;

    @Autowired
    GioHangService gioHangService;


    @GetMapping()
    public String hienThi(Model model) {

        model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
        model.addAttribute("khachHang", new TaiKhoan());
        return "/admin-template/khach_hang/khach-hang";
    }

    @GetMapping("/dang-hoat-dong")
    public String hienThiDangHoatDong(Model model) {

        model.addAttribute("listTaiKhoan", taiKhoanService.getAllDangHoatDong());
        model.addAttribute("khachHang", new TaiKhoan());
        return "/admin-template/khach_hang/khach-hang";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(Model model) {

        model.addAttribute("listTaiKhoan", taiKhoanService.getAllNgungHoatDong());
        model.addAttribute("khachHang", new TaiKhoan());
        return "/admin-template/khach_hang/khach-hang";
    }

    @GetMapping("/view-update-khach-hang/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {

        List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(id);
        TaiKhoan taiKhoan = taiKhoanService.getById(id);
        model.addAttribute("listDiaChi", listDiaChi);

        model.addAttribute("idKhachHangUpdate", id);
        if (listDiaChi.size() == 5) {
            model.addAttribute("checkButtonAdd", "true");
            model.addAttribute("soDiaChi", listDiaChi.size());
        } else {
            model.addAttribute("checkButtonAdd", "false");
            model.addAttribute("soDiaChi", listDiaChi.size());
        }

//        check button add
        model.addAttribute("checkAdd", "add");

        model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
        model.addAttribute("khachHang", taiKhoan);
        model.addAttribute("diaChi", new DiaChi());
        return "/admin-template/khach_hang/sua-khach-hang";

    }

    @PostMapping("/dia-chi/add")
    public String addDiaChi(
            @RequestParam("idTaiKhoan") String idTaiKhoan,
            @RequestParam("phuongXaID") String phuongXa,
            @RequestParam("quanHuyenID") String quanHuyen,
            @RequestParam("thanhPhoID") String thanhPho,
            @RequestParam("diaChiCuThe") String diaChiCuThe,
            RedirectAttributes redirectAttributes) {
        DiaChi diaChi = new DiaChi();
        diaChi.setPhuongXa(phuongXa);
        diaChi.setQuanHuyen(quanHuyen);
        diaChi.setThanhPho(thanhPho);
        diaChi.setDiaChiCuThe(diaChiCuThe);
        diaChi.setTrangThai(1);
        diaChi.setNgayTao(new Date());
        diaChi.setNgaySua(new Date());
        diaChi.setTaiKhoan(TaiKhoan.builder().id(Long.valueOf(idTaiKhoan)).build());
        diaChiService.save(diaChi);
        return "redirect:/admin/khach-hang/view-update-khach-hang/" + idTaiKhoan;
    }

    @PostMapping("/dia-chi/update")
    public String updateDiaChi(
            @RequestParam("idKhachHang") Long idKhachHang,
            @RequestParam("idDiaChi") Long idDiaChi,
            @RequestParam("phuongXa") String phuongXa,
            @RequestParam("quanHuyen") String quanHuyen,
            @RequestParam("thanhPho") String thanhPho,
            @RequestParam("diaChiCuThe") String diaChiCuThe,
            @RequestParam("trangThai") Integer trangThai,
            RedirectAttributes redirectAttributes
    ) {
        if (trangThai == 0) {
            List<DiaChi> listDiaChi = diaChiService.getAllTrangThai(0);
            DiaChi diaChiNew = new DiaChi();
            for (DiaChi diaChiUpdate : listDiaChi) {
                diaChiNew.setId(diaChiUpdate.getId());
                diaChiNew.setPhuongXa(diaChiUpdate.getPhuongXa());
                diaChiNew.setQuanHuyen(diaChiUpdate.getQuanHuyen());
                diaChiNew.setThanhPho(diaChiUpdate.getThanhPho());
                diaChiNew.setDiaChiCuThe(diaChiUpdate.getDiaChiCuThe());
                diaChiNew.setTrangThai(1);
                diaChiNew.setNgayTao(diaChiUpdate.getNgayTao());
                diaChiNew.setNgaySua(diaChiUpdate.getNgaySua());
                diaChiNew.setTaiKhoan(diaChiUpdate.getTaiKhoan());

                diaChiService.update(diaChiNew);
            }
        }
        Date date = new Date();
        DiaChi diaChi = new DiaChi();
        diaChi.setId(idDiaChi);
        diaChi.setPhuongXa(phuongXa);
        diaChi.setQuanHuyen(quanHuyen);
        diaChi.setThanhPho(thanhPho);
        diaChi.setDiaChiCuThe(diaChiCuThe);
        diaChi.setTrangThai(trangThai);
        diaChi.setNgayTao(date);
        diaChi.setNgaySua(date);
        diaChi.setTaiKhoan(TaiKhoan.builder().id(idKhachHang).build());
        diaChiService.update(diaChi);
        return "redirect:/admin/khach-hang/view-update-khach-hang/" + idKhachHang;
    }

    @GetMapping("/dia-chi/delete/{idDiaChi}/{idKhachHang}")
    public String deleteDiaChiKhachHang(
            @PathVariable("idDiaChi") Long idDiaChi,
            @PathVariable("idKhachHang") Long idKhachHang,
            RedirectAttributes redirectAttributes) {
        diaChiService.deleteById(idDiaChi);
        redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
        redirectAttributes.addFlashAttribute("checkModal", "modal");
        return "redirect:/admin/khach-hang/view-update-khach-hang/" + idKhachHang;
    }

    @PostMapping("/update")
    public String update(
            @Valid
            @ModelAttribute("khachHang") TaiKhoan taiKhoan,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        TaiKhoan taiKhoanEntity = new TaiKhoan();
        taiKhoanEntity.setNgaySinh(taiKhoan.getNgaySinh());
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(taiKhoan.getId());
            model.addAttribute("listDiaChi", listDiaChi);
            model.addAttribute("diaChi", new DiaChi());
            return "/admin-template/khach_hang/sua-khach-hang";
        } else if (!taiKhoanEntity.isValidNgaySinh()) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkNgaySinh", "ngaySinh");
            List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(taiKhoan.getId());
            model.addAttribute("listDiaChi", listDiaChi);
            model.addAttribute("diaChi", new DiaChi());

            return "/admin-template/khach_hang/sua-khach-hang";
        } else if (!taiKhoanService.checkTenTkTrungSua(taiKhoan.getId(), taiKhoan.getTenTaiKhoan())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên tài khoản đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(taiKhoan.getId());
            model.addAttribute("listDiaChi", listDiaChi);
            model.addAttribute("diaChi", new DiaChi());
            return "/admin-template/khach_hang/sua-khach-hang";
        } else if (!taiKhoanService.checkEmailSua(taiKhoan.getId(), taiKhoan.getEmail())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            model.addAttribute("checkEmailTrung", "Email đã tồn tại");
            List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(taiKhoan.getId());
            model.addAttribute("listDiaChi", listDiaChi);
            model.addAttribute("diaChi", new DiaChi());
            return "/admin-template/khach_hang/sua-khach-hang";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            taiKhoan.setNgayTao(taiKhoan.getNgayTao());
            taiKhoan.setNgaySua(new Date());
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(2));
            taiKhoan.setVaiTro(vaiTro);
            taiKhoan.setTrangThai(taiKhoan.getTrangThai());
            taiKhoanService.update(taiKhoan);
            return "redirect:/admin/khach-hang";
        }
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("khachHang") TaiKhoan taiKhoan,
                      BindingResult result, Model model,
                      RedirectAttributes redirectAttributes,
                      HttpServletRequest request,
                      @RequestParam("email") String email
    ) {
        TaiKhoan userInfo = taiKhoan;
        TaiKhoan taiKhoanEntity = new TaiKhoan();
        taiKhoanEntity.setNgaySinh(taiKhoan.getNgaySinh());
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/khach_hang/khach-hang";
        } else if (!taiKhoanEntity.isValidNgaySinh()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkNgaySinh", "ngaySinh");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/khach_hang/khach-hang";
        } else if (!taiKhoanService.checkTenTaiKhoanTrung(taiKhoan.getTenTaiKhoan())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên tài khoản đã tồn tại");
            model.addAttribute("checkEmailTrung", "Email đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/khach_hang/khach-hang";
        } else if (!taiKhoanService.checkEmail(taiKhoan.getEmail())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkEmailTrung", "Email đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/khach_hang/khach-hang";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            String url = request.getRequestURL().toString();
            System.out.println(url);
            url = url.replace(request.getServletPath(), "");
//            taiKhoanService.sendEmail(userInfo, url, random3);
            System.out.println(userInfo);
            userInfo.setNgayTao(new Date());
            userInfo.setNgaySua(new Date());
//            userInfo.setMatKhau(passwordEncoder.encode(random3));
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(2));
            userInfo.setVaiTro(vaiTro);
            userInfo.setTrangThai(0);
            userInfo.setVaiTro(vaiTro);
            taiKhoanService.update(userInfo);

            GioHang gioHang = new GioHang();
            gioHang.setMaGioHang("GH" + gioHangService.genMaTuDong());
            gioHang.setGhiChu("");
            gioHang.setNgaySua(new Date());
            gioHang.setNgayTao(new Date());
            gioHang.setTaiKhoan(TaiKhoan.builder().id(userInfo.getId()).build());
            gioHang.setTrangThai(0);
            gioHangService.save(gioHang);

            return "redirect:/admin/khach-hang";
        }
    }

    public String ranDom1() {
        // Khai báo một mảng chứa 6 số nguyên ngẫu nhiên
        String ran = "";
        int[] randomNumbers = new int[6];

        // Tạo một đối tượng Random
        Random random = new Random();

        // Đổ số nguyên ngẫu nhiên vào mảng
        for (int i = 0; i < 6; i++) {
            randomNumbers[i] = random.nextInt(100); // Giới hạn số ngẫu nhiên từ 0 đến 99
        }

        // In ra các số nguyên ngẫu nhiên trong mảng
        System.out.println("Dãy 6 số nguyên ngẫu nhiên:");
        for (int number : randomNumbers) {
            ran = ran + number;
            System.out.println(number);
        }
        return ran;
    }
}
