package com.eshopping.dao.daoImpl;

import com.eshopping.dao.AdminDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eshopping.model.Admin;

@Repository
@Transactional
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addAdmin(Admin c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);
	}
}
