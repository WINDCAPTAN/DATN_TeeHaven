package com.example.datn_teehaven.repository;


import com.example.datn_teehaven.entyti.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {



}
