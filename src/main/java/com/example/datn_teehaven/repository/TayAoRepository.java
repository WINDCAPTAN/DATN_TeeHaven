package com.example.datn_teehaven.repository;


import com.example.datn_teehaven.entyti.TayAo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TayAoRepository extends JpaRepository<TayAo,Long> {



}

