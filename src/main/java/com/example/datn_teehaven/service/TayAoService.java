package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.TayAo;

import java.util.List;

public interface TayAoService {

    List<TayAo> findAll();

    List<TayAo> getAllDangHoatDong();
}
