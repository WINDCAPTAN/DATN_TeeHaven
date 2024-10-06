package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.HinhAnhSanPham;

import java.util.List;

public interface HinhAnhSanPhamSerivce {

    List<HinhAnhSanPham> listHinhAnh(Long id);

    void deleteByID(Long id);
}
