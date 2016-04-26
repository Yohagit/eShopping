package com.eshopping.service;

import java.util.List;

import com.eshopping.model.Customer;
import com.eshopping.model.SystemUser;
import com.eshopping.model.Vendor;

public interface SystemUserService {

	public SystemUser checkLogin(String userName, String userPassword);
        
        public boolean UserExisting(String userName);
        
	public void addUser(SystemUser user);

	public void updateUser(SystemUser user);

	public List<SystemUser> allUsers();
	public List<Customer> allCustomerUsers();

	public List<Vendor> allVendorUsers();
	public SystemUser getUserById(int id);

	public void removeUser(int id);
}
