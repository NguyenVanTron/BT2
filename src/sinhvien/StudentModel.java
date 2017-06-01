package sinhvien;

public class StudentModel {

	private String maSSV;
	private String tenSV;
	public StudentModel(String maSSV, String tenSV) {
		super();
		this.maSSV = maSSV;
		this.tenSV = tenSV;
	}
	public StudentModel(){}
	public String getMaSSV() {
		return maSSV;
	}
	public void setMaSSV(String maSSV) {
		this.maSSV = maSSV;
	}
	public String getTenSV() {
		return tenSV;
	}
	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
	};
	
}
