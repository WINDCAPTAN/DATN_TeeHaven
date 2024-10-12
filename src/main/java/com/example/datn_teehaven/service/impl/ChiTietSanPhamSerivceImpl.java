package com.example.datn_teehaven.service.impl;


import com.example.datn_teehaven.entyti.ChiTietSanPham;
import com.example.datn_teehaven.entyti.KichCo;
import com.example.datn_teehaven.entyti.MauSac;
import com.example.datn_teehaven.entyti.SanPham;
import com.example.datn_teehaven.entyti.TayAo;
import com.example.datn_teehaven.repository.ChiTietSanPhamRepository;
import com.example.datn_teehaven.service.ChiTietSanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ChiTietSanPhamSerivceImpl implements ChiTietSanPhamSerivce {

    private static final Logger logger = LoggerFactory.getLogger(ChiTietSanPhamSerivceImpl.class);

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public List<ChiTietSanPham> add(
            List<String> listSanPham, List<String> listKichCo,
            List<String> listMauSac, List<String> listTayAo,List<String> listChatLieu,
            List<String> listSoLuong, List<String> listDonGia ) {

        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();
        List<ChiTietSanPham> listCtspCheck = chiTietSanPhamRepository.findAll();

        for (int i = 0; i < listSanPham.size(); i++) {
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            boolean isUpdated = false;

            logger.info("Processing: SanPhamID: {}, KichCoID: {}, MauSacID: {}, TayAoID: {}, ChatLieuID: {}",
                    listSanPham.get(i), listKichCo.get(i), listMauSac.get(i), listTayAo.get(i), listChatLieu.get(i));

            for (ChiTietSanPham listCheck : listCtspCheck) {
                if (listCheck.getSanPham().getId().equals(Long.valueOf(listSanPham.get(i))) &&
                        listCheck.getKichCo().getId().equals(Long.valueOf(listKichCo.get(i))) &&
                        listCheck.getMauSac().getId().equals(Long.valueOf(listMauSac.get(i))) &&
                        listCheck.getTayAo().getId().equals(Long.valueOf(listTayAo.get(i)))
//                        listCheck.getChatLieu().getId().equals(Long.valueOf(listChatLieu.get(i)))
                ) {
                    int soLuongMoi = Integer.parseInt(listSoLuong.get(i));
                    listCheck.setSoLuong(listCheck.getSoLuong() + soLuongMoi);
                    listCheck.setGiaHienHanh(Long.valueOf(listDonGia.get(i)));
                    listCheck.setTrangThai(0);

                    // Correct conversion from java.util.Date to java.sql.Date
                    listCheck.setNgaySua(new java.sql.Date(new Date().getTime()));

                    ChiTietSanPham updatedChiTietSanPham = chiTietSanPhamRepository.save(listCheck);
                    chiTietSanPhamList.add(updatedChiTietSanPham);

                    isUpdated = true;
                    break;
                }
            }
            if (!isUpdated) {
                chiTietSanPham.setSanPham(SanPham.builder().id(Long.valueOf(listSanPham.get(i))).build());
                chiTietSanPham.setKichCo(KichCo.builder().id(Long.valueOf(listKichCo.get(i))).build());
                chiTietSanPham.setMauSac(MauSac.builder().id(Long.valueOf(listMauSac.get(i))).build());
                chiTietSanPham.setTayAo(TayAo.builder().id(Long.valueOf(listTayAo.get(i))).build());
//                chiTietSanPham.setChatLieu(ChatLieu.builder().id(Long.valueOf(listChatLieu.get(i))).build());
                chiTietSanPham.setSoLuong(Integer.parseInt(listSoLuong.get(i)));
                chiTietSanPham.setGiaHienHanh(Long.valueOf(listDonGia.get(i)));
                chiTietSanPham.setTrangThai(0);

                // Correct conversion from java.util.Date to java.sql.Date
                java.sql.Date currentDate = new java.sql.Date(new Date().getTime());
                chiTietSanPham.setNgayTao(currentDate);
                chiTietSanPham.setNgaySua(currentDate);

                if (chiTietSanPham.getSoLuong() > 0) {
                    ChiTietSanPham savedChiTietSanPham = chiTietSanPhamRepository.save(chiTietSanPham);
                    chiTietSanPhamList.add(savedChiTietSanPham);
                }
            }
        }
        return chiTietSanPhamList;
    }

    @Override
    public List<ChiTietSanPham> updateAllCtsp(
            List<String> listIdChiTietSp, List<String> listSanPham,
            List<String> listKichCo, List<String> listMauSac,
            List<String> listTayAo, List<String> listTrangThai,
            List<String> listChatLieu, List<String> listSoLuong, List<String> listDonGia) {
        ChiTietSanPham chiTietSanPhamNew = this.getById(Long.valueOf(listIdChiTietSp.get(0)));
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();
        for (int i = 0; i < listSanPham.size(); i++) {
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setId(Long.valueOf(listIdChiTietSp.get(i)));
            chiTietSanPham.setSanPham(SanPham.builder().id(Long.valueOf(listSanPham.get(i))).build());
            chiTietSanPham.setKichCo(KichCo.builder().id(Long.valueOf(listKichCo.get(i))).build());
            chiTietSanPham.setMauSac(MauSac.builder().id(Long.valueOf(listMauSac.get(i))).build());
            chiTietSanPham.setTayAo(TayAo.builder().id(Long.valueOf(listTayAo.get(i))).build());
//            chiTietSanPham.setChatLieu(ChatLieu.builder().id(Long.valueOf(listChatLieu.get(i))).build());
            chiTietSanPham.setTrangThai(Integer.parseInt(listTrangThai.get(i)));
            chiTietSanPham.setSoLuong(Integer.parseInt(listSoLuong.get(i)));
            chiTietSanPham.setNgayTao(chiTietSanPhamNew.getNgayTao());
            chiTietSanPham.setNgaySua(chiTietSanPhamNew.getNgaySua());
            ChiTietSanPham savedChiTietSanPham = chiTietSanPhamRepository.save(chiTietSanPham);
            chiTietSanPhamList.add(savedChiTietSanPham);
        }
        return chiTietSanPhamList;
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham sanPham) {
        return chiTietSanPhamRepository.save(sanPham);
    }


    @Override
    public ChiTietSanPham getById(Long id) {
        return chiTietSanPhamRepository.findById(id).get();
    }
    @Override
    public void checkSoLuongBang0() {
        chiTietSanPhamRepository.checkSoLuongBang0();
    }
    @Override
    public List<ChiTietSanPham> fillAllDangHoatDongLonHon0() {
        return chiTietSanPhamRepository.fillAllDangHoatDongLonHon0();
    }

}
