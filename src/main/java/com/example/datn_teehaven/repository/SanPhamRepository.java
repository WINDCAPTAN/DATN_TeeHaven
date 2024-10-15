package com.example.datn_teehaven.repository;


import com.example.datn_teehaven.entyti.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {


    @Query(value = "SELECT MAX(CONVERT(INT, SUBSTRING(Ma,3,10))) from san_pham",nativeQuery = true)
    Integer index();

    @Query(value = "SELECT * FROM san_pham sp WHERE (sp.ten = ?1 OR ?1 IS NULL OR ?1 LIKE '')" +
            "AND (sp.trang_thai = ?2 OR ?2 IS NULL OR ?2 LIKE '')",nativeQuery = true)
    Page<SanPham> search(String ten, Boolean trangThai, Pageable pageable);



}
