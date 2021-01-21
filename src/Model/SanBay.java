/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Scoobydo
 */
public class SanBay {
    String maSanBay;
    String  tenSanBay, tenThanhPho;

    public SanBay() {
    }

    public SanBay(String maSanBay, String tenSanBay, String tenThanhPho) {
        this.maSanBay = maSanBay;
        this.tenSanBay = tenSanBay;
        this.tenThanhPho = tenThanhPho;
    }

    public String getMaSanBay() {
        return maSanBay;
    }

    public void setMaSanBay(String maSanBay) {
        this.maSanBay = maSanBay;
    }

    public String getTenSanBay() {
        return tenSanBay;
    }

    public void setTenSanBay(String tenSanBay) {
        this.tenSanBay = tenSanBay;
    }

    public String getTenThanhPho() {
        return tenThanhPho;
    }

    public void setTenThanhPho(String tenThanhPho) {
        this.tenThanhPho = tenThanhPho;
    }
    @Override
    public String toString(){
        return tenSanBay;
    }
    
}
