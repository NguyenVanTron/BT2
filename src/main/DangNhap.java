package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.TaiKhoanDAO;
import giaovu.GiaoVu;
import pojo.TaiKhoan;
import sinhvien.SinhVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
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
import java.awt.Container;

public class DangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static DangNhap framedn = new DangNhap();
	//private static GiaoVu framegv = new GiaoVu();
	//private static SinhVien framesv = new SinhVien();
	/**
	 * Launch the application.
	 */
	
	 private static void createAndShowGUI() {
		 try {
				framedn.setVisible(true);
		} catch (Exception e) {
				e.printStackTrace();
		}
	 }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
				/*try {
					 DangNhap framedn = new DangNhap();
					 framedn.setVisible(true);
				} catch (Exception e) {
						e.printStackTrace();
				}*/
			}
		});
	}

	/**
	 * Create the frame.
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
	private List<TaiKhoan> listTK = new ArrayList<TaiKhoan>();
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
	
	public DangNhap() {
		listTK.addAll(TaiKhoanDAO.layDanhSachTaiKhoan());
		setBackground(new Color(135, 206, 250));
		setTitle("\u0110\u0103ng nh\u1EADp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E0n H\u00ECnh \u0110\u0103ng Nh\u1EADp");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setBounds(105, 30, 213, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn t\u00E0i kho\u1EA3n");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(67, 91, 94, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBounds(171, 88, 132, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMtKhu.setBounds(67, 119, 75, 14);
		contentPane.add(lblMtKhu);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passwordField.setBounds(171, 119, 132, 20);
		contentPane.add(passwordField);
		
		JButton btnngNhp = new JButton("\u0110\u0103ng nh\u1EADp");
		btnngNhp.setBackground(new Color(255, 160, 122));
		btnngNhp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnngNhp.setBounds(143, 172, 106, 32);
		contentPane.add(btnngNhp);
		btnngNhp.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pass = encryptPassword(passwordField.getText());
				boolean loai = false;
				boolean flag = false;
				boolean flag2 = false;
				String tensinhvien = new String();
				String masosv = new String();
				String mak = new String();
				for(int i = 0;i< listTK.size();i++){
					if(listTK.get(i).getMaTaiKhoan().equals(textField.getText())){
						if(pass.equals(listTK.get(i).getPassword())){
							if(listTK.get(i).getLoai()){
								loai = true; 
								tensinhvien = listTK.get(i).getTenNguoiDung();
								masosv = listTK.get(i).getMaTaiKhoan();
								mak = listTK.get(i).getPassword();
							}
							tensinhvien = listTK.get(i).getTenNguoiDung();
							masosv = listTK.get(i).getMaTaiKhoan();
						}else {
							OptionPaneError("Mật khẩu không đúng !");
							flag2=true;
							break;
						}
						flag=true;
						break;
					}
				}
				if(flag2){
					return;
				}
				if((!flag)){
					OptionPaneError("Tài khoản không đúng !");
					return;
				}
				if(!loai){
					framedn.setVisible(false);
					try {
						setVisible(false);
						GiaoVu framegv = new GiaoVu(masosv  +"-"+ tensinhvien);
						framegv.setResizable(false);
				        //frame.setPreferredSize(new Dimension(1400, 730));
						framegv.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
				else{
					framedn.setVisible(false);
					try {
						setVisible(false);
						SinhVien framesv = new SinhVien( masosv  +"-"+ tensinhvien +"-" + mak);
						framesv.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
	}
}
