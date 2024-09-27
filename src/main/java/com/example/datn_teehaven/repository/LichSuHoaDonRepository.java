package com.example.datn_teehaven.repository;



import com.example.datn_teehaven.entyti.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LichSuHoaDonRepository extends JpaRepository<LichSuHoaDon, Long> {


}
