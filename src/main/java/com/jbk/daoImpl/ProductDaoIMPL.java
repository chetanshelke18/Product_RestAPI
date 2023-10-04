package com.jbk.daoImpl;

import java.util.Collections;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;

@Repository
public class ProductDaoIMPL implements ProductDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public boolean saveProduct(Product product) {
		Session session = null;
		Transaction transaction = null;
		boolean isAdded = false;
		try {
			session = sf.openSession();
			transaction = session.beginTransaction();
			Product prd = session.get(Product.class, product.getProductId());
			if (prd == null) {
				session.save(product);
				transaction.commit();
				isAdded = true;
			}
		} 
		catch (PersistenceException e) {
			isAdded = false;
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isAdded;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list = null;
		Session session = null;

		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			// criteria.addOrder(Order.asc("ProductId"));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Product getProductById(String productId) {
		Session session = null;
		Product product = null;
		try {
			session = sf.openSession();
			product = session.get(Product.class, productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean deleteProductById(String productId) {
		Session session = null;
		Transaction transaction = null; // save, update,delete
		boolean isDeleted = false;
		try {
			session = sf.openSession();
			transaction = session.beginTransaction();
			Product product = session.get(Product.class, productId);
			if (product != null) {
				session.delete(product);
				transaction.commit();
				isDeleted = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = null;
		Transaction transaction = null; // save, update,delete
		boolean isUpdated = false;
		try {
			session = sf.openSession();

			transaction = session.beginTransaction();
			Product prd = session.get(Product.class, product.getProductId());
			if (prd != null) {
				session.evict(prd);
				session.update(product);
				transaction.commit();
				isUpdated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public  int[] uploadProductList(List<Product> list) {
		Session session = null;
		Transaction transaction = null;
		int uploadedcount = 0;
		int existCount=0;
		int[]arr= new int[2];
		try {
			
			for (Product product : list) {
				boolean isAdded= saveProduct(product);
				if(isAdded) {
					uploadedcount=uploadedcount+1;
				}else {
					existCount=existCount+1;
				}
			}
			arr[0]=uploadedcount;
			arr[1]=existCount;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();

			}
		}

		return arr;
	}

	@Override
	public List<Product> getMaxPriceProduct() {
		Session session =null;
		List<Product> list=null;
		try {
			double maxPrice=getMaxPrice();
			if(maxPrice>0) {
				session =sf.openSession();
				Criteria criteria=session.createCriteria(Product.class);
				criteria.add(Restrictions.eq("productPrice", maxPrice));
				list=criteria.list();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) 
				session.close();
		}
		return list;
	}

	@Override
	public double getMaxPrice() {
		Session session =null;
		List<Double> list=null;
		double maxPrice=0;
		try {
			session =sf.openSession();
			Criteria criteria=session.createCriteria(Product.class);
			criteria.setProjection(Projections.max("productPrice"));
			list=criteria.list();
			if(!(list.isEmpty())) 
				maxPrice=list.get(0);	

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			if(session!=null) 
				session.close();
		}
		return maxPrice;
	}

	@Override
	public List<Product> sortProductById_ASC() {
		Session session =null;
		List<Product> list=null;
		try {
			session =sf.openSession();
			Criteria criteria=session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("productId"));
			list=criteria.list();	

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			if(session!=null) 
				session.close();
		}
		return list;
	}

	@Override
	public List<Product> sortProductById_DESC() {
		Session session =null;
		List<Product> list=null;
		try {
			session =sf.openSession();
			Criteria criteria=session.createCriteria(Product.class);
			criteria.addOrder(Order.desc("productName"));
			list=criteria.list();	

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			if(session!=null) 
				session.close();
		}
		return list;
	}

	@Override
	public double countSumOfProductPrice() {
		Session session =null;
		List<Double> list=null;
		double sumOfPrice=0;
		try {
			session =sf.openSession();
			Criteria criteria=session.createCriteria(Product.class);
			criteria.setProjection(Projections.sum("productPrice"));
			list=criteria.list();	
			if(!list.isEmpty()) {
				sumOfPrice=list.get(0);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			if(session!=null) 
				session.close();
		}
		return sumOfPrice;
	}

	@Override
	public int getTotalCountOfProducts() {
		Session session =null;
		List<Integer> list=null;
		int  totalproducts=0;
		try {
			session =sf.openSession();
			Criteria criteria=session.createCriteria(Product.class);
			criteria.setProjection(Projections.rowCount());
			list=criteria.list();	
			if(!list.isEmpty()) {
				totalproducts=list.get(0);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			if(session!=null) 
				session.close();
		}
		return totalproducts;
	}

	@Override
	public String uploadProducts(List<Product> list) {
		Session session =null;
		int addedCount=0;
		int excludedCount=0;
		for (Product product : list) {
			session =sf.openSession();
			try {
				session.save(product);
				session.beginTransaction().commit();
				addedCount=addedCount+1;
			}catch (PersistenceException e) {
				excludedCount=excludedCount+1;
				System.out.println("Duplicate");		
			}
			catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(session!=null) { 
					session.close();
				}
			}
		}
		return "Added= "+ addedCount +"Excluded= " + excludedCount;
	}

}