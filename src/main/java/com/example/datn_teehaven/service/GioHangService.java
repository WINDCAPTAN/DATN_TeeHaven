package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.GioHang;

import java.util.List;

public interface GioHangService {

    List<GioHang> findAll();

    GioHang save(GioHang gioHang);

    Integer genMaTuDong();
}
