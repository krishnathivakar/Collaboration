package com.niit.collaboration.DAO;

import java.util.List;


import com.niit.collaboration.model.Chat;

public interface ChatDAO {

	public List<Chat> list();
	
	public void save(Chat chat);

	public void saveOrUpdate(Chat chat);

	public Chat getByFriendId(int id);
	
	public void delete(int id);

}
