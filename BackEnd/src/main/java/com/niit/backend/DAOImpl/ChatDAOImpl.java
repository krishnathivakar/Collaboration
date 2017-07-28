/*package com.niit.backend.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.backend.DAO.ChatDAO;
import com.niit.backend.model.chat;

@Repository("ChatDAO")
public class ChatDAOImpl implements ChatDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ChatDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Transactional
	public List<chat> list() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<chat> chatList = sessionFactory.getCurrentSession().createQuery("from Chat").list();
		return chatList;
	}

	@Transactional
	public void save(chat chat) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(chat);
	}

	@Transactional
	public void saveOrUpdate(chat chat) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(chat);
	}

	@Transactional
	public chat getByFriendId(int id) {
		// TODO Auto-generated method stub
		chat chatListByID = (chat) sessionFactory.getCurrentSession().get(chat.class, id);

		return chatListByID;

	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(id);
	}

}
*/