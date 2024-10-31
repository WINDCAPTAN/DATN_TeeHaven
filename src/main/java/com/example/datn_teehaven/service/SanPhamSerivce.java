package com.example.datn_teehaven.service;



import com.example.datn_teehaven.entyti.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamSerivce {


    List<SanPham> getAll();

    List<SanPham> getAllDangHoatDong();

    List<SanPham> getAllNgungHoatDong();

    SanPham add(SanPham sanPham);

    SanPham update(SanPham sanPham);

    void remove(Long id);

    SanPham getById(Long id);

    Integer genMaTuDong();

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String ma, String ten);

    Page<SanPham> search(String ten, Boolean trangThai, Pageable pageable);

}
