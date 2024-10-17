package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.HoaDonChiTiet;

import java.util.List;


public interface HoaDonChiTietService {

    List<HoaDonChiTiet> findAll();

    HoaDonChiTiet findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(HoaDonChiTiet hoaDonChiTiet);

}
