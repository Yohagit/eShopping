package com.eshopping.service;

import java.util.List;

import com.eshopping.model.Vendor;;

public interface VendorService {

	public void addVendor(Vendor v);

	public void updateVendor(Vendor v);

	public List<Vendor> allVendors();

	public Vendor getVendorById(int id);

	public void removeVendor(int id);
}
