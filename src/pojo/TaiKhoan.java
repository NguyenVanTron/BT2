package pojo;

import java.util.HashSet;
import java.util.Set;

public class TaiKhoan implements java.io.Serializable{
	private String maTaiKhoan;
	private String matKhau;
	private String tenNguoiDung;
	private Boolean loai;
	private Set<DanhSachDiemDanh> monHoc = new HashSet<DanhSachDiemDanh>(0); 
	public Set<DanhSachDiemDanh> getMonHoc() {
		return monHoc;
	}
	public void setMonHoc(Set<DanhSachDiemDanh> monHoc) {
		this.monHoc = monHoc;
	}
	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}
	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}
	public TaiKhoan(String maTaiKhoan, String password, String tenNguoiDung, Boolean loai) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.matKhau = password;
		this.tenNguoiDung = tenNguoiDung;
		this.loai = loai;
	}
	public TaiKhoan(){}
	public String getPassword() {
		return matKhau;
	}
	public void setPassword(String password) {
		this.matKhau = password;
	}
	public String getTenNguoiDung() {
		return tenNguoiDung;
	}
	public void setTenNguoiDung(String tenNguoiDung) {
		this.tenNguoiDung = tenNguoiDung;
	}
	public Boolean getLoai() {
		return loai;
	}
	public void setLoai(Boolean loai) {
		this.loai = loai;
	};
	
}
