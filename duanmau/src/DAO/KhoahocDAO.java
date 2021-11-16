/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.Khoahoc;
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
public class KhoahocDAO extends EduSyDAO<Khoahoc, Integer>{
    final String SQL_insert = "insert into KhoaHoc values(?,?,?,?,?,?,?)";
    final String SQL_Update = "update KhoaHoc set MaCD=?,HocPhi=?,ThoiLuong=?,NgayKG=?,GhiChu=?,MaNV=?,NgayTao=? where MaKH=?";
    final String SQL_Delete = "delete from KhoaHoc where MaKH=?";
    final String SQL_SelectALl = "select * from KhoaHoc";
    final String SQL_SelectID = "select*from KhoaHoc where MaKH=?";
    final String SQL_BY_MA_CD_SQL = "select*from KhoaHoc where MaCD=?";
    
    @Override
    public void insert(Khoahoc entity) {
        try {
            jdbcHelper.update(SQL_insert,entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao());
        } catch (SQLException ex) {
            Logger.getLogger(KhoahocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Khoahoc entity) {
        try {
            jdbcHelper.update(SQL_Update, entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao(),entity.getMaKH());
        } catch (SQLException ex) {
            Logger.getLogger(KhoahocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            jdbcHelper.update(SQL_Delete, id);
        } catch (SQLException ex) {
            Logger.getLogger(KhoahocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Khoahoc> selectAll() {
         return this.selectBysql(SQL_SelectALl); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Khoahoc selectById(Integer id) {
       List<Khoahoc> list = this.selectBysql(SQL_SelectID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Khoahoc> selectBysql(String sql, Object... args) {
       List<Khoahoc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                list.add(new Khoahoc(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Khoahoc> selectKhaoHocByChuyenDe(String maCD) {
         return this.selectBysql(SQL_BY_MA_CD_SQL,maCD); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Integer> selectYears() {
         String sql="select distinct YEAR(NgayKG) from KhoaHoc order by YEAR desc";
         List<Integer> list=new ArrayList<>();
         try {
            ResultSet rs=jdbcHelper.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
