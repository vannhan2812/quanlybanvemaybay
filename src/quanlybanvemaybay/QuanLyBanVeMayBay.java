/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybanvemaybay;

import Controller.ChuyenBayDAO;
import Controller.DBConnection;
import Controller.HangVeDAO;
import Controller.MayBayDAO;
import Controller.SanBayDAO;
import Controller.TuyenBayDAO;
import Model.ChuyenBay;
import Model.HangVe;
import Model.MayBay;
import Model.SanBay;
import Model.TuyenBay;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.logging.Logger;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.RootPaneUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Scoobydo
 */
public class QuanLyBanVeMayBay {
        JFrame fr;
        JTabbedPane tabbedPane;
        //BanVe       
        JPanel panelBanVe;
        JLabel lbBV_Title,lbBV_ttVe,lbBV_MaVe,lbBV_dsVe;
        JPanel panelBanVe_1,panelBanVe_2,panelBV_3_head,panelBV_3,panelBV_button;
        JTextField tfBV_MaVe,tfBV_MaTuyenBay,tfBV_SanBayDi,tfBV_SanBayDen,tfBV_NgayKhoiHanh,tfBV_GioKhoiHanh,tfBV_CMND,
                tfBV_TenKhachHang,tfBV_DienThoai,tfBV_SoGheTrong,tfBV_GiaTien,tfBV_TinhTrangGD,tfBV_CMND_tk
                ,tfBV_MaVe_tk, tfBV_SoLuong;
        JComboBox cbBV_TenHangVe,cbBV_MaChuyenBay;
        JButton btnBV_GiaoDich,btnBV_TimChuyenBay,btnBV_search;
        JScrollPane scPaneBV;
        JTable tableBV_Ve;
        
        //Tra cuu chuyen bay      
        JPanel panelTCCB,panelTCCB_button,panelTCCB_phai;
        JLabel lbTCCB_Title,lbTCCB_ThongTinChuyenBay,lbTCCB_dsChuyenBay;
        JPanel panelTCCB_Left,panelTCCB_Left1,panelTCCB_Left2;
        JTextField tfTCCB_MaChuyenBay,tfTCCB_SanBayDi,tfTCCB_SanBayDen;
        JComboBox cbTCCB_SanBayDi,cbTCCB_SanBayDen;
        JButton btnTCCB_Search;
        JTable tableTCCB_ChuyenBay;
        JScrollPane scPaneTCCB;
        JDateChooser dateTCCB_NgayKhoiHanh; 
        JDateChooser dateTCCB_NgayKetThuc; 
        
        // Quản lý chuyến bay
        JPanel panelQLChuyenBay;
        JPanel panelQLCB_Left,panelQLCB_Left1,panelQLCB_Left2,panelQLCB_button,panelQLCB_Right,panelQLCB_contain
                ,panelQLCB_HV_TG , panelQLCB_HangVe ,  panelQLCB_HangVe0, panelQLCB_HangVe1, panelQLCB_buttonHV, panelQLCB_SanBayTG, panelQLCB_SanBayTG0, 
                panelQLCB_SanBayTG1, panelQLCB_buttonSanBayTG;
        JLabel lbQLCB_Title,lbQLCB_ThongTinChuyenBay,lbQLCB_dsChuyenBay;
        JTextField tfQLCB_MaChuyenBay,tfQLCB_GioKhoiHanh,
                tfQLCB_SoGhe, tfQLCB_TGNghi, tfQLCB_GhiChu, tfQLCB_TGBay;
        JComboBox cbQLCB_MaTuyenBay,cbQLCB_SanBayDi,cbQLCB_SanBayDen,cbQLCB_MaMayBay
                ,cbQLCB_HangVe, cbQLCB_TenSanBay;
        JButton btnQLCB_Them,btnQLCB_Xoa,btnQLCB_Sua,btnQLCB_ThemHV, btnQLCB_XoaHV, btnQLCB_SuaHV , 
                btnQLCB_ThemSanBayTG, btnQLCB_XoaSanBayTG, btnQLCB_SuaSanBayTG;
        JTable tableQLCB_ChuyenBay,tableQLCB_HangVe, tableQLCB_SanBayTG;
        JScrollPane scPaneQLCB_ChuyenBay, scPaneQLCB_HangVe, scPaneQLCB_SanBayTG;
        JDateChooser dateQLCB_NgayKhoiHanh;
        ChuyenBayDAO chuyenBayDAO;
        ArrayList<ChuyenBay> listChuyenBay=null;
        DefaultTableModel modelChuyenBay;
        //Quản lý tuyến bay
        JPanel panelQLTuyenBay;
        JLabel lbQLTB_Title, lbQLTB_ttTuyenBay, lbQLTB_dsTuyenBay, lbQLTB_ThongBao;
        JPanel panelQLTB_Left, panelQLTB_Left1, panelQLTB_button, panelQLTB_Right;
        JTextField tfQLTB_MaTuyenBay;
        JComboBox cbQLTB_SanBayDi, cbQLTB_SanBayDen;
        JButton btnQLTB_Them, btnQLTB_Xoa,  btnQLTB_Sua;
        JTable tableQLTB_TuyenBay;
        JScrollPane scPaneQLTB_TuyenBay;
        TuyenBayDAO tuyenBayDAO;
        ArrayList<TuyenBay> listTuyenBay=null;
        DefaultTableModel modelTuyenBay;
        
        //Quản lý sân bay
        JPanel panelQLSanBay;
        JLabel lbQLSB_Title, lbQLSB_ttSanBay, lbQLSB_dsSanBay, lbQLSB_ThongBao;
        JPanel panelQLSB_Left, panelQLSB_Left1, panelQLSB_button, panelQLSB_Right;
        JTextField tfQLSB_MaSanBay, tfQLSB_TenSanBay,tfQLSB_TenThanhPho;
        JButton btnQLSB_Them, btnQLSB_Xoa, btnQLSB_Sua;
        JTable tableQLSB_SanBay;
        JScrollPane scPaneQLSB_SanBay;
        SanBayDAO sanBayDAO;
        ArrayList<SanBay> listSanBay=null;
        DefaultTableModel modelSanBay;
        // Quản lý máy bay
        JPanel panelQLMayBay;
        JLabel lbQLMB_Title, lbQLMB_ttMayBay, lbQLMB_dsMayBay;
        JPanel panelQLMB_Left, panelQLMB_Left1, panelQLMB_button, panelQLMB_Right;
        JTextField tfQLMB_MaMayBay, tfQLMB_TenMayBay,tfQLMB_SoLuongGhe;
        JButton btnQLMB_Them, btnQLMB_Xoa, btnQLMB_Sua;
        JTable tableQLMB_MayBay;
        JScrollPane scPaneQLMB_MayBay;
        MayBayDAO mayBayDAO;
        ArrayList<MayBay> listMayBay=null;
        DefaultTableModel modelMayBay;
        
        //Quản lý hạng vé.
        JPanel panelQLHangVe;
        JLabel lbQLHV_Title, lbQLHV_ttHangVe, lbQLHV_dsHangVe;
        JPanel panelQLHV_Left, panelQLHV_Left1, panelQLHV_button, panelQLHV_Right;
        JTextField tfQLHV_MaHangVe, tfQLHV_TenHangVe;
        JButton btnQLHV_Them, btnQLHV_Xoa, btnQLHV_Sua;
        JTable tableQLHV_HangVe;
        JScrollPane scPaneQLHV_HangVe;
        HangVeDAO hangVeDAO;
        ArrayList<HangVe> listHangVe;
        DefaultTableModel modelHangVe;
        //Quản lý đơn giá
        JPanel panelQLDonGia;
        JLabel lbQLDG_Title, lbQLDG_ttDonGia, lbQLDG_dsDonGia;
        JPanel panelQLDG_Left, panelQLDG_Left1, panelQLDG_button, panelQLDG_Right;
        JComboBox cbQLDG_MaTuyenBay, cbQLDG_SanBayDi,cbQLDG_SanBayDen, cbQLDG_TenHangVe;
        JTextField tfQLDG_DonGia;
        JButton btnQLDG_Them, btnQLDG_Xoa, btnQLDG_Sua;
        JTable tableQLDG_DonGia;
        JScrollPane scPaneQLDG_DonGia;
        
        //Quan lý khách hàng
        JPanel panelQLKhachHang;
        JLabel lbQLKH_Title, lbQLKH_ttKhachHang, lbQLKH_dsKhachHang;
        JPanel panelQLKH_Left, panelQLKH_Left1, panelQLKH_button, panelQLKH_Right;
        JTextField tfQLKH_MaKhachHang, tfQLKH_TenKhachHang,tfQLKH_CMND, tfQLKH_SDT;
        JButton btnQLKH_Xoa, btnQLKH_Sua;
        JTable tableQLKH_KhachHang;
        JScrollPane scPaneQLKH_KhachHang;
        
        //Quản lý nhân viên
        JPanel panelQLNhanVien;
        JLabel lbQLNV_Title, lbQLNV_ttNhanVien, lbQLNV_dsNhanVien;
        JPanel panelQLNV_Left, panelQLNV_Left1, panelQLNV_button, panelQLNV_Right;
        JTextField tfQLNV_MaNhanVien, tfQLNV_TenNhanVien;
        JButton btnQLNV_Them, btnQLNV_Xoa, btnQLNV_Sua;
        JTable tableQLNV_NhanVien;
        JScrollPane scPaneQLNV_NhanVien;
        
        //Thay đổi quy định
        JPanel panelThayDoiQD;
        JLabel lbTDQD_Title;
        JPanel panelTDQD_1, panelTDQD_2, panelTDQD_mid;
        JTextField tfTDQD_tgBayToiThieu, tfTDQD_SoSanBayTgToiDa, tfTDQD_tgDungToiThieu, 
                tfTDQD_tgDungToiDa, tfTDQD_tgChamNhatDatVe, tfTDQD_tgChamNhatHuyVe;
        JButton btnTDQD_Sua;
        
        //Quản lý tài khoản
        JPanel panelQuanlyTK;
        JLabel lbQLTK_Title; 
        JPanel panelQLTK_contain, panelQLTK_DangXuat, pannelQLTK_1, pannelQLTK_2, panelQLTK_mid;
        JButton btnQLTK_DangXuat, btnQLTK_Reset, btnQLTK_SignUp;
        JTextField tfQLTK_Username;
        JPasswordField pfQLTK_Pass, pfQLTK_NewPass;
        
