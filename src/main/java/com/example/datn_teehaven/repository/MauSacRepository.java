package com.example.datn_teehaven.repository;



import com.example.datn_teehaven.entyti.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MauSacRepository extends JpaRepository<MauSac,Long> {

}
