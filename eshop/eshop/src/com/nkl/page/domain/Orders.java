package com.nkl.page.domain;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.domain.BaseDomain;

public class Orders extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int orders_id; // 
	private String orders_no;
	private String orders_date;
	private int user_id; // 
	private String real_name;
	private String user_address; // 
	private String user_phone; // 
	private double orders_money;
	private int send_id; // 
	private String send_name;
	private int orders_flag; // 1：待发货 2-已发货 3-已收货 4-已评价
	
	private String orders_date_min;
	private String orders_date_max;
	
	private List<OrdersDetail> ordersDetails = new ArrayList<OrdersDetail>();
	
	private String ids;
	
	public String getOrders_flagDesc() {
		switch (orders_flag) {
			case 1:
				return "待发货";
			case 2:
				return "已发货";
			case 3:
				return "已收货";
			case 4:
				return "已评价";
			default:
				return "";
		}
	}
	
	public int getOrders_flag() {
		return orders_flag;
	}

	public void setOrders_flag(int orders_flag) {
		this.orders_flag = orders_flag;
	}

	public int getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrders_date() {
		return orders_date;
	}

	public void setOrders_date(String orders_date) {
		this.orders_date = orders_date;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOrders_no() {
		return orders_no;
	}

	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public double getOrders_money() {
		return orders_money;
	}

	public void setOrders_money(double orders_money) {
		this.orders_money = orders_money;
	}

	public String getOrders_date_min() {
		return orders_date_min;
	}

	public void setOrders_date_min(String orders_date_min) {
		this.orders_date_min = orders_date_min;
	}

	public String getOrders_date_max() {
		return orders_date_max;
	}

	public void setOrders_date_max(String orders_date_max) {
		this.orders_date_max = orders_date_max;
	}

	public List<OrdersDetail> getOrdersDetails() {
		return ordersDetails;
	}

	public void setOrdersDetails(List<OrdersDetail> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public int getSend_id() {
		return send_id;
	}

	public void setSend_id(int send_id) {
		this.send_id = send_id;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

}
