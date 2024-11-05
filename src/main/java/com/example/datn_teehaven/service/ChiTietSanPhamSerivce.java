package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ChiTietSanPhamSerivce {

    List<ChiTietSanPham> getAll();

    List<ChiTietSanPham> getAllCtspOneSanPham();

    // List<ChiTietSanPham> getAllCtspOneSanPhamMinGia();

//    List<ChiTietSanPham> updateAllCtsp(List<String> listIdChiTietSp, List<String> listSanPham, List<String> listKichCo, List<String> listMauSac, List<String> listTrangThai, List<String> listChatLieu, List<String> listTayAo, List<String> listSoLuong, List<String> listDonGia);

    List<ChiTietSanPham> getAllDangHoatDong();

    List<ChiTietSanPham> getAllNgungHoatDong();


    List<ChiTietSanPham> add(
            List<String> listSanPham, List<String> listKichCo,
            List<String> listMauSac, List<String> listTayAo,
            List<String> listSoLuong, List<String> listDonGia,List<String> listHinhAnh);

    List<ChiTietSanPham> updateAllCtsp(
            List<String> listIdChiTietSp, List<String> listSanPham,
            List<String> listKichCo, List<String> listMauSac,
            List<String> listTayAo, List<String> listTrangThai,
            List<String> listSoLuong, List<String> listDonGia,
            List<String> listHinhAnh);

    ChiTietSanPham update(ChiTietSanPham chiTietSanPham);

    void checkSoLuongBang0();


    ChiTietSanPham saveExcel(ChiTietSanPham chiTietSanPham);

    void remove(Long id);

    ChiTietSanPham getById(Long id);


    List<ChiTietSanPham> getAllById(Long id);

    List<ChiTietSanPham> getAllbyIdSPAndIdMS(Long idSanPham, Long idMauSac);

    List<ChiTietSanPham> getAllCtspByIdSanPham(Long idSanPham);

    List<ChiTietSanPham> fillAllDangHoatDongLonHon0();

    Page<List<ChiTietSanPham>> searchAll(Integer pageNo, Integer size, String tenSanPham, List<Long> idMauSac,
                                         List<Long> idKichCo,
                                         List<Long> idTayAo, List<Long> idThuongHieu, Long minGia, Long maxGia);



}
