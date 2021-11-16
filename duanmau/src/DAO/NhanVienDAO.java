/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Nhanvien;
//import Model.Nhanvien;
import Helper.jdbcHelper;
import Model.Nhanvien;
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
public class NhanVienDAO extends EduSyDAO<Nhanvien, String> {

    String SQL_insert = "insert into NhanVien values(?,?,?,?)";
    String SQL_Update = "update NhanVien set HoTen=?,MatKhau=?, VaiTro=? where MaNV=?";
    String SQL_Delete = "delete from NhanVien where MaNV=?    ";
    String SQL_SelectALl = "select * from NhanVien";
    String SQL_SelectID = "select * from NhanVien where MaNV=?";

    @Override
    public void insert(Nhanvien entity) {
        try {
            jdbcHelper.update(SQL_insert, entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Nhanvien entity) {
        try {
            jdbcHelper.update(SQL_Update, entity.getHoTen(),entity.getMatKhau(),  entity.isVaiTro(), entity.getMaNV());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            jdbcHelper.update(SQL_Delete, id);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Nhanvien selectById(String id) {
       List<Nhanvien> list = this.selectBysql(SQL_SelectID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Nhanvien> selectBysql(String sql, Object... args) {
        List<Nhanvien> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                list.add(new Nhanvien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Nhanvien> selectAll() {
       return this.selectBysql(SQL_SelectALl); //To change body of generated methods, choose Tools | Templates. 
    }

}
