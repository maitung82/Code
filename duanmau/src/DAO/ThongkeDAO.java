/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongkeDAO {

    private List<Object[]> getListofArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    public List<Object[]> getBangDiem(Integer makh){
//        String sql="{call sp_BangDiem1(?)}";
//        String[] cols={"MaNH","TenNH","Diem"};
//        return getListofArray(sql, cols, makh);
//    }

    public List<Object[]> getBangDiem(Integer makh) {
        String sql = "{CALL sp_BangDiem1(?)}";
        String[] cols = {"MaNH", "TenNH", "Diem"};
        return this.getListofArray(sql, cols, makh);
    }

    public List<Object[]> getLuongNguoiHoc() {
        String sql = "{call sp_LuongNguoiHoc}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return getListofArray(sql, cols);
    }

    public List<Object[]> getDiemChuyenDe() {
        String sql = "{call sp_DiemChuyenDe}";
        String[] cols = {"ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh"};
        return getListofArray(sql, cols);
    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{call sp_DoanhThu(?)}";
        String[] cols = {"ChuyenDe", "SoKH", "SoHV", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
        return getListofArray(sql, cols, nam);
    }
}
