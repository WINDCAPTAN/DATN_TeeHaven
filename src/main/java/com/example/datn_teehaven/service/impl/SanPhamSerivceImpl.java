package com.example.datn_teehaven.service.impl;


import com.example.datn_teehaven.entyti.SanPham;
import com.example.datn_teehaven.entyti.ThuongHieu;
import com.example.datn_teehaven.repository.SanPhamRepository;
import com.example.datn_teehaven.service.SanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamSerivceImpl implements SanPhamSerivce {

    @Autowired
    private SanPhamRepository repository;

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @Override
    public List<SanPham> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "ngaySua");

        return repository.findAll(sort);

    }






    @Override
    public SanPham add(SanPham sanPham) {
        return repository.save(sanPham);
    }

    @Override
    public List<SanPham> getAllDangHoatDong() {
        return sanPhamRepo.fillAllDangHoatDong();
    }

    @Override
    public List<SanPham> getAllNgungHoatDong() {
        return sanPhamRepo.fillAllNgungHoatDong();
    }



    @Override
    public Integer genMaTuDong() {
        Long count = sanPhamRepo.count();
        return count.intValue() + 1;
    }

    @Override
    public SanPham update(SanPham sanPham) {
        return sanPhamRepo.save(sanPham);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public SanPham getById(Long id) {
        return sanPhamRepo.findById(id).get();
    }


    @Override
    public boolean checkTenTrung(String ten) {
        for (SanPham sp : sanPhamRepo.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(Long id, String ten) {
        for (SanPham sp : sanPhamRepo.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                if (!sp.getId().equals(id)) {
                    return false;
                }
            }
        }
        return true;
    }

}
