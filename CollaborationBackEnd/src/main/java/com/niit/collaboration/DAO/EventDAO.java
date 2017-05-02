package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.Event;

public interface EventDAO {

	public List<Event> list();

	public void save(Event event);

	public void saveOrUpdate(Event event);

	public Event getEventId(int id);

	public void delete(int id);

}
