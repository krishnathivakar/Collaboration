package com.niit.collaboration.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.collaboration.DAO.BlogDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Forum;

public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Blog> list() {
		@SuppressWarnings("unchecked")
		List<Blog> blogList = sessionFactory.getCurrentSession().createQuery("from Blog").list();
		return blogList;
	}

	@Transactional
	public Blog get(int id) {
		Blog blogListByID = (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);

		return blogListByID;

	}

	@Transactional
	public Blog getByTitle(String title) {
		Blog blogListByID = (Blog) sessionFactory.getCurrentSession().get(Blog.class, title);

		return blogListByID;

	}

	@Transactional
	public Blog save(Blog blog) {
		// TODO Auto-generated method stub
		return (Blog) sessionFactory.getCurrentSession().save(blog);
	}

	@Transactional
	public Blog saveOrUpdate(Blog blog) {
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
		return blog;
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		Blog blogToDelete = new Blog();
		blogToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(blogToDelete);

	}


}
