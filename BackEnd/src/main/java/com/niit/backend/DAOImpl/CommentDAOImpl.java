package com.niit.backend.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.backend.DAO.CommentDAO;
import com.niit.backend.model.Comment;

public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public CommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Comment> list() {
		@SuppressWarnings({ "unchecked" })
		List<Comment> listComment = (List<Comment>) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listComment;
	}
	@Transactional
	public List<Comment> getForumComments(int forumId) {
		String hql = "from Comment where forumId ='" + forumId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Comment> listComment = (List<Comment>) query.list();
		
		return listComment;
	}
	@Transactional
	public Comment saveOrUpdate(Comment comment) {
		sessionFactory.getCurrentSession().saveOrUpdate(comment);
		return comment;

	}
	@Transactional
	public void delete(int id) {
		Comment commentToDelete = new Comment();
		commentToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(commentToDelete);

	}

	@Transactional
	public Comment getComment(int id) {
		String hql = "from Comment where id ='" + id + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Comment> listComment = (List<Comment>) query.list();
		if (listComment != null && !listComment.isEmpty()) {
			return listComment.get(0);
		}
		return null;
	}

}