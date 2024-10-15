package com.example.datn_teehaven.service.impl;



import com.example.datn_teehaven.entyti.HoaDon;
import com.example.datn_teehaven.repository.HoaDonRepository;
import com.example.datn_teehaven.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon findById(Long id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public List<HoaDon> findByTrangThai(Integer trangThai) {
        return hoaDonRepository.findByTrangThai(trangThai);
    }

    @Override
    public Integer countHoaDonTreo() {
        return hoaDonRepository.countHoaDonTreo();
    }

    @Override
    public List<HoaDon> find5ByTrangThai(Integer trangThai) {
        return hoaDonRepository.find5ByTrangThai(trangThai);
    }

    @Override
    public HoaDon findByMa(String ma) {
        return null;
    }

    @Override
    public HoaDon finByHoaDonMaHDSdt(String maDonHang, String sdt) {
        return null;
    }

    @Override
    public List<HoaDon> findAllOrderByNgaySua() {
        return null;
    }

    @Override
    public List<HoaDon> getAllHoaDonByTaiKhoanOrderByNgaySua(Long idTaiKhoan) {
        return null;
    }

    @Override
    public List<HoaDon> getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(Long idTaiKhoan, Integer trangThai) {
        return null;
    }

    @Override
    public Integer countHoaDonDay(Date ngayTao) {
        return null;
    }

    @Override
    public Long sumHoaDonDay(Date ngayTao) {
        return null;
    }

    @Override
    public Integer countHoaDonMonth(Date ngayTao) {
        return null;
    }

    @Override
    public Long sumHoaDonMonth(Date ngayTao) {
        return null;
    }

    @Override
    public Integer countHoaDon(Integer trangThai) {
        return null;
    }

    @Override
    public Integer countHoaDonBetween(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Long sumGiaTriHoaDonBetween(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Integer countHoaDonTrangThaiBetween(Date startDate, Date endDate, Integer trangThai) {
        return null;
    }

    @Override
    public Integer countHoaDonTrangThaiNgay(Date ngayTao, Integer trangThai) {
        return null;
    }

    @Override
    public Integer countHoaDonTrangThaiThang(Date ngayTao, Integer trangThai) {
        return null;
    }

    @Override
    public Integer countHoaDonAll() {
        return null;
    }

    @Override
    public Long sumGiaTriHoaDonAll() {
        return null;
    }

    @Override
    public void guiHoaDonDienTu(HoaDon hoaDon, String url) {

    }

}
