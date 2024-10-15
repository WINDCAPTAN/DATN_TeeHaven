package com.example.datn_teehaven.service;



import com.example.datn_teehaven.entyti.SanPham;

import java.util.List;

public interface SanPhamSerivce {

    List<SanPham> getAll();
    SanPham getById(Long id);

    boolean checkTenTrung(String ten);

    Integer genMaTuDong();

    SanPham add(SanPham sanPham);


}
