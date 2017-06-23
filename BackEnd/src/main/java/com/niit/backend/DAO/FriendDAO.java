package com.niit.backend.DAO;

import java.util.List;

import com.niit.backend.model.Friend;

public interface FriendDAO {
	public List<Friend> list();

	public List<Friend> list(int friendId);
	
	public void save(Friend friend);

	public void saveOrUpdate(Friend friend);

	public Friend getByFriendId(int id);
	
	public Friend getByFriendName(String name);

	public void delete(int id);

}
