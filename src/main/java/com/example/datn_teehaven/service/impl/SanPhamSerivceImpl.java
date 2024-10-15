package com.example.datn_teehaven.service.impl;


import com.example.datn_teehaven.entyti.SanPham;
import com.example.datn_teehaven.repository.SanPhamRepository;
import com.example.datn_teehaven.service.SanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamSerivceImpl implements SanPhamSerivce {

    @Autowired
    private SanPhamRepository repository;

    @Override
    public List<SanPham> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "ngaySua");
        return repository.findAll(sort);

    }

    @Override
    public SanPham getById(Long id) {

        return repository.findById(id).get();

    }
    @Override
    public boolean checkTenTrung(String ten) {

        for (SanPham sp : repository.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;

    }

    @Override
    public Integer genMaTuDong() {

        String maStr = "";
        try {
            if (repository.index() != null) {
                maStr = repository.index().toString();
            } else {
                maStr = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maStr == null) {
            maStr = "0";
            int ma = Integer.parseInt(maStr);
            return ++ma;
        }
        int ma = Integer.parseInt(maStr);
        return ++ma;

    }

    @Override
    public SanPham add(SanPham sanPham) {
        return repository.save(sanPham);

    }
}
