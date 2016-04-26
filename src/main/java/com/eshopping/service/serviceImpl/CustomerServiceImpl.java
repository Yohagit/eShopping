package com.eshopping.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.eshopping.dao.Customerdao;
import com.eshopping.model.Customer;
import com.eshopping.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private Customerdao customerdoa;

	@Override
	public void addCustomer(Customer c) {
		this.customerdoa.addCustomer(c);

	}

	@Override
	public void updateCustomer(Customer c) {
		this.customerdoa.updateCustomer(c);

	}

	@Override
	public List<Customer> allCustomers() {
		return this.customerdoa.allCustomers();
	}

	@Override
	public Customer getCustomerById(int id) {
		return this.customerdoa.getCustomerById(id);
	}

	@Override
	public void removeCustomer(int id) {
		this.customerdoa.removeCustomer(id);
	}

}
