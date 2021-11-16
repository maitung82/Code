/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Nguoihoc {
     private String MaNH;
    private String hoTen;
    private Date ngaySinh;
    private boolean GioiTinh;
    private String dienThoai;
    private String Email;
    private String ghiChu;
    private String MaNV;
    private Date ngayDK;

    public Nguoihoc() {
    }

    public Nguoihoc(String MaNH, String hoTen, Date ngaySinh, boolean GioiTinh, String dienThoai, String Email, String ghiChu, String MaNV, Date ngayDK) {
        this.MaNH = MaNH;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.GioiTinh = GioiTinh;
        this.dienThoai = dienThoai;
        this.Email = Email;
        this.ghiChu = ghiChu;
        this.MaNV = MaNV;
        this.ngayDK = ngayDK;
    }

    public String getMaNH() {
        return MaNH;
    }

    public void setMaNH(String MaNH) {
        this.MaNH = MaNH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

   
}
