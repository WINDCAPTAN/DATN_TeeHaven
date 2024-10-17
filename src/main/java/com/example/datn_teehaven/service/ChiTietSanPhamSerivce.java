package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.ChiTietSanPham;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ChiTietSanPhamSerivce {

    List<ChiTietSanPham> getAll();

    List<ChiTietSanPham> getAllCtspOneSanPham();

    List<ChiTietSanPham> add(
            List<String> listSanPham, List<String> listKichCo,
            List<String> listMauSac, List<String> listTayAo, List<String> listChatLieu,
            List<String> listSoLuong, List<String> listDonGia);

    List<ChiTietSanPham> updateAllCtsp(
            List<String> listIdChiTietSp, List<String> listSanPham,
            List<String> listKichCo, List<String> listMauSac,
            List<String> listTrangThai, List<String> listChatLieu, List<String> listTayAo,
            List<String> listSoLuong, List<String> listDonGia);

    ChiTietSanPham update(ChiTietSanPham chiTietSanPham);

    ChiTietSanPham getById(Long id);

    void checkSoLuongBang0();

    List<ChiTietSanPham> fillAllDangHoatDongLonHon0();




    List<ChiTietSanPham> getAllDangHoatDong();

    List<ChiTietSanPham> getAllNgungHoatDong();

    List<ChiTietSanPham> add(
            List<String> listSanPham, List<String> listKichCo,
            List<String> listMauSac, List<String> listTayAo,
            List<String> listSoLuong, List<String> listDonGia);

    List<ChiTietSanPham> updateAllCtsp(
            List<String> listIdChiTietSp, List<String> listSanPham,
            List<String> listKichCo, List<String> listMauSac,
            List<String> listTayAo, List<String> listTrangThai,
            List<String> listSoLuong, List<String> listDonGia);



    ChiTietSanPham saveExcel(ChiTietSanPham chiTietSanPham);

    void remove(Long id);



    List<ChiTietSanPham> getAllById(Long id);

    List<ChiTietSanPham> getAllbyIdSPAndIdMS(Long idSanPham, Long idMauSac);

    List<ChiTietSanPham> getAllCtspByIdSanPham(Long idSanPham);



    Page<List<ChiTietSanPham>> searchAll(Integer pageNo, Integer size, String tenSanPham, List<Long> idMauSac,
                                         List<Long> idKichCo,
                                         List<Long> idTayAo, List<Long> idThuongHieu, Long minGia, Long maxGia);

    List<Long> getAllIdMauSacCTSP();

    List<Long> getAllIdKichCoCTSP();

    List<Long> getAllIdTayAoCTSP();

    List<Long> getAllIdThuongHieuCTSP();

    Long getAllMinGiaCTSP();

    Long getAllMaxGiaCTSP();

    Integer checkPage(Integer page);

    List<Object[]> danhSachHangSapHet(Integer soLuong);

}
