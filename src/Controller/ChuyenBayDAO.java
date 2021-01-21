/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ChuyenBay;
import Model.TuyenBay;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Scoobydo
 */
public class ChuyenBayDAO {
     public ArrayList<ChuyenBay> getListChuyenBay() throws SQLException{
        ArrayList<ChuyenBay> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.CHUYENBAY";
        System.out.println(sql);
        ResultSet rs=DBConnection.dbExcuteQuery(sql);
        while(rs.next()){
            ChuyenBay cb = new ChuyenBay();
            cb.setMaChuyenBay(rs.getString(1));
            cb.setMaTuyenBay(rs.getString(2));
            cb.setMaMayBay(rs.getString(3));
            cb.setNgayKhoiHanh(rs.getDate(4));
            cb.setGioKhoiHanh(rs.getTime(5));
            cb.setThoiGianBay(rs.getFloat(6));
            list.add(cb);
        }
        return list;
    }
}
