package com.example.datn_teehaven.service.impl;


import com.example.datn_teehaven.entyti.TayAo;
import com.example.datn_teehaven.repository.TayAoRepository;
import com.example.datn_teehaven.service.TayAoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TayAoServiceImpl implements TayAoService {
    @Autowired
    TayAoRepository tayAoRepository;

    @Override
    public List<TayAo> findAll() {
        return null;
    }

    @Override
    public List<TayAo> getAllDangHoatDong() {

        return tayAoRepository.fillAllDangHoatDong();

    }
}
