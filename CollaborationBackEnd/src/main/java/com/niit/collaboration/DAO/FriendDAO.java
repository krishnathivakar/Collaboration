package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.Friend;

public interface FriendDAO {
	public List<Friend> list();

	public void save(Friend friend);

	public void saveOrUpdate(Friend friend);

	public Friend getByFriendId(int id);

	public void delete(int id);

}
