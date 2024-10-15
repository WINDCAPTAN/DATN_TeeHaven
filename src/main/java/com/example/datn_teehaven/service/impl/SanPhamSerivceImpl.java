package com.example.datn_teehaven.service.impl;


import com.example.datn_teehaven.entyti.SanPham;
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
        return null;

    }

    @Override
    public List<SanPham> getAllNgungHoatDong() {
        return null;
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
        return false;
    }

    @Override
    public boolean checkTenTrungSua(String ma, String ten) {
        return false;
    }

    @Override
    public Page<SanPham> search(String ten, Boolean trangThai, Pageable pageable) {
        return sanPhamRepo.search(ten,trangThai,pageable);
    }
}
