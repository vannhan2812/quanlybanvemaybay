/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MayBay;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class MayBayDAO {
    public ArrayList<MayBay> getListMayBay() throws SQLException
    {
        ArrayList<MayBay>list = new ArrayList<>();
        String sql="select*from dbo.MAYBAY";
        System.out.println(sql);
        ResultSet rs = DBConnection.dbExcuteQuery(sql);
        while(rs.next())
        {
            MayBay sb = new MayBay();
            sb.setMaMayBay(rs.getString(1));
            sb.setTenMaybay(rs.getString(2));
            sb.setSoluongGhe(rs.getInt(3));
            list.add(sb);
        }
        return list;
    }
    public boolean themMayBay(String maMayBay,String tenMayBay,int soluongGhe ) throws SQLException
    {
        String sql="INSERT INTO dbo.MAYBAY(MAMAYBAY, TENMAYBAY, SOLUONGGHE ) VALUES (  N'MB"+maMayBay+"', N'"+tenMayBay+"' , N'"+soluongGhe+"')";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row>0) return true;
        else return false;
    }
    public boolean xoaMayBay(String maMayBay) throws SQLException
    {
        String sql="DELETE dbo.MAYBAY WHERE MAMAYBAY = '"+ maMayBay+ "'";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row > 0) return true;
        else return false;
    }
    public boolean suaMayBay(String maMayBay,String tenMayBay,int soluongGhe)throws SQLException
    {
        String sql ="UPDATE dbo.MAYBAY SET TENMAYBAY = N'"+tenMayBay+"' , SOLUONGGHE = N'"+soluongGhe+"' WHERE MAMAYBAY = N'"
                +maMayBay+"'";
        System.out.println(sql);
        int row = DBConnection.dbExcuteUpdate(sql);
        if(row > 0) return true;
        else return false;
    }
    public int maxMaMayBay()
    {
        String sql="SELECT MAX(MAMAYBAY) AS maxcd FROM dbo.MAYBAY";
        ResultSet rs =null;
        int max=0;
        String maxstring;
        try{
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
            Logger.getLogger(MayBayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
}
