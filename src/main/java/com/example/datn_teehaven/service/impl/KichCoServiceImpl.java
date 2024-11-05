package com.example.datn_teehaven.service.impl;

import com.example.datn_teehaven.entyti.KichCo;
import com.example.datn_teehaven.repository.KichCoRepository;
import com.example.datn_teehaven.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KichCoServiceImpl implements KichCoService {
    @Autowired
    KichCoRepository kichCoRepository;

    @Override
    public List<KichCo> findAll() {
        return null;
    }

    @Override
    public List<KichCo> getAllDangHoatDong() {

        return kichCoRepository.fillAllDangHoatDong();

    }
}
