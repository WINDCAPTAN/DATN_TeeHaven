package com.example.datn_teehaven.service.impl;



import com.example.datn_teehaven.entyti.TaiKhoan;
import com.example.datn_teehaven.repository.TaiKhoanRepository;
import com.example.datn_teehaven.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    TaiKhoanRepository repository;

    @Override
    public List<TaiKhoan> getAll() {

        return repository.fillAllKhachHang();
    }

    @Override
    public void addKhachLe() {
        repository.addKhachLe();
    }

    @Override
    public TaiKhoan findKhachLe() {
        return repository.findKhachLe();
    }
}
