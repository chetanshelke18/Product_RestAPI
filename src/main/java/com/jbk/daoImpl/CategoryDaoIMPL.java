package com.jbk.daoImpl;

import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.Category;



@Repository
public class CategoryDaoIMPL implements CategoryDao{
	

	@Autowired
	private SessionFactory sf;

	@Override
	public boolean saveCategory(Category category) {
		boolean isAdded=false;
		Session session=null;
		int i=0;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(category);
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
	public Category getCategoryById(String categoryId) {
		Category category=null;
		Session session=null;
		try {
			session = sf.openSession();
			category=session.get(Category.class, categoryId);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) {
				session.close();
			}
		}
		return category;

	}

	@Override
	public List<Category> getAllCategorys() {
		Session session=null;
		List<Category> list=null;
		try {
			session =sf.openSession();
			Criteria criteria =session.createCriteria(Category.class);
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
	public boolean deleteCategoryById(String categoryId) {
		Session session=null;
		boolean isDeleted=false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			Category category = getCategoryById(categoryId);
			if(category !=null) {
				session.delete(category); 
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
	public boolean updateCategory(Category category) {

		Session session =null;
		boolean isUpdated=false;
		try {
			session =sf.openSession();
			Transaction transaction = session.beginTransaction();
			Category dbCategory =getCategoryById(category.getCategoryId());
			if(dbCategory !=null) {
				session.update(category);

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
