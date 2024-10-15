package com.example.datn_teehaven.service.impl;


import com.example.datn_teehaven.entyti.HinhAnhSanPham;
import com.example.datn_teehaven.entyti.SanPham;
import com.example.datn_teehaven.repository.HinhAnhSanPhamRepository;
import com.example.datn_teehaven.service.HinhAnhSanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


@Service
public class HinhAnhSanPhamSerivceImpl implements HinhAnhSanPhamSerivce {

    @Autowired
    private HinhAnhSanPhamRepository repository;

    private Date currentDate = new Date();
    @Override
    public void saveImage(List<MultipartFile> files, SanPham sanPham) {

        for (MultipartFile multipartFile : files) {
            if (!multipartFile.isEmpty()) {
                try {
                    HinhAnhSanPham hinhAnh = new HinhAnhSanPham();
                    // Lưu tệp vào cơ sở dữ liệu
//                    hinhAnh.setUrl(multipartFile.getOriginalFilename());
//                    hinhAnh.setNgayTao(currentDate);
//                    hinhAnh.setNgaySua(currentDate);
//                    hinhAnh.setTrangThai(0);
//                    hinhAnh.setUuTien(0);
//                    hinhAnh.setSanPham(sanPham);
                    // Thực hiện các tác vụ khác nếu cần thiết
                    hinhAnh.setUrl(multipartFile.getOriginalFilename());
                    hinhAnh.setNgayTao(currentDate);
                    hinhAnh.setNgaySua(currentDate);
                    hinhAnh.setTrangThai(0);
                    hinhAnh.setUuTien(0);
                    hinhAnh.setSanPham(sanPham);
                    repository.save(hinhAnh);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Xử lý lỗi
                }
            }
        }

    }

}
