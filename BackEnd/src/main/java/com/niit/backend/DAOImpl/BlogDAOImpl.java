package com.niit.backend.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.backend.DAO.BlogDAO;
import com.niit.backend.model.Blog;

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
		/*Blog blogListByID = (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);

		return blogListByID;*/
		String hql = "from Blog where id ='" + id + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Blog> listBlog = (List<Blog>) query.list();

		if (listBlog != null && !listBlog.isEmpty()) {
			return listBlog.get(0);
		}
		return null;

	}

	@Transactional
	public Blog getByTitle(String title) {
		/*Blog blogListByID = (Blog) sessionFactory.getCurrentSession().get(Blog.class, title);

		return blogListByID;*/
		String hql = "from Blog where title ='" + title + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Blog> listBlog = (List<Blog>) query.list();

		if (listBlog != null && !listBlog.isEmpty()) {
			return listBlog.get(0);
		}
		return null;

	}

	@Transactional
	public void save(Blog blog) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(blog);
	}

	@Transactional
	public void saveOrUpdate(Blog blog) {
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
		
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		Blog blogToDelete = new Blog();
		blogToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(blogToDelete);

	}

	@Transactional
	public List<Blog> getAcceptedList() {
		// TODO Auto-generated method stub
		String hql = "from Blog where status = " + "'A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Blog> list = (List<Blog>) query.list();
		
		return list;
	}

	@Transactional
	public List<Blog> getNotAcceptedList() {
		// TODO Auto-generated method stub
		String hql = "from Blog where status = " + "'NA'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Blog> list = (List<Blog>) query.list();
		
		return list;

	}


}
