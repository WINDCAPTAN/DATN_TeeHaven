package com.example.datn_teehaven.repository;

import com.example.datn_teehaven.entyti.ChiTietSanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Long> {
    @Transactional
    @Modifying
    @Query(value = " update chi_tiet_san_pham  set trang_thai = 1  where so_luong =0", nativeQuery = true)
    void checkSoLuongBang0();

    @Query(value = "select * from chi_tiet_san_pham where trang_thai = 0 and so_luong>0", nativeQuery = true)
    List<ChiTietSanPham> fillAllDangHoatDongLonHon0();
}
