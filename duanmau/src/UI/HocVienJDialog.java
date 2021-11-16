/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.ChuyendeDAO;
import DAO.HocvienDAO;
import DAO.KhoahocDAO;
import DAO.NguoihocDAO;
import Helper.Auth;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Helper.Xdate;
import Model.Chuyende;
import Model.Hocvien;
import Model.Khoahoc;
import Model.Nguoihoc;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class HocVienJDialog extends javax.swing.JDialog {

    ChuyendeDAO cddao = new ChuyendeDAO();
    KhoahocDAO khdao = new KhoahocDAO();
    NguoihocDAO nddao = new NguoihocDAO();
    HocvienDAO hvdao = new HocvienDAO();
    int row = -1;

    public HocVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    private void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
        setTitle("Quản lý học viên");
        fillComboBoxChuyenDe();
        setDefaultCloseOperation(2);
    }

    private void fillComboBoxChuyenDe() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbChuyenDe.getModel();
        model.removeAllElements();
        try {
            List<Chuyende> list = cddao.selectAll();
            for (Chuyende cd : list) {
                model.addElement(cd);
            }
            fillComboBoxKhoaHoc();
        } catch (Exception e) {
            //DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }

    }

//    private void fillComboBoxKhoaHoc() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbChuyenDe.getModel();
//        model.removeAllElements();
//        try {
//            Chuyende chuyende = (Chuyende) cbbChuyenDe.getSelectedItem();
//            if (chuyende != null) {
//                List<Khoahoc> list = khdao.selectKhaoHocByChuyenDe(chuyende.getMaCD());
//                for (Khoahoc x : list) {
//                    model.addElement(x);
//                }
    //            fillTableHocVien();
