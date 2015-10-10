package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Goods extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int goods_id; // 
	private int goods_type_id; // 
	private String goods_name; // 
	private String goods_pic;
	private double goods_price1; // 
	private double goods_price2; // 
	private int goods_count; // 
	private String goods_date; // 
	private String goods_desc; // 
	private int goods_flag; // 1：正常 2：促销
	
	private String goods_type_name; // 
	private String ids;
	
	public String getGoods_flagDesc() {
		switch (goods_flag) {
			case 1:
				return "正常 ";
			case 2:
				return "促销 ";
			default:
				return "";
		}
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getGoods_type_id() {
		return goods_type_id;
	}

	public void setGoods_type_id(int goods_type_id) {
		this.goods_type_id = goods_type_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_pic() {
		return goods_pic;
	}

	public void setGoods_pic(String goods_pic) {
		this.goods_pic = goods_pic;
	}

	public String getGoods_descShow(){
		if (!StringUtil.isEmptyString(goods_desc)) {
			return Transcode.htmlDiscode(goods_desc);
		}
		return goods_desc;
	}
	
	public String getGoods_desc() {
		return goods_desc;
	}

	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}

	public String getGoods_type_name() {
		return goods_type_name;
	}

	public void setGoods_type_name(String goods_type_name) {
		this.goods_type_name = goods_type_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public double getGoods_price1() {
		return goods_price1;
	}

	public void setGoods_price1(double goods_price1) {
		this.goods_price1 = goods_price1;
	}

	public double getGoods_price2() {
		return goods_price2;
	}

	public void setGoods_price2(double goods_price2) {
		this.goods_price2 = goods_price2;
	}

	public int getGoods_count() {
		return goods_count;
	}

	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}

	public String getGoods_date() {
		return goods_date;
	}

	public void setGoods_date(String goods_date) {
		this.goods_date = goods_date;
	}

	public int getGoods_flag() {
		return goods_flag;
	}

	public void setGoods_flag(int goods_flag) {
		this.goods_flag = goods_flag;
	}


}
