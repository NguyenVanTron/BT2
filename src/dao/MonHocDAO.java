package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pojo.DanhSachDiemDanh;
import pojo.MonHoc;
import util.HibernateUtil;

public class MonHocDAO {

	public static List<MonHoc> layDanhSachMonHoc() {
		List<MonHoc> ds = null;
		Session session = HibernateUtil.getSessionFactory()
				.openSession();
		try {
			String hql = "select mh from MonHoc mh";
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
	public static List<MonHoc> layDanhSachMonHocTheoSinhVien(String maTaiKhoan) {
		List<MonHoc> ds = null;
		List<MonHoc> ds2 = new ArrayList<MonHoc>();
		Session session = HibernateUtil.getSessionFactory()
				.openSession();
		try {
			String hql = "select mh from MonHoc mh";
			Query query = session.createQuery(hql);
			ds = query.list();
			for(int i = 0;i< ds.size();i++){
				Iterator<DanhSachDiemDanh> dsdd =ds.get(i).getDanhSachDiemDanh().iterator();
				while(dsdd.hasNext()){
					DanhSachDiemDanh s =dsdd.next();
					if(s.getTaiKhoan().getMaTaiKhoan().equals(maTaiKhoan)){
						ds2.add(ds.get(i));break;
					}
				}
			}
			
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds2;
	}
	public static MonHoc layThongTinMonHocBangTen(String tenMonHoc) {
		MonHoc mh = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
		try {
			mh = (MonHoc) session.get(MonHoc.class,
			tenMonHoc);
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return mh;
	}
	public static MonHoc layThongTinMonHocBangMa(String maMonHoc) {
		MonHoc mh = null;
			Session session = HibernateUtil.getSessionFactory()
			.openSession();
		try {
			mh = (MonHoc) session.get(MonHoc.class,
			maMonHoc);
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return mh;
	}
	public static boolean themMonHocBangMa(MonHoc mh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (MonHocDAO.layThongTinMonHocBangMa(mh.getMaMonHoc())!=null) {
			return false;
		}
			Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(mh);
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
	public static boolean capNhatThongTinMonHocBangMa(MonHoc mh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (MonHocDAO.layThongTinMonHocBangMa(mh.getMaMonHoc()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(mh);
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
	public static boolean capNhatThongTinMonHocBangTen(MonHoc mh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (MonHocDAO.layThongTinMonHocBangTen(mh.getTenMonHoc()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(mh);
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
	public static boolean xoaMonHocBangTen(String tenMonHoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		MonHoc mh = MonHocDAO.layThongTinMonHocBangTen(tenMonHoc);
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
	public static boolean xoaMonHocBangMa(String maMonHoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		MonHoc mh = MonHocDAO.layThongTinMonHocBangMa(maMonHoc);
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
