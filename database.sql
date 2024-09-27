USE [master]
GO
/****** Object:  Database [DATN_TeeHaven]    Script Date: 9/27/2024 9:50:09 AM ******/
CREATE DATABASE [DATN_TeeHaven]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DATN_TeeHaven', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\DATN_TeeHaven.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DATN_TeeHaven_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\DATN_TeeHaven_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [DATN_TeeHaven] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DATN_TeeHaven].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DATN_TeeHaven] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET ARITHABORT OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [DATN_TeeHaven] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DATN_TeeHaven] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DATN_TeeHaven] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET  ENABLE_BROKER 
GO
ALTER DATABASE [DATN_TeeHaven] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DATN_TeeHaven] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [DATN_TeeHaven] SET  MULTI_USER 
GO
ALTER DATABASE [DATN_TeeHaven] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DATN_TeeHaven] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DATN_TeeHaven] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DATN_TeeHaven] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DATN_TeeHaven] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [DATN_TeeHaven] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [DATN_TeeHaven] SET QUERY_STORE = OFF
GO
USE [DATN_TeeHaven]
GO
/****** Object:  Table [dbo].[chi_tiet_san_pham]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chi_tiet_san_pham](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[so_luong] [int] NULL,
	[gia_hien_hanh] [money] NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[san_pham_id] [bigint] NULL,
	[kich_co_id] [bigint] NULL,
	[mau_sac_id] [bigint] NULL,
	[tay_ao_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[dia_chi]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[dia_chi](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[thanh_pho] [nvarchar](50) NULL,
	[quan_huyen] [nvarchar](50) NULL,
	[phuong_xa] [nvarchar](50) NULL,
	[dia_chi_cu_the] [nvarchar](100) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
	[tai_khoan_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[gio_hang]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[gio_hang](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ma_gio_hang] [varchar](20) NULL,
	[ghi_chu] [nvarchar](255) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
	[nguoi_so_huu] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[gio_hang_chi_tiet]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[gio_hang_chi_tiet](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[so_luong] [int] NULL,
	[ghi_chu] [nvarchar](255) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
	[gio_hang_id] [bigint] NULL,
	[chi_tiet_san_pham_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hinh_anh_san_pham]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hinh_anh_san_pham](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[url] [nvarchar](255) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
	[uu_tien] [int] NULL,
	[san_pham_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoa_don]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_don](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ma_hoa_don] [varchar](20) NULL,
	[ngay_thanh_toan] [datetime] NULL,
	[loai_hoa_don] [int] NULL,
	[phi_ship] [money] NULL,
	[tien_giam] [money] NULL,
	[tong_tien] [money] NULL,
	[tong_tien_khi_giam] [money] NULL,
	[ghi_chu] [nvarchar](255) NULL,
	[nguoi_nhan] [nvarchar](100) NULL,
	[sdt_nguoi_nhan] [varchar](15) NULL,
	[thanh_pho] [nvarchar](50) NULL,
	[quan_huyen] [nvarchar](50) NULL,
	[phuong_xa] [nvarchar](50) NULL,
	[dia_chi_nguoi_nhan] [nvarchar](100) NULL,
	[email_nguoi_nhan] [varchar](100) NULL,
	[ngay_nhan] [datetime] NULL,
	[ngay_mong_muon] [datetime] NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
	[voucher_id] [bigint] NULL,
	[tai_khoan_id] [bigint] NULL,
	[phuong_thuc_thanh_toan_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoa_don_chi_tiet]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_don_chi_tiet](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[so_luong] [int] NULL,
	[don_gia] [money] NULL,
	[ghi_chu] [nvarchar](255) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[nguoi_tao] [nvarchar](100) NULL,
	[nguoi_sua] [nvarchar](100) NULL,
	[trang_thai] [int] NULL,
	[hoa_don_id] [bigint] NULL,
	[chi_tiet_san_pham_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[kich_co]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[kich_co](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lich_su_hoa_don]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lich_su_hoa_don](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[ghi_chu] [nvarchar](255) NULL,
	[trang_thai] [int] NULL,
	[nguoi_sua] [nvarchar](70) NULL,
	[hoa_don_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[mau_sac]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[mau_sac](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ma_mau] [varchar](30) NULL,
	[ten] [nvarchar](100) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[phuong_thuc_thanh_toan]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[phuong_thuc_thanh_toan](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ma_phuong_thuc] [varchar](20) NULL,
	[ten] [nvarchar](50) NULL,
	[ghi_chu] [nvarchar](255) NULL,
	[trang_thai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[san_pham]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[san_pham](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](50) NOT NULL,
	[ten] [nvarchar](255) NULL,
	[mo_ta] [nvarchar](max) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[nguoi_tao] [nvarchar](100) NULL,
	[nguoi_sua] [nvarchar](100) NULL,
	[trang_thai] [int] NULL,
	[thuong_hieu_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tai_khoan]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tai_khoan](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ho_va_ten] [nvarchar](100) NULL,
	[ngay_sinh] [date] NULL,
	[gioi_tinh] [int] NULL,
	[so_dien_thoai] [varchar](15) NULL,
	[email] [varchar](255) NULL,
	[ten_tai_khoan] [nvarchar](100) NULL,
	[mat_khau] [varchar](300) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
	[vai_tro_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tay_ao]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tay_ao](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](100) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[thuong_hieu]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[thuong_hieu](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](100) NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[trang_thai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[vai_tro]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[vai_tro](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucher]    Script Date: 9/27/2024 9:50:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucher](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ma_voucher] [varchar](50) NULL,
	[ten_voucher] [nvarchar](50) NULL,
	[ngay_bat_dau] [datetime] NULL,
	[ngay_ket_thuc] [datetime] NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_sua] [datetime] NULL,
	[so_luong] [int] NULL,
	[phan_tram_giam] [int] NULL,
	[giam_toi_da] [money] NULL,
	[gia_tri_don_toi_thieu] [money] NULL,
	[trang_thai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] ADD  DEFAULT ((0)) FOR [trang_thai]
GO
ALTER TABLE [dbo].[dia_chi] ADD  DEFAULT ((0)) FOR [trang_thai]
GO
ALTER TABLE [dbo].[hinh_anh_san_pham] ADD  DEFAULT ((0)) FOR [trang_thai]
GO
ALTER TABLE [dbo].[hinh_anh_san_pham] ADD  DEFAULT ((0)) FOR [uu_tien]
GO
ALTER TABLE [dbo].[kich_co] ADD  DEFAULT ((0)) FOR [trang_thai]
GO
ALTER TABLE [dbo].[mau_sac] ADD  DEFAULT (NULL) FOR [ten]
GO
ALTER TABLE [dbo].[mau_sac] ADD  DEFAULT ((0)) FOR [trang_thai]
GO
ALTER TABLE [dbo].[san_pham] ADD  DEFAULT ((0)) FOR [trang_thai]
GO
ALTER TABLE [dbo].[tai_khoan] ADD  DEFAULT (NULL) FOR [ho_va_ten]
GO
ALTER TABLE [dbo].[tai_khoan] ADD  DEFAULT ((0)) FOR [trang_thai]
GO
ALTER TABLE [dbo].[tay_ao] ADD  DEFAULT (NULL) FOR [ten]
GO
ALTER TABLE [dbo].[tay_ao] ADD  DEFAULT ((0)) FOR [trang_thai]
GO
ALTER TABLE [dbo].[thuong_hieu] ADD  DEFAULT (NULL) FOR [ten]
GO
ALTER TABLE [dbo].[thuong_hieu] ADD  DEFAULT ((0)) FOR [trang_thai]
GO
ALTER TABLE [dbo].[vai_tro] ADD  DEFAULT (NULL) FOR [ten]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD FOREIGN KEY([kich_co_id])
REFERENCES [dbo].[kich_co] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD FOREIGN KEY([mau_sac_id])
REFERENCES [dbo].[mau_sac] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD FOREIGN KEY([san_pham_id])
REFERENCES [dbo].[san_pham] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD FOREIGN KEY([tay_ao_id])
REFERENCES [dbo].[tay_ao] ([id])
GO
ALTER TABLE [dbo].[dia_chi]  WITH CHECK ADD FOREIGN KEY([tai_khoan_id])
REFERENCES [dbo].[tai_khoan] ([id])
GO
ALTER TABLE [dbo].[gio_hang]  WITH CHECK ADD FOREIGN KEY([nguoi_so_huu])
REFERENCES [dbo].[tai_khoan] ([id])
GO
ALTER TABLE [dbo].[gio_hang_chi_tiet]  WITH CHECK ADD FOREIGN KEY([chi_tiet_san_pham_id])
REFERENCES [dbo].[chi_tiet_san_pham] ([id])
GO
ALTER TABLE [dbo].[gio_hang_chi_tiet]  WITH CHECK ADD FOREIGN KEY([gio_hang_id])
REFERENCES [dbo].[gio_hang] ([id])
GO
ALTER TABLE [dbo].[hinh_anh_san_pham]  WITH CHECK ADD FOREIGN KEY([san_pham_id])
REFERENCES [dbo].[san_pham] ([id])
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD FOREIGN KEY([phuong_thuc_thanh_toan_id])
REFERENCES [dbo].[phuong_thuc_thanh_toan] ([id])
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD FOREIGN KEY([tai_khoan_id])
REFERENCES [dbo].[tai_khoan] ([id])
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD FOREIGN KEY([voucher_id])
REFERENCES [dbo].[voucher] ([id])
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet]  WITH CHECK ADD FOREIGN KEY([chi_tiet_san_pham_id])
REFERENCES [dbo].[chi_tiet_san_pham] ([id])
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet]  WITH CHECK ADD FOREIGN KEY([hoa_don_id])
REFERENCES [dbo].[hoa_don] ([id])
GO
ALTER TABLE [dbo].[lich_su_hoa_don]  WITH CHECK ADD FOREIGN KEY([hoa_don_id])
REFERENCES [dbo].[hoa_don] ([id])
GO
ALTER TABLE [dbo].[san_pham]  WITH CHECK ADD FOREIGN KEY([thuong_hieu_id])
REFERENCES [dbo].[thuong_hieu] ([id])
GO
ALTER TABLE [dbo].[tai_khoan]  WITH CHECK ADD FOREIGN KEY([vai_tro_id])
REFERENCES [dbo].[vai_tro] ([id])
GO
USE [master]
GO
ALTER DATABASE [DATN_TeeHaven] SET  READ_WRITE 
GO
