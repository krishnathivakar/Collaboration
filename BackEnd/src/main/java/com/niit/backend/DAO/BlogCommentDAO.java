package com.niit.backend.DAO;

import java.util.List;

import com.niit.backend.model.BlogComment;

public interface BlogCommentDAO {
	
	public List<BlogComment> list();
	
	public List<BlogComment> getBlogComments(int blogId);
	
	public BlogComment getBlogComment(int blogCommentId);
	
	public void saveOrUpdate(BlogComment bcomment);
		
	public void delete(int id);


}
