/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.ChuyendeDAO;
import DAO.KhoahocDAO;
import Helper.Auth;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Helper.Xdate;
import Model.Chuyende;
import Model.Khoahoc;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class KhoaHocJFrame extends javax.swing.JFrame {

    int row = -1;
    KhoahocDAO dao = new KhoahocDAO();
    ArrayList<Khoahoc> kh = new ArrayList<>();

    /**
     * Creates new form KhoaHocJFrame
     */
    public KhoaHocJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        init();
    }

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
        fillTable();
        updateStatus();
        fillComboboxChuyenDe();
        setDefaultCloseOperation(2);
    }
    ChuyendeDAO cddao = new ChuyendeDAO();

    void fillComboboxChuyenDe() {
        DefaultComboBoxModel mol = (DefaultComboBoxModel) cbochuyende.getModel();
        mol.removeAllElements();
        List<Chuyende> list = cddao.selectAll();
        for (Chuyende cd : list) {
            mol.addElement(cd);
        }
    }

    void chonChuyenDe() {
        Chuyende cd = (Chuyende) cbochuyende.getSelectedItem();
        txtThoiLuong.setText(cd.getThoiLuong() + "");
        txtHocPhi.setText(cd.getHocPhi() + "");
        txtGhiChu.setText(cd.getTenCD());
        txtchuyende.setText(cd.getTenCD());
        this.fillTable();
        this.row = -1;
        this.updateStatus();
        tabs.setSelectedIndex(1);
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
        if (this.row < tblkhoahoc.getRowCount() - 1) {
            this.row++;
            edit();
        }
    }

    void last() {
        row = tblkhoahoc.getRowCount() - 1;
        edit();
    }

    void edit() {
        try {
            Integer maKH = (Integer) tblkhoahoc.getValueAt(this.row, 0);
            Khoahoc model = dao.selectById(maKH);
            if (model != null) {
                setForm(model);
                tabs.setSelectedIndex(0);
                updateStatus();
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi");
            e.printStackTrace();
        }
    }

    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblkhoahoc.getRowCount() - 1);

        txtchuyende.setEditable(!edit);
        btnInsert.setEnabled(!edit);
        btnUpdate.setEnabled(edit);
        btnDelete.setEnabled(edit);

        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
        txtnguoitao.setText(Auth.user.getMaNV());
    }

    void setForm(Khoahoc kh) {
        txtchuyende.setText(kh.getMaCD());
        txtNgayKG.setText(Xdate.toString(kh.getNgayKG(), "yyyy-MM-dd"));
        txtHocPhi.setText(kh.getHocPhi() + "");
        txtThoiLuong.setText(kh.getThoiLuong() + "");
        txtnguoitao.setText(kh.getMaNV());
        txtNgayTao.setText(Xdate.toString(kh.getNgayTao(), "yyyy-MM-dd"));
        txtGhiChu.setText(kh.getGhiChu());
    }

    Khoahoc getForm() {
        Khoahoc kh = new Khoahoc();
        Chuyende chuyenDe = (Chuyende) cbochuyende.getSelectedItem();

        kh.setMaCD(chuyenDe.getMaCD());
        kh.setHocPhi(Float.valueOf(txtHocPhi.getText()));
        kh.setMaNV(Auth.user.getMaNV());
        kh.setNgayKG(Xdate.toDate(txtNgayKG.getText(), "yyyy-MM-dd"));
        kh.setNgayTao(Xdate.now());
        kh.setGhiChu(txtGhiChu.getText());
        kh.setThoiLuong(Integer.valueOf(txtThoiLuong.getText()));
        return kh;
    }

    void fillTable() {
        DefaultTableModel mol = (DefaultTableModel) tblkhoahoc.getModel();
        mol.setRowCount(0);
        try {
            List<Khoahoc> list = dao.selectAll();
            for (Khoahoc kh : list) {
                Object[] row = {
                    kh.getMaKH(), kh.getMaCD(), kh.getThoiLuong(), kh.getHocPhi(), Xdate.toString(kh.getNgayKG(), "yyyy-MM-dd"), kh.getMaNV(),  Xdate.toString(kh.getNgayTao(), "yyyy-MM-dd")
                };
                mol.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn");
        }
    }

    void clearForm() {
        Khoahoc kh = new Khoahoc();
        setForm(kh);
        row = -1;
        updateStatus();
    }

    void update() {
        Khoahoc kh = getForm();
        kh.setMaKH((int) tblkhoahoc.getValueAt(row, 0));
        try {
            dao.update(kh);
            this.fillTable();
            clearForm();
            DialogHelper.alert(this, "Cap nhat thanh cong!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cap nhat that bai!");
            e.printStackTrace();
        }
    }

    void delete() {
        Integer makh = (Integer) tblkhoahoc.getValueAt(row, 0);
        try {
            dao.delete(makh);
            clearForm();
            fillTable();
            DialogHelper.alert(this, "Xóa khoa hoc thành công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Khong the xoa");
        }
    }

    void insert() {
        Khoahoc model = getForm();
        try {
            dao.insert(model);
            fillTable();
            clearForm();
            DialogHelper.alert(this, "Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Thêm thất bại");
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
        lblChuyenDe = new javax.swing.JLabel();
        lblNgayKG = new javax.swing.JLabel();
        txtNgayKG = new javax.swing.JTextField();
        lblHocPhi = new javax.swing.JLabel();
        lblThoiLuong = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblGhiChu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnHocVien = new javax.swing.JButton();
        txtHocPhi = new javax.swing.JTextField();
        txtThoiLuong = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        txtnguoitao = new javax.swing.JTextField();
        txtchuyende = new javax.swing.JTextField();
        pnlList = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblkhoahoc = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cbochuyende = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 204));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("QUẢN LÝ KHÓA HỌC");

        lblChuyenDe.setText("Chuyên đề");

        lblNgayKG.setText("Ngày khai giảng");

        lblHocPhi.setText("Học phí");

        lblThoiLuong.setText(" Thời lượng (giờ)");

        lblMaNV.setText("Người tạo");

        lblNgayTao.setText("Ngày tạo");

        lblGhiChu.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        btnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save as.png"))); // NOI18N
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

        btnHocVien.setText("Học viên");
        btnHocVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHocVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblChuyenDe)
                            .addComponent(lblHocPhi)
                            .addComponent(lblMaNV)
                            .addComponent(txtHocPhi, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(txtnguoitao)
                            .addComponent(txtchuyende))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                                .addComponent(lblNgayKG)
                                .addGap(177, 177, 177))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblThoiLuong)
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayKG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtThoiLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayTao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlEditLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(lblNgayTao)))))
                    .addComponent(jScrollPane1)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(lblGhiChu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(btnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHocVien, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)))
                .addContainerGap())
        );

        pnlEditLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtNgayKG, txtNgayTao, txtThoiLuong});

        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChuyenDe)
                    .addComponent(lblNgayKG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayKG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtchuyende, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHocPhi)
                    .addComponent(lblThoiLuong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgayTao)
                    .addComponent(lblMaNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnguoitao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGhiChu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPrev)
                    .addComponent(btnFirst)
                    .addComponent(btnHocVien))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        tabs.addTab("CẬP NHẬT", pnlEdit);

        tblkhoahoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ KH", "CHUYÊN ĐỀ", "THỜI LƯỢNG", "HỌC PHÍ", "KHAI GIẢNG", "TẠO BỞI", "NGÀY TẠO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblkhoahoc.setRowHeight(25);
        tblkhoahoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkhoahocMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblkhoahoc);

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab(" DANH SÁCH", pnlList);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chuyên đề"));

        cbochuyende.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbochuyende.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbochuyendeMouseClicked(evt);
            }
        });
        cbochuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbochuyendeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbochuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbochuyende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabs))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(lblTitle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnHocVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHocVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHocVienActionPerformed

    private void tblkhoahocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhoahocMouseClicked
        if (evt.getClickCount() == 2) {
            this.row = tblkhoahoc.getSelectedRow();
            this.edit();
        }

    }//GEN-LAST:event_tblkhoahocMouseClicked

    private void cbochuyendeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbochuyendeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbochuyendeMouseClicked

    private void cbochuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbochuyendeActionPerformed
        int index = cbochuyende.getSelectedIndex();
        if (index >= 0) {
            this.chonChuyenDe();
        }
    }//GEN-LAST:event_cbochuyendeActionPerformed

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
            java.util.logging.Logger.getLogger(KhoaHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhoaHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhoaHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhoaHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhoaHocJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnHocVien;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbochuyende;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblChuyenDe;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblHocPhi;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNgayKG;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblThoiLuong;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlList;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblkhoahoc;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtNgayKG;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtThoiLuong;
    private javax.swing.JTextField txtchuyende;
    private javax.swing.JTextField txtnguoitao;
    // End of variables declaration//GEN-END:variables
}
