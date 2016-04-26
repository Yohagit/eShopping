package com.eshopping.service;

import java.util.List;
import com.eshopping.model.Customer;

public interface CustomerService {
	
	public void addCustomer(Customer c);

	public void updateCustomer(Customer c);

	public List<Customer> allCustomers();

	public Customer getCustomerById(int id);

	public void removeCustomer(int id);
}
