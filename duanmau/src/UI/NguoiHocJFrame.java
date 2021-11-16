/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.NguoihocDAO;
import Helper.Auth;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Helper.Xdate;
import Model.Nguoihoc;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class NguoiHocJFrame extends javax.swing.JFrame {

    NguoihocDAO dao = new NguoihocDAO();
    int row = -1;

    /**
     * Creates new form NguoiHocJFrame
     */
    public NguoiHocJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        init();
    }

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
        fillTable();
        updateStatus();
        setDefaultCloseOperation(2);
    }

    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblNguoihoc.getRowCount() - 1);

        txtMaNH.setEditable(!edit);
        btnInsert.setEnabled(!edit);
        btnUpdate.setEnabled(edit);
        btnDelete.setEnabled(edit);

        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    void fillTable() {
        DefaultTableModel mol = (DefaultTableModel) tblNguoihoc.getModel();
        mol.setRowCount(0);
        try {
            List<Nguoihoc> list = dao.selectAll();
            for (Nguoihoc nh : list) {
                Object[] row = {
                    nh.getMaNH(),
                    nh.getHoTen(),
                   Xdate.toString(nh.getNgaySinh(),"yyyy-MM-dd"),
                    nh.isGioiTinh(),
                    nh.getDienThoai(),
                    nh.getEmail(),
                    nh.getGhiChu(),
                    nh.getMaNV(),
                    Xdate.toString(nh.getNgayDK(),"yyyy-MM-dd")
                };
                mol.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this,"Lỗi truy vấn");
        }
    }

    void firt() {
        row = 0;
        edit();
    }

    void pre() {
        if (this.row > 0) {
            row--;
            edit();
        }
    }

    void next() {
        if (this.row < tblNguoihoc.getRowCount() - 1) {
            this.row++;
            edit();
        }
    }

    void last() {
        row = tblNguoihoc.getRowCount() - 1;
        edit();
    }

    void edit() {
        try {
            String maNV = (String) tblNguoihoc.getValueAt(this.row, 0);
            Nguoihoc model = dao.selectById(maNV);
            if (model != null) {
                setForm(model);
                tabs.setSelectedIndex(0);
                updateStatus();
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "lỗi truy vấn dữ liệu");
        }
    }

    Nguoihoc getForm() {
        Nguoihoc nh = new Nguoihoc();
        nh.setMaNH(txtMaNH.getText());
        nh.setHoTen(txtHoTen.getText());
        nh.setGioiTinh(rdonam.isSelected());
        nh.setNgaySinh(Xdate.toDate(txtNgaySinh.getText(), "yyyy-MM-dd"));
        nh.setDienThoai(txtDienThoai.getText());
        nh.setEmail(txtEmail.getText());
        nh.setGhiChu(txtGhiChu.getText());
        nh.setMaNV(Auth.user.getMaNV());
        nh.setNgayDK(Xdate.now());
        return nh;
    }

    void setForm(Nguoihoc nh) {
        txtMaNH.setText(nh.getMaNH());
        txtHoTen.setText(nh.getHoTen());
        txtNgaySinh.setText(Xdate.toString(nh.getNgaySinh(), "yyyy-MM-dd"));
        txtDienThoai.setText(nh.getDienThoai());
        txtEmail.setText(nh.getEmail());
        txtGhiChu.setText(nh.getGhiChu());
        if (nh.isGioiTinh()) {
            rdonu.setSelected(true);
        } else {
            rdonam.setSelected(nh.isGioiTinh());
            rdonam.setSelected(!nh.isGioiTinh());
        }
    }

    void clearForm() {
        Nguoihoc nv = new Nguoihoc();
        setForm(nv);
        row = -1;
        updateStatus();
    }

    void insert() {
        Nguoihoc nh = getForm();
        if (txtEmail.getText().length() == 0
                || txtDienThoai.getText().length() == 0
                || txtGhiChu.getText().length() == 0
                || txtHoTen.getText().length() == 0
                || txtMaNH.getText().length() == 0
                || txtNgaySinh.getText().length() == 0) {
            DialogHelper.alert(this, "Không được để trống!");
            return;
        } else {
            try {
                dao.insert(nh);
                this.fillTable();
                this.clearForm();
                DialogHelper.alert(this, "Them moi thanh cong!");
            } catch (Exception e) {
                DialogHelper.alert(this, "them moi that bai!");
                e.printStackTrace();
            }
        }
    }

    void update() {
        Nguoihoc nh = getForm();
        try {
            dao.update(nh);
            this.fillTable();
            clearForm();
            DialogHelper.alert(this, "Cap nhat thanh cong!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cap nhat that bai!");
            e.printStackTrace();
        }
    }

    void delete() {
        String maNH = txtMaNH.getText();
        try {
            dao.delete(maNH);
            clearForm();
            fillTable();
            DialogHelper.alert(this, "Xóa người học thành công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Người học này vẫn đang trong khóa học");
        }
    }

    void timKiem() {
        this.fillTable();
        this.clearForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblTitle = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlList = new javax.swing.JPanel();
        lblMaNH = new javax.swing.JLabel();
        txtMaNH = new javax.swing.JTextField();
        lblHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        lblDienThoai = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblGhiChu = new javax.swing.JLabel();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        pnlEdit = new javax.swing.JPanel();
        pnlTimKiem = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNguoihoc = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 204));
        lblTitle.setText("QUẢN LÝ NGƯỜI HỌC");

        pnlList.setPreferredSize(new java.awt.Dimension(650, 350));

        lblMaNH.setText("Mã người học");

        lblHoTen.setText("Họ và tên");

        lblGioiTinh.setText("Giới tính");

        lblNgaySinh.setText("Ngày sinh");

        lblDienThoai.setText("Điện thoại");

        lblEmail.setText("Email");

        lblGhiChu.setText("Ghi chú");

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

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cuoi.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tien.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lui.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dau.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        buttonGroup1.add(rdonam);
        rdonam.setText("Nam");

        buttonGroup1.add(rdonu);
        rdonu.setText("Nữ");

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoTen)
                    .addComponent(txtMaNH)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListLayout.createSequentialGroup()
                        .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaNH)
                            .addComponent(lblHoTen)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblDienThoai)
                            .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlListLayout.createSequentialGroup()
                                .addComponent(rdonam)
                                .addGap(18, 18, 18)
                                .addComponent(rdonu)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail)
                            .addComponent(txtNgaySinh)
                            .addGroup(pnlListLayout.createSequentialGroup()
                                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail)
                                    .addComponent(lblNgaySinh))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlListLayout.createSequentialGroup()
                        .addComponent(btnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast))
                    .addGroup(pnlListLayout.createSequentialGroup()
                        .addComponent(lblGhiChu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMaNH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHoTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh)
                    .addComponent(lblNgaySinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdonam)
                    .addComponent(rdonu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addComponent(lblDienThoai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGhiChu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPrev)
                    .addComponent(btnFirst))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        tabs.addTab("DANH SÁCH", pnlList);

        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TÌM KIẾM", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        btnTimKiem.setText("Tìm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(btnTimKiem)
                .addContainerGap())
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem)))
        );

        tblNguoihoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ NH", "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "ĐIỆN THOẠI ", "EMAIL", "MA NV", "NGÀY ĐK"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguoihoc.setRowHeight(25);
        tblNguoihoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoihocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNguoihoc);

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("CẬP NHẬT", pnlEdit);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(lblTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        pre();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        firt();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblNguoihocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoihocMouseClicked
          if (evt.getClickCount() == 2) {
            this.row = tblNguoihoc.getSelectedRow();
            this.edit();
        }

    }//GEN-LAST:event_tblNguoihocMouseClicked

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
            java.util.logging.Logger.getLogger(NguoiHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoiHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoiHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoiHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NguoiHocJFrame().setVisible(true);
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
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaNH;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlList;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblNguoihoc;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNH;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
