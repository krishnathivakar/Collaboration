package com.niit.backend.DAO;

import java.util.List;

import com.niit.backend.model.chat;

public interface ChatDAO {

	public List<chat> list();
	
	public void save(chat chat);

	public void saveOrUpdate(chat chat);

	public chat getByFriendId(int id);
	
	public void delete(int id);

}
