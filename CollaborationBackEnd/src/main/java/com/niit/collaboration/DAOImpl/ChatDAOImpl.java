package com.niit.collaboration.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.DAO.ChatDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Chat;

@Repository("ChatDAO")
public class ChatDAOImpl implements ChatDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ChatDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Transactional
	public List<Chat> list() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Chat> chatList = sessionFactory.getCurrentSession().createQuery("from Chat").list();
		return chatList;
	}

	@Transactional
	public void save(Chat chat) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(chat);
	}

	@Transactional
	public void saveOrUpdate(Chat chat) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(chat);
	}

	@Transactional
	public Chat getByFriendId(int id) {
		// TODO Auto-generated method stub
		Chat chatListByID = (Chat) sessionFactory.getCurrentSession().get(Chat.class, id);

		return chatListByID;

	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(id);
	}

}
