package com.example.datn_teehaven.controller;

import com.example.datn_teehaven.entyti.ChiTietSanPham;
import com.example.datn_teehaven.entyti.HoaDon;
import com.example.datn_teehaven.entyti.HoaDonChiTiet;
import com.example.datn_teehaven.entyti.LichSuHoaDon;
import com.example.datn_teehaven.entyti.TaiKhoan;
import com.example.datn_teehaven.entyti.Voucher;
import com.example.datn_teehaven.service.ChiTietSanPhamSerivce;
import com.example.datn_teehaven.service.HoaDonChiTietService;
import com.example.datn_teehaven.service.HoaDonService;
import com.example.datn_teehaven.service.KhachHangService;
import com.example.datn_teehaven.service.LichSuHoaDonService;
import com.example.datn_teehaven.service.TaiKhoanService;
import com.example.datn_teehaven.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ban-hang-tai-quay")
public class BanHangController {
// 123456
    // 123
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

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private LichSuHoaDonService lichSuHoaDonService;

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
    void thongBao(RedirectAttributes redirectAttributes, String thongBao, int trangThai) {
        if (trangThai == 0) {
            redirectAttributes.addFlashAttribute("checkThongBao", "thatBai");
            redirectAttributes.addFlashAttribute("thongBao", thongBao);
        } else if (trangThai == 1) {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            redirectAttributes.addFlashAttribute("thongBao", thongBao);
        } else {

            redirectAttributes.addFlashAttribute("checkThongBao", "canhBao");
            redirectAttributes.addFlashAttribute("thongBao", thongBao);
        }

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
        model.addAttribute("lstTaiKhoanDc",
                khachHangService.getById(hoaDonService.findById(id).getTaiKhoan().getId()));
        model.addAttribute("listVoucher", voucherService.fillAllDangDienRa());

        HoaDon hd = hoaDonService.findById(id);

        Boolean ctb = false;

        if (hd.getVoucher() != null && hd.getTrangThai() != 6) {
            if (hd.tongTienHoaDonDaNhan() < hd.getVoucher().getGiaTriDonToiThieu().longValue()) {

                hd.setVoucher(null);
                hd.setTongTien(hd.tongTienHoaDonDaNhan());
                hd.setTongTienKhiGiam(hd.tongTienHoaDonDaNhan());
                hoaDonService.saveOrUpdate(hd);
                ctb = true;
                 thongBao(redirectAttributes, "Đã xóa mã giảm giá vì chưa đạt giá trị đơn tối thiếu", 0);
            }
        }
        if (ctb) {
            model.addAttribute("thongBao", "Đã xóa mã giảm giá vì chưa đạt giá trị đơn tối thiếu");
            model.addAttribute("checkThongBao", "thatBai");
        }
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
        // Kiểm tra nếu sản phẩm còn đủ số lượng để thêm
        if (ctsp.getSoLuong() <= 0) {
            // Sản phẩm hết hàng, không cho thêm
            thongBao(redirectAttributes, "Sản phẩm không còn hàng.", 0);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idHoaDon;
        }
        // Kiểm tra xem sản phẩm đã có trong hóa đơn chi tiết hay chưa
        for (HoaDonChiTiet obj : hoaDonChiTietService.findAll()) {
            if (obj.getHoaDon().equals(hoaDon) && obj.getChiTietSanPham().equals(ctsp)) {
                hdct = obj;
                cr = false;
                break;
            }
        }
        if (cr) {
            // Nếu sản phẩm chưa có trong hóa đơn chi tiết, tạo mới và thêm vào
            hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hoaDon);
            hdct.setChiTietSanPham(ctsp);
            hdct.setSoLuong(1);
            hdct.setDonGia(ctsp.getGiaHienHanh());
            // Cập nhật lại số lượng sản phẩm
            ctsp.setSoLuong(ctsp.getSoLuong() - 1);
            chiTietSanPhamSerivce.update(ctsp); // Cập nhật số lượng sản phẩm
        } else {
            // Nếu sản phẩm đã có trong hóa đơn chi tiết, chỉ tăng số lượng lên 1 đơn vị
            if (ctsp.getSoLuong() > 0) {
                hdct.setSoLuong(hdct.getSoLuong() + 1);
                // Trừ số lượng sản phẩm khi đã tồn tại trong hóa đơn chi tiết
                ctsp.setSoLuong(ctsp.getSoLuong() - 1);
                chiTietSanPhamSerivce.update(ctsp); // Cập nhật số lượng sản phẩm
            }
        }

