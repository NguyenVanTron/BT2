package pojo;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MonHoc implements java.io.Serializable{

	private String maMonHoc;
	private String tenMonHoc;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private String thuTrongTuan;
	private Time thoiGianBatDau;
	private Time thoiGianKetThuc;
	private String tenPhongHoc;
	private Set<DanhSachDiemDanh> danhSachDiemDanh = new HashSet<DanhSachDiemDanh>(0);
	public Set<DanhSachDiemDanh> getDanhSachDiemDanh() {
		return danhSachDiemDanh;
	}
	public void setDanhSachDiemDanh(Set<DanhSachDiemDanh> danhSachDiemDanh) {
		this.danhSachDiemDanh = danhSachDiemDanh;
	}
	@SuppressWarnings("deprecation")
	public MonHoc(String maMonHoc, String tenMonHoc, Date ngayBatDau, Date ngayKetThuc, String thuTrongTuan,
			Time thoiGianBatDau, Time thoiGianKetThuc, String tenPhongHoc) {
		this.maMonHoc = maMonHoc;
		this.tenMonHoc = tenMonHoc;
		this.ngayBatDau = new Date();
		this.ngayKetThuc = new Date();
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.thuTrongTuan = thuTrongTuan;
		this.thoiGianBatDau = new Time(0,0,0);
		this.thoiGianKetThuc = new Time(0,0,0);
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.tenPhongHoc = tenPhongHoc;
	}
	@SuppressWarnings("deprecation")
	public MonHoc(){
		this.ngayBatDau = new Date();
		this.ngayKetThuc = new Date();
		this.thoiGianBatDau = new Time(0,0,0);
		this.thoiGianKetThuc = new Time(0,0,0);
	}
	public String getMaMonHoc() {
		return maMonHoc;
	}
	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public String getThuTrongTuan() {
		return thuTrongTuan;
	}
	public void setThuTrongTuan(String thuTrongTuan) {
		this.thuTrongTuan = thuTrongTuan;
	}
	public Time getThoiGianBatDau() {
		return thoiGianBatDau;
	}
	public void setThoiGianBatDau(Time thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}
	public Time getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}
	public void setThoiGianKetThuc(Time thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}
	public String getTenPhongHoc() {
		return tenPhongHoc;
	}
	public void setTenPhongHoc(String tenPhongHoc) {
		this.tenPhongHoc = tenPhongHoc;
	};
}
