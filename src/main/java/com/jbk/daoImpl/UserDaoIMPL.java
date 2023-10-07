package com.jbk.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.UserDao;
import com.jbk.entity.User;

@Repository
public class UserDaoIMPL implements UserDao{

	@Autowired
	private SessionFactory sf;

	@Override
	public User login(User user) {
		Session session=null; 
		User dbUser=null;
		try {
			session=sf.openSession();
			dbUser=session.get(User.class, user.getUsername());
			if(dbUser!=null) {
				if(user.getPassword().equals(dbUser.getPassword())) {
					return dbUser;
				}else {
					dbUser=null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) {
				session.close();
			}
		}


		return dbUser;
	}

}
