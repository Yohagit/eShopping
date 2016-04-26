package com.eshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eshopping.dao.OrderItemdao;
import com.eshopping.model.OrderItem;
import com.eshopping.service.OrderItemservice;

@Service
@Transactional
public class OrderItemserviceImpl implements OrderItemservice{
	@Autowired
	private OrderItemdao orderItemDao;
	
	public void addOrderItem(OrderItem o){
		orderItemDao.addOrderItem(o);
	}
}
