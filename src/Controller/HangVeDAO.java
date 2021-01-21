/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.HangVe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class HangVeDAO {
    public ArrayList<HangVe>getListHangVe() throws SQLException
    {
        ArrayList<HangVe>list = new ArrayList<>();
        String sql="SELECT *FROM dbo.HANGVE";
        System.out.println(sql);
        ResultSet rs = DBConnection.dbExcuteQuery(sql);
        while(rs.next())
        {
            HangVe hv = new HangVe();
            hv.setMaHangVe(rs.getString(1));
            hv.setTenHangVe(rs.getString(2));
            list.add(hv);
        }
        return list;
    }
    public boolean themHangVe(String maHangVe,String tenHangVe) throws SQLException
    {
        String sql="INSERT INTO dbo.HANGVE VALUES(N'HV"+maHangVe+"',N'"+tenHangVe+"')";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0) return true;
        else return false;
    }
    public boolean xoaHangVe(String maHangVe) throws SQLException
    {
        String sql="DELETE dbo.HANGVE WHERE MAHANGVE = '"+maHangVe+"'";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0) return true;
        else return false;
    }
    public boolean suaHangVe(String maHangVe,String tenHangVe) throws SQLException
    {
        String sql="UPDATE dbo.HANGVE SET TENHANGVE =N'"+tenHangVe+"' WHERE MAHANGVE =N'"+maHangVe+"'";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0) return true;
        else return false;
    }
    public int maxMaHangVe() throws SQLException
    {
        String sql="SELECT MAX(MAHANGVE) AS maxcd FROM dbo.HANGVE";
        ResultSet rs=null;
        int max=0;
        String maxstring;
        try{
            rs = DBConnection.dbExcuteQuery(sql);
            while(rs.next())
            {
                maxstring = rs.getString("maxcd");
                if(maxstring==null)
                {
                    max=0;
                }
                else{
                    max = Integer.parseInt(maxstring.substring(2));
                }
                System.out.println(max);
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(HangVeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
}
