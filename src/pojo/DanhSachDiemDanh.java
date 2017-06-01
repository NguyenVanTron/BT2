package pojo;

public class DanhSachDiemDanh implements java.io.Serializable {
	private String maDanhSach;
	private TaiKhoan taiKhoan;
	private String diemDanh;
	private MonHoc monHoc;
	public MonHoc getMonHoc() {
		return monHoc;
	}
	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	public String getMaDanhSach() {
		return maDanhSach;
	}
	public void setMaDanhSach(String maDanhSach) {
		this.maDanhSach = maDanhSach;
	}
	public DanhSachDiemDanh(String diemDanh,TaiKhoan taiKhoan,MonHoc monHoc) {
		super();
		this.taiKhoan = new TaiKhoan();
		this.monHoc = new MonHoc();
		this.monHoc = monHoc;
		this.taiKhoan = taiKhoan;
		this.diemDanh = diemDanh;
	}
	public DanhSachDiemDanh(String maDanhSach, String diemDanh,String maTaiKhoan,String maMonHoc) {
		this.taiKhoan = new TaiKhoan(maTaiKhoan,"","",false);
		this.monHoc = new MonHoc();
		this.monHoc.setMaMonHoc(maMonHoc);
		this.maDanhSach = maDanhSach;
		this.diemDanh = diemDanh;
	}
	public DanhSachDiemDanh(){
		this.taiKhoan = new TaiKhoan();
		this.monHoc = new MonHoc();
	}
	
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getDiemDanh() {
		return diemDanh;
	}
	public void setDiemDanh(String diemDanh) {
		this.diemDanh = diemDanh;
	};
	
}
