/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SanBay;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scoobydo
 */
public class SanBayDAO {

    public ArrayList<SanBay> getListSanBay() throws SQLException{
        ArrayList<SanBay> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.SANBAY";
        System.out.println(sql);
        ResultSet rs=DBConnection.dbExcuteQuery(sql);
        while(rs.next()){
            SanBay sb = new SanBay();
            sb.setMaSanBay(rs.getString(1));
            sb.setTenSanBay(rs.getString(2));
            sb.setTenThanhPho(rs.getString(3));
            list.add(sb);
        }
        return list;
    }
    public SanBay getSanBayByID(String maSanBay) throws SQLException{
        SanBay sb = null;
        String sql = "SELECT * FROM dbo.SANBAY WHERE MASANBAY = N'"+maSanBay+"'";
        System.out.println(sql);
        ResultSet rs=DBConnection.dbExcuteQuery(sql);
        while(rs.next()){
            sb = new SanBay();
            sb.setMaSanBay(rs.getString(1));
            sb.setTenSanBay(rs.getString(2));
            sb.setTenThanhPho(rs.getString(3));
        }
        return sb;
    }
    public  boolean themSanBay(String maSanBay, String tenSanBay, String tenThanhPho) throws SQLException{
        String sql ="INSERT INTO dbo.SANBAY(MASANBAY, TENSANBAY, TENTHANHPHO ) VALUES (  N'SB"+maSanBay+"', N'"+tenSanBay+"' , N'"+tenThanhPho+"')";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0)
            return true;
        else
            return false;    
    }
    public int maxMaSanBay(){
        String sql = "SELECT MAX(MASANBAY) AS maxcd FROM dbo.SANBAY";
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
    public  boolean xoaSanBay(String maSanBay) throws SQLException{
        
        String sql ="DELETE dbo.SANBAY WHERE MASANBAY = '"+ maSanBay+ "'";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0)
            return true;
        else
            return false;       
    }    
    public  boolean suaSanBay(String maSanBay, String tenSanBay, String tenThanhPho) throws SQLException{
        String sql ="UPDATE dbo.SANBAY SET TENSANBAY = N'"+tenSanBay+"' , TENTHANHPHO = N'"+tenThanhPho+"' WHERE MASANBAY = N'"
                +maSanBay+"'";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
}
