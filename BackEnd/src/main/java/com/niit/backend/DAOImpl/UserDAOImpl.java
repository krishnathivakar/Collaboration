package com.niit.backend.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.backend.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<User> list() {

		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class)
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
		/*
		 * String hql = "from User where id =" + "'"+ id +"'";
		 * org.hibernate.Query query =
		 * sessionFactory.getCurrentSession().createQuery(hql);
		 * 
		 * @SuppressWarnings("unchecked") List<User> listUser = (List<User>)
		 * query.list();
		 * 
		 * if (listUser != null && !listUser.isEmpty()){ return listUser.get(0);
		 * } return null; Blog blogListByID = (Blog)
		 * sessionFactory.getCurrentSession().get(Blog.class, id);
		 * 
		 * return blogListByID;
		 *
		 * 
		 */
		User userById = (User) sessionFactory.getCurrentSession().get(User.class, id);

		return userById;
	}

	@Transactional
	public User getByName(String name) {
		String hql = "from User where name =" + "'" + name + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();

		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;
	}

	@Transactional
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		User userByEmail = (User) sessionFactory.getCurrentSession().get(User.class, email);

		return userByEmail;
		/*
		 * String hql = "from User where email =" + "'"+ email +"'";
		 * org.hibernate.Query query =
		 * sessionFactory.getCurrentSession().createQuery(hql);
		 * 
		 * @SuppressWarnings("unchecked") List<User> listUser = (List<User>)
		 * query.list();
		 * 
		 * if (listUser != null && !listUser.isEmpty()){ return listUser.get(0);
		 * } return null;
		 */
	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub

		User userToDelete = new User();
		userToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	@Transactional
	public User login(User user) {

		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		String hql = "from User where email=" + "'" + user.getEmail() + "'and password = " + "'" + user.getPassword() + "'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();

		if (list != null && !list.isEmpty()) {
			return list.get(0);

		}
		return null;
	}
	
	public List<String> getOnlineUsers() {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		Query query=session.createQuery("select username from User where online=1");
		List<String> onlineUsers=query.list();
		session.close();
		return onlineUsers;
		}
}
