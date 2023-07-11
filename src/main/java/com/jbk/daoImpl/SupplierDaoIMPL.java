package com.jbk.daoImpl;

import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.Supplier;


@Repository
public class SupplierDaoIMPL implements SupplierDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public boolean saveSupplier(Supplier supplier) {
		boolean isAdded=false;
		Session session=null;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(supplier);
			transaction.commit();
			isAdded=true;
		} 
		catch (PersistenceException e) {
			e.printStackTrace(); 
			isAdded=false;
		}

		catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}

		return isAdded;
	}

	@Override
	public Supplier getSupplierById(String supplierId) {
		Supplier supplier=null;
		Session session=null;
		try {
			session = sf.openSession();
			supplier=session.get(Supplier.class, supplierId);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) {
				session.close();
			}
		}
		return supplier;

	}

	@Override
	public List<Supplier> getAllSuppliers() {
		Session session=null;
		List<Supplier> list=null;
		try {
			session =sf.openSession();
			Criteria criteria =session.createCriteria(Supplier.class);
			list =criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session !=null) {
				session.close();
			}
		}
		return list;	
	}

	@Override
	public boolean deleteSupplierById(String supplierId) {
		Session session=null;
		boolean isDeleted=false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			Supplier supplier = getSupplierById(supplierId);
			if(supplier !=null) {
				session.delete(supplier); 
				transaction.commit();
				isDeleted=true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return isDeleted;
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {

		Session session =null;
		boolean isUpdated=false;
		try {
			session =sf.openSession();
			Transaction transaction = session.beginTransaction();
			Supplier dbSupplier =getSupplierById(supplier.getSupplierId());
			if(dbSupplier !=null) {
				session.update(supplier);

				transaction.commit();
				isUpdated=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session !=null) {
				session.close();
			}
		}
		return isUpdated;
	}	

}



