package com.example.datn_teehaven.service;


import com.example.datn_teehaven.entyti.HoaDon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HoaDonService {
    List<HoaDon> findAll();

}
