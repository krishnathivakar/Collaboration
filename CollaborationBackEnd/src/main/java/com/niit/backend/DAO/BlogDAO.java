package com.niit.backend.DAO;

import java.util.List;

import com.niit.backend.model.Blog;

public interface BlogDAO {

	public List<Blog> list();
	
	public List<Blog> getAcceptedList();
	
	public List<Blog> getNotAcceptedList();

	public Blog get(int id);
	

	
	public Blog getByTitle(String title);

	public void save(Blog blog);
	
	public void saveOrUpdate(Blog blog);

	public void delete(int id);

}
