/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Helper.DialogHelper;
import Helper.ShareHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author ADMIN
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public MainFrame() {
        initComponents();
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void actionPerformed(ActionEvent e) {
                lblDongHo1.setText(format.format(new Date()));
            }
        }).start();
        this.openWelcome();
        this.openLogin();
        //setDefaultCloseOperation(0);

    }

    private void openWelcome() {
        new ChaoJDialog(this, true).setVisible(true);
    }

    private void openLogin() {
        ShareHelper.logoff();
        new DangNhapDialog(this, true).setVisible(true);
    }

    private void logoff() {
        ShareHelper.logoff();
        this.openLogin();
    }

    private void exit() {
        if (DialogHelper.confirm(this, "Bạn có muốn thoát chương trình ?")) {

            System.exit(0);
        }
    }

    private void openAbout() {
        new GioiThieuDialog(this, true).setVisible(true);
    }

    private void openWebsite() {

    }

    private void chuyende() {
        ChuyenDeJFrame cd = new ChuyenDeJFrame();
        cd.setVisible(true);
    }

    private void nguoihoc() {
        NguoiHocJFrame nh = new NguoiHocJFrame();
        nh.setVisible(true);
    }

    private void khoahoc() {
        KhoaHocJFrame kh = new KhoaHocJFrame();
        kh.setVisible(true);
    }

    private void doipass() {
        DoimatkhauDialog pas = new DoimatkhauDialog();
        pas.setVisible(true);
    }

    private void hocvien() {
        HocVienJDialog hv = new HocVienJDialog(this, true);
        hv.setVisible(true);
    }

    private void thongke() {
        ThongKeJFrame tk = new ThongKeJFrame();
        tk.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDongHo = new javax.swing.JLabel();
        toolBar = new javax.swing.JToolBar();
        btnDangXuat = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnChuyenDe = new javax.swing.JButton();
        btnNguoiHoc = new javax.swing.JButton();
        btnKhoaHoc = new javax.swing.JButton();
        btnhocvien = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btnHuongDan = new javax.swing.JButton();
        lblNoiDung = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        lblDongHo1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuHeThong = new javax.swing.JMenu();
        mniDangNhap = new javax.swing.JMenuItem();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniDoiMatKhau = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mniKetThuc = new javax.swing.JMenuItem();
        mnuQuanLy = new javax.swing.JMenu();
        mniQLNguoiHoc = new javax.swing.JMenuItem();
        mniQLChuyenDe = new javax.swing.JMenuItem();
        mniQLKhoaHoc = new javax.swing.JMenuItem();
        mniQLNhanVien = new javax.swing.JMenuItem();
        mniHocVien = new javax.swing.JMenuItem();
        mnuThongKe = new javax.swing.JMenu();
        mniTKNguoiHoc = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniTKBangDem = new javax.swing.JMenuItem();
        mniTChuyende = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mniTKDoanhThu = new javax.swing.JMenuItem();
        mnuTroGiup = new javax.swing.JMenu();
        mniHuongDan = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mniGioiThieu = new javax.swing.JMenuItem();

        lblDongHo.setText("10:55 PM");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        toolBar.setRollover(true);
        toolBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Exit.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        toolBar.add(btnDangXuat);

        btnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Stop.png"))); // NOI18N
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });
        toolBar.add(btnKetThuc);
        toolBar.add(jSeparator6);

        btnChuyenDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Lists.png"))); // NOI18N
        btnChuyenDe.setText("Chuyên đề");
        btnChuyenDe.setFocusable(false);
        btnChuyenDe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChuyenDe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenDeActionPerformed(evt);
            }
        });
        toolBar.add(btnChuyenDe);

        btnNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Conference.png"))); // NOI18N
        btnNguoiHoc.setText("Người học");
        btnNguoiHoc.setFocusable(false);
        btnNguoiHoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNguoiHoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNguoiHocActionPerformed(evt);
            }
        });
        toolBar.add(btnNguoiHoc);

        btnKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Certificate.png"))); // NOI18N
        btnKhoaHoc.setText("Khóa học");
        btnKhoaHoc.setFocusable(false);
        btnKhoaHoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhoaHoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoaHocActionPerformed(evt);
            }
        });
        toolBar.add(btnKhoaHoc);

        btnhocvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Boy.png"))); // NOI18N
        btnhocvien.setText("Học Viên");
        btnhocvien.setFocusable(false);
        btnhocvien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnhocvien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnhocvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhocvienActionPerformed(evt);
            }
        });
        toolBar.add(btnhocvien);
        toolBar.add(jSeparator8);

        btnHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Globe.png"))); // NOI18N
        btnHuongDan.setText("Hướng dẫn");
        btnHuongDan.setFocusable(false);
        btnHuongDan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHuongDan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuongDanActionPerformed(evt);
            }
        });
        toolBar.add(btnHuongDan);

        lblNoiDung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N
        lblNoiDung.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Info.png"))); // NOI18N
        lblTrangThai.setText("Hệ quản lý đào tạo");

        lblDongHo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Alarm.png"))); // NOI18N
        lblDongHo1.setText("10:55 PM");

        mnuHeThong.setText("Hệ thống");

        mniDangNhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mniDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Key.png"))); // NOI18N
        mniDangNhap.setText("Đăng nhập");
        mniDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangNhapActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangNhap);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Exit.png"))); // NOI18N
        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangXuat);
        mnuHeThong.add(jSeparator1);

        mniDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        mniDoiMatKhau.setText("Đổi mật khẩu");
        mniDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDoiMatKhauActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDoiMatKhau);
        mnuHeThong.add(jSeparator2);

        mniKetThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mniKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Stop.png"))); // NOI18N
        mniKetThuc.setText("Kết thúc");
        mniKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKetThucActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniKetThuc);

        menuBar.add(mnuHeThong);

        mnuQuanLy.setText("Quản lý");

        mniQLNguoiHoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_MASK));
        mniQLNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Conference.png"))); // NOI18N
        mniQLNguoiHoc.setText("Người học");
        mniQLNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLNguoiHocActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLNguoiHoc);

        mniQLChuyenDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_MASK));
        mniQLChuyenDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Lists.png"))); // NOI18N
        mniQLChuyenDe.setText("Chuyên đề");
        mniQLChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLChuyenDeActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLChuyenDe);

        mniQLKhoaHoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_MASK));
        mniQLKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Certificate.png"))); // NOI18N
        mniQLKhoaHoc.setText("Khóa học");
        mniQLKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLKhoaHocActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLKhoaHoc);

        mniQLNhanVien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_MASK));
        mniQLNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User group.png"))); // NOI18N
        mniQLNhanVien.setText("Nhân viên");
        mniQLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLNhanVienActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLNhanVien);

        mniHocVien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.ALT_MASK));
        mniHocVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Boy.png"))); // NOI18N
        mniHocVien.setText("Học viên");
        mniHocVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHocVienActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniHocVien);

        menuBar.add(mnuQuanLy);

        mnuThongKe.setText("Thống kê");

        mniTKNguoiHoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        mniTKNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Clien list.png"))); // NOI18N
        mniTKNguoiHoc.setText("Lượng người học");
        mniTKNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKNguoiHocActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKNguoiHoc);
        mnuThongKe.add(jSeparator3);

        mniTKBangDem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        mniTKBangDem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Card file.png"))); // NOI18N
        mniTKBangDem.setText("Bảng điểm");
        mniTKBangDem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKBangDemActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKBangDem);

        mniTChuyende.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        mniTChuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Bar chart.png"))); // NOI18N
        mniTChuyende.setText("Điểm chuyên đề");
        mniTChuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTChuyendeActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTChuyende);
        mnuThongKe.add(jSeparator4);

        mniTKDoanhThu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        mniTKDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Dollar.png"))); // NOI18N
        mniTKDoanhThu.setText("Doanh thu ");
        mniTKDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKDoanhThuActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKDoanhThu);

        menuBar.add(mnuThongKe);

        mnuTroGiup.setText("Trợ giúp");

        mniHuongDan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mniHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Globe.png"))); // NOI18N
        mniHuongDan.setText("Hướng dẫn sử dụng");
        mniHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHuongDanActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniHuongDan);
        mnuTroGiup.add(jSeparator5);

        mniGioiThieu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        mniGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Brick house.png"))); // NOI18N
        mniGioiThieu.setText("Giới thiệu sản phẩm");
        mniGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGioiThieuActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniGioiThieu);

        menuBar.add(mnuTroGiup);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(lblDongHo1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNoiDung, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 352, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDongHo1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangNhapActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_mniDangNhapActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        // TODO add your handling code here:
        logoff();

    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoiMatKhauActionPerformed
        // TODO add your handling code here:
        doipass();

    }//GEN-LAST:event_mniDoiMatKhauActionPerformed

    private void mniKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKetThucActionPerformed
        // TODO add your handling code here:
        exit();

    }//GEN-LAST:event_mniKetThucActionPerformed

    private void mniQLNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLNguoiHocActionPerformed
        // TODO add your handling code here:
        nguoihoc();
    }//GEN-LAST:event_mniQLNguoiHocActionPerformed

    private void mniQLChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLChuyenDeActionPerformed
        // TODO add your handling code here:
        chuyende();


    }//GEN-LAST:event_mniQLChuyenDeActionPerformed

    private void mniQLKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLKhoaHocActionPerformed
        // TODO add your handling code here:
        khoahoc();
    }//GEN-LAST:event_mniQLKhoaHocActionPerformed

    private void mniQLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLNhanVienActionPerformed
        NhanVienJFrame nv = new NhanVienJFrame();
        nv.setVisible(true);

    }//GEN-LAST:event_mniQLNhanVienActionPerformed

    private void mniTKNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKNguoiHocActionPerformed
        // TODO add your handling code here:
        thongke();

    }//GEN-LAST:event_mniTKNguoiHocActionPerformed

    private void mniTKBangDemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKBangDemActionPerformed
        // TODO add your handling code here:
        thongke();

    }//GEN-LAST:event_mniTKBangDemActionPerformed

    private void mniTChuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTChuyendeActionPerformed
        // TODO add your handling code here:
        thongke();
    }//GEN-LAST:event_mniTChuyendeActionPerformed

    private void mniTKDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKDoanhThuActionPerformed
        // TODO add your handling code here:
        thongke();

    }//GEN-LAST:event_mniTKDoanhThuActionPerformed

    private void mniHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHuongDanActionPerformed
        // TODO add your handling code here:
        openAbout();
    }//GEN-LAST:event_mniHuongDanActionPerformed

    private void mniGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGioiThieuActionPerformed
        // TODO add your handling code here:
        openWebsite();
    }//GEN-LAST:event_mniGioiThieuActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        logoff();

    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        // TODO add your handling code here:
        exit();

    }//GEN-LAST:event_btnKetThucActionPerformed

    private void btnChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenDeActionPerformed
        // TODO add your handling code here:
        chuyende();
    }//GEN-LAST:event_btnChuyenDeActionPerformed

    private void btnNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNguoiHocActionPerformed
        // TODO add your handling code here:
        nguoihoc();
    }//GEN-LAST:event_btnNguoiHocActionPerformed

    private void btnKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoaHocActionPerformed
        // TODO add your handling code here:
        khoahoc();
    }//GEN-LAST:event_btnKhoaHocActionPerformed

    private void btnHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuongDanActionPerformed
        // TODO add your handling code here:
        openAbout();
    }//GEN-LAST:event_btnHuongDanActionPerformed

    private void btnhocvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhocvienActionPerformed
        // TODO add your handling code here:
        hocvien();
    }//GEN-LAST:event_btnhocvienActionPerformed

    private void mniHocVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHocVienActionPerformed
        // TODO add your handling code here:
        hocvien();
    }//GEN-LAST:event_mniHocVienActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChuyenDe;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnHuongDan;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnKhoaHoc;
    private javax.swing.JButton btnNguoiHoc;
    private javax.swing.JButton btnhocvien;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblDongHo1;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mniDangNhap;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniDoiMatKhau;
    private javax.swing.JMenuItem mniGioiThieu;
    private javax.swing.JMenuItem mniHocVien;
    private javax.swing.JMenuItem mniHuongDan;
    private javax.swing.JMenuItem mniKetThuc;
    private javax.swing.JMenuItem mniQLChuyenDe;
    private javax.swing.JMenuItem mniQLKhoaHoc;
    private javax.swing.JMenuItem mniQLNguoiHoc;
    private javax.swing.JMenuItem mniQLNhanVien;
    private javax.swing.JMenuItem mniTChuyende;
    private javax.swing.JMenuItem mniTKBangDem;
    private javax.swing.JMenuItem mniTKDoanhThu;
    private javax.swing.JMenuItem mniTKNguoiHoc;
    private javax.swing.JMenu mnuHeThong;
    private javax.swing.JMenu mnuQuanLy;
    private javax.swing.JMenu mnuThongKe;
    private javax.swing.JMenu mnuTroGiup;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

}
