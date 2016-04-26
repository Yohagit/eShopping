package com.eshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshopping.dao.AdminDao;
import com.eshopping.model.Admin;
import com.eshopping.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public void addAdmin(Admin c) {
		this.adminDao.addAdmin(c);

	}
}
