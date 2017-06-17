package com.niit.backend.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.backend.DAO.BlogCommentDAO;
import com.niit.backend.model.BlogComment;
import com.niit.backend.model.Comment;

public class BlogCommentDAOImpl implements BlogCommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public BlogCommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<BlogComment> list() {
		@SuppressWarnings({ "unchecked" })
		List<BlogComment> listBlogComment = (List<BlogComment>) sessionFactory.getCurrentSession().createCriteria(BlogComment.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listBlogComment;
	}
	@Transactional
	public List<BlogComment> getBlogComments(int blogId) {
		String hql = "from BlogComment where blogId ='" + blogId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BlogComment> listBlogComment = (List<BlogComment>) query.list();
		
		return listBlogComment;
	}
	@Transactional
	public void saveOrUpdate(BlogComment bcomment) {
		sessionFactory.getCurrentSession().saveOrUpdate(bcomment);
		

	}
	@Transactional
	public void delete(int id) {
		BlogComment commentToDelete = new BlogComment();
		commentToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(commentToDelete);

	}

	@Transactional
	public BlogComment getBlogComment(int blogCommentId) {
		String hql = "from BlogComment where blogId ='" + blogCommentId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BlogComment> listComment = (List<BlogComment>) query.list();
		if (listComment != null && !listComment.isEmpty()) {
			return listComment.get(0);
		}
		return null;
	}
}