        hdct.setTrangThai(0);
        hoaDonChiTietService.saveOrUpdate(hdct);
        thongBao(redirectAttributes, "Thành công", 1);
        redirectAttributes.addFlashAttribute("batModal", "ok");

        // Điều hướng tùy vào trạng thái hóa đơn
        if (hoaDon.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idHoaDon;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idHoaDon;
        }
    }

    @GetMapping("/hoa-don-chi-tiet/delete/{id}")
    public String deleteHdct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Lấy chi tiết hóa đơn từ service
        HoaDonChiTiet hdct = hoaDonChiTietService.findById(id);
        if (hdct == null) {
            thongBao(redirectAttributes, "Không tìm thấy chi tiết hóa đơn.", 0);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hdct.getHoaDon().getId();
        }
        // Lấy hóa đơn tương ứng
        HoaDon hd = hdct.getHoaDon();
        // Lấy sản phẩm chi tiết và tăng số lượng
        ChiTietSanPham ctsp = hdct.getChiTietSanPham();
        ctsp.setSoLuong(ctsp.getSoLuong() + hdct.getSoLuong());
        chiTietSanPhamSerivce.update(ctsp); // Cập nhật số lượng sản phẩm

        hoaDonChiTietService.deleteById(id);

        // Thông báo thành công
        thongBao(redirectAttributes, "Xóa chi tiết hóa đơn thành công.", 1);

        if (hd.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        }
    }

    @PostMapping("/hoa-don/add-khach-hang")
    public String addKhachHang(@RequestParam Long idTaiKhoan, @RequestParam Long idhdc, RedirectAttributes redirectAttributes) {
        HoaDon hd = hoaDonService.findById(idhdc);
        if (idTaiKhoan == -1) {
            hd.setTaiKhoan(khachHangService.findKhachLe());
            hd.setDiaChiNguoiNhan(null);
            hd.setThanhPho(null);
            hd.setQuanHuyen(null);
            hd.setPhuongXa(null);
            hd.setNguoiNhan(null);
            hd.setSdtNguoiNhan(null);
        } else {
            TaiKhoan kh = khachHangService.getById(idTaiKhoan);
            hd.setTaiKhoan(kh);
            hd.setNguoiNhan(kh.getHoVaTen());
            hd.setSdtNguoiNhan(kh.getSoDienThoai());

        }

        hoaDonService.saveOrUpdate(hd);
        thongBao(redirectAttributes, "Thành công", 1);
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    @GetMapping("/hoa-don/detail/{id}")
    public String detailHoaDon(@PathVariable Long id, Model model) {

//        lstHoaDonCtDoiTra = new ArrayList<HoaDonChiTiet>();
        model.addAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        model.addAttribute("lstHdct", hoaDonChiTietService.findAll());
        model.addAttribute("lstCtsp", chiTietSanPhamSerivce.fillAllDangHoatDongLonHon0());
        model.addAttribute("lstTaiKhoan", khachHangService.getAll());
        model.addAttribute("lstTaiKhoanDc",
                khachHangService.getById(hoaDonService.findById(id).getTaiKhoan().getId()));
        model.addAttribute("listVoucher", voucherService.fillAllDangDienRa());
        // idhdc = id;

        model.addAttribute("lstLshd", lichSuHoaDonService.findByIdhd(id));
        model.addAttribute("listLichSuHoaDon", lichSuHoaDonService.findByIdhdNgaySuaAsc(id));
        HoaDon hd = hoaDonService.findById(id);
        if (hd.getTrangThai() == 6 && hd.getNgayMongMuon() == null) {
            hd.setNgayMongMuon(new java.util.Date());
//            sendMail(hd);
            hoaDonService.saveOrUpdate(hd);
        }
        if (hd.getVoucher() != null && hd.getTrangThai() != 6) {
            if (hd.tongTienHoaDonDaNhan() < hd.getVoucher().getGiaTriDonToiThieu().longValue()) {
                hd.setVoucher(null);
                hd.setTienGiam((long) 0);
                hd.setTongTien(hd.tongTienHoaDonDaNhan());
                hd.setTongTienKhiGiam(hd.tongTienHoaDonDaNhan());
                hoaDonService.saveOrUpdate(hd);
            }
        }
        List<LichSuHoaDon> lstLshd = lichSuHoaDonService.findByIdhd(id);
        Integer tt = lstLshd.get(0).getTrangThai();
        model.addAttribute("checkRollback", tt);
        // checkVoucher();
        model.addAttribute("hoaDon", hd);
        model.addAttribute("byHoaDon", hd);
        if (hd.getTrangThai() == 4) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + id;
        }
        return "/admin-template/detail-hoa-don";
    }

    //12345678910
    @PostMapping("/hoa-don-chi-tiet/update")
    public String updateSoLuong(RedirectAttributes redirectAttributes,
                                @RequestParam(defaultValue = "") Integer soLuongEdit,
                                @RequestParam(defaultValue = "") Integer soLuongEditTra, @RequestParam Long idHdct) {
        HoaDonChiTiet hdct = hoaDonChiTietService.findById(idHdct);
        HoaDon hd = hdct.getHoaDon();
        HoaDonChiTiet hdctnew = new HoaDonChiTiet();

        hdctnew.setSoLuong(0);
        System.out.println(hd.getTrangThai() + "tthd");
        if (hd.getTrangThai() == 3) {

            for (HoaDonChiTiet hdctf : hd.getLstHoaDonChiTiet()) {
                if (hdctf.getChiTietSanPham() == hdct.getChiTietSanPham() && hdctf.getTrangThai() == 2) {
                    hdctnew = hdctf;
                    break;
                }
            }

            hdct.setSoLuong(hdct.getSoLuong() - soLuongEditTra);
            hdctnew.setHoaDon(hdct.getHoaDon());
            hdctnew.setChiTietSanPham(hdct.getChiTietSanPham());
            hdctnew.setSoLuong(hdctnew.getSoLuong() + soLuongEditTra);
            hdctnew.setTrangThai(2);
            hdctnew.setDonGia(hdct.getDonGia());
            hoaDonChiTietService.saveOrUpdate(hdctnew);
            hoaDonChiTietService.saveOrUpdate(hdct);

            if (hd.getTrangThai() == -1) {
                thongBao(redirectAttributes, "Thành công", 1);
                return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
            } else {
                thongBao(redirectAttributes, "Thành công", 1);
                return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
            }
        }
        if (soLuongEdit == 0) {
            hoaDonChiTietService.deleteById(idHdct);
        } else {
            hdct.setSoLuong(soLuongEdit);
            hdct.setTrangThai(0);
            hoaDonChiTietService.saveOrUpdate(hdct);
        }

        if (hd.getTrangThai() == -1) {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
        } else if (hd.getTrangThai() == 3) {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/doi-tra/" + hd.getId();

        } else {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        }
    }
    void updateSoLuongRollBack(Long idhdc) {
        HoaDon hd = hoaDonService.findById(idhdc);
        List<ChiTietSanPham> lstCtsp = chiTietSanPhamSerivce.getAll();
        for (HoaDonChiTiet hoaDonChiTiet : hd.getLstHoaDonChiTiet()) {
            for (ChiTietSanPham ctsp : lstCtsp) {
                if (hoaDonChiTiet.getChiTietSanPham().getId() == ctsp.getId()) {
                    ctsp.setSoLuong(ctsp.getSoLuong() + hoaDonChiTiet.getSoLuong());
                    chiTietSanPhamSerivce.update(ctsp);
                }
            }
        }

        if (hd.getVoucher() != null) {
            Voucher v = hd.getVoucher();
            v.setSoLuong(v.getSoLuong().add(new BigDecimal(1)));
            voucherService.save(v);
        }

    }
    @PostMapping("/hoa-don/rollback/{id}")
    public String rollback(@RequestParam String ghiChu, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        HoaDon hd = hoaDonService.findById(id);

        if (lichSuHoaDonService.findByIdhdNgaySuaAsc(id).size() == 3) {
            updateSL(hd);
        }
        for (LichSuHoaDon lichSuHoaDon : lichSuHoaDonService.findByIdhd(id)) {
            if (lichSuHoaDon.getTrangThai() == hd.getTrangThai()) {
                lichSuHoaDon.setTrangThai(lichSuHoaDon.getTrangThai() + 20);
                lichSuHoaDonService.saveOrUpdate(lichSuHoaDon);
                addLichSuHoaDon(id, ghiChu, 8);
            }
        }

        if (hd.getTrangThai() == 1 && hd.getLoaiHoaDon() == 2) {
            hd.setTrangThai(4);
            updateSoLuongRollBack(hd.getId());

        } else {
            if (hd.getTrangThai() == 5) {
                if (lichSuHoaDonService.findByIdhdNgaySuaAsc(id).size() == 1) {
                    hd.setTrangThai(4);
                } else {
                    Integer tt = lichSuHoaDonService.findByIdhdNgaySuaAsc(hd.getId()).size() - 1;
                    hd.setTrangThai(tt);
                }
            } else {
                // System.out.println("sizee------" +
                // lichSuHoaDonService.findByIdhdNgaySuaAsc(idhdc).size());
                if (lichSuHoaDonService.findByIdhdNgaySuaAsc(hd.getId()).size() == 1 && hd.getLoaiHoaDon() == 2) {
                    updateSoLuongRollBack(hd.getId());

                    hd.setTrangThai(4);

                } else if (lichSuHoaDonService.findByIdhdNgaySuaAsc(hd.getId()).size() == 1 && hd.getLoaiHoaDon() == 1) {
                    updateSoLuongRollBack(hd.getId());

                    hd.setTrangThai(0);

//                } else if (hd.getTrangThai() == 6) {
//                    rollbackHoanTra(hd.getId());
//                    hd.setTrangThai(3);
                } else {
                    hd.setTrangThai(hd.getTrangThai() - 1);
                }

            }
        }
        // if (hd.getTrangThai() == 1) {
        // updateSoLuongRollBack();

        // }
        hoaDonService.saveOrUpdate(hd);
        if (hd.getTrangThai() == -1) {
//            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
        } else {
//            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        }

    }
    private void updateSL(HoaDon hd) {
        List<HoaDonChiTiet> lstHdct = hoaDonService.findById(hd.getId()).getLstHoaDonChiTiet();
        for (HoaDonChiTiet hdct : lstHdct) {
            Long idid = hdct.getChiTietSanPham().getId();
            ChiTietSanPham ctsp = chiTietSanPhamSerivce.getById(idid);
            ctsp.setSoLuong(ctsp.getSoLuong() - hdct.getSoLuong());
            chiTietSanPhamSerivce.update(ctsp);
            if (ctsp.getSoLuong() == 0) {
                ctsp.setTrangThai(1);
                chiTietSanPhamSerivce.update(ctsp);
            }
        }
        if (hd.getVoucher() != null) {
            Voucher v = hd.getVoucher();
            v.setSoLuong(v.getSoLuong().subtract(new BigDecimal(1)));
            voucherService.save(v);
        }
    }
    public void addLichSuHoaDon(Long idHoaDon, String ghiChu, Integer trangThai) {
        HoaDon hd = hoaDonService.findById(idHoaDon);
        LichSuHoaDon lshd = new LichSuHoaDon();
        lshd.setHoaDon(hd);
        lshd.setGhiChu(ghiChu);
        lshd.setTrangThai(trangThai);
        // lshd.setNgayTao(new Date());
//        TaiKhoan tk = nhanVienService.getById(idTk);
//        System.out.println(tk + "====================");
//        lshd.setNguoiSua(tk.getHoVaTen());
        lichSuHoaDonService.saveOrUpdate(lshd);
    }
}
