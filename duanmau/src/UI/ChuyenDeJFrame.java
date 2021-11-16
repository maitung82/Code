/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.ChuyendeDAO;
import Helper.Auth;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Helper.Ximage;
import Model.Chuyende;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ChuyenDeJFrame extends javax.swing.JFrame {
          JFileChooser fileChooser = new JFileChooser("logos");
          ChuyendeDAO dao=new ChuyendeDAO();
          int row=0;
    /**
     * Creates new form ChuyenDeJFrame
     */
    public ChuyenDeJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("EduSy Quản lý chuyên đề");  
        init();
    }
    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
        fillTable();
        updateStatus();
        setDefaultCloseOperation(2);
    }
    
    void fillTable(){
       DefaultTableModel mol=(DefaultTableModel) tblChuyende.getModel();
       mol.setRowCount(0);
       try {
          List<Chuyende> list=dao.selectAll();
          for(Chuyende cd: list){
              Object[] row={
                  cd.getMaCD(),cd.getTenCD(),cd.getThoiLuong(),cd.getHocPhi(),cd.getHinh()
              };
              mol.addRow(row);
          }
       } catch (Exception e) {
           DialogHelper.alert(this,"Lỗi truy vấn");
           e.printStackTrace();
         //  e.printStackTrace();
       }
   }
   void insert(){
       Chuyende cd=getForm();
       try {
           dao.insert(cd);
           fillTable();
           clearForm();
           DialogHelper.alert(this, "Thêm mới thành công");
       } catch (Exception e) {
          DialogHelper.alert(this, "Thêm mới thất bại");
          e.printStackTrace();
       }
   }
   void update(){
       Chuyende cd=getForm();
       try {
           dao.update(cd);
           fillTable();
           DialogHelper.alert(this, "cập nhật thành công");
       } catch (Exception e) {
           DialogHelper.alert(this, "cập nhật thất bại");
           e.printStackTrace();
       }
   }
   void clearForm(){
       Chuyende cd=new Chuyende();
       setForm(cd);
       row=-1;
       this.updateStatus();
   }
   
   Chuyende getForm(){
       Chuyende cd=new Chuyende();
       cd.setMaCD(txtMaCD.getText());
       cd.setTenCD(txtTenCD.getText());
       cd.setHocPhi(Float.parseFloat(txtHocPhi.getText()));
       cd.setThoiLuong(Integer.valueOf(txtThoiLuong.getText()));
       cd.setHinh(lblHinh.getToolTipText());
       cd.setMoTa(txtMota.getText());
       return cd;
   }
   
   void setForm(Chuyende cd){
       txtMaCD.setText(cd.getMaCD());
       txtTenCD.setText(cd.getTenCD());
       txtHocPhi.setText(cd.getHocPhi()+"");
       txtThoiLuong.setText(cd.getThoiLuong()+"");
       lblHinh.setText(cd.getHinh());
       txtMota.setText(cd.getMoTa());
       if(cd.getHinh() !=null){
           lblHinh.setToolTipText(cd.getHinh());
           lblHinh.setIcon(Ximage.read(cd.getHinh()));
       }
   }
