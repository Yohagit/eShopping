package com.eshopping.dao.daoImpl;

import com.eshopping.dao.OrderDao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.eshopping.model.Order;
import com.eshopping.model.SystemUser;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {
	
	@Autowired
	SessionFactory sessionFactory;

	public SystemUser getCustomerById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		SystemUser c = (SystemUser) session.load(SystemUser.class, new Integer(
				id));

		return c;
	}

	@Override
	public ArrayList<Order> getOrdersByCustomer(int custId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		
		String sql = "SELECT * FROM ordert WHERE user_userId = " + custId;
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Order.class);
		List result = query.list();
		
		return (ArrayList<Order>)result;
	}

	@Override
	public ArrayList<Order> allOrders() {
            
                Session session = this.sessionFactory.getCurrentSession();
            
                String sql = "SELECT * FROM ordert";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Order.class);
		List result = query.list();
		
		return (ArrayList<Order>)result;
//		Session session = this.sessionFactory.getCurrentSession();
//		ArrayList<Order> orders = (ArrayList<Order>) session.createCriteria(
//				Order.class).list();
//		//System.out.print("&&&&&&&&" + orders.get(0).getTotal());
//		return orders;
	}
        
  

	@Override
	public void addOrder(Order o) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(o);

	}

	@Override
	public ArrayList<Order> getOrdersByVendor(int vendorId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		
		//String sql = "SELECT o FROM ordert o WHERE o.items.product.vendor.userId = " + vendorId;
		String sql = "select o.* from ordert o inner join orderitem oi inner join product p "+
				"where o.id = oi.order_id and oi.product_product_id = p.product_id "+
				"and p.vendor_id = "+vendorId;
		System.out.println(sql);
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Order.class);
		List result = query.list();
		
		return (ArrayList<Order>)result;
	}

}