    public QuanLyBanVeMayBay() throws SQLException
    {
        fr=new JFrame("Quản lý bán vé máy bay"); 
        fr.setSize(1083,640);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        tabbedPane = new JTabbedPane();
        
        banVe();
        TraCuuChuyenBay();
        QuanLyChuyenBay();
        QuanLyTuyenBay();
        QuanLySanBay();
        QuanLyMayBay();
        QuanLyHangVe();
        QuanLyDonGia();
        QuanLyKhachHang();
        QuanLyNhanVien();
        ThayDoiQuyDinh();
        QuanLyTaiKhoan();
        
        //SANBAY
        loadTableSanBay();
        
        //TUYENBAY
        loadComBoxQLTB();
        loadTableTuyenBay();
        loadTableChuyenBay();
        
        tabbedPane.addTab("Bán vé", panelBanVe);
        tabbedPane.addTab("Tra cứu chuyến bay", panelTCCB);
        tabbedPane.addTab("Quản lý chuyến bay", panelQLChuyenBay);
        tabbedPane.addTab("Quản lý tuyến bay", panelQLTuyenBay);
        tabbedPane.addTab("Quản lý sân bay", panelQLSanBay);
        tabbedPane.addTab("Quản lý máy bay", panelQLMayBay);
        tabbedPane.addTab("Quản lý hạng vé", panelQLHangVe);
        tabbedPane.addTab("Quản lý đơn giá", panelQLDonGia);
        tabbedPane.addTab("Quản lý khách hàng", panelQLKhachHang);
        tabbedPane.addTab("Quản lý nhân viên", panelQLNhanVien);
        tabbedPane.addTab("Thay đổi quy định", panelThayDoiQD);
        tabbedPane.addTab("Quản lý tài khoản", panelQuanlyTK);
        
        fr.add(tabbedPane);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
    public static void main(String[] args) throws SQLException {
        new QuanLyBanVeMayBay();
    }
    //------------- FUNCTION--------------------------//
    //..............SANBAY............................//
    public void loadTableSanBay() throws SQLException{
        //Clear table
        tableQLSB_SanBay.setModel(new DefaultTableModel(null,new String[]{"Mã sân bay","Tên sân bay","Tên thành phố"}));
        sanBayDAO = new SanBayDAO();
        listSanBay=sanBayDAO.getListSanBay();   
        modelSanBay = (DefaultTableModel)tableQLSB_SanBay.getModel();
        modelSanBay.setColumnIdentifiers(new Object[]{
            "Mã sân bay","Tên sân bay","Tên thành phố"
        });
        
        for(SanBay sb:listSanBay){
             modelSanBay.addRow(new Object[]{
             sb.getMaSanBay(),sb.getTenSanBay(),sb.getTenThanhPho()
        });
        }
    }
    public void addSanBay() throws SQLException{
        sanBayDAO = new SanBayDAO();
        int id = sanBayDAO.maxMaSanBay();
        String maSanBay = String.valueOf(id+1);
        String tenSanBay= tfQLSB_TenSanBay.getText();
        String tenThanhPho=tfQLSB_TenThanhPho.getText();
        Boolean result = sanBayDAO.themSanBay(maSanBay,tenSanBay, tenThanhPho);
        if(result==true)
            lbQLSB_ThongBao.setText("Thành công!");
        else
            lbQLSB_ThongBao.setText("Thất bại!");
        loadTableSanBay();
        loadComBoxQLTB();
    }
    public void deleteSanBay() throws SQLException{
        
        sanBayDAO = new SanBayDAO();
        String maSanBay= tfQLSB_MaSanBay.getText();
        if(maSanBay.isEmpty()==true){
            JOptionPane.showMessageDialog(null, "Chọn sân bay cần xóa!");
        }
        else{   //Hiển thị thông báo xác nhận
            int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc muốn xóa?", "Alert",JOptionPane.YES_NO_OPTION);
            if(n==JOptionPane.YES_OPTION){
                Boolean result = sanBayDAO.xoaSanBay(maSanBay);
                if(result==true){
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    tfQLSB_MaSanBay.setText(null);
                }
                else
                    JOptionPane.showMessageDialog(null, "Xóa thất bại!");
            }
        }
        loadTableSanBay();
        loadComBoxQLTB();
        
    }
    public void updateSanBay() throws SQLException{
        sanBayDAO = new SanBayDAO();
        String maSanBay= tfQLSB_MaSanBay.getText();
        String tenSanBay= tfQLSB_TenSanBay.getText();
        String trnThanhPho=tfQLSB_TenThanhPho.getText();
        if(maSanBay.isEmpty()==true){
            JOptionPane.showMessageDialog(null, "Chọn sân bay cần sửa!");
        }
        else{  
                Boolean result = sanBayDAO.suaSanBay(maSanBay, tenSanBay, trnThanhPho);
                if(result==true){
                    JOptionPane.showMessageDialog(null, "Sửa thành công!");
                }
                else
                    JOptionPane.showMessageDialog(null, "Sửa thất bại!");
            
        }
        loadTableSanBay();
        loadComBoxQLTB();
    }
    //..............TUYENBAY..................
    public void loadComBoxQLTB() throws SQLException{
        cbQLTB_SanBayDi.removeAllItems();
        cbQLTB_SanBayDen.removeAllItems();
        sanBayDAO = new SanBayDAO();
        listSanBay = sanBayDAO.getListSanBay();
         for(SanBay sb:listSanBay){
             cbQLTB_SanBayDi.addItem(sb);
             cbQLTB_SanBayDen.addItem(sb);
             
        }
        }
    public void loadTableTuyenBay() throws SQLException{
        //Set data in tabel null
        tableQLTB_TuyenBay.setModel(new DefaultTableModel(null,new String[]{"Mã sân bay","Tên sân bay","Tên thành phố"}));
        tuyenBayDAO = new TuyenBayDAO();
        listTuyenBay=tuyenBayDAO.getListTuyenBay();   
        modelTuyenBay = (DefaultTableModel)tableQLTB_TuyenBay.getModel();
        modelTuyenBay.setColumnIdentifiers(new Object[]{
            "Mã tuyến bay","Sân bay đi","Sân bay đến"
        });
        
        for(TuyenBay tb:listTuyenBay){
            
             modelTuyenBay.addRow(new Object[]{
             tb.getMaTuyenBay(),sanBayDAO.getSanBayByID(tb.getMaSanBayDi()),sanBayDAO.getSanBayByID(tb.getMaSanBayDen())
        });
        }
    }
    public void addTuyenBay() throws SQLException{
        tuyenBayDAO = new TuyenBayDAO();
        int id = tuyenBayDAO.maxMaTuyenBay();
        String maTuyenBay = String.valueOf(id+1);
        String maSanBayDi= ((SanBay)cbQLTB_SanBayDi.getSelectedItem()).getMaSanBay();
        String maSanBayDen=((SanBay)cbQLTB_SanBayDen.getSelectedItem()).getMaSanBay();
        if(maSanBayDi == null ? maSanBayDen == null : maSanBayDi.equals(maSanBayDen))
        {
            lbQLTB_ThongBao.setText("Thất bại , sân bay đi phải khác sân bay đến !!!");
        }
        else
        {
            Boolean result = tuyenBayDAO.themTuyenBay(maTuyenBay,maSanBayDi, maSanBayDen);
            if(result==true)
                JOptionPane.showMessageDialog(null, "Thêm thành công!");
            else
                JOptionPane.showMessageDialog(null, "Thêm thất bại!");
            loadTableTuyenBay();
            loadComBoxQLTB();
        }
   
    }
    public void deleteTuyenBay() throws SQLException
    {
        tuyenBayDAO = new TuyenBayDAO();
        String maTuyenBay = tfQLTB_MaTuyenBay.getText();
        if(maTuyenBay.isEmpty()==true)
        {
            JOptionPane.showMessageDialog(null, "Chọn tuyến bay cần xóa!");
        }
        else{
            int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc muốn xóa?", "Alert",JOptionPane.YES_NO_OPTION);
            if(n==JOptionPane.YES_OPTION){
                Boolean result = tuyenBayDAO.xoaTuyenBay(maTuyenBay);
                if(result==true){
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    tfQLTB_MaTuyenBay.setText(null);
                }
                else
                    JOptionPane.showMessageDialog(null, "Xóa thất bại!");
            }
        }
        loadTableTuyenBay();
        loadComBoxQLTB();
        
    }
    public void updateTuyenBay() throws SQLException
    {
        tuyenBayDAO = new TuyenBayDAO();
        String maTuyenBay = tfQLTB_MaTuyenBay.getText();
        String maSanBayDi = cbQLTB_SanBayDi.getSelectedItem().toString();
        String maSanBayDen = cbQLTB_SanBayDen.getSelectedItem().toString();
        if(maTuyenBay.isEmpty()==true)
        {
            JOptionPane.showMessageDialog(null, "Chọn tuyến bay cần sửa!");   
        }
        else
        {
            Boolean result = tuyenBayDAO.suaTuyenBay(maTuyenBay, maSanBayDi, maSanBayDen);
            if(result==true)
            {
                JOptionPane.showMessageDialog(null, "Sửa thành công!");
            }
            else JOptionPane.showMessageDialog(null, "Sửa thất bại!");
        }
    }
     //..............HANG VE...................//
    public void loadTableHangVe() throws SQLException
    {
        tableQLHV_HangVe.setModel(new DefaultTableModel(null,new String[]{"Mã hạng vé","Tên hạng vé"}));
        hangVeDAO = new HangVeDAO();
        listHangVe=hangVeDAO.getListHangVe();
        modelHangVe = (DefaultTableModel)tableQLHV_HangVe.getModel();
        modelHangVe.setColumnIdentifiers(new Object[]{
            "Mã hạng vé","Tên hạng vé"
        });
        for(HangVe hv:listHangVe)
        {
            modelHangVe.addRow(new Object[]{
                hv.getMaHangVe(),hv.getTenHangVe()
            });
        }
    }
    public void addHangVe() throws SQLException
    {
        hangVeDAO = new HangVeDAO();
        int id = hangVeDAO.maxMaHangVe();
        String maHangVe = String.valueOf(id+1);
        String tenHangVe = tfQLHV_TenHangVe.getText();
        boolean result = hangVeDAO.themHangVe(maHangVe, tenHangVe);
        if(result==true) JOptionPane.showMessageDialog(null, "Thêm thành công!");
        else JOptionPane.showMessageDialog(null, "Thêm thất bại!");
        loadTableHangVe();
    }
    public void deleteHangVe() throws SQLException
    {
        hangVeDAO = new HangVeDAO();
        String maHangVe = tfQLHV_MaHangVe.getText();
        if(maHangVe.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Chọn hạng vé cần xóa");
        }
        else{
            int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa","Alert",JOptionPane.YES_NO_OPTION);
            if(n==JOptionPane.YES_OPTION)
            {
                
                boolean result = hangVeDAO.xoaHangVe(maHangVe);
                if(result==true){
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                    tfQLHV_MaHangVe.setText(null);
                }else JOptionPane.showMessageDialog(null, "Xóa thất bại");
            }
           
        }
        loadTableHangVe();
        
    }
    public void updateHangVe() throws SQLException
    {
        hangVeDAO = new HangVeDAO();
        String maHangVe = tfQLHV_MaHangVe.getText();
        String tenHangVe = tfQLHV_TenHangVe.getText();
        if(maHangVe.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Chọn hạng vé cần sửa");
        }
        else{
            boolean result = hangVeDAO.suaHangVe(maHangVe, tenHangVe);
            if(result==true) JOptionPane.showMessageDialog(null, "Sửa thành công");
            else JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
        loadTableHangVe();
        
    }
    //..............MAYBAY..................//
    public void loadTableMayBay() throws SQLException
    {
        //clear table
        tableQLMB_MayBay.setModel(new DefaultTableModel(null,new String[]{"Mã máy bay","Tên máy bay",
            "Số lượng ghế"}));
        mayBayDAO = new MayBayDAO();
        listMayBay = mayBayDAO.getListMayBay();
        modelMayBay=(DefaultTableModel)tableQLMB_MayBay.getModel();
        modelMayBay.setColumnIdentifiers(new Object[]{
            "Mã máy bay","Tên máy bay","Số lượng ghế"
        });
        for(MayBay mb:listMayBay)
        {
            modelMayBay.addRow(new Object[]{
                mb.getMaMayBay(),mb.getTenMaybay(),mb.getSoluongGhe()
            });
        }
    }
    public void addMayBay() throws SQLException
    {
        mayBayDAO = new MayBayDAO();
        int id = mayBayDAO.maxMaMayBay();
        String maMayBay = String.valueOf(id+1);
        String tenMayBay = tfQLMB_TenMayBay.getText();
        int soluongGhe = Integer.parseInt(tfQLMB_SoLuongGhe.getText());
        boolean result = mayBayDAO.themMayBay(maMayBay, tenMayBay, soluongGhe);
        if(result) JOptionPane.showMessageDialog(null, "Thêm thành công");
        else JOptionPane.showMessageDialog(null, "Thêm thất bại");
        loadTableMayBay();  
    }
    public void deleteMayBay() throws SQLException
    {
        mayBayDAO = new MayBayDAO();
        String maMayBay = tfQLMB_MaMayBay.getText();
        if(maMayBay.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Chọn máy bay cần xóa");
        }
        else{
            int n = JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn xóa  ? ","Alert",JOptionPane.YES_NO_OPTION);
            if(n==JOptionPane.YES_OPTION)
            {
                boolean result = mayBayDAO.xoaMayBay(maMayBay);
                if(result)
                {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    tfQLMB_MaMayBay.setText(null);
                }
                else  JOptionPane.showMessageDialog(null, "Xóa không thành công!");
            }                    
        }
        loadTableMayBay();
    }
    public void updateMayBay() throws SQLException
    {
        mayBayDAO = new MayBayDAO();
        String maMayBay = tfQLMB_MaMayBay.getText();
        String tenMayBay=tfQLMB_TenMayBay.getText();
        int soluongGhe = Integer.parseInt(tfQLMB_SoLuongGhe.getText());
        if(maMayBay.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Chọn máy bay cần sửa");
        }
        else
        {
            boolean result = mayBayDAO.suaMayBay(maMayBay, tenMayBay, soluongGhe);
            if(result==true) JOptionPane.showMessageDialog(null, "Sửa thành công!");
            else JOptionPane.showMessageDialog(null, "Sửa thất bại !");
        }
        loadTableMayBay();
    }
    
    //..............CHUYENBAY..................//
      public void loadTableChuyenBay() throws SQLException{
        //Clear table
        tableQLCB_ChuyenBay.setModel(new DefaultTableModel(null,new String[]{"Mã chuyến bay","Tên tuyến bay",
            "Mã máy bay", "Ngày khởi hành", "Giờ khởi hành","Thời gian bay"}));
        
        chuyenBayDAO = new ChuyenBayDAO();
        listChuyenBay=chuyenBayDAO.getListChuyenBay();   
        modelChuyenBay = (DefaultTableModel)tableQLCB_ChuyenBay.getModel();
        modelChuyenBay.setColumnIdentifiers(new Object[]{
            "Mã chuyến bay","Tên tuyến bay",
            "Mã máy bay", "Ngày khởi hành", "Giờ khởi hành","Thời gian bay"
        });
        
        for(ChuyenBay cb:listChuyenBay){
             modelChuyenBay.addRow(new Object[]{
             cb.getMaChuyenBay(),cb.getMaTuyenBay(),cb.getMaMayBay(),cb.getNgayKhoiHanh(),cb.getGioKhoiHanh(),cb.getThoiGianBay()
        });
        }
    }
    //_________________________________________________//
    public void Print(JTable table,String head) {
        MessageFormat header = new MessageFormat(head);
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try {
         table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException ep) {
         JOptionPane.showMessageDialog(null, "Error "+head.toLowerCase()+"!");
        }
       }
    public void banVe()
    {
        // Tab bán vé
        panelBanVe = new JPanel();
        panelBanVe.setLayout(new BorderLayout(10,10));
        
        panelBanVe.setBorder(new MatteBorder(null));
        lbBV_Title = new JLabel("BÁN VÉ MÁY BAY");
        lbBV_Title.setFont(new Font("Tahoma", 1, 24));
        lbBV_Title.setOpaque(true);
        lbBV_Title.setBackground(new Color(102, 255, 102));
        lbBV_Title.setForeground(new Color(0,0,255));
        lbBV_Title.setHorizontalAlignment(JLabel.CENTER);
        panelBanVe.add(lbBV_Title,BorderLayout.PAGE_START); 
        //Cột bên trái
        panelBanVe_1= new JPanel();
        panelBanVe_1.setLayout(new GridLayout(16,2,0,5));
        panelBanVe_1.setPreferredSize(new Dimension(300,600));
        lbBV_ttVe = new JLabel("* Thông tin vé:");
        lbBV_ttVe.setFont(new Font("Tahoma", 3, 11));
        panelBanVe_1.add(lbBV_ttVe);
        panelBanVe_1.add(new Label());
        lbBV_MaVe = new JLabel("Mã vé");
        lbBV_MaVe.setFont(new Font("Tahoma", 1, 11));
        panelBanVe_1.add(lbBV_MaVe);
         tfBV_MaVe = new JTextField("Mã vé");
        tfBV_MaVe.setEditable(false);
        panelBanVe_1.add(tfBV_MaVe);
        
        panelBanVe_1.add(new JLabel("Mã chuyến bay"));
        cbBV_MaChuyenBay = new JComboBox();
        panelBanVe_1.add(cbBV_MaChuyenBay);
        panelBanVe_1.add(new JLabel("Mã tuyến bay"));
        tfBV_MaTuyenBay = new JTextField("Mã tuyến bay");
        panelBanVe_1.add(tfBV_MaTuyenBay);
        tfBV_MaTuyenBay.setEditable(false);
        panelBanVe_1.add(new JLabel("Sân bay đi"));
        tfBV_SanBayDi= new JTextField("San Bay Di");
        tfBV_SanBayDi.setEditable(false);
        panelBanVe_1.add(tfBV_SanBayDi);
        panelBanVe_1.add(new JLabel("Sân bay đến"));
        tfBV_SanBayDen= new JTextField("San Bay đến");
        tfBV_SanBayDen.setEditable(false);
        panelBanVe_1.add(tfBV_SanBayDen);
        panelBanVe_1.add(new JLabel("Ngày khởi hành"));
        tfBV_NgayKhoiHanh= new JTextField("Ngày khởi hành");
        tfBV_NgayKhoiHanh.setEditable(false);
        panelBanVe_1.add(tfBV_NgayKhoiHanh);
        panelBanVe_1.add(new JLabel("Giờ khởi hành"));
        tfBV_GioKhoiHanh= new JTextField("Giờ khởi hành");
        tfBV_GioKhoiHanh.setEditable(false);
        panelBanVe_1.add(tfBV_GioKhoiHanh);
        panelBanVe_1.add(new JLabel("CMND"));
        tfBV_CMND= new JTextField("CMND");
        panelBanVe_1.add(tfBV_CMND);
        panelBanVe_1.add(new JLabel("Tên khách hàng"));
        tfBV_TenKhachHang = new JTextField("Tên khách hàng");
        panelBanVe_1.add(tfBV_TenKhachHang);
        panelBanVe_1.add(new JLabel("Điện thoại"));
        tfBV_DienThoai= new JTextField("Điện thoại");
        panelBanVe_1.add(tfBV_DienThoai);
        panelBanVe_1.add(new JLabel("Tên hạng vé"));
        cbBV_TenHangVe= new JComboBox();
        panelBanVe_1.add(cbBV_TenHangVe);
        panelBanVe_1.add(new JLabel("Số ghế trống"));
        tfBV_SoGheTrong= new JTextField("Số ghế trống");
        tfBV_SoGheTrong.setEditable(false);
        panelBanVe_1.add(tfBV_SoGheTrong);
        panelBanVe_1.add(new JLabel("Giá tiền (VND)"));
        tfBV_GiaTien= new JTextField("Giá tiền");
        tfBV_GiaTien.setEditable(false);
        panelBanVe_1.add(tfBV_GiaTien);
        panelBanVe_1.add(new JLabel("Tình trạng giao dịch"));
        tfBV_TinhTrangGD= new JTextField("Tình trạng giao dịch");
        tfBV_TinhTrangGD.setEditable(false);
        panelBanVe_1.add(tfBV_TinhTrangGD);
        panelBanVe_1.add(new JLabel("Số lượng"));
        tfBV_SoLuong = new JTextField();
        panelBanVe_1.add(tfBV_SoLuong);
        panelBanVe.add(panelBanVe_1, BorderLayout.LINE_START);
        
        //Cột bên phải
        panelBanVe_2= new JPanel();
        panelBanVe_2.setLayout(new BorderLayout(10,10));
        //Hàng tìm kiếm
        panelBV_3_head = new JPanel();
        panelBV_3_head.setLayout(new GridLayout(2, 1));
        lbBV_dsVe = new JLabel("* Danh sách vé:");
        lbBV_dsVe.setFont(new Font("Tahoma", 3, 11));
        panelBV_3_head.add(lbBV_dsVe);
        panelBV_3=new JPanel();
        panelBV_3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBV_3.add(new JLabel("CMND"));
        tfBV_CMND_tk = new JTextField("nhập CMND");
        tfBV_CMND_tk.setPreferredSize(new Dimension(200,30));
        panelBV_3.add(tfBV_CMND_tk);
        panelBV_3.add(new JLabel("Mã vé"));
        tfBV_MaVe_tk = new JTextField("nhập Mã vé");
        tfBV_MaVe_tk.setPreferredSize(new Dimension(200,30));
        panelBV_3.add(tfBV_MaVe_tk);
        btnBV_search = new JButton("Tìm kiếm");
        btnBV_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/search2.png")));
        panelBV_3.add(btnBV_search);
        panelBV_3_head.add(panelBV_3);
        panelBanVe_2.add(panelBV_3_head,BorderLayout.PAGE_START);
        //Table view
          tableBV_Ve=new JTable();  
          tableBV_Ve.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
               
            },
            new String [] {
                "Mã vé", "Tên khách hàng", "CMND", "Mã chuyến bay","Tên hạng vé", "Giá tiền","Tình trạng giao dịch",
                "Tiền đã đóng","Ngày GD", "Ngày quá hạn"
            }
        ) 
          {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        tableBV_Ve.getColumnModel().getColumn(1).setMinWidth(95);
        tableBV_Ve.getColumnModel().getColumn(3).setMinWidth(90);
        tableBV_Ve.getColumnModel().getColumn(6).setMinWidth(120);
        tableBV_Ve.getColumnModel().getColumn(9).setMinWidth(80);
        tableBV_Ve.getColumnModel().getColumn(4).setMinWidth(80);
        tableBV_Ve.getColumnModel().getColumn(7).setMinWidth(75);
        tableBV_Ve.getColumnModel().getColumn(8).setMinWidth(75);
        tableBV_Ve.setBounds(30,40,200,300);  
        scPaneBV=new JScrollPane(tableBV_Ve); 
        panelBanVe_2.add(scPaneBV,BorderLayout.CENTER);
        //Hàng button
        panelBV_button = new JPanel();
        panelBV_button.setLayout(new GridLayout(1, 6, 15, 15));
        btnBV_GiaoDich = new JButton("Giao dịch");
        btnBV_GiaoDich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/sell.png")));
        btnBV_TimChuyenBay = new JButton("Tìm chuyến bay");
        btnBV_TimChuyenBay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/search.png")));
        panelBV_button.add(new JLabel());
        panelBV_button.add(btnBV_GiaoDich);
        panelBV_button.add(btnBV_TimChuyenBay);
        panelBV_button.add(new  JLabel());
        panelBV_button.setPreferredSize(new Dimension(300, 35));
        panelBanVe_2.add(panelBV_button,BorderLayout.PAGE_END);
        panelBanVe.add(panelBanVe_2, BorderLayout.CENTER);
    }
    public void TraCuuChuyenBay()
    {
        panelTCCB = new JPanel();
        panelTCCB.setLayout(new BorderLayout(10,10));
        lbTCCB_Title = new JLabel("TRA CỨU CHUYẾN BAY");
        lbTCCB_Title.setFont(new Font("Tahoma", 1, 24));
        lbTCCB_Title.setOpaque(true);
        lbTCCB_Title.setBackground(new Color(102, 255, 102));
        lbTCCB_Title.setForeground(new Color(0,0,255));
        lbTCCB_Title.setHorizontalAlignment(JLabel.CENTER);
        panelTCCB.add(lbTCCB_Title,BorderLayout.PAGE_START); 
        //Cot Trai
        panelTCCB_Left=new JPanel();   
        panelTCCB_Left.setLayout(new BorderLayout(10, 10));
        panelTCCB_Left.setPreferredSize(new Dimension(300,600));
        panelTCCB_Left1 = new  JPanel();
        panelTCCB_Left1.setLayout(new GridLayout(15, 2, 0, 5));
        lbTCCB_ThongTinChuyenBay = new JLabel("* Thông tin chuyến bay");
        lbTCCB_ThongTinChuyenBay.setFont(new  Font("Tahoma", 3, 11));
        tfTCCB_MaChuyenBay = new JTextField("Ma chuyen bay");
        cbTCCB_SanBayDi=new JComboBox();
        cbTCCB_SanBayDen=new JComboBox();
        dateTCCB_NgayKhoiHanh = new JDateChooser();
        dateTCCB_NgayKetThuc = new JDateChooser();
        panelTCCB_Left1.add(lbTCCB_ThongTinChuyenBay);
        panelTCCB_Left1.add(new JLabel());
        panelTCCB_Left1.add(new JLabel("Mã chuyến bay"));
        panelTCCB_Left1.add(tfTCCB_MaChuyenBay);
        panelTCCB_Left1.add(new JLabel("Sân bay đi"));
        panelTCCB_Left1.add(cbTCCB_SanBayDi);
        panelTCCB_Left1.add(new JLabel("Sân bay đến"));
        panelTCCB_Left1.add(cbTCCB_SanBayDen);
        panelTCCB_Left1.add(new JLabel("Ngày khởi hành"));
        panelTCCB_Left1.add(dateTCCB_NgayKhoiHanh);
        panelTCCB_Left1.add(new JLabel("đến"));
        panelTCCB_Left1.add(dateTCCB_NgayKetThuc);
        panelTCCB_Left1.add(new JLabel());
        //nut 
        panelTCCB_button = new JPanel();
        panelTCCB_button.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnTCCB_Search = new JButton("Tìm kiếm");
        btnTCCB_Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/search2.png")));
        panelTCCB_button.add(btnTCCB_Search);
        panelTCCB_Left1.add(panelTCCB_button);
        panelTCCB_Left1.add(new JLabel());
        panelTCCB_Left1.add(new JLabel());
        panelTCCB_Left1.add(new JLabel());
        
        panelTCCB_Left.add(panelTCCB_Left1,BorderLayout.CENTER);
        //Table
        tableTCCB_ChuyenBay=new JTable();  
          tableTCCB_ChuyenBay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã chuyến bay", "Ngày khởi hành", "Giờ khởi hành", "Thời gian bay"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneTCCB = new JScrollPane(tableTCCB_ChuyenBay);
        panelTCCB_phai = new JPanel();
        panelTCCB_phai.setLayout(new BorderLayout(20, 20));
        lbTCCB_dsChuyenBay=new JLabel("Danh sách chuyến bay");
        lbTCCB_dsChuyenBay.setFont(new  Font("Tahoma", 3, 11));
        panelTCCB_phai.add(lbTCCB_dsChuyenBay,BorderLayout.PAGE_START);
        panelTCCB_phai.add(scPaneTCCB,BorderLayout.CENTER);
        
        panelTCCB.add(panelTCCB_Left,BorderLayout.LINE_START); 
        panelTCCB.add(panelTCCB_phai,BorderLayout.CENTER); 
    }
    public  void QuanLyChuyenBay(){
        panelQLChuyenBay = new JPanel();
        panelQLChuyenBay.setLayout(new BorderLayout(10, 10));
        //Title
        lbQLCB_Title = new JLabel("QUẢN LÝ CHUYẾN BAY");
        lbQLCB_Title.setFont(new Font("Tahoma", 1, 24));
        lbQLCB_Title.setOpaque(true);
        lbQLCB_Title.setBackground(new Color(102, 255, 102));
        lbQLCB_Title.setForeground(new Color(0,0,255));
        lbQLCB_Title.setHorizontalAlignment(JLabel.CENTER);
        panelQLChuyenBay.add(lbQLCB_Title,BorderLayout.PAGE_START);
        //Cot trai
        panelQLCB_Left = new JPanel();
        panelQLCB_Left.setLayout(new BorderLayout(10, 10));
        panelQLCB_Left.setPreferredSize(new Dimension(300,600));
        lbQLCB_ThongTinChuyenBay = new JLabel("* Thông tin chuyến bay");
        lbQLCB_ThongTinChuyenBay.setFont(new  Font("Tahoma", 3, 11));
        panelQLCB_Left.add(lbQLCB_ThongTinChuyenBay,BorderLayout.PAGE_START);
        panelQLCB_Left1 = new JPanel();
        panelQLCB_Left1.setLayout(new GridLayout(12, 2, 0, 5));
        panelQLCB_Left1.add(new JLabel("Mã chuyến bay"));
        tfQLCB_MaChuyenBay = new JTextField("Mã chuyến bay");
        tfQLCB_MaChuyenBay.setEditable(false);
        panelQLCB_Left1.add(tfQLCB_MaChuyenBay);
        panelQLCB_Left1.add(new JLabel("Mã tuyến bay"));
        cbQLCB_MaTuyenBay = new JComboBox();
        panelQLCB_Left1.add(cbQLCB_MaTuyenBay);
        panelQLCB_Left1.add(new JLabel("Sân bay đi"));
        cbQLCB_SanBayDi = new JComboBox();
        panelQLCB_Left1.add(cbQLCB_SanBayDi);
        panelQLCB_Left1.add(new JLabel("Sân bay đến"));
        cbQLCB_SanBayDen = new JComboBox();
        panelQLCB_Left1.add(cbQLCB_SanBayDen);
        panelQLCB_Left1.add(new JLabel("Mã máy bay"));
        cbQLCB_MaMayBay = new JComboBox();
        panelQLCB_Left1.add(cbQLCB_MaMayBay);
        panelQLCB_Left1.add(new JLabel("Ngày khởi hành"));
        dateQLCB_NgayKhoiHanh = new JDateChooser();
        panelQLCB_Left1.add(dateQLCB_NgayKhoiHanh);
        panelQLCB_Left1.add(new JLabel("Giờ khởi hành (dd/mm/yyyy)"));
        tfQLCB_GioKhoiHanh = new JTextField();
        panelQLCB_Left1.add(tfQLCB_GioKhoiHanh); 
        panelQLCB_Left1.add(new JLabel("Thời gian bay(phút)"));
        tfQLCB_TGBay = new JTextField("Thoi gian bay");
        panelQLCB_Left1.add(tfQLCB_TGBay); 
        panelQLCB_Left.add(panelQLCB_Left1,BorderLayout.CENTER);
        //Nút
        panelQLCB_Left2 = new JPanel();
        panelQLCB_Left2.setLayout(new GridLayout(2, 1));
        panelQLCB_button = new JPanel();
        panelQLCB_button.setPreferredSize(new Dimension(200, 50));
        panelQLCB_button.setLayout(new GridLayout(1, 3, 10, 10));
        btnQLCB_Them = new JButton("Thêm");
        btnQLCB_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/add.png")));
        panelQLCB_button.add(btnQLCB_Them);
        btnQLCB_Xoa = new JButton("Xóa");
        btnQLCB_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        panelQLCB_button.add(btnQLCB_Xoa);
        btnQLCB_Sua = new JButton("Sửa");
        btnQLCB_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLCB_button.add(btnQLCB_Sua);
        panelQLCB_Left2.add(panelQLCB_button);
        panelQLCB_Left2.add(new JLabel());
        
        panelQLCB_Left.add(panelQLCB_Left2,BorderLayout.PAGE_END);
        
        panelQLChuyenBay.add(panelQLCB_Left,BorderLayout.LINE_START);
        //Bên phải
        panelQLCB_Right = new JPanel();
        panelQLCB_Right.setLayout(new BorderLayout(10, 10));
        lbQLCB_dsChuyenBay = new JLabel("* Danh sách chuyến bay");
        lbQLCB_dsChuyenBay.setFont(new  Font("Tahoma", 3, 11));
        panelQLCB_Right.add(lbQLCB_dsChuyenBay,BorderLayout.PAGE_START);
        
        //table
         tableQLCB_ChuyenBay=new JTable();  
          tableQLCB_ChuyenBay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã chuyến bay", "Mã tuyến bay", "Mã máy bay", "Ngày khởi hành","Giờ khởi hành", "Thời gian bay"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLCB_ChuyenBay = new JScrollPane(tableQLCB_ChuyenBay);
        panelQLCB_contain = new JPanel();
        panelQLCB_contain.setLayout(new GridLayout(2,1));
        scPaneQLCB_ChuyenBay.setSize(new Dimension(200,200));
        panelQLCB_contain.add(scPaneQLCB_ChuyenBay);
        
        
        panelQLCB_HV_TG = new JPanel();
        panelQLCB_HV_TG.setLayout(new GridLayout(1,2,10,10));
        panelQLCB_HV_TG.setPreferredSize(new Dimension(200,400));
        //them hang ve
        panelQLCB_HangVe = new JPanel();
        panelQLCB_HangVe.setLayout(new BorderLayout(5,5));
        panelQLCB_HangVe.add(new JLabel("* Thêm hạng vé cho chuyến bay"),BorderLayout.PAGE_START);
        panelQLCB_HangVe0 = new JPanel();
        panelQLCB_HangVe0.setLayout(new BorderLayout( 5, 5));
        panelQLCB_HangVe1 = new JPanel();
        panelQLCB_HangVe1.setLayout(new GridLayout(3,2,5,5));
        panelQLCB_HangVe1.add(new JLabel("Hạng vé"));
        cbQLCB_HangVe=new JComboBox();
        panelQLCB_HangVe1.add(cbQLCB_HangVe);
        panelQLCB_HangVe1.add(new JLabel("Số ghế cua hạng vé"));
        tfQLCB_SoGhe = new JTextField("Số ghế ");
        panelQLCB_HangVe1.add(tfQLCB_SoGhe);
        panelQLCB_HangVe0.add(panelQLCB_HangVe1,BorderLayout.CENTER);
        panelQLCB_buttonHV = new JPanel();
        panelQLCB_buttonHV.setLayout(new GridLayout(1,3,10,10));
        btnQLCB_ThemHV = new JButton("Thêm");
        btnQLCB_ThemHV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/add.png")));
        btnQLCB_XoaHV = new JButton("Xóa");
        btnQLCB_XoaHV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        btnQLCB_SuaHV = new JButton("Sửa");
        btnQLCB_SuaHV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLCB_buttonHV.add(btnQLCB_ThemHV);
        panelQLCB_buttonHV.add(btnQLCB_XoaHV);
        panelQLCB_buttonHV.add(btnQLCB_SuaHV);
        panelQLCB_buttonHV.setPreferredSize(new Dimension(100, 30));
        panelQLCB_HangVe0.add(panelQLCB_buttonHV,BorderLayout.PAGE_END);
        panelQLCB_HangVe.add(panelQLCB_HangVe0,BorderLayout.CENTER);
        //table hạng vé
        tableQLCB_HangVe=new JTable();  
          tableQLCB_HangVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
               
            },
            new String [] {
                "Tên hạng vé", "Số ghế", "Số ghế rống"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLCB_HangVe = new JScrollPane(tableQLCB_HangVe);
        scPaneQLCB_HangVe.setPreferredSize(new Dimension(50, 100));
        panelQLCB_HangVe.add(scPaneQLCB_HangVe,BorderLayout.PAGE_END);
        panelQLCB_HV_TG.add(panelQLCB_HangVe);
        
        //them Sân bay TG
        panelQLCB_SanBayTG = new JPanel();
        panelQLCB_SanBayTG.setLayout(new BorderLayout(5,5));
        panelQLCB_SanBayTG.add(new JLabel("* Thêm Sân bay TG cho chuyến bay"),BorderLayout.PAGE_START);
        panelQLCB_SanBayTG0 = new JPanel();
        panelQLCB_SanBayTG0.setLayout(new BorderLayout(5, 5));
        panelQLCB_SanBayTG1 = new JPanel();
        panelQLCB_SanBayTG1.setLayout(new GridLayout(3,2,5,5));
        panelQLCB_SanBayTG1.add(new JLabel("Tên sân bay"));
        cbQLCB_TenSanBay=new JComboBox();
        panelQLCB_SanBayTG1.add(cbQLCB_TenSanBay);
        panelQLCB_SanBayTG1.add(new JLabel("Thời gian nghỉ(phút)"));
        tfQLCB_TGNghi = new JTextField("Thời gian nghỉ");
        panelQLCB_SanBayTG1.add(tfQLCB_TGNghi);
        panelQLCB_SanBayTG1.add(new JLabel("Ghi chú"));
        tfQLCB_GhiChu = new JTextField("Ghi chú");
        panelQLCB_SanBayTG1.add(tfQLCB_GhiChu);
        panelQLCB_SanBayTG0.add(panelQLCB_SanBayTG1,BorderLayout.CENTER);
        panelQLCB_buttonSanBayTG = new JPanel();
        panelQLCB_buttonSanBayTG.setLayout(new GridLayout(1,3,10,10));
        btnQLCB_ThemSanBayTG = new JButton("Thêm");
        btnQLCB_ThemSanBayTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/add.png")));
        btnQLCB_XoaSanBayTG = new JButton("Xóa");
        btnQLCB_XoaSanBayTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        btnQLCB_SuaSanBayTG = new JButton("Sửa");
        btnQLCB_SuaSanBayTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLCB_buttonSanBayTG.add(btnQLCB_ThemSanBayTG);
        panelQLCB_buttonSanBayTG.add(btnQLCB_XoaSanBayTG);
        panelQLCB_buttonSanBayTG.add(btnQLCB_SuaSanBayTG); 
        panelQLCB_buttonSanBayTG.setPreferredSize(new Dimension(100, 30));
        panelQLCB_SanBayTG0.add(panelQLCB_buttonSanBayTG,BorderLayout.PAGE_END);
        panelQLCB_SanBayTG.add(panelQLCB_SanBayTG0,BorderLayout.CENTER);
       
        //table Sân bay trung gian
        tableQLCB_SanBayTG=new JTable();  
          tableQLCB_SanBayTG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
               
            },
            new String [] {
                "Tên sân bay", "Thời gian dùng", "Ghi chú"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLCB_SanBayTG = new JScrollPane(tableQLCB_SanBayTG);
        scPaneQLCB_SanBayTG.setPreferredSize(new Dimension(50, 100));
        panelQLCB_SanBayTG.add(scPaneQLCB_SanBayTG,BorderLayout.PAGE_END);
        panelQLCB_HV_TG.add(panelQLCB_SanBayTG);
        
        panelQLCB_contain.add(panelQLCB_HV_TG);
       panelQLCB_Right.add(panelQLCB_contain,BorderLayout.CENTER);
      
        panelQLChuyenBay.add(panelQLCB_Right,BorderLayout.CENTER);
    }
    public void QuanLyTuyenBay(){
        panelQLTuyenBay = new JPanel(); 
        panelQLTuyenBay.setLayout(new BorderLayout(10, 10));
        //Title
        lbQLTB_Title = new JLabel("QUẢN LÝ TUYẾN BAY");
        lbQLTB_Title.setFont(new Font("Tahoma", 1, 24));
        lbQLTB_Title.setOpaque(true);
        lbQLTB_Title.setBackground(new Color(102, 255, 102));
        lbQLTB_Title.setForeground(new Color(0,0,255));
        lbQLTB_Title.setHorizontalAlignment(JLabel.CENTER);
        panelQLTuyenBay.add(lbQLTB_Title,BorderLayout.PAGE_START); 
        //Cột trái
        panelQLTB_Left = new JPanel();
        panelQLTB_Left.setLayout(new BorderLayout(10, 10));
        panelQLTB_Left.setPreferredSize(new Dimension(300, 600));
        lbQLTB_ttTuyenBay = new JLabel("* Thông tin tuyến bay");
        lbQLTB_ttTuyenBay.setFont(new Font("Tahoma", 3, 11));
        panelQLTB_Left.add(lbQLTB_ttTuyenBay, BorderLayout.PAGE_START);
        
        panelQLTB_Left1 = new JPanel();
        panelQLTB_Left1.setLayout(new GridLayout(3,2,5,5));
        panelQLTB_Left1.add(new JLabel("Mã tuyến bay"));
        tfQLTB_MaTuyenBay = new JTextField("Mã tuyến bay");
        tfQLTB_MaTuyenBay.setEditable(false);
        panelQLTB_Left1.add(tfQLTB_MaTuyenBay);
        panelQLTB_Left1.add(new JLabel("Sân bay đi"));
        cbQLTB_SanBayDi = new JComboBox();
        panelQLTB_Left1.add(cbQLTB_SanBayDi);
        panelQLTB_Left1.add(new JLabel("Sân bay đến"));
        cbQLTB_SanBayDen = new JComboBox();
        panelQLTB_Left1.add(cbQLTB_SanBayDen);
        panelQLTB_Left.add(panelQLTB_Left1, BorderLayout.CENTER);
        
        //nút
        panelQLTB_button = new JPanel();
        panelQLTB_button.setLayout(new GridLayout(7, 3, 10, 10));
        btnQLTB_Them = new JButton("Thêm");
        btnQLTB_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/add.png")));
        btnQLTB_Xoa = new JButton("Xóa");
        btnQLTB_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        btnQLTB_Sua = new JButton("Sửa");
        btnQLTB_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLTB_button.add(btnQLTB_Them);
        panelQLTB_button.add(btnQLTB_Xoa);
        panelQLTB_button.add(btnQLTB_Sua);
        lbQLTB_ThongBao = new JLabel();
        lbQLTB_ThongBao.setFont(new Font("Tahoma", 0, 12));
        lbQLTB_ThongBao.setForeground(Color.RED);
        lbQLTB_ThongBao.setHorizontalAlignment(JLabel.CENTER);
        panelQLTB_button.add(new JLabel());
         panelQLTB_button.add(lbQLTB_ThongBao);
          panelQLTB_button.add(new JLabel());
           panelQLTB_button.add(new JLabel());
            panelQLTB_button.add(new JLabel());
             panelQLTB_button.add(new JLabel());
              panelQLTB_button.add(new JLabel());
               panelQLTB_button.add(new JLabel());
                panelQLTB_button.add(new JLabel());
                panelQLTB_button.add(new JLabel());
                panelQLTB_button.add(new JLabel());
                panelQLTB_button.add(new JLabel());
                panelQLTB_button.add(new JLabel());panelQLTB_button.add(new JLabel());
 
                
        panelQLTB_button.setPreferredSize(new Dimension(300, 400));
        
        panelQLTB_Left.add(panelQLTB_button,BorderLayout.PAGE_END);
        //Ben phải
        panelQLTB_Right = new JPanel();
        panelQLTB_Right.setLayout(new BorderLayout(10, 10));
        lbQLTB_dsTuyenBay = new JLabel("* Danh sách tuyến bay");
        lbQLTB_dsTuyenBay.setFont(new Font("Tahoma", 3, 11));
        panelQLTB_Right.add(lbQLTB_dsTuyenBay, BorderLayout.PAGE_START);
        
        //table
        tableQLTB_TuyenBay=new JTable();  
          tableQLTB_TuyenBay.setModel(new javax.swing.table.DefaultTableModel(
            new TuyenBay [][] {
  
            },
            new String [] {
                 "Mã tuyến bay", "Sân bay đi", "Sân bay đến"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLTB_TuyenBay = new JScrollPane(tableQLTB_TuyenBay);
        panelQLTB_Right.add(scPaneQLTB_TuyenBay,BorderLayout.CENTER);
        
        panelQLTuyenBay.add(panelQLTB_Left,BorderLayout.LINE_START);
        panelQLTuyenBay.add(panelQLTB_Right,BorderLayout.CENTER);
        
        //-------------EVENT-----------
        tableQLTB_TuyenBay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel)tableQLTB_TuyenBay.getModel();
                int row = tableQLTB_TuyenBay.getSelectedRow();
                tfQLTB_MaTuyenBay.setText(String.valueOf(model.getValueAt(row,0)));
                
                SanBay sb1 = (SanBay) model.getValueAt(row, 1);
                String maSanBayDi=sb1.getMaSanBay();
                
                SanBay sb2 = (SanBay) model.getValueAt(row, 2);
                String maSanBayDen=sb2.getMaSanBay();
                
                for(int i=0; i<cbQLTB_SanBayDi.getItemCount();i++){
                    Object item1=cbQLTB_SanBayDi.getItemAt(i);
                    String maSanBay2 = ((SanBay)item1).getMaSanBay();
                    if(maSanBayDi.equals(maSanBay2)){
                        cbQLTB_SanBayDi.setSelectedIndex(i);
                        break;
                    }
                }
                 for(int j=0; j<cbQLTB_SanBayDen.getItemCount();j++){
                    Object item2=cbQLTB_SanBayDen.getItemAt(j);
                    String maSanBay3 = ((SanBay)item2).getMaSanBay();
                    if(maSanBayDen.equals(maSanBay3)){
                        cbQLTB_SanBayDen.setSelectedIndex(j);
                        break;
                    }        
                            
                }
                //cbQLTB_SanBayDi.setSelectedIndex(1);
                //System.out.println(cbQLTB_SanBayDi.getItemCount());
//                cbQLTB_SanBayDen.setSelectedItem(model.getValueAt(row, 2));
                }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               
            }

            @Override
            public void mouseExited(MouseEvent e) {
              
            }
        });
        btnQLTB_Them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addTuyenBay();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        btnQLTB_Xoa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try {
                    deleteTuyenBay();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnQLTB_Sua.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try {
                    updateTuyenBay();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
     public void QuanLySanBay()
    {
        panelQLSanBay=new JPanel();
        panelQLSanBay.setLayout(new BorderLayout(10, 10));
        //Title
        lbQLSB_Title = new JLabel("QUẢN LÝ SÂN BAY");
        lbQLSB_Title.setFont(new Font("Tahoma", 1, 24));
        lbQLSB_Title.setOpaque(true);
        lbQLSB_Title.setForeground(new Color(0,0,255));
        lbQLSB_Title.setBackground(new Color(102, 255, 102));
        lbQLSB_Title.setHorizontalAlignment(JLabel.CENTER);
        panelQLSanBay.add(lbQLSB_Title,BorderLayout.PAGE_START); 
        //Cột trái
       panelQLSB_Left = new JPanel();
        panelQLSB_Left.setLayout(new BorderLayout(10, 10));
        panelQLSB_Left.setPreferredSize(new Dimension(300, 600));
        lbQLSB_ttSanBay = new JLabel("* Thông tin sân tuyến bay");
        lbQLSB_ttSanBay.setFont(new Font("Tahoma", 3, 11));
        panelQLSB_Left.add(lbQLSB_ttSanBay, BorderLayout.PAGE_START);
        
        panelQLSB_Left1 = new JPanel();
        panelQLSB_Left1.setLayout(new GridLayout(3,2,5,5));
        panelQLSB_Left1.add(new JLabel("Mã sân bay"));
        tfQLSB_MaSanBay = new JTextField();
        tfQLSB_MaSanBay.setEditable(false);
        panelQLSB_Left1.add(tfQLSB_MaSanBay);
        panelQLSB_Left1.add(new JLabel("Tên sân bay"));
        tfQLSB_TenSanBay = new JTextField();
        panelQLSB_Left1.add(tfQLSB_TenSanBay);
        panelQLSB_Left1.add(new JLabel("Tên thành phố"));
        tfQLSB_TenThanhPho = new JTextField();
        panelQLSB_Left1.add(tfQLSB_TenThanhPho);
        panelQLSB_Left.add(panelQLSB_Left1, BorderLayout.CENTER);
        //nút
        panelQLSB_button = new JPanel();
        panelQLSB_button.setLayout(new GridLayout(7, 3, 10, 10));
        btnQLSB_Them = new JButton("Thêm");
        btnQLSB_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/add.png")));
        btnQLSB_Xoa = new JButton("Xóa");
        btnQLSB_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        btnQLSB_Sua = new JButton("Sửa");
        btnQLSB_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLSB_button.add(btnQLSB_Them);
        panelQLSB_button.add(btnQLSB_Xoa);
        panelQLSB_button.add(btnQLSB_Sua);
        panelQLSB_button.add(new JLabel());
        lbQLSB_ThongBao = new JLabel();
        lbQLSB_ThongBao.setFont(new Font("Tahoma", 0, 12));
        lbQLSB_ThongBao.setForeground(Color.RED);
        lbQLSB_ThongBao.setHorizontalAlignment(JLabel.CENTER);
        panelQLSB_button.add(lbQLSB_ThongBao);
         panelQLSB_button.add(new JLabel());
           panelQLSB_button.add(new JLabel());
            panelQLSB_button.add(new JLabel());
             panelQLSB_button.add(new JLabel());
              panelQLSB_button.add(new JLabel());
               panelQLSB_button.add(new JLabel());
                panelQLSB_button.add(new JLabel());
                panelQLSB_button.add(new JLabel());
                panelQLSB_button.add(new JLabel());
                panelQLSB_button.add(new JLabel());
                panelQLSB_button.add(new JLabel());panelQLSB_button.add(new JLabel());
 
                
        panelQLSB_button.setPreferredSize(new Dimension(300, 400));
        
        panelQLSB_Left.add(panelQLSB_button,BorderLayout.PAGE_END);
        //Ben phải
        panelQLSB_Right = new JPanel();
        panelQLSB_Right.setLayout(new BorderLayout(10, 10));
        lbQLSB_dsSanBay = new JLabel("* Danh sách sân bay");
        lbQLSB_dsSanBay.setFont(new Font("Tahoma", 3, 11));
        panelQLSB_Right.add(lbQLSB_dsSanBay, BorderLayout.PAGE_START);
        
        //table
        tableQLSB_SanBay=new JTable();  
        
          tableQLSB_SanBay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                 "Mã sân bay", "Tên sân bay", "Tên thành phố"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLSB_SanBay = new JScrollPane(tableQLSB_SanBay);
        panelQLSB_Right.add(scPaneQLSB_SanBay,BorderLayout.CENTER);
        
        panelQLSanBay.add(panelQLSB_Left,BorderLayout.LINE_START);
        panelQLSanBay.add(panelQLSB_Right,BorderLayout.CENTER);
        
//-------------------EVENT-------------------//
        tableQLSB_SanBay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                DefaultTableModel model = (DefaultTableModel)tableQLSB_SanBay.getModel();
                int row = tableQLSB_SanBay.getSelectedRow();
                tfQLSB_MaSanBay.setText(String.valueOf(model.getValueAt(row,0)));
                tfQLSB_TenSanBay.setText(String.valueOf(model.getValueAt(row,1)));
                tfQLSB_TenThanhPho.setText(String.valueOf(model.getValueAt(row,2)));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }

           
        });
        btnQLSB_Them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addSanBay();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnQLSB_Xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteSanBay();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnQLSB_Sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateSanBay();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void QuanLyMayBay()
    {
        panelQLMayBay=new JPanel();
       
        panelQLMayBay.setLayout(new BorderLayout(10, 10));
        //Title
        lbQLMB_Title = new JLabel("QUẢN LÝ MÁY BAY");
        lbQLMB_Title.setFont(new Font("Tahoma", 1, 24));
        lbQLMB_Title.setOpaque(true);
        lbQLMB_Title.setBackground(new Color(102, 255, 102));
        lbQLMB_Title.setForeground(new Color(0,0,255));
        lbQLMB_Title.setHorizontalAlignment(JLabel.CENTER);
        panelQLMayBay.add(lbQLMB_Title,BorderLayout.PAGE_START); 
        //Cột trái
        panelQLMB_Left = new JPanel();
        panelQLMB_Left.setLayout(new BorderLayout(10, 10));
        panelQLMB_Left.setPreferredSize(new Dimension(300, 600));
        lbQLMB_ttMayBay = new JLabel("* Thông tin máy bay");
        lbQLMB_ttMayBay.setFont(new Font("Tahoma", 3, 11));
        panelQLMB_Left.add(lbQLMB_ttMayBay, BorderLayout.PAGE_START);
        
        panelQLMB_Left1 = new JPanel();
        panelQLMB_Left1.setLayout(new GridLayout(5, 1, 10, 10));
        panelQLMB_Left1.setLayout(new GridLayout(3,2,5,5));
        panelQLMB_Left1.add(new JLabel("Mã máy bay"));
        tfQLMB_MaMayBay = new JTextField("Mã máy bay");
        tfQLMB_MaMayBay.setEditable(false);
        panelQLMB_Left1.add(tfQLMB_MaMayBay);
        panelQLMB_Left1.add(new JLabel("Tên máy bay"));
        tfQLMB_TenMayBay = new JTextField("Tên máy bay");
        panelQLMB_Left1.add(tfQLMB_TenMayBay);
        panelQLMB_Left1.add(new JLabel("Số lượng ghế"));
        tfQLMB_SoLuongGhe = new JTextField("Số lượng ghế");
        panelQLMB_Left1.add(tfQLMB_SoLuongGhe);
        panelQLMB_Left.add(panelQLMB_Left1, BorderLayout.CENTER);
        
        //nút
        panelQLMB_button = new JPanel();
        panelQLMB_button.setLayout(new GridLayout(7, 3, 10, 10));
        btnQLMB_Them = new JButton("Thêm");
        btnQLMB_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/add.png")));
        btnQLMB_Xoa = new JButton("Xóa");
        btnQLMB_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        btnQLMB_Sua = new JButton("Sửa");
        btnQLMB_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLMB_button.add(btnQLMB_Them);
        panelQLMB_button.add(btnQLMB_Xoa);
        panelQLMB_button.add(btnQLMB_Sua);
        panelQLMB_button.add(new JLabel());
         panelQLMB_button.add(new JLabel());
          panelQLMB_button.add(new JLabel());
           panelQLMB_button.add(new JLabel());
            panelQLMB_button.add(new JLabel());
             panelQLMB_button.add(new JLabel());
              panelQLMB_button.add(new JLabel());
               panelQLMB_button.add(new JLabel());
                panelQLMB_button.add(new JLabel());
                panelQLMB_button.add(new JLabel());
                panelQLMB_button.add(new JLabel());
                panelQLMB_button.add(new JLabel());
                panelQLMB_button.add(new JLabel());panelQLMB_button.add(new JLabel());
 
                
        panelQLMB_button.setPreferredSize(new Dimension(300, 400));
        
        panelQLMB_Left.add(panelQLMB_button,BorderLayout.PAGE_END);
        //Ben phải
        panelQLMB_Right = new JPanel();
        panelQLMB_Right.setLayout(new BorderLayout(10, 10));
        lbQLMB_dsMayBay = new JLabel("* Danh sách máy bay");
        lbQLMB_dsMayBay.setFont(new Font("Tahoma", 3, 11));
        panelQLMB_Right.add(lbQLMB_dsMayBay, BorderLayout.PAGE_START);
        
        //table
        tableQLMB_MayBay=new JTable();  
          tableQLMB_MayBay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                 "Mã máy bay", "Tên máy bay", "Số lượng ghế"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLMB_MayBay = new JScrollPane(tableQLMB_MayBay);
        panelQLMB_Right.add(scPaneQLMB_MayBay,BorderLayout.CENTER);
        
        panelQLMayBay.add(panelQLMB_Left,BorderLayout.LINE_START);
        panelQLMayBay.add(panelQLMB_Right,BorderLayout.CENTER);
        //-------------------EVENT-------------------//
        tableQLMB_MayBay.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                DefaultTableModel model = (DefaultTableModel)tableQLMB_MayBay.getModel();
                int row = tableQLMB_MayBay.getSelectedRow();
                tfQLMB_MaMayBay.setText(String.valueOf(model.getValueAt(row, 0)));
                tfQLMB_TenMayBay.setText(String.valueOf(model.getValueAt(row, 1)));
                tfQLMB_SoLuongGhe.setText(String.valueOf(model.getValueAt(row, 2)));
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                 
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                 
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        });
        btnQLMB_Them.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try {
                    addMayBay();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnQLMB_Xoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    deleteMayBay();
                }
                catch(SQLException ex)
                {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        btnQLMB_Sua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    updateMayBay();
                }
                catch(SQLException ex)
                {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
    }
    public void QuanLyHangVe()
    {
        panelQLHangVe=new JPanel();
        
         panelQLHangVe.setLayout(new BorderLayout(10, 10));
        //Title
        lbQLHV_Title = new JLabel("QUẢN LÝ HẠNG VÉ");
        lbQLHV_Title.setFont(new Font("Tahoma", 1, 24));
        lbQLHV_Title.setOpaque(true);
        lbQLHV_Title.setBackground(new Color(102, 255, 102));
        lbQLHV_Title.setForeground(new Color(0,0,255));
        lbQLHV_Title.setHorizontalAlignment(JLabel.CENTER);
        panelQLHangVe.add(lbQLHV_Title,BorderLayout.PAGE_START); 
        //Cột trái
        panelQLHV_Left = new JPanel();
        panelQLHV_Left.setLayout(new BorderLayout(10, 10));
        panelQLHV_Left.setPreferredSize(new Dimension(300, 600));
        lbQLHV_ttHangVe = new JLabel("* Thông tin hạng vé");
        lbQLHV_ttHangVe.setFont(new Font("Tahoma", 3, 11));
        panelQLHV_Left.add(lbQLHV_ttHangVe, BorderLayout.PAGE_START);
        
        panelQLHV_Left1 = new JPanel();
        panelQLHV_Left1.setLayout(new GridLayout(5, 1, 10, 10));
        panelQLHV_Left1.setLayout(new GridLayout(3,2,5,5));
        panelQLHV_Left1.add(new JLabel("Mã hạng vé"));
        tfQLHV_MaHangVe = new JTextField("Mã hạng vé");
        tfQLHV_MaHangVe.setEditable(false);
        panelQLHV_Left1.add(tfQLHV_MaHangVe);
        panelQLHV_Left1.add(new JLabel("Tên hạng vé"));
        tfQLHV_TenHangVe = new JTextField("Tên hạng vé");
        panelQLHV_Left1.add(tfQLHV_TenHangVe);
        
        panelQLHV_Left.add(panelQLHV_Left1, BorderLayout.CENTER);
        
        //nút
        panelQLHV_button = new JPanel();
        panelQLHV_button.setLayout(new GridLayout(7, 3, 10, 10));
        btnQLHV_Them = new JButton("Thêm");
        btnQLHV_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/add.png")));
        btnQLHV_Xoa = new JButton("Xóa");
        btnQLHV_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        btnQLHV_Sua = new JButton("Sửa");
        btnQLHV_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLHV_button.add(btnQLHV_Them);
        panelQLHV_button.add(btnQLHV_Xoa);
        panelQLHV_button.add(btnQLHV_Sua);
        panelQLHV_button.add(new JLabel());
         panelQLHV_button.add(new JLabel());
          panelQLHV_button.add(new JLabel());
           panelQLHV_button.add(new JLabel());
            panelQLHV_button.add(new JLabel());
             panelQLHV_button.add(new JLabel());
              panelQLHV_button.add(new JLabel());
               panelQLHV_button.add(new JLabel());
                panelQLHV_button.add(new JLabel());
                panelQLHV_button.add(new JLabel());
                panelQLHV_button.add(new JLabel());
                panelQLHV_button.add(new JLabel());
                panelQLHV_button.add(new JLabel());panelQLHV_button.add(new JLabel());
 
                
        panelQLHV_button.setPreferredSize(new Dimension(300, 400));
        
        panelQLHV_Left.add(panelQLHV_button,BorderLayout.PAGE_END);
        //Ben phải
        panelQLHV_Right = new JPanel();
        panelQLHV_Right.setLayout(new BorderLayout(10, 10));
        lbQLHV_dsHangVe = new JLabel("* Danh sách Hạng vé");
        lbQLHV_dsHangVe.setFont(new Font("Tahoma", 3, 11));
        panelQLHV_Right.add(lbQLHV_dsHangVe, BorderLayout.PAGE_START);
        
        //table
        tableQLHV_HangVe=new JTable();  
          tableQLHV_HangVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                 "Mã hạng vé", "Tên hạng vé"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLHV_HangVe = new JScrollPane(tableQLHV_HangVe);
        panelQLHV_Right.add(scPaneQLHV_HangVe,BorderLayout.CENTER);
        
        panelQLHangVe.add(panelQLHV_Left,BorderLayout.LINE_START);
        panelQLHangVe.add(panelQLHV_Right,BorderLayout.CENTER);
         //-------------------EVENT-------------------//
         tableQLHV_HangVe.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
               DefaultTableModel model = (DefaultTableModel)tableQLHV_HangVe.getModel();
               int row = tableQLHV_HangVe.getSelectedRow();
               tfQLHV_MaHangVe.setText(String.valueOf(model.getValueAt(row, 0)));
               tfQLHV_TenHangVe.setText(String.valueOf(model.getValueAt(row, 1)));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
             
         });
         btnQLHV_Them.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addHangVe();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             
         });
          btnQLHV_Xoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteHangVe();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             
         });
         btnQLHV_Sua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateHangVe();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyBanVeMayBay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             
         });  

          
    }
    public void QuanLyDonGia()
    {
        panelQLDonGia=new JPanel();
         panelQLDonGia.setLayout(new BorderLayout(10, 10));
        //Title
        lbQLDG_Title = new JLabel("QUẢN LÝ ĐƠN GIÁ");
        lbQLDG_Title.setFont(new Font("Tahoma", 1, 24));
        lbQLDG_Title.setOpaque(true);
        lbQLDG_Title.setBackground(new Color(102, 255, 102));
        lbQLDG_Title.setForeground(new Color(0,0,255));
        lbQLDG_Title.setHorizontalAlignment(JLabel.CENTER);
        panelQLDonGia.add(lbQLDG_Title,BorderLayout.PAGE_START); 
        //Cột trái
        panelQLDG_Left = new JPanel();
        panelQLDG_Left.setLayout(new BorderLayout(10, 10));
        panelQLDG_Left.setPreferredSize(new Dimension(300, 600));
        lbQLDG_ttDonGia = new JLabel("* Thông tin đơn giá");
        lbQLDG_ttDonGia.setFont(new Font("Tahoma", 3, 11));
        panelQLDG_Left.add(lbQLDG_ttDonGia, BorderLayout.PAGE_START);
        
        panelQLDG_Left1 = new JPanel();
        panelQLDG_Left1.setLayout(new GridLayout(7,2,5,5));
        panelQLDG_Left1.add(new JLabel("Mã tuyến bay"));
        cbQLDG_MaTuyenBay = new JComboBox();
        panelQLDG_Left1.add(cbQLDG_MaTuyenBay);
        panelQLDG_Left1.add(new JLabel("Sân bay đi"));
        cbQLDG_SanBayDi = new JComboBox();
        panelQLDG_Left1.add(cbQLDG_SanBayDi);
        panelQLDG_Left1.add(new JLabel("Sân bay đến"));
        cbQLDG_SanBayDen = new JComboBox();
        panelQLDG_Left1.add(cbQLDG_SanBayDen);
        panelQLDG_Left1.add(new JLabel("Tên hạng vé"));
        cbQLDG_TenHangVe = new JComboBox();
        panelQLDG_Left1.add(cbQLDG_TenHangVe);
        panelQLDG_Left1.add(new JLabel("Đơn giá"));
        tfQLDG_DonGia = new JTextField("Đơn giá");
        panelQLDG_Left1.add(tfQLDG_DonGia);
//         panelQLDG_Left1.add(new JLabel());
//         panelQLDG_Left1.add(new JLabel());
//         panelQLDG_Left1.add(new JLabel());
//         panelQLDG_Left1.add(new JLabel());
                 
        
        panelQLDG_Left.add(panelQLDG_Left1, BorderLayout.CENTER);
        
        //nút
        panelQLDG_button = new JPanel();
        panelQLDG_button.setLayout(new GridLayout(7, 3, 10, 10));
        btnQLDG_Them = new JButton("Thêm");
        btnQLDG_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/add.png")));
        btnQLDG_Xoa = new JButton("Xóa");
        btnQLDG_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        btnQLDG_Sua = new JButton("Sửa");
        btnQLDG_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLDG_button.add(btnQLDG_Them);
        panelQLDG_button.add(btnQLDG_Xoa);
        panelQLDG_button.add(btnQLDG_Sua);
        panelQLDG_button.add(new JLabel());
         panelQLDG_button.add(new JLabel());
          panelQLDG_button.add(new JLabel());
           panelQLDG_button.add(new JLabel());
            panelQLDG_button.add(new JLabel());
             panelQLDG_button.add(new JLabel());
              panelQLDG_button.add(new JLabel());
               panelQLDG_button.add(new JLabel());
                panelQLDG_button.add(new JLabel());
                panelQLDG_button.add(new JLabel());
                panelQLDG_button.add(new JLabel());
                panelQLDG_button.add(new JLabel());
                panelQLDG_button.add(new JLabel());panelQLDG_button.add(new JLabel());
 
                
        panelQLDG_button.setPreferredSize(new Dimension(300, 300));
        
        panelQLDG_Left.add(panelQLDG_button,BorderLayout.PAGE_END);
        //Ben phải
        panelQLDG_Right = new JPanel();
        panelQLDG_Right.setLayout(new BorderLayout(10, 10));
        lbQLDG_dsDonGia = new JLabel("* Danh sách đơn giá");
        lbQLDG_dsDonGia.setFont(new Font("Tahoma", 3, 11));
        panelQLDG_Right.add(lbQLDG_dsDonGia, BorderLayout.PAGE_START);
        
        //table
        tableQLDG_DonGia=new JTable();  
          tableQLDG_DonGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                 "Mã tuyến bay", "Mã hạng vé", "Đơn giá"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLDG_DonGia = new JScrollPane(tableQLDG_DonGia);
        panelQLDG_Right.add(scPaneQLDG_DonGia,BorderLayout.CENTER);
        
        panelQLDonGia.add(panelQLDG_Left,BorderLayout.LINE_START);
        panelQLDonGia.add(panelQLDG_Right,BorderLayout.CENTER);
         //-------------------EVENT-------------------//
         
    }
    public void QuanLyKhachHang()
    {
        panelQLKhachHang=new JPanel();
        panelQLKhachHang.setLayout(new BorderLayout(10, 10));
        //Title
        lbQLKH_Title = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lbQLKH_Title.setFont(new Font("Tahoma", 1, 24));
        lbQLKH_Title.setOpaque(true);
        lbQLKH_Title.setBackground(new Color(102, 255, 102));
        lbQLKH_Title.setForeground(new Color(0,0,255));
        lbQLKH_Title.setHorizontalAlignment(JLabel.CENTER);
        panelQLKhachHang.add(lbQLKH_Title,BorderLayout.PAGE_START); 
        //Cột trái
        panelQLKH_Left = new JPanel();
        panelQLKH_Left.setLayout(new BorderLayout(10, 10));
        panelQLKH_Left.setPreferredSize(new Dimension(300, 600));
        lbQLKH_ttKhachHang = new JLabel("* Thông tin Khách hàng");
        lbQLKH_ttKhachHang.setFont(new Font("Tahoma", 3, 11));
        panelQLKH_Left.add(lbQLKH_ttKhachHang, BorderLayout.PAGE_START);
        
        panelQLKH_Left1 = new JPanel();
        panelQLKH_Left1.setLayout(new GridLayout(7,2,5,5));
        panelQLKH_Left1.add(new JLabel("Mã khách hàng"));
        tfQLKH_MaKhachHang = new JTextField("Mã khách hàng");
        tfQLKH_MaKhachHang.setEditable(false);
        panelQLKH_Left1.add(tfQLKH_MaKhachHang);
        panelQLKH_Left1.add(new JLabel("Tên khách hàng"));
        tfQLKH_TenKhachHang = new JTextField("Tên khách hàng");
        panelQLKH_Left1.add(tfQLKH_TenKhachHang);
        panelQLKH_Left1.add(new JLabel("CMND"));
        tfQLKH_CMND = new JTextField("CMND");
        panelQLKH_Left1.add(tfQLKH_CMND);
        panelQLKH_Left1.add(new JLabel("Số điện thoại"));
        tfQLKH_SDT = new JTextField("SDT");
        panelQLKH_Left1.add(tfQLKH_SDT);
//         panelQLDG_Left1.add(new JLabel());
//         panelQLDG_Left1.add(new JLabel());
//         panelQLDG_Left1.add(new JLabel());
//         panelQLDG_Left1.add(new JLabel());
                 
        
        panelQLKH_Left.add(panelQLKH_Left1, BorderLayout.CENTER);
        
        //nút
        panelQLKH_button = new JPanel();
        panelQLKH_button.setLayout(new GridLayout(7, 4, 10, 10));
        btnQLKH_Xoa = new JButton("Xóa");
        btnQLKH_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        btnQLKH_Sua = new JButton("Sửa");
        btnQLKH_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLKH_button.add(new JLabel());
        panelQLKH_button.add(btnQLKH_Xoa);
        panelQLKH_button.add(btnQLKH_Sua);
        panelQLKH_button.add(new JLabel());
        panelQLKH_button.add(new JLabel());
         panelQLKH_button.add(new JLabel());
          panelQLKH_button.add(new JLabel());
           panelQLKH_button.add(new JLabel());
            panelQLKH_button.add(new JLabel());
             panelQLKH_button.add(new JLabel());
              panelQLKH_button.add(new JLabel());
               panelQLKH_button.add(new JLabel());
                panelQLKH_button.add(new JLabel());
                panelQLKH_button.add(new JLabel());
                panelQLKH_button.add(new JLabel());
                panelQLKH_button.add(new JLabel());
                panelQLKH_button.add(new JLabel());panelQLDG_button.add(new JLabel());
 
                
        panelQLKH_button.setPreferredSize(new Dimension(300, 300));
        
        panelQLKH_Left.add(panelQLKH_button,BorderLayout.PAGE_END);
        //Ben phải
        panelQLKH_Right = new JPanel();
        panelQLKH_Right.setLayout(new BorderLayout(10, 10));
        lbQLKH_dsKhachHang = new JLabel("* Danh sách khách hàng");
        lbQLKH_dsKhachHang.setFont(new Font("Tahoma", 3, 11));
        panelQLKH_Right.add(lbQLKH_dsKhachHang, BorderLayout.PAGE_START);
        
        //table
        tableQLKH_KhachHang=new JTable();  
          tableQLKH_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                 "Mã khách hàng", "Tên khách hàng", "CMND", "Số điện thoại"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLKH_KhachHang = new JScrollPane(tableQLKH_KhachHang);
        panelQLKH_Right.add(scPaneQLKH_KhachHang,BorderLayout.CENTER);
        
        panelQLKhachHang.add(panelQLKH_Left,BorderLayout.LINE_START);
        panelQLKhachHang.add(panelQLKH_Right,BorderLayout.CENTER);
    }
    public void QuanLyNhanVien()
    {
        panelQLNhanVien=new JPanel();
        
        panelQLNhanVien.setLayout(new BorderLayout(10, 10));
        //Title
        lbQLNV_Title = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lbQLNV_Title.setFont(new Font("Tahoma", 1, 24));
        lbQLNV_Title.setOpaque(true);
        lbQLNV_Title.setBackground(new Color(102, 255, 102));
        lbQLNV_Title.setForeground(new Color(0,0,255));;
        lbQLNV_Title.setHorizontalAlignment(JLabel.CENTER);
        panelQLNhanVien.add(lbQLNV_Title,BorderLayout.PAGE_START); 
        //Cột trái
        panelQLNV_Left = new JPanel();
        panelQLNV_Left.setLayout(new BorderLayout(10, 10));
        panelQLNV_Left.setPreferredSize(new Dimension(300, 600));
        lbQLNV_ttNhanVien = new JLabel("* Thông tin hạng vé");
        lbQLNV_ttNhanVien.setFont(new Font("Tahoma", 3, 11));
        panelQLNV_Left.add(lbQLNV_ttNhanVien, BorderLayout.PAGE_START);
        
        panelQLNV_Left1 = new JPanel();
        panelQLNV_Left1.setLayout(new GridLayout(5, 1, 10, 10));
        panelQLNV_Left1.setLayout(new GridLayout(3,2,5,5));
        panelQLNV_Left1.add(new JLabel("Mã nhân viên"));
        tfQLNV_MaNhanVien = new JTextField("Mã nhân viên");
        tfQLNV_MaNhanVien.setEditable(false);
        panelQLNV_Left1.add(tfQLNV_MaNhanVien);
        panelQLNV_Left1.add(new JLabel("Tên nhân viên"));
        tfQLNV_TenNhanVien = new JTextField("Tên nhân viên");
        panelQLNV_Left1.add(tfQLNV_TenNhanVien);
        
        panelQLNV_Left.add(panelQLNV_Left1, BorderLayout.CENTER);
        
        //nút
        panelQLNV_button = new JPanel();
        panelQLNV_button.setLayout(new GridLayout(7, 3, 10, 10));
        btnQLNV_Them = new JButton("Thêm");
        btnQLNV_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/add.png")));
        btnQLNV_Xoa = new JButton("Xóa");
        btnQLNV_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/remove.png")));
        btnQLNV_Sua = new JButton("Sửa");
        btnQLNV_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelQLNV_button.add(btnQLNV_Them);
        panelQLNV_button.add(btnQLNV_Xoa);
        panelQLNV_button.add(btnQLNV_Sua);
        panelQLNV_button.add(new JLabel());
         panelQLNV_button.add(new JLabel());
          panelQLNV_button.add(new JLabel());
           panelQLNV_button.add(new JLabel());
            panelQLNV_button.add(new JLabel());
             panelQLNV_button.add(new JLabel());
              panelQLNV_button.add(new JLabel());
               panelQLNV_button.add(new JLabel());
                panelQLNV_button.add(new JLabel());
                panelQLNV_button.add(new JLabel());
                panelQLNV_button.add(new JLabel());
                panelQLNV_button.add(new JLabel());
                panelQLNV_button.add(new JLabel());panelQLNV_button.add(new JLabel());
 
                
        panelQLNV_button.setPreferredSize(new Dimension(300, 400));
        
        panelQLNV_Left.add(panelQLNV_button,BorderLayout.PAGE_END);
        //Ben phải
        panelQLNV_Right = new JPanel();
        panelQLNV_Right.setLayout(new BorderLayout(10, 10));
        lbQLNV_dsNhanVien = new JLabel("* Danh sách nhân viên");
        lbQLNV_dsNhanVien.setFont(new Font("Tahoma", 3, 11));
        panelQLNV_Right.add(lbQLNV_dsNhanVien, BorderLayout.PAGE_START);
        
        //table
        tableQLNV_NhanVien=new JTable();  
          tableQLNV_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                 "Mã nhân viên", "Tên nhân viên"
            }
        ) {          
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scPaneQLNV_NhanVien = new JScrollPane(tableQLNV_NhanVien);
        panelQLNV_Right.add(scPaneQLNV_NhanVien,BorderLayout.CENTER);
        
        panelQLNhanVien.add(panelQLNV_Left,BorderLayout.LINE_START);
        panelQLNhanVien.add(panelQLNV_Right,BorderLayout.CENTER);
    }
    public void ThayDoiQuyDinh()
    {
        panelThayDoiQD=new JPanel();
        panelThayDoiQD.setLayout(new BorderLayout(20, 20));
        //Title
        lbTDQD_Title = new JLabel("THAY ĐỔI QUY ĐỊNH");
        lbTDQD_Title.setFont(new Font("Tahoma", 1, 24));
        lbTDQD_Title.setOpaque(true);
        lbTDQD_Title.setBackground(new Color(102, 255, 102));
        lbTDQD_Title.setForeground(new Color(0,0,255));;
        lbTDQD_Title.setHorizontalAlignment(JLabel.CENTER);
        panelThayDoiQD.add(lbTDQD_Title,BorderLayout.PAGE_START); 
        
        panelTDQD_1 = new JPanel();
        panelTDQD_1.setPreferredSize(new Dimension(350, 600));
        panelThayDoiQD.add(panelTDQD_1,BorderLayout.LINE_START);
        
        panelTDQD_2 = new JPanel();
        panelTDQD_2.setPreferredSize(new Dimension(350, 600));
        panelThayDoiQD.add(panelTDQD_2,BorderLayout.LINE_END);
        
        panelTDQD_mid = new JPanel();
        panelTDQD_mid.setLayout(new GridLayout(11, 2, 10, 10));
        panelTDQD_mid.add(new JLabel("Thời gian bay tối thiểu"));
        tfTDQD_tgBayToiThieu = new JTextField("tg bay tối thiểu");
        panelTDQD_mid.add(tfTDQD_tgBayToiThieu);
        panelTDQD_mid.add(new JLabel("Số sân bay trung gian tối đa"));
        tfTDQD_SoSanBayTgToiDa = new JTextField("sân bay tg tối đa");
        panelTDQD_mid.add(tfTDQD_SoSanBayTgToiDa);
        panelTDQD_mid.add(new JLabel("Thời gian dừng tối thiểu"));
        tfTDQD_tgDungToiThieu = new JTextField("tg dừng tối thiểu");
        panelTDQD_mid.add(tfTDQD_tgDungToiThieu);
        panelTDQD_mid.add(new JLabel("Thời gian dừng tối đa"));
        tfTDQD_tgDungToiDa = new JTextField("tg dừng tối đa");
        panelTDQD_mid.add(tfTDQD_tgDungToiDa);
        panelTDQD_mid.add(new JLabel("Thời gian chậm nhất đặt vé"));
        tfTDQD_tgChamNhatDatVe = new JTextField("Thời gian chậm nhất đặt vé");
        panelTDQD_mid.add(tfTDQD_tgChamNhatDatVe);
        panelTDQD_mid.add(new JLabel("Thời gian chậm nhất hủy vé"));
        tfTDQD_tgChamNhatHuyVe = new JTextField("Thời gian chậm nhất hủy vé");
        panelTDQD_mid.add(tfTDQD_tgChamNhatHuyVe);
        panelTDQD_mid.add(new JLabel());
        btnTDQD_Sua = new JButton("Sửa");
        btnTDQD_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/edit.png")));
        panelTDQD_mid.add(btnTDQD_Sua);
        panelTDQD_mid.add(new JLabel());
        panelTDQD_mid.add(new JLabel());
        panelTDQD_mid.add(new JLabel());
        panelTDQD_mid.add(new JLabel());
        
        panelThayDoiQD.add(panelTDQD_mid,BorderLayout.CENTER);
        
    }
   public  void  QuanLyTaiKhoan()
   {
       panelQuanlyTK = new JPanel();
       panelQuanlyTK.setLayout(new BorderLayout(20,20));
      
       lbQLTK_Title = new JLabel("QUẢN LÝ TÀI KHOẢN");
       lbQLTK_Title.setFont(new Font("Tahoma", 1, 24));
       lbQLTK_Title.setOpaque(true);
       lbQLTK_Title.setBackground(new Color(102, 255, 102));
       lbQLTK_Title.setForeground(new Color(0,0,255));;
       lbQLTK_Title.setHorizontalAlignment(JLabel.CENTER);
       panelQuanlyTK.add(lbQLTK_Title,BorderLayout.PAGE_START); 
       
       panelQLTK_contain = new JPanel();
       panelQLTK_contain.setLayout(new BorderLayout(20, 20));
       panelQLTK_DangXuat = new JPanel();
       panelQLTK_DangXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
       btnQLTK_DangXuat = new JButton("Đăng xuất");
       panelQLTK_DangXuat.add(btnQLTK_DangXuat);
       panelQLTK_DangXuat.add(new JLabel());
       panelQLTK_contain.add(panelQLTK_DangXuat,BorderLayout.PAGE_START);
       
       pannelQLTK_1 = new JPanel();
       pannelQLTK_1.setPreferredSize(new Dimension(350, 600));
       pannelQLTK_2 = new JPanel();
       pannelQLTK_2.setPreferredSize(new Dimension(350, 600));
       
       panelQLTK_contain.add(pannelQLTK_1,BorderLayout.LINE_START);
       panelQLTK_contain.add(pannelQLTK_2,BorderLayout.LINE_END);
       
       panelQLTK_mid = new JPanel();
       panelQLTK_mid.setLayout(new GridLayout(9, 2,10,10));
       panelQLTK_mid.add(new JLabel("Username"));
       tfQLTK_Username = new JTextField("Username");
       panelQLTK_mid.add(tfQLTK_Username);
       panelQLTK_mid.add(new JLabel("Password"));
       pfQLTK_Pass = new JPasswordField();
       panelQLTK_mid.add(pfQLTK_Pass);
       panelQLTK_mid.add(new JLabel("New Password"));
       pfQLTK_NewPass = new JPasswordField();
       panelQLTK_mid.add(pfQLTK_NewPass);
        btnQLTK_Reset = new JButton("Reset");
        panelQLTK_mid.add(btnQLTK_Reset);
        btnQLTK_SignUp = new JButton("Sign up");
        panelQLTK_mid.add(btnQLTK_SignUp);
        panelQLTK_mid.add(new JLabel());
        panelQLTK_mid.add(new JLabel());
        panelQLTK_mid.add(new JLabel());
        panelQLTK_mid.add(new JLabel());
        panelQLTK_mid.add(new JLabel());
        
        panelQLTK_contain.add(panelQLTK_mid,BorderLayout.CENTER);
        
        panelQuanlyTK.add(panelQLTK_contain,BorderLayout.CENTER);
       
       
       
       
   }
  
         //EVENT
//    private void btnQLSB_ThemActionPerformed(java.awt.event.ActionEvent evt) {                                         
//        // TODO add your handling code here:
//         listSanBay = new SanBayDAO().getListSanBay();
//        
//        SanBay s = new SanBay();
//        s.setMaSanBay(tfQLSB_MaSanBay.getText());
//        s.setTenSanBay(tfQLSB_TenSanBay.getText());
//        s.setTenThanhPho(tfQLSB_TenThanhPho.getText()); 
//        
//        listSanBay.add(s); // them vao danh sach SV
//        inSanBay();
//System.out.println("day ne");
//    }                                        
//  
  
}



