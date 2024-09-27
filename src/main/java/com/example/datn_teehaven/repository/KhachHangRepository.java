package com.example.datn_teehaven.repository;


import com.example.datn_teehaven.entyti.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface KhachHangRepository extends JpaRepository<TaiKhoan, Long> {


}
