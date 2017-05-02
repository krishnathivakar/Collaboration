package com.niit.collaboration.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.collaboration.DAO.FriendDAO;
import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.Friend;

public class FriendDAOImpl implements FriendDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}


	@Transactional
	public List<Friend> list() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Friend").list();
	}

	@Transactional
	public void save(Friend friend) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(friend);
	}

	@Transactional
	public void saveOrUpdate(Friend friend) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(friend);
	}

	@Transactional
	public Friend getByFriendId(int id) {
		// TODO Auto-generated method stub
		Friend friendListByID = (Friend) sessionFactory.getCurrentSession().get(Friend.class, id);

		return friendListByID;

	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(id);
	}

}
