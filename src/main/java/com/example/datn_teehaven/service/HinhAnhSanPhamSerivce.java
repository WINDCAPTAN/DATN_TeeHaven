package com.example.datn_teehaven.service;



import com.example.datn_teehaven.entyti.SanPham;
import org.springframework.web.multipart.MultipartFile;

import com.example.datn_teehaven.entyti.HinhAnhSanPham;


import java.util.List;

public interface HinhAnhSanPhamSerivce {


    void saveImage(List<MultipartFile> files, SanPham sanPham);


    List<HinhAnhSanPham> listHinhAnh(Long id);

    void deleteByID(Long id);

}
