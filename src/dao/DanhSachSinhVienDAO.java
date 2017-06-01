package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.DanhSachDiemDanh;
import pojo.MonHoc;
import pojo.TaiKhoan;
import util.HibernateUtil;

public class DanhSachSinhVienDAO {

	
	public static List<DanhSachDiemDanh> layDanhSachMonHoc() {
		List<DanhSachDiemDanh> ds = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
		try {
			String hql = "select ds from DanhSachDiemDanh ds";
			Query query = session.createQuery(hql);
			ds = query.list();
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	// TRue=======================================================================
	public static List<DanhSachDiemDanh> layThongTinDiemDanh(String maMonHoc) {
		List<DanhSachDiemDanh> ds = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
		boolean loai = true;
		try {
			//DanhSachDiemDanh monhoc = (DanhSachDiemDanh) session.get(DanhSachDiemDanh.class, maMonHoc);
			String hql = "from DanhSachDiemDanh ds where ds.monHoc.maMonHoc =:maMonHoc and ds.taiKhoan.loai =:loai";
			//String hql = "select ds from DanhSachDiemDanh ds";
			Query query = session.createQuery(hql);
			query.setString("maMonHoc", maMonHoc);
			query.setBoolean("loai", loai);
			ds = query.list();
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	public static List<DanhSachDiemDanh> layThongTinDiemDanhTheoTenMon(String tenMonHoc) {
		List<DanhSachDiemDanh> ds = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
		try {
			String hql = "select ds from DanhSachDiemDanh ds where ds.tenMonHoc like :"+
			"select ten.mamonhoc from MonHoc where ten.mamonhoc like:tenmonhoc";
			Query query = session.createQuery(hql);
			ds = query.list();
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	public static List<DanhSachDiemDanh> layThongTinTheoMaSinhVien(String maSinhVien) {
		List<DanhSachDiemDanh> ds = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
			boolean loai = true;
		try {
			
			String hql = "from DanhSachDiemDanh ds where ds.taiKhoan.maTaiKhoan =:maSinhVien and ds.taiKhoan.loai =:loai";
			Query query = session.createQuery(hql);
			query.setString("maTaiKhoan", maSinhVien);
			query.setBoolean("loai", loai);
			ds = query.list();
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	public static List<DanhSachDiemDanh> layTenSinhVien(String maTaiKhoan, String maMonHoc) {
		List<DanhSachDiemDanh> ds = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
		try {
			String hql = "from DanhSachDiemDanh ds where ds.taiKhoan.maTaiKhoan =:maTaiKhoan and ds.monHoc.maMonHoc =:maMonHoc";
			Query query = session.createQuery(hql);
			query.setString("maTaiKhoan", maTaiKhoan);
			query.setString("maMonHoc", maMonHoc);
			ds = query.list();
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	public static DanhSachDiemDanh layMaDanhSach(String maDS) {
		DanhSachDiemDanh ds = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
		try {
			ds = (DanhSachDiemDanh) session.get(DanhSachDiemDanh.class,
					maDS);
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	public static boolean themSinhVien(DanhSachDiemDanh ds, String maDS) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (DanhSachSinhVienDAO.layMaDanhSach(maDS)!=null) {
			return false;
		}
			Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			ds.setTaiKhoan(new TaiKhoan(ds.getTaiKhoan().getMaTaiKhoan(),"","",true));
			session.save(ds);
			transaction.commit();
		} catch (HibernateException ex) {
			//Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}
	public static boolean suaSinhVien(DanhSachDiemDanh ds, String maDS) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (DanhSachSinhVienDAO.layMaDanhSach(maDS)==null) {
			return false;
		}
			Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			ds.setTaiKhoan(new TaiKhoan(ds.getTaiKhoan().getMaTaiKhoan(),"","",true));
			session.update(ds);
			transaction.commit();
		} catch (HibernateException ex) {
			//Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}
	
	public static boolean xoaSinhVien(String maMonHoc, String maSinhVien) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<DanhSachDiemDanh> mh = DanhSachSinhVienDAO.layTenSinhVien(maSinhVien,maMonHoc);
		if(mh==null){
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(mh);
			transaction.commit();
		} catch (HibernateException ex) {
			//Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}
	
}
