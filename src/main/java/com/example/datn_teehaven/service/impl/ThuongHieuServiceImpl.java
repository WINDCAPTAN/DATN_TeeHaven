package com.example.datn_teehaven.service.impl;



import com.example.datn_teehaven.entyti.ThuongHieu;
import com.example.datn_teehaven.repository.ThuongHieuRepository;
import com.example.datn_teehaven.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepo;
    @Override
    public List<ThuongHieu> getAll() {
        return thuongHieuRepo.findAll();
    }

    @Override
    public ThuongHieu getById(Long id) {
        return thuongHieuRepo.findById(id).get();
    }
}
