/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SanBay;
import Model.TuyenBay;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scoobydo
 */
public class TuyenBayDAO {
     public ArrayList<TuyenBay> getListTuyenBay() throws SQLException{
        ArrayList<TuyenBay> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.TUYENBAY";
        System.out.println(sql);
        ResultSet rs=DBConnection.dbExcuteQuery(sql);
        while(rs.next()){
            TuyenBay sb = new TuyenBay();
            sb.setMaTuyenBay(rs.getString(1));
            sb.setMaSanBayDi(rs.getString(2));
            sb.setMaSanBayDen(rs.getString(3));
            list.add(sb);
        }
        return list;
    }
      public  boolean themTuyenBay(String maTuyenBay, String maSanBayDi, String maSanBayDen) throws SQLException{
        String sql ="INSERT INTO dbo.TUYENBAY(MATUYENBAY, MASANBAYDI, MASANBAYDEN ) VALUES (  N'TB"+maTuyenBay+"', N'"+maSanBayDi+"' , N'"+maSanBayDen+"')";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0)
            return true;
        else
            return false;    
    }
      public boolean xoaTuyenBay(String maTuyenBay) throws SQLException
      {
        String sql ="DELETE dbo.TUYENBAY WHERE MATUYENBAY = '"+ maTuyenBay+ "'";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0)
            return true;
        else
            return false;       
    }    
      public boolean suaTuyenBay(String maTuyenBay, String maSanBayDi, String maSanBayDen) throws SQLException
      {
          String sql ="UPDATE dbo.TUYENBAY SET MASANBAYDI = N'"+maSanBayDi+"', MASANBAYDEN=N'"+maSanBayDen+"' WHERE MATUYENBAY = N'"
                +maTuyenBay+"'";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0)
            return true;
        else
            return false;
      }
    
    public int maxMaTuyenBay(){
        String sql = "SELECT MAX(MATUYENBAY) AS maxcd FROM dbo.TUYENBAY";
        ResultSet rs = null;
        int max=0;
        String maxstring;
        try {
            rs=DBConnection.dbExcuteQuery(sql);
                while (rs.next()){
                    maxstring=rs.getString("maxcd");
                    if(maxstring==null)
                    {
                        max=0;
                    }
                    else
                        max=Integer.parseInt(maxstring.substring(2));
                    System.out.println(max);
                }
            } 
        catch (SQLException ex) {
            Logger.getLogger(SanBayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
}
