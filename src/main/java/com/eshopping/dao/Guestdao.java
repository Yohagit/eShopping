package com.eshopping.dao;

import java.util.List;

import com.eshopping.model.Guest;

public interface Guestdao {

	public void addGuestr(Guest g);

	public void updateGuest(Guest g);

	public List<Guest> allGuest();

	public Guest getGuestById(int id);

	public void removeGuest(int id);
}
