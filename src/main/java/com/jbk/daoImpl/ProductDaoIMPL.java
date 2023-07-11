package com.jbk.daoImpl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;

@Repository
public class ProductDaoIMPL implements ProductDao{
	
	@Autowired
	private SessionFactory sf;

	@Override
	public boolean saveProduct(Product product) {
		boolean isAdded=false;
		Session session=null;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(product);
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
	public Product getProductById(String productId) {
		Product product=null;
		Session session=null;
		try {
			session = sf.openSession();
			product=session.get(Product.class, productId);
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) {
				session.close();
			}
		}
		return product;
		
	}

	@Override
	public List<Product> getAllProducts() {
		Session session=null;
		List<Product> list=null;
		try {
			session =sf.openSession();
			Criteria criteria =session.createCriteria(Product.class);
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
	public boolean deleteProductById(String productId) {
		Session session=null;
		boolean isDeleted=false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			Product product = session.get(Product.class, productId);
			if(product !=null) {
				session.delete(product); 
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
	public boolean updateProduct(Product product) {
		
		Session session =null;
		boolean isUpdated=false;
		try {
			session =sf.openSession();
			Transaction transaction = session.beginTransaction();
			Product dbProduct =getProductById(product.getProductId());
			if(dbProduct !=null) {
			session.update(product);

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
