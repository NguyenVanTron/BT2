package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.TaiKhoan;
import util.HibernateUtil;

public class TaiKhoanDAO {
	public static List<TaiKhoan> layDanhSachTaiKhoan() {
		List<TaiKhoan> ds = null;
		Session session = HibernateUtil.getSessionFactory()
				.openSession();
		try {
			String hql = "select tk from TaiKhoan tk";
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
	public static List<TaiKhoan> layDanhSachTaiKhoanSV() {
		List<TaiKhoan> ds = null;
		Session session = HibernateUtil.getSessionFactory()
				.openSession();
		Boolean loai = true;
		try {
			String hql = "from TaiKhoan tk where tk.loai =:loai";
			Query query = session.createQuery(hql);
			query.setBoolean("loai",loai);
			ds = query.list();
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	public static TaiKhoan layThongTinTaiKhoanSinhVien(String maSSV) {
		TaiKhoan tk = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
		try {
			tk = (TaiKhoan) session.get(TaiKhoan.class,
					maSSV);
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return tk;
	}
	
	public static TaiKhoan layThongTinTaiKhoanGiaoVu(String tenNguoiDung) {
		TaiKhoan tk = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
		try {
			tk = (TaiKhoan) session.get(TaiKhoan.class,
					tenNguoiDung);
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		if(tk.getLoai()==true)
			return null;
		return tk;
	}
	public static boolean themTaoKhoanSinhVien(TaiKhoan tk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (TaiKhoanDAO.layThongTinTaiKhoanSinhVien(tk.getMaTaiKhoan())!=null) {
			return false;
		}
			Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(tk);
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
	public static boolean themTaoKhoanGiaoVu(TaiKhoan tk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (TaiKhoanDAO.layThongTinTaiKhoanGiaoVu(tk.getTenNguoiDung())!=null
				|| tk.getLoai()==true) {
			return false;
		}
			Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(tk);
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
	public static boolean capNhatThongTinSinhVien(TaiKhoan tk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (TaiKhoanDAO.layThongTinTaiKhoanSinhVien(tk.getMaTaiKhoan()) == null
				|| tk.getLoai()==false) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(tk);
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
	public static boolean capNhatThongTinGiaoVu(TaiKhoan tk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (TaiKhoanDAO.layThongTinTaiKhoanGiaoVu(tk.getTenNguoiDung()) == null
				|| tk.getLoai()== true) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(tk);
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
	public static boolean xoaTaiKhoanSinhVien(String maSSV) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoanSinhVien(maSSV);
		if(tk==null || tk.getLoai()==false){
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(tk);
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
	public static boolean xoaTaiKhoanGiaoVu(String tenNguoiDung) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoanGiaoVu(tenNguoiDung);
		if(tk==null || tk.getLoai()==true){
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(tk);
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
