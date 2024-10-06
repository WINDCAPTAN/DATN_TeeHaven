package com.example.datn_teehaven.service;

import com.example.datn_teehaven.entyti.ThuongHieu;

import java.util.List;

public interface ThuongHieuService {

    List<ThuongHieu> getAll();

    ThuongHieu getById(Long id);

}
