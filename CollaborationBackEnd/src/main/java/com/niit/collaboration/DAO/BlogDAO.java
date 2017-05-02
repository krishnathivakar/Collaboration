package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.Blog;

public interface BlogDAO {

	public List<Blog> list();

	public Blog get(int id);
	
	public Blog getByTitle(String title);

	public Blog save(Blog blog);
	
	public Blog saveOrUpdate(Blog blog);

	public void delete(int id);

}
