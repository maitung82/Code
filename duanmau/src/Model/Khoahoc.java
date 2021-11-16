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
public class Khoahoc {

    private int maKH;
    private String MaCD;
    private float hocPhi;
    private int thoiLuong;
    private Date NgayKG;
    private String ghiChu;
    private String MaNV;
    private Date ngayTao;

    public Khoahoc() {
    }

    public Khoahoc(int maKH, String MaCD, float hocPhi, int thoiLuong, Date NgayKG, String ghiChu, String MaNV, Date ngayTao) {
        this.maKH = maKH;
        this.MaCD = MaCD;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.NgayKG = NgayKG;
        this.ghiChu = ghiChu;
        this.MaNV = MaNV;
        this.ngayTao = ngayTao;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getMaCD() {
        return MaCD;
    }

    public void setMaCD(String MaCD) {
        this.MaCD = MaCD;
    }

    public float getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(float hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public Date getNgayKG() {
        return NgayKG;
    }

    public void setNgayKG(Date NgayKG) {
        this.NgayKG = NgayKG;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return this.MaCD + "(" + this.getNgayKG() + ")";
    }
   

}
