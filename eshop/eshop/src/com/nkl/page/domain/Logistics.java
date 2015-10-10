package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Logistics extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -674161960515333295L;
	private int logistics_id; // 
	private String orders_no; // 
	private String logistics_date; // 
	private String logistics_desc; //
	
	private String random; // 
	private String ids; // 

	public void setLogistics_id(int logistics_id){
		this.logistics_id=logistics_id;
	}

	public int getLogistics_id(){
		return logistics_id;
	}

	public void setLogistics_desc(String logistics_desc){
		this.logistics_desc=logistics_desc;
	}

	public String getLogistics_desc(){
		return logistics_desc;
	}

	public void setLogistics_date(String logistics_date){
		this.logistics_date=logistics_date;
	}

	public String getLogistics_date(){
		return logistics_date;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
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

}
