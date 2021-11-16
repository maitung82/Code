/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.Hocvien;
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
public class HocvienDAO extends EduSyDAO<Hocvien, Integer>{
    final String SQL_insert = "insert into HocVien values(?,?,?)";
    final String SQL_Update = "update HocVien set MaKH=?,MaNH=?,Diem=? where MaHV=?";
    final String SQL_Delete = "delete from HocVien where MaHV=?";
    final String SQL_SelectALl = "select * from HocVien";
    final String SQL_SelectID = "select * from HocVien where MaHV=?";

    @Override
    public void insert(Hocvien entity) {
        try {
            jdbcHelper.update(SQL_insert,entity.getMaKH(),entity.getMaNH(),entity.getDiem());
        } catch (SQLException ex) {
            Logger.getLogger(HocvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Hocvien entity) {
        try {
            jdbcHelper.update(SQL_Update, entity.getMaKH(),entity.getMaNH(),entity.getDiem(),entity.getMaHV());
        } catch (SQLException ex) {
            Logger.getLogger(HocvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer id) {
         try {
            jdbcHelper.update(SQL_Delete, id);
        } catch (SQLException ex) {
            Logger.getLogger(HocvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Hocvien> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hocvien selectById(Integer id) {
        List<Hocvien> list = this.selectBysql(SQL_SelectID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Hocvien> selectBysql(String sql, Object... args) {
        List<Hocvien> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                list.add(new Hocvien(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
public List<Hocvien> selectByKhoaHoc(int makh) {
        String sql="Select*from HocVien where MaKH=?";
        return this.selectBysql(sql, makh);
    }

    
    
}
