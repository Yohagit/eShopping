package com.eshopping.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshopping.dao.Guestdao;
import com.eshopping.model.Guest;
import com.eshopping.service.Guestservice;

@Service
@Transactional
public class GuestserviceImpl implements Guestservice {

	@Autowired
	private Guestdao guestDao;

	@Override
	public void updateGuest(Guest g) {
		guestDao.updateGuest(g);

	}

	@Override
	public List<Guest> allGuest() {
		return guestDao.allGuest();
	}

	@Override
	public Guest getGuestById(int id) {
		return guestDao.getGuestById(id);
	}

	@Override
	public void removeGuest(int id) {
		guestDao.removeGuest(id);
	}

	@Override
	public void addGuest(Guest g) {
		this.guestDao.addGuestr(g);
		
	}

}
