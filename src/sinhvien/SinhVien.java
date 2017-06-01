package sinhvien;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.Date;
import java.util.Formatter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DanhSachSinhVienDAO;
import dao.MonHocDAO;
import dao.TaiKhoanDAO;
import main.DangNhap;
import main.QuanLyTaiKhoan;
import pojo.DanhSachDiemDanh;
import pojo.MonHoc;
import pojo.TaiKhoan;

import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JButton;
import java.awt.Color;

public class SinhVien extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	public String maTaiKhoanSinhVien;
	public String tenSinhvien;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinhVien framesv = new SinhVien();
					framesv.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	private List<MonHoc> listMonHoc = new ArrayList<MonHoc>();
	private String[] defaultValues0 = new String[]{};
    private String[] defaultValues = new String[]{};
	private JList list = createJList(defaultValues);
	private JList list0 = createJList(defaultValues0);
	public class DateLabelFormatter extends AbstractFormatter {
		 
	    private String datePattern = "dd/MM/yyyy";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	     
	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }
	 
	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }
	         
	        return "";
	    }
	 
	}
	private JTextField createTextField() {
        final JTextField field = new JTextField(15);
        field.getDocument().addDocumentListener(new DocumentListener(){
            private void filter() {
                String filter = field.getText();
                filterModel((DefaultListModel<String>)list.getModel(), filter,defaultValues);
            }
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filter();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filter();
			}
        });
        return field;
    }
	private JTextField createTextField0() {
        final JTextField field = new JTextField(15);
        field.getDocument().addDocumentListener(new DocumentListener(){
            private void filter() {
                String filter = field.getText();
                filterModel((DefaultListModel<String>)list0.getModel(), filter,defaultValues0);
            }
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filter();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filter();
			}
        });
        return field;
    }

    private JList createJList(String [] defaultValuess) {
        JList list1 = new JList(createDefaultListModel(defaultValuess));
        list1.setVisibleRowCount(6);
        return list1;
    }
    
    
    private ListModel<String> createDefaultListModel(String [] defaultValuess) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String s : defaultValuess) {
            model.addElement(s);
        }
        return model;
    }

    public void filterModel(DefaultListModel<String> model, String filter, String [] defaultValuess) {
        for (String s : defaultValuess) {
            if (!s.startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }
    private Date starttable = new Date();
	private Date endtable = new Date();
	private Time tstarttable = new Time(0,0,0);
	private Time tendtable = new Time(0,0,0);
	private String monhoctable;
	public String mak;
	public SinhVien(String thongtin) {
		String mssve[] = new String[]{};
		mssve = thongtin.split("-");
		maTaiKhoanSinhVien = mssve[0];
		tenSinhvien = mssve[1];
		mak = mssve[2];
		if(mak.equals(encryptPassword(maTaiKhoanSinhVien))){
			QuanLyTaiKhoan quanly = new QuanLyTaiKhoan(maTaiKhoanSinhVien+"-"+ tenSinhvien);
			
			quanly.show();
		}
		setTitle("Sinh vi\u00EAn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1203, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(192, 192, 192));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 43, 1164, 539);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("\u0110i\u1EC3m danh", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh s\u00E1ch c\u00E1c m\u00F4n h\u1ECDc \u0111\u00E3 \u0111\u0103ng k\u00ED");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 224, 23);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 200, 429);
		list.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		scrollPane.setViewportView(list);
		panel.add(scrollPane);
		//JList<String> list = new JList<String>();
		//
		//listMonHoc.addAll(MonHocDAO.layDanhSachMonHoc());
		listMonHoc.addAll(MonHocDAO.layDanhSachMonHocTheoSinhVien(maTaiKhoanSinhVien));
		String [] listModel = new String[]{};
		List<String> arrl = new ArrayList<String>();
		for(int i = 0;i<listMonHoc.size();i++){
			arrl.add(listMonHoc.get(i).getTenMonHoc());
		}
		listModel = arrl.toArray(new String[0]);
		defaultValues = listModel;
		defaultValues0 = listModel;
		
		textField = new JTextField();
		textField = createTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBounds(10, 38, 200, 23);
		textField.setText("Nhập tên môn học");
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblBngimDanh = new JLabel("B\u1EA3ng \u0110i\u1EC3m Danh");
		lblBngimDanh.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblBngimDanh.setBounds(546, 31, 175, 33);
		panel.add(lblBngimDanh);
		
		
		
		table = new JTable();
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setModel(modelkqsinhvien);
		
		table.setBounds(265, 360, 867, 80);
		panel.add(table);
		
		JLabel label = new JLabel("T\u00EAn m\u00F4n h\u1ECDc");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBounds(322, 73, 95, 33);
		panel.add(label);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setToolTipText("Nh\u1EADp t\u00EAn m\u00F4n h\u1ECDc");
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(427, 83, 208, 23);
		panel.add(textField_1);
		
		JLabel label_1 = new JLabel("Ng\u00E0y b\u1EAFt \u0111\u1EA7u");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_1.setBounds(322, 117, 86, 33);
		panel.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setToolTipText("Nh\u1EADp ng\u00E0y b\u1EAFt \u0111\u1EA7u");
		textField_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(427, 130, 208, 23);
		panel.add(textField_2);
		
		JLabel label_2 = new JLabel("Gi\u1EDD b\u1EAFt \u0111\u1EA7u");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_2.setBounds(322, 219, 86, 28);
		panel.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(427, 223, 208, 20);
		panel.add(textField_3);
		
		JLabel label_3 = new JLabel("Gi\u1EDD k\u1EBFt th\u00FAc");
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_3.setBounds(656, 225, 95, 21);
		panel.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(756, 223, 220, 20);
		panel.add(textField_4);
		
		JLabel label_4 = new JLabel("Th\u1EE9 trong tu\u1EA7n");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_4.setBounds(322, 179, 95, 20);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Ng\u00E0y k\u1EBFt th\u00FAc");
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_5.setBounds(656, 129, 86, 28);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("M\u00E3 m\u00F4n h\u1ECDc");
		label_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_6.setBounds(656, 74, 86, 32);
		panel.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setToolTipText("Nh\u1EADp m\u00E3 m\u00F4n h\u1ECDc");
		textField_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_5.setColumns(10);
		textField_5.setBounds(752, 83, 224, 23);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_6.setColumns(10);
		textField_6.setBounds(754, 133, 222, 20);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_7.setColumns(10);
		textField_7.setBounds(427, 179, 208, 20);
		panel.add(textField_7);
		
		JLabel lblTnPhngHc = new JLabel("Tên phòng học");
		lblTnPhngHc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTnPhngHc.setBounds(656, 179, 115, 28);
		panel.add(lblTnPhngHc);
		
		textField_16 = new JTextField();
		textField_16.setEditable(false);
		textField_16.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_16.setColumns(10);
		textField_16.setBounds(754, 179, 222, 20);
		panel.add(textField_16);
		
		JButton btnDiemDanh = new JButton("Điểm Danh");
		btnDiemDanh.setBackground(new Color(255, 165, 0));
		btnDiemDanh.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnDiemDanh.setBounds(1017, 11, 115, 79);
		btnDiemDanh.hide();
		panel.add(btnDiemDanh);
		
		lblThngTinim = new JLabel("Thông tin điểm danh");
		lblThngTinim.setForeground(new Color(255, 0, 0));
		lblThngTinim.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblThngTinim.setBounds(632, 312, 156, 23);
		panel.add(lblThngTinim);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Xem k\u1EBFt qu\u1EA3", null, panel_1, null);
		panel_1.setBackground(new Color(135, 206, 250));
		panel_1.setLayout(null);
		
		JLabel label_7 = new JLabel("Danh s\u00E1ch c\u00E1c m\u00F4n h\u1ECDc \u0111\u00E3 \u0111\u0103ng k\u00ED");
		label_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_7.setBounds(10, 11, 224, 23);
		panel_1.add(label_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 71, 200, 429);
		list0.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		scrollPane_1.setViewportView(list0);
		panel_1.add(scrollPane_1);
		
		textField_8 = new JTextField();
		textField_8 = createTextField0();
		textField_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_8.setColumns(10);
		textField_8.setBounds(10, 38, 200, 23);
		textField_8.setText("Nhập tên môn học");
		panel_1.add(textField_8);
		
		JLabel lblKtQuim = new JLabel("K\u1EBFt Qu\u1EA3 \u0110i\u1EC3m Danh");
		lblKtQuim.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblKtQuim.setBounds(527, 31, 200, 33);
		panel_1.add(lblKtQuim);
		
		JLabel label_9 = new JLabel("T\u00EAn m\u00F4n h\u1ECDc");
		label_9.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_9.setBounds(386, 75, 95, 17);
		panel_1.add(label_9);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setToolTipText("Nh\u1EADp t\u00EAn m\u00F4n h\u1ECDc");
		textField_9.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_9.setColumns(10);
		textField_9.setBounds(482, 73, 134, 20);
		panel_1.add(textField_9);
		
		JLabel label_10 = new JLabel("Ng\u00E0y b\u1EAFt \u0111\u1EA7u");
		label_10.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_10.setBounds(386, 129, 86, 28);
		panel_1.add(label_10);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setToolTipText("Nh\u1EADp ng\u00E0y b\u1EAFt \u0111\u1EA7u");
		textField_10.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_10.setColumns(10);
		textField_10.setBounds(482, 133, 136, 20);
		panel_1.add(textField_10);
		
		JLabel label_11 = new JLabel("Gi\u1EDD b\u1EAFt \u0111\u1EA7u");
		label_11.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_11.setBounds(386, 221, 86, 28);
		panel_1.add(label_11);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_11.setColumns(10);
		textField_11.setBounds(482, 225, 134, 20);
		panel_1.add(textField_11);
		
		JLabel label_12 = new JLabel("Gi\u1EDD k\u1EBFt th\u00FAc");
		label_12.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_12.setBounds(656, 225, 95, 21);
		panel_1.add(label_12);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_12.setColumns(10);
		textField_12.setBounds(752, 227, 134, 20);
		panel_1.add(textField_12);
		
		JLabel label_13 = new JLabel("Th\u1EE9 trong tu\u1EA7n");
		label_13.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_13.setBounds(557, 179, 95, 20);
		panel_1.add(label_13);
		
		JLabel label_14 = new JLabel("Ng\u00E0y k\u1EBFt th\u00FAc");
		label_14.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_14.setBounds(656, 129, 86, 28);
		panel_1.add(label_14);
		
		JLabel label_15 = new JLabel("M\u00E3 m\u00F4n h\u1ECDc");
		label_15.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_15.setBounds(656, 74, 86, 17);
		panel_1.add(label_15);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setToolTipText("Nh\u1EADp m\u00E3 m\u00F4n h\u1ECDc");
		textField_13.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_13.setColumns(10);
		textField_13.setBounds(752, 72, 136, 20);
		panel_1.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_14.setColumns(10);
		textField_14.setBounds(754, 133, 134, 20);
		panel_1.add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_15.setColumns(10);
		textField_15.setBounds(665, 179, 134, 20);
		panel_1.add(textField_15);
		
		JLabel label_8 = new JLabel("Giờ bắt đầu");
		label_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_8.setBounds(521, 256, 86, 28);
		panel_1.add(label_8);
		
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_17.setColumns(10);
		textField_17.setBounds(617, 260, 134, 20);
		panel_1.add(textField_17);
		
		week1 = new JTextField();
		week1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week1.setBackground(new Color(255, 255, 255));
		week1.setBounds(370, 295, 86, 59);
		panel_1.add(week1);
		week1.setColumns(10);
		
		week6 = new JTextField();
		week6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week6.setColumns(10);
		week6.setBounds(370, 365, 86, 59);
		panel_1.add(week6);
		
		week11 = new JTextField();
		week11.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week11.setColumns(10);
		week11.setBounds(370, 435, 86, 59);
		panel_1.add(week11);
		
		week12 = new JTextField();
		week12.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week12.setColumns(10);
		week12.setBounds(494, 435, 86, 59);
		panel_1.add(week12);
		
		week7 = new JTextField();
		week7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week7.setColumns(10);
		week7.setBounds(494, 365, 86, 59);
		panel_1.add(week7);
		
		week2 = new JTextField();
		week2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week2.setColumns(10);
		week2.setBounds(494, 295, 86, 59);
		panel_1.add(week2);
		
		week13 = new JTextField();
		week13.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week13.setColumns(10);
		week13.setBounds(619, 435, 86, 59);
		panel_1.add(week13);
		
		week8 = new JTextField();
		week8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week8.setColumns(10);
		week8.setBounds(619, 365, 86, 59);
		panel_1.add(week8);
		
		week3 = new JTextField();
		week3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week3.setColumns(10);
		week3.setBounds(619, 295, 86, 59);
		panel_1.add(week3);
		
		week14 = new JTextField();
		week14.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week14.setColumns(10);
		week14.setBounds(745, 435, 86, 59);
		panel_1.add(week14);
		
		week9 = new JTextField();
		week9.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week9.setColumns(10);
		week9.setBounds(745, 365, 86, 59);
		panel_1.add(week9);
		
		week4 = new JTextField();
		week4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week4.setColumns(10);
		week4.setBounds(745, 295, 86, 59);
		panel_1.add(week4);
		
		week15 = new JTextField();
		week15.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week15.setColumns(10);
		week15.setBounds(870, 435, 86, 59);
		panel_1.add(week15);
		
		week10 = new JTextField();
		week10.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week10.setColumns(10);
		week10.setBounds(870, 365, 86, 59);
		panel_1.add(week10);
		
		week5 = new JTextField();
		week5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		week5.setColumns(10);
		week5.setBounds(870, 295, 86, 59);
		panel_1.add(week5);
		
		txtCMt = new JTextField();
		txtCMt.setText("       Có mặt");
		txtCMt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCMt.setColumns(10);
		txtCMt.setBackground(new Color(124, 252, 0));
		txtCMt.setBounds(991, 11, 104, 59);
		panel_1.add(txtCMt);
		
		txtVng = new JTextField();
		txtVng.setText("        Vắng");
		txtVng.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtVng.setColumns(10);
		txtVng.setBounds(991, 81, 104, 59);
		txtVng.setBackground(new Color(255, 160, 122));
		panel_1.add(txtVng);
		
		txtChaCD = new JTextField();
		txtChaCD.setText("Chưa có dữ liệu");
		txtChaCD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtChaCD.setColumns(10);
		txtChaCD.setBounds(991, 151, 104, 59);
		txtChaCD.setBackground(new Color(255, 255, 0));
		panel_1.add(txtChaCD);
		
		btnngXut = new JButton("Đăng Xuất");
		btnngXut.setBackground(new Color(255, 215, 0));
		btnngXut.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		btnngXut.setBounds(1057, 11, 117, 32);
		btnngXut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				DangNhap framedn = new DangNhap();
				framedn.show();
			}
		});
		contentPane.add(btnngXut);
		
		lbliHcKhoa = new JLabel("ĐẠI HỌC KHOA HỌC TỰ NHIÊN");
		lbliHcKhoa.setForeground(new Color(255, 255, 255));
		lbliHcKhoa.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbliHcKhoa.setBounds(474, 11, 221, 21);
		contentPane.add(lbliHcKhoa);
		
		lblKhoaCngNgh = new JLabel("Khoa công nghệ thông tin");
		lblKhoaCngNgh.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblKhoaCngNgh.setBounds(10, 18, 142, 14);
		contentPane.add(lblKhoaCngNgh);
		
		lblTnSinhVin = new JLabel("Thông tin sinh viên");
		lblTnSinhVin.setToolTipText("Nhấp vào để quản lý tài khoản");
		lblTnSinhVin.setForeground(new Color(255, 255, 255));
		lblTnSinhVin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTnSinhVin.setBounds(814, 16, 205, 21);
		lblTnSinhVin.setText(maTaiKhoanSinhVien +"-"+ tenSinhvien);
		lblTnSinhVin.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				QuanLyTaiKhoan qltk = new QuanLyTaiKhoan(maTaiKhoanSinhVien +"-"+ tenSinhvien);
				qltk.show();
			}
		});
		contentPane.add(lblTnSinhVin);
		list.addMouseListener(new MouseListener() {
			private Date starttable = new Date();
			private Date endtable = new Date();
			private Time tstarttable = new Time(0,0,0);
			private Time tendtable = new Time(0,0,0);
			private String monhoctable;
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				table.setEnabled(false);
				if(list.getSelectedValue()==null){
					return;
				}
				textField_1.setText(list.getSelectedValue().toString());
				for(int i = 0;i< listMonHoc.size();i++){
					if(list.getSelectedValue().equals(listMonHoc.get(i).getTenMonHoc())){
						textField_5.setText(listMonHoc.get(i).getMaMonHoc());
						
						textField_6.setText(format.format(listMonHoc.get(i).getNgayKetThuc()).toString());
						textField_2.setText(format.format(listMonHoc.get(i).getNgayBatDau()).toString());
						textField_16.setText(listMonHoc.get(i).getTenPhongHoc());
						textField_7.setText(listMonHoc.get(i).getThuTrongTuan().toString());
						
						textField_3.setText(listMonHoc.get(i).getThoiGianBatDau().toString());
						textField_4.setText(listMonHoc.get(i).getThoiGianKetThuc().toString());
						
						monhoctable = listMonHoc.get(i).getThuTrongTuan().toString();
						maMonHocLay = listMonHoc.get(i).getMaMonHoc();
						starttable = listMonHoc.get(i).getNgayBatDau();
						endtable = listMonHoc.get(i).getNgayKetThuc();
						tstarttable = listMonHoc.get(i).getThoiGianBatDau();
						tendtable = listMonHoc.get(i).getThoiGianKetThuc();
					}
				}
				
				
				String masv = maTaiKhoanSinhVien;
				String mamonhocnd =maMonHocLay;
				List<DanhSachDiemDanh> dsddsv = new ArrayList<DanhSachDiemDanh>();
				dsddsv = DanhSachSinhVienDAO.layTenSinhVien(maTaiKhoanSinhVien, maMonHocLay);
				if (modelkqsinhvien.getRowCount() > 0) {
				    for (int k = modelkqsinhvien.getRowCount() - 1; k > -1; k--) {
				    	modelkqsinhvien.removeRow(k);
					   }
				}
				boolean [] bdiemdanh = new boolean[15];
				if(dsddsv.size() ==0){
					table.setEnabled(false);
					modelkqsinhvien.addRow(new Object[]{});
					modelkqsinhvien.addRow(new Object[]{});
					modelkqsinhvien.addRow(new Object[]{});
					modelkqsinhvien.addRow(new Object[]{});
					modelkqsinhvien.addRow(new Object[]{});
					return;
					
				}
				String diemdanhsv = dsddsv.get(0).getDiemDanh();
				diemdanhcu = diemdanhsv;
				char c = '0';
				for(int x = 0; x < 15;x++){
					if(diemdanhsv.charAt(x) == c){
						bdiemdanh[x] = false;
					}
					else{
						bdiemdanh[x] = true;
					}
				}
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 1", bdiemdanh[0], " Tu\u1EA7n 6", bdiemdanh[5], " Tu\u1EA7n 11", bdiemdanh[10]});
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 2", bdiemdanh[1], " Tu\u1EA7n 7", bdiemdanh[6], " Tu\u1EA7n 12", bdiemdanh[11]});
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 3", bdiemdanh[2], " Tu\u1EA7n 8", bdiemdanh[7], " Tu\u1EA7n 13", bdiemdanh[12]});
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 4", bdiemdanh[3], " Tu\u1EA7n 9", bdiemdanh[8], " Tu\u1EA7n 14", bdiemdanh[13]});
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 5", bdiemdanh[4], " Tu\u1EA7n 10", bdiemdanh[9], " Tu\u1EA7n 15", bdiemdanh[14]});
				
				//-------------------------------------------------//
				btnDiemDanh.hide();
				if(checkTime(starttable,endtable,tstarttable,tendtable,monhoctable)){
					/*Calendar calendar = Calendar.getInstance();
					calendar.setTime(starttable);
					Calendar calendar1 = Calendar.getInstance();
					calendar1.setTime(today);
					int weekofyear = calendar.get(Calendar.WEEK_OF_YEAR);
					int weekofyear1 = calendar1.get(Calendar.WEEK_OF_YEAR);*/
					daybttd = getWorkingDaysBetweenTwoDates(starttable,today) + 21;
					if(bdiemdanh[daybttd/7]){
						return;
					}
					btnDiemDanh.show();
				}
				
			}
		});
		
		btnDiemDanh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*Calendar calendar = Calendar.getInstance();
				calendar.setTime(starttable);
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(today);
				int weekofyear = calendar.get(Calendar.WEEK_OF_YEAR);
				int weekofyear1 = calendar1.get(Calendar.WEEK_OF_YEAR);*/
				//getWorkingDaysBetweenTwoDates(starttable,today);
				//int tuanthu = getWorkingDaysBetweenTwoDates(starttable,today);
				int a = daybttd/7;
				if(checkTime(starttable,endtable,tstarttable,tendtable,monhoctable)){
					OptionPaneError("Đã hết hạn điểm danh !");btnDiemDanh.hide();
					return;
				}
				String masv = maTaiKhoanSinhVien;
				String mamonhoc = maMonHocLay;
				String maDS = "DS" + mamonhoc + masv;
				String diemdanhmoi = diemdanhcu.substring(0, a) + "1";
				int length = diemdanhmoi.length();
				for(int i = 0;i<15 - length;i++){
					diemdanhmoi = diemdanhmoi+"0";
				}
				
		    	DanhSachDiemDanh dsdd = new DanhSachDiemDanh(maDS,diemdanhmoi,masv,mamonhoc);
		    	if(DanhSachSinhVienDAO.suaSinhVien(dsdd, maDS)){
		    		System.out.println("Thành công!");
		    	}
		    	else{
		    		System.out.println("Không tìm thấy sinh viên");
		    	}
		    	if (modelkqsinhvien.getRowCount() > 0) {
				    for (int k = modelkqsinhvien.getRowCount() - 1; k > -1; k--) {
				    	modelkqsinhvien.removeRow(k);
					   }
				}
		    	boolean [] bdiemdanh = new boolean[15];
		    	char c = '0';
				for(int x = 0; x < 15;x++){
					if(diemdanhmoi.charAt(x) == c){
						bdiemdanh[x] = false;
					}
					else{
						bdiemdanh[x] = true;
					}
				}
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 1", bdiemdanh[0], " Tu\u1EA7n 6", bdiemdanh[5], " Tu\u1EA7n 11", bdiemdanh[10]});
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 2", bdiemdanh[1], " Tu\u1EA7n 7", bdiemdanh[6], " Tu\u1EA7n 12", bdiemdanh[11]});
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 3", bdiemdanh[2], " Tu\u1EA7n 8", bdiemdanh[7], " Tu\u1EA7n 13", bdiemdanh[12]});
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 4", bdiemdanh[3], " Tu\u1EA7n 9", bdiemdanh[8], " Tu\u1EA7n 14", bdiemdanh[13]});
				modelkqsinhvien.addRow(new Object[]{" Tu\u1EA7n 5", bdiemdanh[4], " Tu\u1EA7n 10", bdiemdanh[9], " Tu\u1EA7n 15", bdiemdanh[14]});
				OptionPaneSuccess("Đã điểm danh thành công !");
				btnDiemDanh.hide();
			}
		});
		list0.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(list0.getSelectedValue()==null){
					return;
				}
				textField_9.setText(list0.getSelectedValue().toString());
				for(int i = 0;i< listMonHoc.size();i++){
					if(list0.getSelectedValue().equals(listMonHoc.get(i).getTenMonHoc())){
						textField_13.setText(listMonHoc.get(i).getMaMonHoc());
						textField_14.setText(format.format(listMonHoc.get(i).getNgayKetThuc()).toString());
						textField_10.setText(format.format(listMonHoc.get(i).getNgayBatDau()).toString());
						textField_17.setText(listMonHoc.get(i).getTenPhongHoc());
						textField_15.setText(listMonHoc.get(i).getThuTrongTuan().toString());
						textField_11.setText(listMonHoc.get(i).getThoiGianBatDau().toString());
						textField_12.setText(listMonHoc.get(i).getThoiGianKetThuc().toString());
						monhoctable = listMonHoc.get(i).getThuTrongTuan().toString();
						maMonHocLay = listMonHoc.get(i).getMaMonHoc();
						starttable = listMonHoc.get(i).getNgayBatDau();
						endtable = listMonHoc.get(i).getNgayKetThuc();
					}
				}
				List<DanhSachDiemDanh> dsddsv = new ArrayList<DanhSachDiemDanh>();
				dsddsv = DanhSachSinhVienDAO.layTenSinhVien(maTaiKhoanSinhVien, maMonHocLay);
				String diemdanhmoi = dsddsv.get(0).getDiemDanh();
				boolean [] bdiemdanh = new boolean[15];
		    	char c = '0';
				for(int x = 0; x < 15;x++){
					if(diemdanhmoi.charAt(x) == c){
						bdiemdanh[x] = false;
					}
					else{
						bdiemdanh[x] = true;
					}
				}
				int noOfDays = -7;
				week1.setText(format.format(starttable).toString());
				week15.setText(format.format(endtable).toString());
				for( int i = 0; i <15;i++){
					noOfDays += 7;
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(starttable);            
					calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
					int year = calendar.get(Calendar.YEAR);
				    int month = calendar.get(Calendar.MONTH);
				    int day = calendar.get(Calendar.DAY_OF_MONTH);
					Date next = new Date(year - 1900,month,day);
					if(i==1){
						week2.setText(format.format(next).toString());
					}
					if(i==2){
						week3.setText(format.format(next).toString());
					}
					if(i==3){
						week4.setText(format.format(next).toString());
					}
					if(i==4){
						week5.setText(format.format(next).toString());
					}
					if(i==5){
						week6.setText(format.format(next).toString());
					}
					if(i==6){
						week7.setText(format.format(next).toString());
					}
					if(i==7){
						week8.setText(format.format(next).toString());
					}
					if(i==8){
						week9.setText(format.format(next).toString());
					}
					if(i==9){
						week10.setText(format.format(next).toString());
					}
					if(i==10){
						week11.setText(format.format(next).toString());
					}
					if(i==11){
						week12.setText(format.format(next).toString());
					}
					if(i==12){
						week13.setText(format.format(next).toString());
					}
					if(i==3){
						week14.setText(format.format(next).toString());
					}
					if(bdiemdanh[i] && (i==0) ){
						week1.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==1) ){
						week2.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==2) ){
						week3.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==3) ){
						week4.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==4) ){
						week5.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==5) ){
						week6.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==6) ){
						week7.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==7) ){
						week8.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==8) ){
						week9.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==9) ){
						week10.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==10) ){
						week11.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==11) ){
						week12.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==12) ){
						week13.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==13) ){
						week14.setBackground(new Color(124, 252, 0));
					}
					if(bdiemdanh[i] && (i==14) ){
						week15.setBackground(new Color(124, 252, 0));
					}
					
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==0){
						week1.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==1){
						week2.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==2){
						week3.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==3){
						week4.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==4){
						week5.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==5){
						week6.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==6){
						week7.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==7){
						week8.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==8){
						week9.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==9){
						week10.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==10){
						week11.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==11){
						week12.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==12){
						week13.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==13){
						week14.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.after(endtable) || today.after(next)) && i==14){
						week15.setBackground(new Color(255, 160, 122));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==0){
						week1.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==1){
						week2.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==2){
						week3.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==3){
						week4.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==4){
						week5.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==5){
						week6.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==6){
						week7.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==7){
						week8.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==8){
						week9.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==9){
						week10.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==10){
						week11.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==11){
						week12.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==12){
						week13.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==13){
						week14.setBackground(new Color(255, 255, 0));
					}
					if(!bdiemdanh[i] && (today.before(starttable) || today.before(next)) && i==14){
						week15.setBackground(new Color(255, 255, 0));
					}
				}
				
			}
		});
		
	}
	private DefaultTableModel modelkqsinhvien = new DefaultTableModel(
			new Object[][] {
				/*{" Tu\u1EA7n 1", false, " Tu\u1EA7n 6", false, " Tu\u1EA7n 11", false},
				{" Tu\u1EA7n 2", false, " Tu\u1EA7n 7", false, " Tu\u1EA7n 12", false},
				{" Tu\u1EA7n 3", false, " Tu\u1EA7n 8", false, " Tu\u1EA7n 13", false},
				{" Tu\u1EA7n 4", false, " Tu\u1EA7n 9", false, " Tu\u1EA7n 14", false},
				{" Tu\u1EA7n 5", false, " Tu\u1EA7n 10", false, " Tu\u1EA7n 15", false},*/
			},
			new String[] {
				"0", "1","2", "3","4", "5"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Boolean.class, String.class, Boolean.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
	public List<TaiKhoan> listTaiKhoanSinhVien = new ArrayList<TaiKhoan>();
	public SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField textField_16;
	private JTextField textField_17;
	
	private String thongtinsinhvien = maTaiKhoanSinhVien +"-"+  tenSinhvien;
	private String maMonHocLay;
	public String diemdanhcu;
	public void OptionPaneError(String active){
	    JOptionPane.showMessageDialog(this,active,"Thông báo !",JOptionPane.WARNING_MESSAGE);     
	} 
	public void OptionPaneSuccess(String active){
	    JOptionPane.showMessageDialog(this,active,"Thông báo !",JOptionPane.DEFAULT_OPTION);     
	} 
	public Date today = new Date();
	private JTextField week1;
	private JTextField week6;
	private JTextField week11;
	private JTextField week12;
	private JTextField week7;
	private JTextField week2;
	private JTextField week13;
	private JTextField week8;
	private JTextField week3;
	private JTextField week14;
	private JTextField week9;
	private JTextField week4;
	private JTextField week15;
	private JTextField week10;
	private JTextField week5;
	private JTextField txtCMt;
	private JTextField txtVng;
	private JTextField txtChaCD;
	private JButton btnngXut;
	private JLabel lbliHcKhoa;
	private JLabel lblKhoaCngNgh;
	private JLabel lblTnSinhVin;
	private JLabel lblThngTinim;
	public boolean checkTime(Date start, Date end, Time tstart, Time tend, String thutrongtuan){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH) + 1;
	    int day = calendar.get(Calendar.DAY_OF_MONTH);
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    int minute = calendar.get(Calendar.MINUTE);
	    int second = calendar.get(Calendar.SECOND);
		LocalDate a = LocalDate.of(year,month,day);
		int dayofweek = a.getDayOfWeek().getValue()+1;
		String dayofw = "Thứ "+dayofweek;
		if(dayofweek==8){
			dayofw="Chủ nhật";
		}
		if(dayofw.equals(thutrongtuan)){
			if((today.after(start) && today.before(end)) || (today.compareTo(end)==0) || (today.compareTo(start) ==0)){
				int thours = tstart.getHours();
				int tminutes = tstart.getMinutes();
				int tseconds = tstart.getSeconds();
				int thoure = tend.getHours();
				int tminutee = tend.getMinutes();
				int tseconde = tend.getSeconds();
				SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
				Date ten = new Date();
				try {
					ten = parser.parse(thours +":" + tminutes+":"+tseconds);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Date eighteen = new Date();
				try {
					eighteen = parser.parse(thoure +":" + tminutee+":"+tseconde);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
				    Date userDate = parser.parse(hour +":" + minute +":" + second);
				    if (userDate.after(ten) && userDate.before(eighteen)) {
				        return true;
				    }
				} catch (ParseException e) {
				    // Invalid date was entered
				}
				return false;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
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
	public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    int workDays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++workDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

	    return workDays;
	}
	public int daybttd;
}
