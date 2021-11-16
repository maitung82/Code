/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.Chuyende;
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
public class ChuyendeDAO extends EduSyDAO<Chuyende, String>{
    final String SQL_insert = "insert into ChuyenDe values(?,?,?,?,?,?)";
    final String SQL_Update = "update ChuyenDe set TenCD=?,HocPhi=?,ThoiLuong=?,Hinh=?,MoTa=? where MaCD=?";
    final String SQL_Delete = "delete from ChuyenDe where MaCD=?";
    final String SQL_SelectALl = "select * from ChuyenDe";
    final String SQL_SelectID = "select * from ChuyenDe where MaCD=?";

    @Override
    public void insert(Chuyende entity) {
        try {
            jdbcHelper.update(SQL_insert, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(),entity.getHinh(),entity.getMoTa());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyendeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Chuyende entity) {
        try {
            jdbcHelper.update(SQL_Update, entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa(),entity.getMaCD());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyendeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            jdbcHelper.update(SQL_Delete, id);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyendeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public Chuyende selectById(String id) {
        List<Chuyende> list = this.selectBysql(SQL_SelectID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Chuyende> selectBysql(String sql, Object... args) {
        List<Chuyende> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                list.add(new Chuyende(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Chuyende> selectAll() {
    return this.selectBysql(SQL_SelectALl);
    }


    
}
