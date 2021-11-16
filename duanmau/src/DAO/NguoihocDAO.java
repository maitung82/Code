/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.Khoahoc;
import Model.Nguoihoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NguoihocDAO extends EduSyDAO<Nguoihoc, String> {

    final String SQL_insert = "insert into NguoiHoc(MaNH,TenNH,GioiTinh,NgaySinh,DienThoai, Email,GhiChu, MaNV, NgayDK) values(?,?,?,?,?,?,?,?,?)";
    final String SQL_Update = "update NguoiHoc set TenNH=?,GioiTinh=?,NgaySinh=?,DienThoai=?,Email=?,GhiChu=?,MaNV=?,NgayDK=? where MaNH=?";
    final String SQL_Delete = "delete from NguoiHoc where MaNH=?";
    final String SQL_SelectALl = "select * from NguoiHoc";
    final String SQL_SelectID = "select * from NguoiHoc where MaNH=?";
    final String SQL_NOT_IN_COURSE_SQL = "select * from NguoiHoc where TenNH like ? and MaNH not in(Select MaNH from HocVien where MaKH=?)";

    @Override
    public void insert(Nguoihoc entity) {
        try {
            jdbcHelper.update(SQL_insert,entity.getMaNH(), entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK());
        } catch (SQLException ex) {
            Logger.getLogger(NguoihocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Nguoihoc entity) {
        try {
            jdbcHelper.update(SQL_Update, entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK(), entity.getMaNH());
        } catch (SQLException ex) {
            Logger.getLogger(NguoihocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            jdbcHelper.update(SQL_Delete, id);
        } catch (SQLException ex) {
            Logger.getLogger(NguoihocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Nguoihoc> selectAll() {
        return this.selectBysql(SQL_SelectALl);
    }

    @Override
    public Nguoihoc selectById(String id) {
        List<Nguoihoc> list = this.selectBysql(SQL_SelectID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Nguoihoc> selectBysql(String sql, Object... args) {
        List<Nguoihoc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                Nguoihoc entity = new Nguoihoc();
                entity.setMaNH(rs.getString(1));
                entity.setHoTen(rs.getString(2));

                entity.setGioiTinh(rs.getBoolean(3));
                entity.setNgaySinh(rs.getDate(4));

                entity.setDienThoai(rs.getString(5));
                entity.setEmail(rs.getString(6));
                entity.setGhiChu(rs.getString(7));
                entity.setMaNV(rs.getString(8));
                entity.setNgayDK(rs.getDate(9));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Nguoihoc> selectNotInCourse(int maKH,String keyword) {
        return this.selectBysql(SQL_NOT_IN_COURSE_SQL,"%"+keyword+"%",maKH);
    }


}