//            }
//        } catch (Exception e) {
//            // DialogHelper.alert(this, "lỗi truy vấn dữ liệu");
//            e.printStackTrace();
//        }
//    }
    void fillComboBoxKhoaHoc() {
        DefaultComboBoxModel dtc = (DefaultComboBoxModel) cbbKhoaHoc.getModel();
        dtc.removeAllElements();
        Chuyende cd = (Chuyende) cbbChuyenDe.getSelectedItem();
        if (cd != null) {
            List<Khoahoc> list = khdao.selectKhaoHocByChuyenDe(cd.getMaCD());
            for (Khoahoc x : list) {
                dtc.addElement(x);
            }
            this.fillTableHocVien();
        }
    }

    private void fillTableNguoiHoc() {
        DefaultTableModel model = (DefaultTableModel) tblNguoiHoc.getModel();
        model.setRowCount(0);
        try {
            Khoahoc kh = (Khoahoc) cbbKhoaHoc.getSelectedItem();
            if (kh != null) {
                System.out.println("MaKH" + kh.getMaKH());
                String keyword = txtTimKiem.getText();
                List<Nguoihoc> list = nddao.selectNotInCourse(kh.getMaKH(), keyword);
                for (Nguoihoc nh : list) {
                    Object[] row = {
                        nh.getMaNH(), nh.getHoTen(), nh.isGioiTinh() ? "Nam" : "Nữ", Xdate.toString(nh.getNgaySinh(), "yyyy-MM-dd"), nh.getDienThoai(), nh.getEmail()
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            // DialogHelper.alert(this, "lỗi truy vấn dữ liệu fillTableNguoiHoc");
            e.printStackTrace();
        }
    }

    private void fillTableHocVien() {
        DefaultTableModel model = (DefaultTableModel) tblHocVien.getModel();
        model.setRowCount(0);
        try {
            Khoahoc kh = (Khoahoc) cbbKhoaHoc.getSelectedItem();
            if (kh != null) {
                List<Hocvien> list = hvdao.selectByKhoaHoc(kh.getMaKH());
                for (int i = 0; i < list.size(); i++) {
                    Hocvien hv = list.get(i);
                    String hoTen = nddao.selectById(hv.getMaNH()).getHoTen();
                    Object[] row = {
                        i + 1, hv.getMaHV(), hv.getMaNH(), hoTen, hv.getDiem()
                    };
                    model.addRow(row);
                }
            }
            fillTableNguoiHoc();
        } catch (Exception e) {
            //  DialogHelper.alert(this, "lỗi truy vân dữ liệu");
            e.printStackTrace();
        }
    }

    private void addHocVien() {
        Khoahoc khoahoc = (Khoahoc) cbbKhoaHoc.getSelectedItem();
        for (int row : tblNguoiHoc.getSelectedRows()) {
            Hocvien hv = new Hocvien();
            hv.setMaKH(khoahoc.getMaKH());
            hv.setDiem(0);
            hv.setMaNH((String) tblNguoiHoc.getValueAt(row, 0));
            hvdao.insert(hv);
        }
        fillTableHocVien();
        tabs.setSelectedIndex(0);
    }

    void removeHocVien() {
        if (!Auth.isManager()) {
            DialogHelper.alert(this, "bạn không đủ quyền xóa học viên");
        } else {
            if (DialogHelper.confirm(this, "bạn muốn xóa các học viên được chọn")) {
                for (int row : tblHocVien.getSelectedRows()) {
                    int maHV = (Integer) tblHocVien.getValueAt(row, 1);
                    hvdao.delete(maHV);
                }
                fillTableHocVien();
            }
        }
    }

    void updateDiem() {
        for (int i = 0; i < tblHocVien.getRowCount(); i++) {
            int maHV = (Integer) tblHocVien.getValueAt(i, 1);
            Hocvien hocvien = hvdao.selectById(maHV);
            hocvien.setDiem(Double.parseDouble(tblHocVien.getValueAt(i, 4).toString()));
            hvdao.update(hocvien);
        }
        DialogHelper.alert(this, "cập nhật thành công");
    }

    private void timKiem() {
        this.fillTableNguoiHoc();
        this.row = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlKhoaHoc = new javax.swing.JPanel();
        cbbKhoaHoc = new javax.swing.JComboBox<>();
        pnlChuyenDe = new javax.swing.JPanel();
        cbbChuyenDe = new javax.swing.JComboBox<>();
        tabs = new javax.swing.JTabbedPane();
        pnlHocVien = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHocVien = new javax.swing.JTable();
        btnXoaHV = new javax.swing.JButton();
        btnSuaDiem = new javax.swing.JButton();
        pnlNguoiHoc = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNguoiHoc = new javax.swing.JTable();
        btnThemHV = new javax.swing.JButton();
        pnlTimKiem = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edysys- Quản lý học viên");

        pnlKhoaHoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "KHÓA HỌC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pnlKhoaHoc.setLayout(new java.awt.GridLayout(1, 0));

        cbbKhoaHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKhoaHocActionPerformed(evt);
            }
        });
        pnlKhoaHoc.add(cbbKhoaHoc);

        pnlChuyenDe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHUYÊN ĐỀ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pnlChuyenDe.setLayout(new java.awt.GridLayout(1, 0));

        cbbChuyenDe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChuyenDe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbChuyenDeItemStateChanged(evt);
            }
        });
        cbbChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChuyenDeActionPerformed(evt);
            }
        });
        pnlChuyenDe.add(cbbChuyenDe);

        tblHocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "TT", "MÃ hv", "MÃ NH", "HỌ TÊN", "ĐIểm"
            }
        ));
        jScrollPane2.setViewportView(tblHocVien);

        btnXoaHV.setText("Xóa khỏi khóa học");
        btnXoaHV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHVActionPerformed(evt);
            }
        });

        btnSuaDiem.setText("Cập nhập điểm");
        btnSuaDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHocVienLayout = new javax.swing.GroupLayout(pnlHocVien);
        pnlHocVien.setLayout(pnlHocVienLayout);
        pnlHocVienLayout.setHorizontalGroup(
            pnlHocVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHocVienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaHV, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
            .addGroup(pnlHocVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHocVienLayout.createSequentialGroup()
                    .addContainerGap(457, Short.MAX_VALUE)
                    .addComponent(btnSuaDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19)))
        );
        pnlHocVienLayout.setVerticalGroup(
            pnlHocVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHocVienLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaHV, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(pnlHocVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHocVienLayout.createSequentialGroup()
                    .addContainerGap(382, Short.MAX_VALUE)
                    .addComponent(btnSuaDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)))
        );

        tabs.addTab("HỌC VIÊN", pnlHocVien);

        tblNguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MÃ NH", "Họ tên", "Giới tính", "Ngày sinh", "Điện thoại", "Email"
            }
        ));
        jScrollPane1.setViewportView(tblNguoiHoc);

        btnThemHV.setText("Thêm vào khóa học");
        btnThemHV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHVActionPerformed(evt);
            }
        });

        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pnlTimKiem.setLayout(new java.awt.GridLayout(1, 0));

        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        pnlTimKiem.add(txtTimKiem);

        javax.swing.GroupLayout pnlNguoiHocLayout = new javax.swing.GroupLayout(pnlNguoiHoc);
        pnlNguoiHoc.setLayout(pnlNguoiHocLayout);
        pnlNguoiHocLayout.setHorizontalGroup(
            pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
            .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                .addGroup(pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNguoiHocLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemHV, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlNguoiHocLayout.setVerticalGroup(
            pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemHV)
                .addContainerGap())
        );

        tabs.addTab("NGƯỜI HỌC", pnlNguoiHoc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlKhoaHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlChuyenDe, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(pnlKhoaHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(tabs)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChuyenDeActionPerformed
        // TODO add your handling code here:
        int viTri = cbbChuyenDe.getSelectedIndex();
        if (viTri >= 0) {
            fillComboBoxKhoaHoc();
        }
    }//GEN-LAST:event_cbbChuyenDeActionPerformed

    private void cbbKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKhoaHocActionPerformed
        // TODO add your handling code here:
        int viTri = cbbKhoaHoc.getSelectedIndex();
        if (viTri >= 0) {
            fillTableHocVien();
            tabs.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cbbKhoaHocActionPerformed

    private void btnXoaHVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHVActionPerformed
        // TODO add your handling code here:
        removeHocVien();
    }//GEN-LAST:event_btnXoaHVActionPerformed

    private void btnSuaDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDiemActionPerformed
        // TODO add your handling code here:
        updateDiem();
    }//GEN-LAST:event_btnSuaDiemActionPerformed

    private void btnThemHVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHVActionPerformed
        // TODO add your handling code here:
        addHocVien();
    }//GEN-LAST:event_btnThemHVActionPerformed

    private void cbbChuyenDeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbChuyenDeItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbbChuyenDeItemStateChanged

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        this.timKiem();
    }//GEN-LAST:event_txtTimKiemMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HocVienJDialog dialog = new HocVienJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaDiem;
    private javax.swing.JButton btnThemHV;
    private javax.swing.JButton btnXoaHV;
    private javax.swing.JComboBox<String> cbbChuyenDe;
    private javax.swing.JComboBox<String> cbbKhoaHoc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlChuyenDe;
    private javax.swing.JPanel pnlHocVien;
    private javax.swing.JPanel pnlKhoaHoc;
    private javax.swing.JPanel pnlNguoiHoc;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblHocVien;
    private javax.swing.JTable tblNguoiHoc;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
