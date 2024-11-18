package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.HoaDon;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface HoaDonService {
    List<HoaDon> findAll();

    HoaDon findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(HoaDon hoaDon);


    Integer countHoaDonTreo();

    List<HoaDon> find5ByTrangThai(Integer trangThai);

    HoaDon finByHoaDonMaHDSdt(String maDonHang, String sdt);


    List<HoaDon> findAllHoaDon();

    List<HoaDon> getAllHoaDonByTaiKhoanOrderByNgaySua(Long idTaiKhoan);

    List<HoaDon> getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(Long idTaiKhoan, Integer trangThai);


}
