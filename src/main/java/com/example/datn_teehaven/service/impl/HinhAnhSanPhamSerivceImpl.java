package com.example.datn_teehaven.service.impl;


import com.example.datn_teehaven.entyti.HinhAnhSanPham;
import com.example.datn_teehaven.repository.HinhAnhSanPhamRepository;
import com.example.datn_teehaven.service.HinhAnhSanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HinhAnhSanPhamSerivceImpl implements HinhAnhSanPhamSerivce {

    @Autowired
    private HinhAnhSanPhamRepository hinhAnhSanPhamRepository;

    @Override
    public List<HinhAnhSanPham> listHinhAnh(Long id) {

        return hinhAnhSanPhamRepository.fillAllByIdSp(id);

    }

    @Override
    public void deleteByID(Long id) {

        hinhAnhSanPhamRepository.deleteAllByIdSp(id);

    }
}
