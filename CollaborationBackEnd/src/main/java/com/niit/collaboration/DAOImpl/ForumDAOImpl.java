package com.niit.collaboration.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.DAO.ForumDAO;
import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.Forum;

@Repository("ForumDAO")
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Forum> list() {
		@SuppressWarnings("unchecked")
		List<Forum> forumList = sessionFactory.getCurrentSession().createQuery("from Forum").list();
		return forumList;
	}

	@Transactional
	public Forum get(int forumId) {
		Forum forumListByID = (Forum) sessionFactory.getCurrentSession().get(Forum.class, forumId);

		return forumListByID;

	}

	@Transactional
	public void save(Forum forum) {
		sessionFactory.getCurrentSession().save(forum);
	}

	@Transactional
	public Forum saveOrUpdate(Forum forum) {
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
		return forum;

	}

	@Transactional
	public void delete(int forumId) {
		Forum forumToDelete = new Forum();
		forumToDelete.setForumId(forumId);
		sessionFactory.getCurrentSession().delete(forumToDelete);

	}

	

}