package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.TaiKhoan;

import java.util.List;

public interface TaiKhoanService {

    List<TaiKhoan> getAll();
    void addKhachLe();

    TaiKhoan findKhachLe();
}
