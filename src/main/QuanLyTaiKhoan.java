package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.TaiKhoanDAO;
import pojo.TaiKhoan;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.awt.Color;

public class QuanLyTaiKhoan extends JDialog {
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			QuanLyTaiKhoan dialog = new QuanLyTaiKhoan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	private static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}
	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	public void OptionPaneError(String active){
	    JOptionPane.showMessageDialog(this,active,"Thông báo !",JOptionPane.WARNING_MESSAGE);     
	} 
	public void OptionPaneSuccess(String active){
	    JOptionPane.showMessageDialog(this,active,"Thông báo !",JOptionPane.DEFAULT_OPTION);     
	} 
	public QuanLyTaiKhoan(String thongtintaikhoan) {
		String mssve[] = new String[]{};
		mssve = thongtintaikhoan.split("-");
		String maTaiKhoan = mssve[0];
		String tennguoidung = mssve[1];
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.ORANGE);
		setTitle("\u0110\u1ED5i m\u1EADt kh\u1EA9u");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(getParent());
		setModal(true);
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		passwordField.setBounds(202, 77, 136, 24);
		getContentPane().add(passwordField);
		
		JLabel lblThayiMt = new JLabel("Thay \u0111\u1ED5i m\u1EADt kh\u1EA9u");
		lblThayiMt.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblThayiMt.setBounds(120, 11, 206, 37);
		getContentPane().add(lblThayiMt);
		
		JLabel lblNhpMtKhu = new JLabel("Nh\u1EADp m\u1EADt kh\u1EA9u c\u0169");
		lblNhpMtKhu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNhpMtKhu.setBounds(56, 80, 136, 21);
		getContentPane().add(lblNhpMtKhu);
		
		JLabel lblNhpMtKhu_1 = new JLabel("Nh\u1EADp m\u1EADt kh\u1EA9u m\u1EDBi");
		lblNhpMtKhu_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNhpMtKhu_1.setBounds(56, 122, 136, 17);
		getContentPane().add(lblNhpMtKhu_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		passwordField_1.setBounds(202, 118, 136, 24);
		getContentPane().add(passwordField_1);
		
		JLabel lblNhpLiMt = new JLabel("Nh\u1EADp l\u1EA1i m\u1EADt kh\u1EA9u ");
		lblNhpLiMt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNhpLiMt.setBounds(56, 155, 136, 24);
		getContentPane().add(lblNhpLiMt);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		passwordField_2.setBounds(202, 157, 136, 22);
		getContentPane().add(passwordField_2);
		
		JButton btnHonTt = new JButton("Ho\u00E0n t\u1EA5t");
		btnHonTt.setBackground(Color.GREEN);
		btnHonTt.setBounds(103, 216, 89, 23);
		getContentPane().add(btnHonTt);
		
		JButton btnHy = new JButton("H\u1EE7y");
		btnHy.setBackground(new Color(255, 0, 0));
		btnHy.setBounds(237, 216, 89, 23);
		getContentPane().add(btnHy);
		btnHonTt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(passwordField.getText().equals("")){
					OptionPaneError("Bạn không được để trống mật khẩu !");
					return;
				}
				if(passwordField_1.getText().toString().equals("")){
					OptionPaneError("Bạn không được để trống mật khẩu !");return;
				}
				if(passwordField_2.getText().toString().equals("")){
					OptionPaneError("Bạn không được để trống mật khẩu !");return;
				}
				TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoanSinhVien(maTaiKhoan);
				String mkc = passwordField.getText();
				String matkhaucu = encryptPassword(mkc);
				if(tk.getPassword().equals(matkhaucu)){
					if(passwordField_1.getText().equals(passwordField_2.getText())){
						if(tk.getLoai()){
							String matkhaumoi = encryptPassword(passwordField_1.getText());
							tk.setPassword(matkhaumoi);
							TaiKhoanDAO.capNhatThongTinSinhVien(tk);
						}
						else{
							String matkhaumoi = encryptPassword(passwordField_1.getText());
							tk.setPassword(matkhaumoi);
							TaiKhoanDAO.capNhatThongTinGiaoVu(tk);
						}
						OptionPaneError("Đổi mật khẩu thành công!");
						setVisible(false);
					}else{
						OptionPaneError("Nhập lại mật khẩu không đúng!");
					}
				}else {
					OptionPaneError("Mật khẩu không đúng !");
					return;
				}
				
			}
		});
		btnHy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
	}
}
