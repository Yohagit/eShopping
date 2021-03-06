package com.eshopping.service.serviceImpl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eshopping.dao.OrderDao;
import com.eshopping.model.Order;
import com.eshopping.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;

	@Override
	@Transactional
	public void addOrder(Order o) {
		this.orderDao.addOrder(o);
		
	}
	
	@Override
	@Transactional
	public ArrayList<Order> getOrdersByCustomer(int custId)
	{
		return orderDao.getOrdersByCustomer(custId);
	}
	
	@Override
	@Transactional
	public ArrayList<Order> getAllOrders()
	{
		return orderDao.allOrders();
	}

	@Override
	@Transactional
	public ArrayList<Order> getOrdersByVendor(int vendorId) {
		return orderDao.getOrdersByVendor(vendorId);
		
	}

	
}
