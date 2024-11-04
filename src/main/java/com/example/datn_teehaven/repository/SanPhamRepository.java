package com.example.datn_teehaven.repository;


import com.example.datn_teehaven.entyti.SanPham;
import com.example.datn_teehaven.entyti.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {


    @Query(value = "select * from san_pham where trang_thai = 0",nativeQuery = true)
    List<SanPham> fillAllDangHoatDong();

    @Query(value = "select * from san_pham where trang_thai = 1",nativeQuery = true)
    List<SanPham> fillAllNgungHoatDong();
}
