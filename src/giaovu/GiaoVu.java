package giaovu;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dao.DanhSachSinhVienDAO;
import dao.MonHocDAO;
import dao.TaiKhoanDAO;

import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import file.CSVClass;
import main.DangNhap;
import main.QuanLyTaiKhoan;
import pojo.DanhSachDiemDanh;
import pojo.MonHoc;
import pojo.TaiKhoan;
import sinhvien.StudentModel;

import javax.swing.border.CompoundBorder;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class GiaoVu extends JFrame {

	private JPanel contentPane;
	private JTextField textField_maMonHoc;
	private JTextField textField_tenMonHoc;
	private JTextField textField_ngayKetThuc;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTable table;
	private JTextField textField_tenPhongHoc;
	private JTextField textField_4;
	private JComboBox comboBox = new JComboBox();
	public String maGiaoVu;
	public String tenGiaovu ;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoVu frame = new GiaoVu();
					frame.setResizable(false);
			        //frame.setPreferredSize(new Dimension(1400, 730));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
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
	private JTextField createTextField2() {
        final JTextField field = new JTextField(15);
        field.getDocument().addDocumentListener(new DocumentListener(){
            private void filter() {
                String filter = field.getText();
                DefaultListModel<String> model = new DefaultListModel<>();
                jliststudentmodel.setModel(model);
                filterModel((DefaultListModel<String>)jliststudentmodel.getModel(), filter,defaultValues2);
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
    private String[] defaultValues0 = new String[]{};
    private String[] defaultValues = new String[]{};
    private String[] defaultValues2 = new String[]{};
    
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
    public void OptionPaneError(String active){
	    JOptionPane.showMessageDialog(this,active,"Thông báo !",JOptionPane.WARNING_MESSAGE);     
	} 
	public void OptionPaneSuccess(String active){
	    JOptionPane.showMessageDialog(this,active,"Thông báo !",JOptionPane.DEFAULT_OPTION);     
	} 
	@SuppressWarnings("deprecation")
	public GiaoVu(String thongtin) {
		String mssve[] = new String[]{};
		mssve = thongtin.split("-");
		maGiaoVu = mssve[0];
		tenGiaovu = mssve[1];
		setTitle("Gi\u00E1o v\u1EE5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1206, 639);
		//setBounds(100, 100, 594, 388);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 50, 1164, 539);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBorder(new CompoundBorder());
		tabbedPane.addTab("T\u1EA1o m\u00F4n h\u1ECDc", null, panel, null);
		panel.setLayout(null);
		UtilDateModel model1 = new UtilDateModel();
		model1.setSelected(true);
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1,p1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1,new DateLabelFormatter());
		datePicker1.setBackground(new Color(152, 251, 152));
		datePicker1.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker1.setBounds(563, 169, 189, 28);
		datePicker1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Date start = new Date(model1.getYear() - 1900,model1.getMonth(),model1.getDay());
				int noOfDays = 105; //i.e two weeks
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(start);     
				int year = calendar.get(Calendar.YEAR);
			    int month = calendar.get(Calendar.MONTH) + 1;
			    int day = calendar.get(Calendar.DAY_OF_MONTH);
			    
				LocalDate a = LocalDate.of(year,month,day);
				int dayofweek = a.getDayOfWeek().getValue()+1;
				String dayofw = "Thứ "+dayofweek;
				if(dayofweek==8){
					dayofw ="Chủ nhật";
				}
				comboBox.setSelectedItem(dayofw);
				calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
				year = calendar.get(Calendar.YEAR);
			    month = calendar.get(Calendar.MONTH);
			    day = calendar.get(Calendar.DAY_OF_MONTH);
				Date end = new Date(year - 1900,month,day);
				
				textField_ngayKetThuc.setText(format.format(calendar.getTime()).toString());
			}
		});
		panel.add(datePicker1);
		
		JLabel lblMMnHc = new JLabel("M\u00E3 m\u00F4n h\u1ECDc");
		lblMMnHc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMMnHc.setBounds(443, 113, 86, 17);
		panel.add(lblMMnHc);
		
		textField_maMonHoc = new JTextField();
		textField_maMonHoc.setToolTipText("Nh\u1EADp m\u00E3 m\u00F4n h\u1ECDc");
		textField_maMonHoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_maMonHoc.setBounds(563, 111, 189, 27);
		panel.add(textField_maMonHoc);
		textField_maMonHoc.setColumns(10);
		
		textField_tenMonHoc = new JTextField();
		textField_tenMonHoc.setToolTipText("Nh\u1EADp t\u00EAn m\u00F4n h\u1ECDc");
		textField_tenMonHoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_tenMonHoc.setBounds(917, 110, 189, 28);
		textField_tenMonHoc.setColumns(10);
		panel.add(textField_tenMonHoc);
		
		JLabel lblTnMnHc = new JLabel("T\u00EAn m\u00F4n h\u1ECDc");
		lblTnMnHc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTnMnHc.setBounds(812, 113, 95, 17);
		panel.add(lblTnMnHc);
		
		JLabel lblNgyBtu = new JLabel("Ng\u00E0y b\u1EAFt \u0111\u1EA7u");
		lblNgyBtu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNgyBtu.setBounds(443, 169, 86, 28);
		panel.add(lblNgyBtu);
		
		JLabel lblThTrongTun = new JLabel("Th\u1EE9 trong tu\u1EA7n");
		lblThTrongTun.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblThTrongTun.setBounds(688, 224, 95, 20);
		panel.add(lblThTrongTun);
		
		JLabel lblNgyKtThc = new JLabel("Ng\u00E0y k\u1EBFt th\u00FAc");
		lblNgyKtThc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNgyKtThc.setBounds(812, 169, 95, 21);
		panel.add(lblNgyKtThc);
		
		textField_ngayKetThuc = new JTextField();
		textField_ngayKetThuc.setEditable(false);
		textField_ngayKetThuc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_ngayKetThuc.setColumns(10);
		textField_ngayKetThuc.setBounds(917, 169, 189, 28);
		panel.add(textField_ngayKetThuc);
		
		JButton btnToMnHc = new JButton("T\u1EA1o m\u00F4n h\u1ECDc");
		btnToMnHc.setBackground(new Color(152, 251, 152));
		btnToMnHc.setToolTipText("T\u1EA1o m\u00F4n h\u1ECDc");
		btnToMnHc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnToMnHc.setBounds(833, 358, 132, 42);
		panel.add(btnToMnHc);
		
		JLabel lblGiBtu = new JLabel("Gi\u1EDD b\u1EAFt \u0111\u1EA7u");
		lblGiBtu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGiBtu.setBounds(443, 262, 86, 28);
		panel.add(lblGiBtu);
		
		JLabel lblGiKtThc = new JLabel("Gi\u1EDD k\u1EBFt th\u00FAc");
		lblGiKtThc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGiKtThc.setBounds(812, 268, 95, 21);
		panel.add(lblGiKtThc);
		comboBox.setEditable(true);
		
		
		comboBox.setBackground(new Color(152, 251, 152));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Th\u1EE9 2", "Th\u1EE9 3", "Th\u1EE9 4", "Th\u1EE9 5", "Th\u1EE9 6", "Th\u1EE9 7", "Ch\u1EE7 nh\u1EADt"}));
		comboBox.setBounds(802, 226, 122, 20);
		panel.add(comboBox);
		
		JButton btnToLi = new JButton("Tạo lại");
		btnToLi.setBackground(new Color(152, 251, 152));
		btnToLi.setToolTipText("Tạo môn học");
		btnToLi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnToLi.setBounds(646, 358, 140, 42);
		panel.add(btnToLi);
		
		JLabel lblToMnHc = new JLabel("Tạo Môn Học");
		lblToMnHc.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblToMnHc.setBounds(755, 56, 152, 28);
		panel.add(lblToMnHc);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBackground(new Color(152, 251, 152));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"06:00:00", "06:10:00", "06:15:00", "06:20:00", "06:30:00", "06:45:00", "07:00:00", "07:10:00", "07:15:00", "07:20:00", "07:30:00", "07:45:00", "08:00:00", "08:10:00", "08:15:00", "08:20:00", "08:30:00", "08:45:00", "09:00:00", "09:10:00", "09:15:00", "09:20:00", "09:30:00", "09:45:00", "10:00:00", "10:10:00", "10:15:00", "10:20:00", "10:30:00", "10:45:00", "11:00:00", "11:10:00", "11:15:00", "11:20:00", "11:30:00", "11:45:00", "12:00:00", "12:10:00", "12:15:00", "12:20:00", "12:30:00", "12:45:00", "13:00:00", "13:10:00", "13:15:00", "13:20:00", "13:30:00", "13:45:00", "14:00:00", "14:10:00", "14:15:00", "14:20:00", "14:30:00", "14:45:00", "15:00:00", "15:10:00", "15:15:00", "15:20:00", "15:30:00", "15:45:00", "16:00:00", "16:10:00", "16:15:00", "16:20:00", "16:30:00", "16:45:00", "17:00:00", "17:10:00", "17:15:00", "17:20:00", "17:30:00", "17:45:00", "18:00:00", "18:10:00", "18:15:00", "18:20:00", "18:30:00", "18:45:00", "19:00:00", "19:10:00", "19:15:00", "19:20:00", "19:30:00", "19:45:00", "20:00:00", "20:10:00", "20:15:00", "20:20:00", "20:30:00", "20:45:00", "21:00:00", "21:10:00", "21:15:00", "21:20:00", "21:30:00", "21:45:00", "22:00:00", "22:10:00", "22:15:00", "22:20:00", "22:30:00", "22:45:00"}));
		comboBox_2.setBounds(533, 268, 122, 20);
		panel.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBackground(new Color(152, 251, 152));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"06:00:00", "06:10:00", "06:15:00", "06:20:00", "06:30:00", "06:45:00", "07:00:00", "07:10:00", "07:15:00", "07:20:00", "07:30:00", "07:45:00", "08:00:00", "08:10:00", "08:15:00", "08:20:00", "08:30:00", "08:45:00", "09:00:00", "09:10:00", "09:15:00", "09:20:00", "09:30:00", "09:45:00", "10:00:00", "10:10:00", "10:15:00", "10:20:00", "10:30:00", "10:45:00", "11:00:00", "11:10:00", "11:15:00", "11:20:00", "11:30:00", "11:45:00", "12:00:00", "12:10:00", "12:15:00", "12:20:00", "12:30:00", "12:45:00", "13:00:00", "13:10:00", "13:15:00", "13:20:00", "13:30:00", "13:45:00", "14:00:00", "14:10:00", "14:15:00", "14:20:00", "14:30:00", "14:45:00", "15:00:00", "15:10:00", "15:15:00", "15:20:00", "15:30:00", "15:45:00", "16:00:00", "16:10:00", "16:15:00", "16:20:00", "16:30:00", "16:45:00", "17:00:00", "17:10:00", "17:15:00", "17:20:00", "17:30:00", "17:45:00", "18:00:00", "18:10:00", "18:15:00", "18:20:00", "18:30:00", "18:45:00", "19:00:00", "19:10:00", "19:15:00", "19:20:00", "19:30:00", "19:45:00", "20:00:00", "20:10:00", "20:15:00", "20:20:00", "20:30:00", "20:45:00", "21:00:00", "21:10:00", "21:15:00", "21:20:00", "21:30:00", "21:45:00", "22:00:00", "22:10:00", "22:15:00", "22:20:00", "22:30:00", "22:45:00"}));
		comboBox_3.setBounds(917, 270, 122, 20);
		panel.add(comboBox_3);
		
		JLabel lblTnPhngHc = new JLabel("Tên phòng học");
		lblTnPhngHc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTnPhngHc.setBounds(646, 320, 95, 17);
		panel.add(lblTnPhngHc);
		
		textField_tenPhongHoc = new JTextField();
		textField_tenPhongHoc.setToolTipText("Nhập tên môn học");
		textField_tenPhongHoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_tenPhongHoc.setColumns(10);
		textField_tenPhongHoc.setBounds(751, 317, 189, 20);
		panel.add(textField_tenPhongHoc);
		
		JButton button_2 = new JButton("Hủy");
		button_2.setBackground(new Color(152, 251, 152));
		button_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button_2.setBounds(876, 457, 89, 23);
		button_2.hide();
		panel.add(button_2);
		
		JButton button_3 = new JButton("Hoàn tất");
		button_3.setBackground(new Color(152, 251, 152));
		button_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button_3.setBounds(876, 423, 89, 23);
		button_3.hide();
		panel.add(button_3);
		
		JButton button_4 = new JButton("Chỉnh sửa thông tin môn học");
		button_4.setBackground(new Color(152, 251, 152));
		button_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button_4.setBounds(646, 423, 220, 23);
		button_4.hide();
		panel.add(button_4);
		
		textField_11 = new JTextField();
		textField_11 = createTextField0();
		textField_11.setText("Nhập tên môn học");
		textField_11.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_11.setBounds(10, 36, 199, 20);
		panel.add(textField_11);
		
		JLabel label_2 = new JLabel("Danh sách môn học");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_2.setBounds(10, 11, 145, 20);
		panel.add(label_2);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		list0.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		scrollPane_5.setViewportView(list0);
		scrollPane_5.setBounds(10, 59, 199, 441);
		
		panel.add(scrollPane_5);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("Th\u00EAm sinh vi\u00EAn", null, panel_1, null);
		panel_1.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 199, 441);
		panel_1.add(scrollPane);
		
		//JList<String> list = new JList<String>();
		//list = createJList();
		list.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		//
		
		/*
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});*/
		
		scrollPane.setViewportView(list);
		JLabel lblDanhSchCc = new JLabel("Danh s\u00E1ch m\u00F4n h\u1ECDc");
		lblDanhSchCc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDanhSchCc.setBounds(10, 11, 145, 20);
		panel_1.add(lblDanhSchCc);
		
		textField_6 = new JTextField();
		
		textField_6 = createTextField();
		
		textField_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_6.setBounds(10, 36, 199, 20);
		
		
		listMonHoc.addAll(MonHocDAO.layDanhSachMonHoc());
		String [] listModel = new String[]{};
		List<String> arrl = new ArrayList<String>();
		for(int i = 0;i<listMonHoc.size();i++){
			arrl.add(listMonHoc.get(i).getTenMonHoc());
		}
		listModel = arrl.toArray(new String[0]);
		defaultValues = listModel;
		defaultValues0 = listModel;
		textField_6.setText("Nhập tên môn học");
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel label = new JLabel("Ng\u00E0y b\u1EAFt \u0111\u1EA7u");
		label.setEnabled(false);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBounds(244, 123, 86, 28);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("M\u00E3 m\u00F4n h\u1ECDc");
		label_1.setEnabled(false);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_1.setBounds(244, 95, 86, 17);
		panel_1.add(label_1);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setToolTipText("Nh\u1EADp m\u00E3 m\u00F4n h\u1ECDc");
		textField_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_7.setColumns(10);
		textField_7.setBounds(344, 93, 216, 20);
		panel_1.add(textField_7);
		
		JLabel label_3 = new JLabel("T\u00EAn m\u00F4n h\u1ECDc");
		label_3.setEnabled(false);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_3.setBounds(244, 68, 95, 17);
		panel_1.add(label_3);
		
		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setToolTipText("Nh\u1EADp t\u00EAn m\u00F4n h\u1ECDc");
		textField_9.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_9.setColumns(10);
		textField_9.setBounds(344, 66, 216, 20);
		panel_1.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_10.setColumns(10);
		textField_10.setBounds(344, 157, 216, 20);
		panel_1.add(textField_10);
		
		JLabel label_4 = new JLabel("Gi\u1EDD k\u1EBFt th\u00FAc");
		label_4.setEnabled(false);
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_4.setBounds(244, 254, 86, 21);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("Gi\u1EDD b\u1EAFt \u0111\u1EA7u");
		label_5.setEnabled(false);
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_5.setBounds(244, 215, 86, 28);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("Th\u1EE9 trong tu\u1EA7n");
		label_6.setEnabled(false);
		label_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_6.setBounds(244, 184, 95, 20);
		panel_1.add(label_6);
		
		//JList<String> list_1 = new JList<String>();
		jliststudentmodel.setEnabled(false);
		jliststudentmodel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		textField_14 = new JTextField();
		textField_14 = createTextField2();
		textField_14.setEnabled(false);
		textField_14.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_14.setColumns(10);
		textField_14.setBounds(919, 36, 230, 20);
		List<DanhSachDiemDanh> listSVTheoMonHoc = new ArrayList<DanhSachDiemDanh>();
		////
		panel_1.add(textField_14);
		
		JLabel lblDanhSchCc_1 = new JLabel("Danh s\u00E1ch sinh vi\u00EAn");
		lblDanhSchCc_1.setEnabled(false);
		lblDanhSchCc_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDanhSchCc_1.setBounds(968, 11, 136, 14);
		panel_1.add(lblDanhSchCc_1);
		
		JButton btnThmSinhVin = new JButton("Th\u00EAm danh s\u00E1ch sinh vi\u00EAn t\u1EEB file");
		btnThmSinhVin.setBackground(new Color(152, 251, 152));
		btnThmSinhVin.setEnabled(false);
		btnThmSinhVin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnThmSinhVin.setBounds(396, 477, 276, 23);
		panel_1.add(btnThmSinhVin);
		
		JButton btnThmMiSinh = new JButton("Th\u00EAm m\u1EDBi");
		btnThmMiSinh.setBackground(new Color(152, 251, 152));
		btnThmMiSinh.setEnabled(false);
		btnThmMiSinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnThmMiSinh.setBounds(919, 482, 230, 22);
		panel_1.add(btnThmMiSinh);
		
		JButton btnExportCsv = new JButton("L\u1EA5y m\u1EABu danh s\u00E1ch sinh vi\u00EAn");
		btnExportCsv.setBackground(new Color(152, 251, 152));
		btnExportCsv.setEnabled(false);
		btnExportCsv.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnExportCsv.setBounds(693, 477, 216, 23);
		panel_1.add(btnExportCsv);
		
		JScrollPane scrollPane_3 = new JScrollPane(jliststudentmodel);
		scrollPane_3.setBounds(919, 59, 230, 412);
		panel_1.add(scrollPane_3);
		
		JLabel lblThngTinMn = new JLabel("Th\u00F4ng tin m\u00F4n h\u1ECDc");
		lblThngTinMn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblThngTinMn.setBounds(309, 13, 188, 28);
		panel_1.add(lblThngTinMn);
		
		JLabel lblNgyKtThc_1 = new JLabel("Ng\u00E0y k\u1EBFt th\u00FAc");
		lblNgyKtThc_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNgyKtThc_1.setEnabled(false);
		lblNgyKtThc_1.setBounds(244, 149, 86, 28);
		panel_1.add(lblNgyKtThc_1);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(344, 283, 216, 20);
		panel_1.add(textField_4);
		
		JLabel lblPhngHc = new JLabel("Phòng học");
		lblPhngHc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPhngHc.setEnabled(false);
		lblPhngHc.setBounds(244, 279, 86, 28);
		panel_1.add(lblPhngHc);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(219, 385, 424, 86);
		panel_3.hide();
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã số sinh viên");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(25, 11, 130, 26);
		panel_3.add(lblNewLabel);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_5.setBounds(129, 10, 189, 29);
		panel_3.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblTnSinhVin = new JLabel("Tên sinh viên");
		lblTnSinhVin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTnSinhVin.setBounds(25, 51, 130, 24);
		panel_3.add(lblTnSinhVin);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_8.setColumns(10);
		textField_8.setBounds(129, 48, 189, 27);
		panel_3.add(textField_8);
		
		JButton button = new JButton("Hủy");
		button.setBackground(new Color(152, 251, 152));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button.setBounds(328, 48, 89, 27);
		panel_3.add(button);
		
		JButton button_1 = new JButton("Hoàn tất");
		button_1.setBackground(new Color(152, 251, 152));
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button_1.setBounds(328, 11, 89, 29);
		panel_3.add(button_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(646, 59, 264, 371);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		JList<String> jlisttaikhoan = new JList<String>();
		jlisttaikhoan.setEnabled(false);
		jlisttaikhoan.setToolTipText("Giữ Ctrl và chọn nhiều sinh viên hoặc bỏ chọn");
		jlisttaikhoan.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		jlisttaikhoan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		List<TaiKhoan> listtaikhoan = new ArrayList<TaiKhoan>();
		listtaikhoan.addAll(TaiKhoanDAO.layDanhSachTaiKhoanSV());
		
		for(int i = 0;i<listtaikhoan.size();i++){
			arrltk.add(listtaikhoan.get(i).getMaTaiKhoan() +"~" + listtaikhoan.get(i).getTenNguoiDung());
		}
		listModeltaikhoan = arrltk.toArray(new String[0]);
		jlisttaikhoan.setListData(listModeltaikhoan);
		jlisttaikhoan.addMouseListener(new MouseListener() {
			
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
				
			}
		});
		JScrollPane scrollPane_4 = new JScrollPane(jlisttaikhoan);
		scrollPane_4.setEnabled(false);
		scrollPane_4.setBounds(10, 11, 244, 349);
		panel_4.add(scrollPane_4);
		
		JButton btnNewButton_1 = new JButton("Chọn hết");
		btnNewButton_1.setBackground(new Color(152, 251, 152));
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(646, 436, 127, 28);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int start = 0;
			    int end = jlisttaikhoan.getModel().getSize() - 1;
			    if (end >= 0) {
			    	jlisttaikhoan.setSelectionInterval(start, end);
			    }
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnThm = new JButton("Thêm");
		btnThm.setBackground(new Color(152, 251, 152));
		btnThm.setEnabled(false);
		btnThm.setBounds(783, 436, 126, 28);
		btnThm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<String> listmssv =new ArrayList<String>();
				listmssv.addAll(jlisttaikhoan.getSelectedValuesList());
				boolean fl = false;
				for(int i = 0; i< listmssv.size();i++){
					String mssv[] = new String[]{};
					mssv = listmssv.get(i).split("~");
			    	String maDS = "DS" + textField_7.getText() + mssv[0];
			    	DanhSachDiemDanh dsdd = new DanhSachDiemDanh(maDS,"000000000000000",mssv[0],textField_7.getText());
			    	if(DanhSachSinhVienDAO.themSinhVien(dsdd, maDS)){
			    		System.out.println("Thành công!");
			    		fl =true;
			    	}
			    	else{
			    		System.out.println("Danh sách đã tồn tại!");
			    	}
				}
				if(fl){
					OptionPaneSuccess("Thêm thành công !");
				}
				else{
					OptionPaneError("Thêm thất bại !");
				}
			}
		});
		panel_1.add(btnThm);
		
		JLabel lblDanhSchSinh = new JLabel("Danh sách sinh viên các môn học trước");
		lblDanhSchSinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDanhSchSinh.setEnabled(false);
		lblDanhSchSinh.setBounds(646, 16, 263, 28);
		panel_1.add(lblDanhSchSinh);
		
		textField_12 = new JTextField();
		textField_12.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_12.setEnabled(false);
		textField_12.setColumns(10);
		textField_12.setBounds(344, 129, 216, 20);
		panel_1.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_13.setEnabled(false);
		textField_13.setColumns(10);
		textField_13.setBounds(344, 188, 216, 20);
		panel_1.add(textField_13);
		
		textField_16 = new JTextField();
		textField_16.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_16.setEnabled(false);
		textField_16.setColumns(10);
		textField_16.setBounds(344, 221, 216, 20);
		panel_1.add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_17.setEnabled(false);
		textField_17.setColumns(10);
		textField_17.setBounds(344, 254, 216, 20);
		panel_1.add(textField_17);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("Xem k\u1EBFt qu\u1EA3", null, panel_2, null);
		panel_2.setLayout(null);
		
		textField_15 = new JTextField();
		textField_15 = createTextField1();
		textField_15.setText("Nhập tên môn học");
		textField_15.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField_15.setColumns(10);
		textField_15.setBounds(10, 36, 221, 20);
		panel_2.add(textField_15);
		
		JLabel label_7 = new JLabel("Danh s\u00E1ch m\u00F4n h\u1ECDc");
		label_7.setBackground(new Color(0, 0, 255));
		label_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_7.setBounds(10, 11, 145, 20);
		panel_2.add(label_7);
		
		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setModel(modelkqsinhvien);
		table.setBounds(261, 120, 1, 1);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setPreferredWidth(45);
		table.getColumnModel().getColumn(3).setPreferredWidth(45);
		table.getColumnModel().getColumn(4).setPreferredWidth(45);
		table.getColumnModel().getColumn(5).setPreferredWidth(45);
		table.getColumnModel().getColumn(6).setPreferredWidth(45);
		table.getColumnModel().getColumn(7).setPreferredWidth(45);
		table.getColumnModel().getColumn(8).setPreferredWidth(45);
		table.getColumnModel().getColumn(9).setPreferredWidth(45);
		table.getColumnModel().getColumn(10).setPreferredWidth(45);
		table.getColumnModel().getColumn(11).setPreferredWidth(55);
		table.getColumnModel().getColumn(12).setPreferredWidth(55);
		table.getColumnModel().getColumn(13).setPreferredWidth(55);
		table.getColumnModel().getColumn(14).setPreferredWidth(55);
		table.getColumnModel().getColumn(15).setPreferredWidth(55);
		table.getColumnModel().getColumn(16).setPreferredWidth(55);
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(241, 40, 908, 460);
		panel_2.add(scrollPane_1);
		
		//JList<String> list_2 = new JList<String>();
		/*list_2.setModel(new AbstractListModel() {
			String[] values = new String[] {"a"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});*/
		
		list1.setBounds(10, 60, 112, 228);
		list1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 60, 221, 440);
		scrollPane_2.setViewportView(list1);
		
		panel_2.add(scrollPane_2);
		
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		panel_2.add(fileChooser);
		
		JButton btnngXut = new JButton("Đăng xuất");
		btnngXut.setBackground(new Color(255, 215, 0));
		btnngXut.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		btnngXut.setBounds(1072, 11, 102, 37);
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
		
		JLabel label_8 = new JLabel("ĐẠI HỌC KHOA HỌC TỰ NHIÊN");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Segoe UI", Font.BOLD, 15));
		label_8.setBounds(474, 13, 221, 21);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("Khoa công nghệ thông tin");
		label_9.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		label_9.setBounds(10, 20, 142, 14);
		contentPane.add(label_9);
		
		JLabel lblThngTinGio = new JLabel("Thông tin giáo vụ");
		lblThngTinGio.setForeground(Color.WHITE);
		lblThngTinGio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblThngTinGio.setBounds(838, 18, 227, 21);
		lblThngTinGio.setText(maGiaoVu+ "-" + tenGiaovu); 
		lblThngTinGio.addMouseListener(new MouseListener() {
			
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
				QuanLyTaiKhoan qltk = new QuanLyTaiKhoan(maGiaoVu+ "-" + tenGiaovu);
				qltk.show();
			}
		});
		contentPane.add(lblThngTinGio);
		/***/
		list.addMouseListener(new MouseListener() {
			
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
				if(list.getSelectedValue()==null){
					return;
				}
				jlisttaikhoan.setEnabled(true);
				textField_12.setEnabled(true);
				textField_13.setEnabled(true);
				textField_16.setEnabled(true);
				textField_17.setEnabled(true);
				scrollPane_4.setEnabled(true);
				btnNewButton_1.setEnabled(true);
				btnThm.setEnabled(true);
				btnThmMiSinh.setEnabled(true);
				btnExportCsv.setEnabled(true);
				btnThmSinhVin.setEnabled(true);
				label.setEnabled(true);
				label_5.setEnabled(true);
				label_4.setEnabled(true);
				label_6.setEnabled(true);
				label_1.setEnabled(true);
				label_3.setEnabled(true);
				lblPhngHc.setEnabled(true);
				textField_14.setEnabled(true);
				scrollPane_3.setEnabled(true);
				jliststudentmodel.setEnabled(true);
				lblNgyKtThc_1.setEnabled(true);
				lblDanhSchCc_1.setEnabled(true);
				textField_9.setText(list.getSelectedValue().toString());
				textField_7.setEditable(false);
				textField_10.setEditable(false);
				textField_12.setEditable(false);
				textField_4.setEditable(false);
				textField_13.setEditable(false);
				textField_16.setEditable(false);
				textField_17.setEditable(false);
				textField_9.setEditable(false);
				textField_7.setEnabled(true);
				textField_10.setEnabled(true);
				textField_12.setEnabled(true);
				textField_4.setEnabled(true);
				textField_13.setEnabled(true);
				textField_16.setEnabled(true);
				textField_17.setEnabled(true);
				textField_9.setEnabled(true);
				for(int i = 0;i< listMonHoc.size();i++){
					if(list.getSelectedValue().equals(listMonHoc.get(i).getTenMonHoc())){
						textField_7.setText(listMonHoc.get(i).getMaMonHoc());
						textField_10.setText(format.format(listMonHoc.get(i).getNgayKetThuc()).toString());
						textField_12.setText(format.format(listMonHoc.get(i).getNgayBatDau()).toString());
						textField_4.setText(listMonHoc.get(i).getTenPhongHoc());
						textField_13.setText(listMonHoc.get(i).getThuTrongTuan().toString());
						textField_16.setText(listMonHoc.get(i).getThoiGianBatDau().toString());
						textField_17.setText(listMonHoc.get(i).getThoiGianKetThuc().toString());
					}
				}
				listSVTheoMonHoc.clear();
				listSVTheoMonHoc.addAll(DanhSachSinhVienDAO.layThongTinDiemDanh(textField_7.getText()));
				String [] listModel1 = new String[]{};
				List<String> arrl1 = new ArrayList<String>();
				for(int i = 0;i<listSVTheoMonHoc.size();i++){
					TaiKhoan tk2 = listSVTheoMonHoc.get(i).getTaiKhoan();
					//String matk = listSVTheoMonHoc.get(i).getTaiKhoan().getMaTaiKhoan();
					//TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoanSinhVien(matk);
					arrl1.add(tk2.getMaTaiKhoan());
				}
				
				listModel1 = arrl1.toArray(new String[0]);
				defaultValues2 = listModel1;
				if(listSVTheoMonHoc.size()>0){
					textField_14.setText(listSVTheoMonHoc.get(0).getTaiKhoan().getMaTaiKhoan().substring(0,4));
				}else{
					textField_14.setText("");
				}
				
			}
		});
		btnThmMiSinh.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_3.show();
			}
		});
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(textField_5.getText().equals("")){
					OptionPaneError("Bạn chưa điền mã sinh viên !"); 
					return;
				}
				if(textField_8.getText().equals("")){
					OptionPaneError("Bạn chưa điền mã tên sinh viên !"); 
					return;
				}
				String mssv = textField_5.getText();
				String tensv = textField_8.getText();
		    	String matkhau = encryptPassword(mssv);
		    	TaiKhoan tksv = new TaiKhoan(mssv,
		    			matkhau,
		    			tensv,
		    			true);
		    	String maDS = "DS" + textField_7.getText() + mssv;
		    	if(TaiKhoanDAO.themTaoKhoanSinhVien(tksv)){
		    		System.out.println("Thành công!");
		    	}else{
		    		System.out.println("Tài khoản đã tồn tại!");
		    	}
		    	DanhSachDiemDanh dsdd = new DanhSachDiemDanh(maDS,"000000000000000",mssv,textField_7.getText());
		    	if(DanhSachSinhVienDAO.themSinhVien(dsdd, maDS)){
		    		System.out.println("Thành công!");
		    		OptionPaneSuccess("Thêm thành công."); 
		    	}
		    	else{
		    		OptionPaneSuccess("Danh sách đã tồn tại!"); 
		    	}
		    	listtaikhoan.clear();
		    	listtaikhoan.addAll(TaiKhoanDAO.layDanhSachTaiKhoanSV());
				arrltk.clear();
				for(int j = 0;j<listtaikhoan.size();j++){
					arrltk.add(listtaikhoan.get(j).getMaTaiKhoan() +"~" + listtaikhoan.get(j).getTenNguoiDung());
				}
				listModeltaikhoan = arrltk.toArray(new String[0]);
				jlisttaikhoan.setListData(listModeltaikhoan);
		    	panel_3.hide();
			}
		});
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_3.hide();
			}
		});
		
		btnThmSinhVin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = fileChooser.showOpenDialog(contentPane);
				if (result == JFileChooser.APPROVE_OPTION) {
				    // user selects a file
					File selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    CSVClass file = new CSVClass();
				    listStudentModel = file.Import(selectedFile.getAbsolutePath());
				    //
				    for(int i = 0;i<listStudentModel.size();i++){
				    	String mssv =listStudentModel.get(i).getMaSSV();
				    	String matkhau = encryptPassword(mssv);
				    	TaiKhoan tksv = new TaiKhoan(mssv,
				    			matkhau,
				    			listStudentModel.get(i).getTenSV(),
				    			true);
				    	String maDS = "DS" + textField_7.getText() + mssv;
				    	if(TaiKhoanDAO.themTaoKhoanSinhVien(tksv)){
				    		System.out.println("Thành công!");
				    	}else{
				    		System.out.println("Tài khoản đã tồn tại!");
				    	}
				    	DanhSachDiemDanh dsdd = new DanhSachDiemDanh(maDS,"000000000000000",mssv,textField_7.getText());
				    	if(DanhSachSinhVienDAO.themSinhVien(dsdd, maDS)){
				    		System.out.println("Thành công!");
				    		//OptionPaneSuccess("Thêm thành công."); 
				    	}
				    	else{
				    		OptionPaneSuccess("Danh sách đã tồn tại!"); 
				    		//System.out.println("Danh sách đã tồn tại!");
				    	}
				    	listtaikhoan.clear();
				    	listtaikhoan.addAll(TaiKhoanDAO.layDanhSachTaiKhoanSV());
						arrltk.clear();
						for(int j = 0;j<listtaikhoan.size();j++){
							arrltk.add(listtaikhoan.get(j).getMaTaiKhoan() +"~" + listtaikhoan.get(j).getTenNguoiDung());
						}
						listModeltaikhoan = arrltk.toArray(new String[0]);
						jlisttaikhoan.setListData(listModeltaikhoan);
				    }
				    OptionPaneSuccess("Thêm thành công."); 
				}
			}
		});
		btnExportCsv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = fileChooser.showSaveDialog(contentPane);
				if (result == JFileChooser.APPROVE_OPTION) {
				    // user selects a file
					File selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    CSVClass file = new CSVClass();
				    file.Export(selectedFile.getAbsolutePath());
				    OptionPaneSuccess("Tạo mẫu dữ liệu thành công tại đường dẫn:" + selectedFile.getAbsolutePath()); 
				}
			}
		});
		
		btnToMnHc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Nvao dc ne");
				// TODO Auto-generated method stub
				Date start = new Date(model1.getYear() - 1900,model1.getMonth(),model1.getDay());
				int noOfDays = 98; //
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(start);
				int year = calendar.get(Calendar.YEAR);
			    int month = calendar.get(Calendar.MONTH) + 1;
			    int day = calendar.get(Calendar.DAY_OF_MONTH);
			    
				LocalDate a = LocalDate.of(year,month,day);
				int dayofweek = a.getDayOfWeek().getValue()+1;
				String dayofw = "Thứ "+dayofweek;
				if(dayofweek==8){
					dayofw ="Chủ nhật";
				}
				calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
				year = calendar.get(Calendar.YEAR);
			    month = calendar.get(Calendar.MONTH);
			    day = calendar.get(Calendar.DAY_OF_MONTH);
				Date end = new Date(year - 1900,month,day);
				
				textField_ngayKetThuc.setText(format.format(calendar.getTime()).toString());
				String ab = comboBox_2.getSelectedItem().toString();
				Time tstart =Time.valueOf(ab);
				Time tend = Time.valueOf(comboBox_3.getSelectedItem().toString());
				if(textField_maMonHoc.getText().equals("")){
					OptionPaneError("Bạn chưa điền mã môn học"); 
					return;
				}
				if(textField_tenMonHoc.getText().equals("")){
					OptionPaneError("Bạn chưa điền tên môn học"); 
					return;
				}
				if(textField_tenPhongHoc.getText().equals("")){
					OptionPaneError("Bạn chưa điền tên phòng học"); 
					return;
				}
				if(tstart.after(tend)){
					OptionPaneError("Thời gian kết thúc phải lớn hơn thời gian bắt đầu !"); 
					return;
				}
				comboBox.setSelectedItem(dayofw);
				MonHoc mh = new MonHoc(textField_maMonHoc.getText(),
						textField_tenMonHoc.getText(),start,end,
						dayofw.toString(),tstart,tend,textField_tenPhongHoc.getText());
				
				if(MonHocDAO.themMonHocBangMa(mh)){
					System.out.println("Thành công!");
	    			OptionPaneSuccess("Thêm thành công.");
				}
				listMonHoc.clear();
				listMonHoc.addAll(MonHocDAO.layDanhSachMonHoc());
				String [] listModel3 = new String[]{};
				List<String> arrl3 = new ArrayList<String>();
				for(int i = 0;i<listMonHoc.size();i++){
					arrl3.add(listMonHoc.get(i).getTenMonHoc());
				}
				listModel3 = arrl3.toArray(new String[0]);
				defaultValues0 = listModel3;
				/*List<MonHoc> ls = new ArrayList<MonHoc>();
				ls.addAll(MonHocDAO.layDanhSachMonHoc());
				for(int i =0;i< ls.size();i++){
					System.out.println(ls.get(i).getTenMonHoc());
				}*/
			}
		});
		btnToLi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField_maMonHoc.setText("");
				textField_ngayKetThuc.setText("");
				textField_tenMonHoc.setText("");
				textField_tenPhongHoc.setText("");
			}
		});
		button_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_3.show();
				button_2.show();
				btnToLi.hide();
				btnToMnHc.hide();
				textField_maMonHoc.setEditable(false);
			}
		});
		button_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date start = new Date(model1.getYear() - 1900,model1.getMonth(),model1.getDay());
				int noOfDays = 105; //i.e two weeks
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(start);     
				int year = calendar.get(Calendar.YEAR);
			    int month = calendar.get(Calendar.MONTH) + 1;
			    int day = calendar.get(Calendar.DAY_OF_MONTH);
			    
				LocalDate a = LocalDate.of(year,month,day);
				int dayofweek = a.getDayOfWeek().getValue()+1;
				String dayofw = "Thứ "+dayofweek;
				if(dayofweek==8){
					dayofw ="Chủ nhật";
				}
				calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
				year = calendar.get(Calendar.YEAR);
			    month = calendar.get(Calendar.MONTH);
			    day = calendar.get(Calendar.DAY_OF_MONTH);
				Date end = new Date(year - 1900,month,day);
				
				textField_ngayKetThuc.setText(format.format(calendar.getTime()).toString());
				String ab = comboBox_2.getSelectedItem().toString();
				Time tstart =Time.valueOf(ab);
				Time tend = Time.valueOf(comboBox_3.getSelectedItem().toString());
				if(textField_maMonHoc.getText().equals("")){
					OptionPaneError("Bạn chưa điền mã môn học"); 
					return;
				}
				if(textField_tenMonHoc.getText().equals("")){
					OptionPaneError("Bạn chưa điền tên môn học"); 
					return;
				}
				if(tstart.after(tend)){
					OptionPaneError("Thời gian kết thúc phải lớn hơn thời gian bắt đầu"); 
					return;
				}
				if(textField_tenPhongHoc.getText().equals("")){
					OptionPaneError("Bạn chưa điền tên phòng học"); 
					return;
				}
				comboBox.setSelectedItem(dayofw);
				MonHoc mh = new MonHoc(textField_maMonHoc.getText(),
						textField_tenMonHoc.getText(),start,end,
						dayofw,tstart,tend,textField_tenPhongHoc.getText());
				
				if(MonHocDAO.capNhatThongTinMonHocBangMa(mh)){
					System.out.println("Thành công!");
		    		OptionPaneSuccess("Sửa thành công.");
				}
				button_3.hide();
				button_2.hide();
				btnToLi.show();
				btnToMnHc.show();
				textField_maMonHoc.setEditable(true);
				listMonHoc.clear();
				listMonHoc.addAll(MonHocDAO.layDanhSachMonHoc());
				String [] listModel3 = new String[]{};
				List<String> arrl3 = new ArrayList<String>();
				for(int i = 0;i<listMonHoc.size();i++){
					arrl3.add(listMonHoc.get(i).getTenMonHoc());
				}
				listModel3 = arrl3.toArray(new String[0]);
				defaultValues0 = listModel3;
			}
		});
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_3.hide();
				button_2.hide();
				btnToLi.show();
				btnToMnHc.show();
				textField_maMonHoc.setEditable(true);
			}
		});
		
		list0.addMouseListener(new MouseListener() {
			
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
				if(list0.getSelectedValue()==null){
					return;
				}
				button_4.show();
				textField_tenMonHoc.setText(list0.getSelectedValue().toString());
				for(int i = 0;i< listMonHoc.size();i++){
					if(list0.getSelectedValue().equals(listMonHoc.get(i).getTenMonHoc())){
						textField_maMonHoc.setText(listMonHoc.get(i).getMaMonHoc());
						textField_ngayKetThuc.setText(format.format(listMonHoc.get(i).getNgayKetThuc()).toString());
						model1.setValue(listMonHoc.get(i).getNgayBatDau());
						textField_tenPhongHoc.setText(listMonHoc.get(i).getTenPhongHoc());
						comboBox.setSelectedItem(listMonHoc.get(i).getThuTrongTuan().toString());
						comboBox_2.setSelectedItem(listMonHoc.get(i).getThoiGianBatDau().toString());
						comboBox_3.setSelectedItem(listMonHoc.get(i).getThoiGianKetThuc().toString());
					}
				}
			}
		});
		list1.addMouseListener(new MouseListener() {
			
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
				//table.setEnabled(false);
				if(list1.getSelectedValue()==null){
					return;
				}
				String tenmonhoc = list1.getSelectedValue().toString();
				for(int i = 0;i< listMonHoc.size();i++){
					if(tenmonhoc.equals(listMonHoc.get(i).getTenMonHoc())){
						String mamonhoc =listMonHoc.get(i).getMaMonHoc();
						maFinalMonHoc = mamonhoc;
						java.util.Date ngayketthuc = listMonHoc.get(i).getNgayKetThuc();
						java.util.Date ngaybatdau = listMonHoc.get(i).getNgayBatDau();
						String tenphonghoc = listMonHoc.get(i).getTenPhongHoc();
						String thutrongtuan = listMonHoc.get(i).getThuTrongTuan().toString();
						java.util.Date thoitianbatdau = listMonHoc.get(i).getThoiGianBatDau();
						java.util.Date thoitianketthuc = listMonHoc.get(i).getThoiGianKetThuc();
						List<DanhSachDiemDanh> dsddsv = new ArrayList<DanhSachDiemDanh>();
						dsddsv = DanhSachSinhVienDAO.layThongTinDiemDanh(mamonhoc);
						if (modelkqsinhvien.getRowCount() > 0) {
						    for (int k = modelkqsinhvien.getRowCount() - 1; k > -1; k--) {
						    	modelkqsinhvien.removeRow(k);
						    }
						}
						for(int j = 0;j < dsddsv.size();j++){
							String masv = dsddsv.get(j).getTaiKhoan().getMaTaiKhoan();
							String tensv = dsddsv.get(j).getTaiKhoan().getTenNguoiDung();
							String diemdanhsv = dsddsv.get(j).getDiemDanh();
							boolean [] bdiemdanh = new boolean[15];
							char c = '0';
							for(int x = 0; x < 15;x++){
								if(diemdanhsv.charAt(x) == c){
									bdiemdanh[x] = false;
								}
								else{
									bdiemdanh[x] = true;
								}
							}
							
							
							modelkqsinhvien.addRow(new Object[]{masv,tensv,bdiemdanh[0],
									bdiemdanh[1],
									bdiemdanh[2],
									bdiemdanh[3],
									bdiemdanh[4],
									bdiemdanh[5],
									bdiemdanh[6],
									bdiemdanh[7],
									bdiemdanh[8],
									bdiemdanh[9],
									bdiemdanh[10],
									bdiemdanh[11],
									bdiemdanh[12],
									bdiemdanh[13],
									bdiemdanh[14]});
						}
					}
				}
			}
		});
		
		table.addMouseListener(new MouseListener() {
			
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
				int row = table.getSelectedRow();
				int column = table.getColumnCount();
				Object[] val = new Object[column];
				for (int i = 0; i  < column; i++) {
				   val[i] = table.getValueAt(row, i);
				}
				String diemdanhsv2 = "";
				char c = '0';
				for(int x = 2; x < column;x++){
					if( (boolean) val[x]){
						diemdanhsv2 =  diemdanhsv2+"1";
					}
					else{
						diemdanhsv2 =  diemdanhsv2+"0";
					}
				}
				String masv = (String) val[0];
				String mamonhoc = maFinalMonHoc;
				String maDS = "DS" + mamonhoc + masv;
				
		    	DanhSachDiemDanh dsdd = new DanhSachDiemDanh(maDS,diemdanhsv2,masv,mamonhoc);
		    	if(DanhSachSinhVienDAO.suaSinhVien(dsdd, maDS)){
		    		System.out.println("Thành công!");
		    		//OptionPaneSuccess("Sửa thành công."); 
		    	}
		    	else{
		    		System.out.println("Không tìm thấy sinh viên");
		    	}
			}
		});
		/***/
	}
	private JTextField createTextField1() {
		// TODO Auto-generated method stub
		 final JTextField field = new JTextField(15);
	        field.getDocument().addDocumentListener(new DocumentListener(){
	            private void filter() {
	                String filter = field.getText();
	                filterModel((DefaultListModel<String>)list1.getModel(), filter,defaultValues0);
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
	public SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private JList list = createJList(defaultValues);
	private JList list0 = createJList(defaultValues0);
	private JList list1 = createJList(defaultValues0);
	private JList jliststudentmodel = createJList(defaultValues2);
	private DefaultTableModel modelkqsinhvien = new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"M\u00E3 SSV", "T\u00EAn sinh vi\u00EAn", "Tu\u1EA7n 1", "Tu\u1EA7n 2", "Tu\u1EA7n 3", "Tu\u1EA7n 4", "Tu\u1EA7n 5", "Tu\u1EA7n 6", "Tu\u1EA7n 7","Tu\u1EA7n 8", "Tu\u1EA7n 9", "Tu\u1EA7n 10", "Tu\u1EA7n 11", "Tu\u1EA7n 12", "Tu\u1EA7n 13", "Tu\u1EA7n 14", "Tu\u1EA7n 15"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class,Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
	private List<StudentModel> listStudentModel = new ArrayList<StudentModel>();
	private List<MonHoc> listMonHoc = new ArrayList<MonHoc>();
	private String [] listModeltaikhoan = new String[]{};
	private List<String> arrltk = new ArrayList<String>();
	private String maFinalMonHoc;
	private JTextField textField_5;
	private JTextField textField_8;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_16;
	private JTextField textField_17;
	
}
