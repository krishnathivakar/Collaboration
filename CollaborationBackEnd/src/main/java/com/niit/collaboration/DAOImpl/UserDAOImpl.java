package com.niit.collaboration.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.model.User;


@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	public List<User> list() {
		
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>)
		sessionFactory.getCurrentSession().createCriteria(User.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUser;

	}

	@Transactional
	public void save(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}
	
	@Transactional
	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Transactional
	public User getById(int id) {
		/*String hql = "from User where id =" + "'"+ id +"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		
		if (listUser != null && !listUser.isEmpty()){
			return listUser.get(0);
		}
		return null;*/
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		 
		 return user;
	}
	
	@Transactional
	public User getByName(String name){
		String hql = "from User where name =" + "'"+ name +"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		
		if (listUser != null && !listUser.isEmpty()){
			return listUser.get(0);
		}
		return null;
	}



	@Transactional
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		User user = (User) sessionFactory.getCurrentSession().get(User.class, email);
		 
		 return user;
	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub

		User userToDelete = new User();
		userToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	
	

}
