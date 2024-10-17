package com.example.datn_teehaven.controller;

import com.example.datn_teehaven.entyti.HinhAnhSanPham;
import com.example.datn_teehaven.entyti.SanPham;
import com.example.datn_teehaven.service.HinhAnhSanPhamSerivce;
import com.example.datn_teehaven.service.SanPhamSerivce;
import com.example.datn_teehaven.service.ThuongHieuService;
import com.google.firebase.cloud.StorageClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamSerivce sanPhamService;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private HinhAnhSanPhamSerivce hinhAnhSanPhamSerivce;

    @GetMapping()
    public String getAll(Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(required = false) String ten,
                         @RequestParam(required = false) Boolean trangThai) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<SanPham> pageSP = sanPhamService.search(ten, trangThai, pageable);
        model.addAttribute("listSP", pageSP.getContent());
        model.addAttribute("tongSoTrang", pageSP.getTotalPages());
        model.addAttribute("trangHienTai", page);
        model.addAttribute("ten", ten);
        model.addAttribute("trangThai", trangThai);
        model.addAttribute("sanPham",new SanPham());
        model.addAttribute("listTH",thuongHieuService.getAll());
        return "admin-template/san_pham/san-pham";
    }
    @GetMapping("/trang-add")
    public String trangAdd(Model model){
        model.addAttribute("sanPham",new SanPham());
        model.addAttribute("listTH",thuongHieuService.getAll());
        return "admin-template/san_pham/add-san-pham";
    }

    @GetMapping("/detail/{id}")
    public String Detail(@PathVariable("id")Long id,
                         Model model){
        SanPham sanPham = sanPhamService.getById(id);
        model.addAttribute("sanPham",sanPham);
        model.addAttribute("listTH",thuongHieuService.getAll());
        return "admin-template/san_pham/sua-san-pham";
    }

    @Value("${app.firebase.bucket.name}")
    private String bucketName;
    @PostMapping("/add")
    public String add(@ModelAttribute("sanPham")@Valid SanPham sanPham,
                      BindingResult result,
                      @RequestParam("fileImage") MultipartFile[] fileImages,
                      Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("listTH", thuongHieuService.getAll());
            return "admin-template/san_pham/add-san-pham";
        }
        List<HinhAnhSanPham> hinhAnhList = new ArrayList<>();
        for (MultipartFile file : fileImages) {
            try {
                String fileName = file.getOriginalFilename();
                // Upload file to Firebase Storage
                StorageClient.getInstance().bucket(bucketName).create(fileName, file.getBytes(), file.getContentType());

                // Retrieve the download URL
                String imageUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", bucketName, fileName);

                HinhAnhSanPham hinhAnh = new HinhAnhSanPham();
                hinhAnh.setUrl(imageUrl);
                hinhAnh.setNgayTao(new java.util.Date());
                hinhAnh.setTrangThai(0);
                hinhAnh.setSanPham(sanPham);
                hinhAnhList.add(hinhAnh);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sanPham.setNgayTao(Date.valueOf(LocalDate.now()));
        sanPham.setTrangThai(0);

        sanPhamService.add(sanPham);

        return "redirect:/san-pham";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("sanPham") @Valid SanPham sanPham,
                         BindingResult result,
                         @RequestParam("fileImage") MultipartFile[] fileImages,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("listTH", thuongHieuService.getAll());
            return "admin-template/san_pham/sua-san-pham";
        }

        SanPham existingSanPham = sanPhamService.getById(sanPham.getId());

        // Xóa tất cả hình ảnh cũ của sản phẩm
        hinhAnhSanPhamSerivce.deleteByID(sanPham.getId());

        // Khởi tạo danh sách mới cho hình ảnh
        List<HinhAnhSanPham> hinhAnhList = new ArrayList<>();

        // Kiểm tra và xử lý các file ảnh mới
        for (MultipartFile file : fileImages) {
            if (!file.isEmpty()) {
                try {
                    String fileName = file.getOriginalFilename();

                    // Upload ảnh lên Firebase Storage
                    StorageClient.getInstance().bucket(bucketName).create(fileName, file.getBytes(), file.getContentType());

                    // Tạo URL truy cập hình ảnh
                    String imageUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", bucketName, fileName);

                    // Tạo đối tượng HinhAnhSanPham và thêm vào danh sách
                    HinhAnhSanPham hinhAnh = new HinhAnhSanPham();
                    hinhAnh.setUrl(imageUrl);
                    hinhAnh.setNgayTao(new java.util.Date());
                    hinhAnh.setNgaySua(new java.util.Date());
                    hinhAnh.setTrangThai(0); // Trạng thái mới
                    hinhAnh.setSanPham(sanPham);
                    hinhAnhList.add(hinhAnh);
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("errorMessage", "Lỗi khi tải lên hình ảnh: " + file.getOriginalFilename());
                    return "admin-template/san_pham/sua-san-pham";
                }
            }
        }

        // Cập nhật thông tin sản phẩm
        sanPham.setMa(existingSanPham.getMa());
        sanPham.setNgaySua(new java.util.Date());
        sanPham.setTrangThai(existingSanPham.getTrangThai());

        // Gọi hàm update của service để lưu sản phẩm và hình ảnh mới
        sanPhamService.update(sanPham);

        return "redirect:/san-pham";
    }


}
