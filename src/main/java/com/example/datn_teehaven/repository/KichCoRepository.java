package com.example.datn_teehaven.repository;

import com.example.datn_teehaven.entyti.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KichCoRepository extends JpaRepository<KichCo, Long> {

    @Query(value = "select * from kich_co where trang_thai = 0", nativeQuery = true)
    List<KichCo> fillAllDangHoatDong();

}
