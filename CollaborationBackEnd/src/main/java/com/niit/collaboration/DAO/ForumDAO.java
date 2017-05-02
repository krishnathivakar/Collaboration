package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.Forum;

public interface ForumDAO {

	public List<Forum> list();

	public Forum get(int forumId);

	public void save(Forum forum);
	
	public Forum saveOrUpdate(Forum forum);

	public void delete(int forumId);
}
