/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybanvemaybay;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import quanlybanvemaybay.QuanLyBanVeMayBay;

/**
 *
 * @author Scoobydo
 */
public class Login {

    JFrame frLogin;
    JTextField tfLogin_userName;
    JPasswordField pfLogin_pass;
    JButton btnLogin;
    JLabel label1, label2, lbTitle, lbImage;

    public Login() {
        frLogin = new JFrame("Login");
        frLogin.setLayout(null);
        frLogin.setResizable(false);
        frLogin.setSize(471, 280);
        frLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frLogin.setLocationRelativeTo(null);
        

        lbTitle = new JLabel("ĐĂNG NHẬP");
        lbTitle.setFont(new Font("Tahoma", 1, 24));
        lbTitle.setOpaque(true);
        lbTitle.setBackground(new Color(102, 255, 102));
        lbTitle.setForeground(new Color(0, 0, 255));
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setSize(471, 42);
        lbTitle.setLocation(0, 0);

        label1 = new JLabel("Username:");
        label1.setSize(70, 17);
        label1.setLocation(225, 60);

        label2 = new JLabel("Password:");
        label2.setSize(70, 17);
        label2.setLocation(225, 126);

        pfLogin_pass = new JPasswordField();
        pfLogin_pass.setSize(211, 25);
        pfLogin_pass.setLocation(226, 156);

        tfLogin_userName = new JTextField();
        tfLogin_userName.setSize(211, 25);
        tfLogin_userName.setLocation(226, 86);

        btnLogin = new JButton();
        btnLogin.setSize(98, 35);
        btnLogin.setLocation(223, 208);
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/btnLogin.jpg")));

        lbImage = new JLabel();
        lbImage.setSize(182, 185);
        lbImage.setLocation(14, 54);
        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlybanvemaybay/image/login.PNG")));

        frLogin.add(lbTitle);
        frLogin.add(label1);
        frLogin.add(label2);
        frLogin.add(btnLogin);
        frLogin.add(lbImage);
        frLogin.add(tfLogin_userName);
        frLogin.add(pfLogin_pass);
        frLogin.setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

}
