package com.example.datn_teehaven.service;



import com.example.datn_teehaven.entyti.ChiTietSanPham;


import java.util.List;

public interface ChiTietSanPhamSerivce {

    List<ChiTietSanPham> getAll();


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




}
