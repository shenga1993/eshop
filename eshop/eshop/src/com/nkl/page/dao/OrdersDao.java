package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.page.domain.Orders;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class OrdersDao {

	public int addOrders(Orders orders, Connection conn){
		String sql = "INSERT INTO orders(orders_id,orders_no,orders_date,user_id,real_name,user_address,user_phone,orders_money,send_id,send_name,orders_flag) values(null,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			orders.getOrders_no(),
			orders.getOrders_date(),
			orders.getUser_id(),
			orders.getReal_name(),
			orders.getUser_address(),
			orders.getUser_phone(),
			orders.getOrders_money(),
			orders.getSend_id(),
			orders.getSend_name(),
			orders.getOrders_flag()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delOrders(String orders_id, Connection conn){
		String sql = "DELETE FROM orders WHERE orders_id=?";

		Object[] params = new Object[] { new Integer(orders_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delOrderss(String[] orders_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <orders_ids.length; i++) {
			sBuilder.append("?");
			if (i !=orders_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM orders WHERE orders_id IN(" +sBuilder.toString()+")";

		Object[] params = orders_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateOrders(Orders orders, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE orders SET ");
		if (!StringUtil.isEmptyString(orders.getReal_name())) {
			sBuilder.append(" ,real_name = '" + orders.getReal_name() +"' ");
		}
		if (!StringUtil.isEmptyString(orders.getUser_address())) {
			sBuilder.append(" ,user_address = '" + orders.getUser_address() +"' ");
		}
		if (!StringUtil.isEmptyString(orders.getUser_phone())) {
			sBuilder.append(" ,user_phone = '" + orders.getUser_phone() +"' ");
		}
		if (orders.getOrders_flag()!=0) {
			sBuilder.append(" ,orders_flag = " + orders.getOrders_flag() +" ");
		}
		if (orders.getSend_id()!=0) {
			sBuilder.append(" ,send_id = " + orders.getSend_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getSend_name())) {
			sBuilder.append(" ,send_name = '" + orders.getSend_name() +"' ");
		}
		if (orders.getOrders_id()!=0) {
			sBuilder.append(" where orders_id = " + orders.getOrders_id() +" ");
		}else {
			sBuilder.append(" where orders_no = '" + orders.getOrders_no() +"' ");
		}

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString().replaceFirst(",", ""), params, conn);
	}
	
	public int updateOrdersMoney(Orders orders, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE orders SET orders_money = (select sum(d.goods_money) from orders_detail d where d.orders_no="+orders.getOrders_no()+") ");
		sBuilder.append(" where orders_no = '" + orders.getOrders_no() +"' ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public Orders getOrders(Orders orders, Connection conn){
		Orders _orders=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM orders WHERE 1=1");
		if (orders.getOrders_id()!=0) {
			sBuilder.append(" and orders_id = " + orders.getOrders_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_no())) {
			sBuilder.append(" and orders_no = '" + orders.getOrders_no() +"' ");
		}

		List<Object> list = BaseDao.executeQuery(Orders.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _orders = (Orders)list.get(0);
		}
		return _orders;
	}

	public List<Orders>  listOrderss(Orders orders, Connection conn){
		List<Orders> orderss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM orders o WHERE 1=1");

		if (orders.getOrders_id()!=0) {
			sBuilder.append(" and o.orders_id = " + orders.getOrders_id() +" ");
		}
		if (orders.getUser_id()!=0) {
			sBuilder.append(" and o.user_id = " + orders.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getReal_name())) {
			sBuilder.append(" and o.real_name like '%" + orders.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_no())) {
			sBuilder.append(" and o.orders_no like '%" + orders.getOrders_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_date_min())) {
			sBuilder.append(" and o.orders_date >= str_to_date('" + orders.getOrders_date_min() +"','%Y-%m-%d') ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_date_max())) {
			sBuilder.append(" and o.orders_date <= str_to_date('" + orders.getOrders_date_max() +"','%Y-%m-%d') ");
		}
		if (orders.getSend_id()!=0) {
			sBuilder.append(" and o.send_id = " + orders.getSend_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getSend_name())) {
			sBuilder.append(" and o.send_name like '%" + orders.getSend_name() +"%' ");
		}
		if (orders.getOrders_flag()!=0) {
			sBuilder.append(" and o.orders_flag = " + orders.getOrders_flag() +" ");
		}
		sBuilder.append(" order by o.orders_date desc,o.orders_id asc) t");

		if (orders.getStart() != -1) {
			sBuilder.append(" limit " + orders.getStart() + "," + orders.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Orders.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			orderss = new ArrayList<Orders>();
			for (Object object : list) {
				orderss.add((Orders)object);
			}
		}
		return orderss;
	}

	public int  listOrderssCount(Orders orders, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM orders o  WHERE 1=1");

		if (orders.getOrders_id()!=0) {
			sBuilder.append(" and o.orders_id = " + orders.getOrders_id() +" ");
		}
		if (orders.getUser_id()!=0) {
			sBuilder.append(" and o.user_id = " + orders.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getReal_name())) {
			sBuilder.append(" and o.real_name like '%" + orders.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_no())) {
			sBuilder.append(" and o.orders_no like '%" + orders.getOrders_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_date_min())) {
			sBuilder.append(" and o.orders_date >= str_to_date('" + orders.getOrders_date_min() +"','%Y-%m-%d') ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_date_max())) {
			sBuilder.append(" and o.orders_date <= str_to_date('" + orders.getOrders_date_max() +"','%Y-%m-%d') ");
		}
		if (orders.getSend_id()!=0) {
			sBuilder.append(" and o.send_id = " + orders.getSend_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getSend_name())) {
			sBuilder.append(" and o.send_name like '%" + orders.getSend_name() +"%' ");
		}
		if (orders.getOrders_flag()!=0) {
			sBuilder.append(" and o.orders_flag = " + orders.getOrders_flag() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
