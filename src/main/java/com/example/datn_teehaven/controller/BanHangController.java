package com.example.datn_teehaven.controller;

import com.example.datn_teehaven.entyti.ChiTietSanPham;
import com.example.datn_teehaven.entyti.HoaDon;
import com.example.datn_teehaven.entyti.HoaDonChiTiet;
import com.example.datn_teehaven.entyti.TaiKhoan;
import com.example.datn_teehaven.service.ChiTietSanPhamSerivce;
import com.example.datn_teehaven.service.HoaDonChiTietService;
import com.example.datn_teehaven.service.HoaDonService;
import com.example.datn_teehaven.service.KhachHangService;
import com.example.datn_teehaven.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/ban-hang-tai-quay")
public class BanHangController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private ChiTietSanPhamSerivce chiTietSanPhamSerivce;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private KhachHangService khachHangService;

    void addKhachLe() {
        if (khachHangService.findKhachLe() == null) {
            khachHangService.addKhachLe();
        }
    }
    @GetMapping("/hoa-don")
    public String home(Model model) {
        model.addAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        return "admin-template/ban-hang";
    }

    @PostMapping("/hoa-don/add")
    public String taoHoaDon(RedirectAttributes redirectAttributes) {
        addKhachLe();
        if (hoaDonService.countHoaDonTreo() < 5) {
            HoaDon hd = new HoaDon();
            hd.setTrangThai(-1); // view 5 hoa don
            hd.setNgayTao(Date.valueOf(LocalDate.now()));
            hd.setTaiKhoan(khachHangService.findKhachLe());
            hd.setPhiShip((long) 0);
            hd.setLoaiHoaDon(2);
            hd.setTongTien((long) 0);
            hd.setTongTienKhiGiam((long) 0);
            hd.setTienGiam((long) 0);
            hoaDonService.saveOrUpdate(hd);
            hd.setMaHoaDon("HD" + hd.getId());
            hoaDonService.saveOrUpdate(hd);

//
//            addLichSuHoaDon(hd.getId(), "", 0);
//            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
        }
        return "redirect:/ban-hang-tai-quay/hoa-don";
    }
    @GetMapping("/hoa-don/{id}")
    public String hoaDon(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        chiTietSanPhamSerivce.checkSoLuongBang0();

        TaiKhoan tk = new TaiKhoan();
        model.addAttribute("khachHang", tk);
        model.addAttribute("listHoaDon", hoaDonService.find5ByTrangThai(-1));
        model.addAttribute("listHdct", hoaDonChiTietService.findAll());
        model.addAttribute("listCtsp", chiTietSanPhamSerivce.fillAllDangHoatDongLonHon0());
        model.addAttribute("listTaiKhoan", taiKhoanService.getAll());

        HoaDon hd = hoaDonService.findById(id);
        model.addAttribute("hoaDon", hd);

        return "admin-template/hoa-don-chi-tiet";
    }

    @PostMapping("/hoa-don-chi-tiet/add")
    public String addHdct(@RequestParam Long idHoaDon, @RequestParam Long idCtsp,
                          RedirectAttributes redirectAttributes) {

        Boolean cr = true;
        HoaDonChiTiet hdct = new HoaDonChiTiet();

        HoaDon hoaDon = hoaDonService.findById(idHoaDon);
        hoaDonService.saveOrUpdate(hoaDon);
        ChiTietSanPham ctsp = chiTietSanPhamSerivce.getById(idCtsp);
        for (HoaDonChiTiet obj : hoaDonChiTietService.findAll()) {
            if (obj.getHoaDon() == hoaDon) {
                if (obj.getChiTietSanPham() == ctsp) {
                    hdct = obj;
                    cr = false;
                    break;
                }
            }
        }

        if (cr) {
            hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hoaDon);
            hdct.setChiTietSanPham(ctsp);
            hdct.setSoLuong(1);
            hdct.setDonGia(ctsp.getGiaHienHanh());
        } else {
            if (ctsp.getSoLuong() > hdct.getSoLuong())
                hdct.setSoLuong(hdct.getSoLuong() + 1);
        }
        hdct.setTrangThai(0);
        hoaDonChiTietService.saveOrUpdate(hdct);
        System.out.println(idCtsp + "idctsp");
        System.out.println(idHoaDon + "idctsp");
//        thongBao(redirectAttributes, "Thành công", 1);
        redirectAttributes.addFlashAttribute("batModal", "ok");
        if (hoaDon.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idHoaDon;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idHoaDon;
        }
    }
}
