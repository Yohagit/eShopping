package com.eshopping.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "ORDERT")
public class Order implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double total;
	private double profit_total;
	private double profit_for_mycompany;
	@ManyToOne
	private SystemUser user;
	@Temporal(TemporalType.DATE)
	private Date date;
	@Embedded
	private Address customer_address;
	@OneToMany(mappedBy = "order", fetch=FetchType.EAGER)
	private List<OrderItem> items;
	
	@ManyToOne
	private Guest guest;
	
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public SystemUser getUser() {
		return user;
	}
	public void setUser(SystemUser user) {
		this.user = user;
	}
	public double getProfit_total() {
		return profit_total;
	}
	public void setProfit_total(double profit_total) {
		this.profit_total = profit_total;
	}
	public double getProfit_for_mycompany() {
		return profit_for_mycompany;
	}
	public void setProfit_for_mycompany(double profit_for_mycompany) {
		this.profit_for_mycompany = profit_for_mycompany;
	}
	public Address getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(Address customer_address) {
		this.customer_address = customer_address;
	}
	
}