// 
   void edit() {
        String maCD = tblChuyende.getValueAt(row, 0)+"";
        Chuyende cd = dao.selectById(maCD);
        this.setForm(cd);
        tabs.setSelectedIndex(0);
        this.updateStatus();
    }
   void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblChuyende.getRowCount() - 1);
        txtMaCD.setEditable(!edit);
        btnInsert.setEnabled(!edit);
        btnUpdate.setEnabled(edit);
        btnDelete.setEnabled(edit);

        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);

    }
   void first() {
        this.row = 0;
        this.edit();
    }
   void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
        }
    }
   void next() {
        if (this.row < tblChuyende.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
    }
   void last() {
        this.row = tblChuyende.getRowCount() - 1;
        this.edit();
    }
//   void delete(){
//         if(!Auth.isManager()){
//             DialogHelper.alert(this, "Bạn không có quyền xóa nhân viên");
//         }else{
//             String maNV=txtMaCD.getText();
//             if(maNV.equals(Auth.user.getMaNV())){
//                 DialogHelper.alert(this, "Bạn không được xóa chính bạn");
//             }else if(DialogHelper.confirm(this, "Bạn thực sự muốn xóa nhân viên này")){
//                 try {
//                     dao.delete(maNV);
//                     fillTable();
//                     clearForm();
//                     DialogHelper.alert(this, "Xóa thành công");
//                 } catch (Exception e) {
//                     DialogHelper.alert(this, "Xóa thất bại");
//                 }
//             }
//         }
//     }
   void delete() {
        if (DialogHelper.confirm(this, "Bạn có chắc muốn xóa chuyên đề này?")) {
            try {
                dao.delete(txtMaCD.getText());
                this.fillTable();
                this.clearForm();
                DialogHelper.alert(this, "Xóa thành công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Không thể xóa chuyên đề có khóa học đã được tổ chức");
            }
        }
    }
   void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Ximage.save(file);
            ImageIcon icon = Ximage.read(file.getName());
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(file.getName());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlEdit = new javax.swing.JPanel();
        lblHinhLogo = new javax.swing.JLabel();
        lblMaCD = new javax.swing.JLabel();
        txtMaCD = new javax.swing.JTextField();
        lblTenCD = new javax.swing.JLabel();
        txtTenCD = new javax.swing.JTextField();
        lblHocPhi = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        lblThoiLuong = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        lblMoTa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMota = new javax.swing.JTextArea();
        lblHinh = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        pnlList = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChuyende = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 204));
        lblTitle.setText("QUẢN LÝ CHUYÊN ĐỀ");

        lblHinhLogo.setText("Hình logo");

        lblMaCD.setText("Mã chuyên đề");

        lblTenCD.setText("Tên chuyên đề");

        lblHocPhi.setText("Học phí");

        lblThoiLuong.setText("Thời lượng (giờ)");

        lblMoTa.setText("Mô tả chuyên đề");

        txtMota.setColumns(20);
        txtMota.setRows(5);
        jScrollPane1.setViewportView(txtMota);

        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnClear.setText("Mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save.png"))); // NOI18N
        btnInsert.setText("Thêm");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dau.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lui.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tien.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cuoi.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHinhLogo)
                            .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHocPhi, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaCD)
                            .addComponent(txtTenCD, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtThoiLuong)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHocPhi)
                                    .addComponent(lblThoiLuong)
                                    .addComponent(lblTenCD)
                                    .addComponent(lblMaCD))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(lblMoTa)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(btnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)))
                .addContainerGap())
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHinhLogo)
                    .addComponent(lblMaCD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(txtMaCD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenCD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThoiLuong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHocPhi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMoTa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLast)
                        .addComponent(btnNext)
                        .addComponent(btnPrev)
                        .addComponent(btnFirst))
                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInsert)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)
                        .addComponent(btnClear)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("CẬP NHẬT", pnlEdit);

        tblChuyende.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ CĐ", "TÊN CĐ", "HỌC PHÍ", "THỜI LƯỢNG", "HÌNH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChuyende.setRowHeight(25);
        tblChuyende.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChuyendeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChuyende);

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("DANH SÁCH", pnlList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addGap(220, 220, 220))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        
            chonAnh();
        
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
       update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblChuyendeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChuyendeMouseClicked
        // TODO add your handling code here:
       if(evt.getClickCount()==2){
           this.row=tblChuyende.rowAtPoint(evt.getPoint());
           edit();
       }
    }//GEN-LAST:event_tblChuyendeMouseClicked

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
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChuyenDeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHinhLogo;
    private javax.swing.JLabel lblHocPhi;
    private javax.swing.JLabel lblMaCD;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTenCD;
    private javax.swing.JLabel lblThoiLuong;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlList;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblChuyende;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaCD;
    private javax.swing.JTextArea txtMota;
    private javax.swing.JTextField txtTenCD;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
