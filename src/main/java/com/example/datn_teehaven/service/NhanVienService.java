package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.TaiKhoan;

import java.util.List;

public interface NhanVienService {

    List<TaiKhoan> getAll();

    List<TaiKhoan> getAllDangHoatDong();

    List<TaiKhoan> getAllNgungHoatDong();

    TaiKhoan add(TaiKhoan sanPham);

    TaiKhoan update(TaiKhoan sanPham);

    void remove(Long id);

    TaiKhoan getById(Long id);

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String id,String ten);

    boolean checkTenTkTrungSua(Long id,String ten);

    boolean checkTenTaiKhoanTrung(String ten);

    boolean checkEmailSua(Long id,String email);

    boolean checkEmail(String email);



}
