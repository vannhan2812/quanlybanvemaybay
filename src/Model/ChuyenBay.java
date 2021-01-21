/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author Scoobydo
 */
public class ChuyenBay {
    private String  maChuyenBay;
    private String maTuyenBay;
    private String maMayBay;
    private Date ngayKhoiHanh;
    private Time gioKhoiHanh;
    private float thoiGianBay;

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getMaTuyenBay() {
        return maTuyenBay;
    }

    public void setMaTuyenBay(String maTuyenBay) {
        this.maTuyenBay = maTuyenBay;
    }

    public String getMaMayBay() {
        return maMayBay;
    }

    public void setMaMayBay(String maMayBay) {
        this.maMayBay = maMayBay;
    }

    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(Date ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public Time getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    public void setGioKhoiHanh(Time gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }

    public float getThoiGianBay() {
        return thoiGianBay;
    }

    public void setThoiGianBay(float thoiGianBay) {
        this.thoiGianBay = thoiGianBay;
    }
    
    
}
