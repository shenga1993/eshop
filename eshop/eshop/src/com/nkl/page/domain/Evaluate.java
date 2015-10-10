package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class Evaluate extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int evaluate_id; // 
	private String orders_no;
	private int user_id; // 
	private String nick_name;
	private int goods_id; // 
	private String evaluate_date; 
	private int evaluate_level; // 1-差评 2-一般 3-比较满意 4-好评
	private String evaluate_content; //
	
	private String evaluate_date_min;
	private String evaluate_date_max;
	
	private String ids;
	
	public String getEvaluate_levelDesc() {
		switch (evaluate_level) {
			case 1:
				return "差评";
			case 2:
				return "一般";
			case 3:
				return "比较满意";
			case 4:
				return "好评";
			default:
				return "";
		}
	}
	
	public int getEvaluate_id() {
		return evaluate_id;
	}

	public void setEvaluate_id(int evaluate_id) {
		this.evaluate_id = evaluate_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEvaluate_date() {
		return evaluate_date;
	}
	
	public String getEvaluate_dateShow() {
		try {
			return DateUtil.dateToDateString(DateUtil.getDate(evaluate_date));
		} catch (Exception e) {
			return evaluate_date;
		}
	}

	public void setEvaluate_date(String evaluate_date) {
		this.evaluate_date = evaluate_date;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getEvaluate_date_min() {
		return evaluate_date_min;
	}

	public void setEvaluate_date_min(String evaluate_date_min) {
		this.evaluate_date_min = evaluate_date_min;
	}

	public String getEvaluate_date_max() {
		return evaluate_date_max;
	}

	public void setEvaluate_date_max(String evaluate_date_max) {
		this.evaluate_date_max = evaluate_date_max;
	}

	public String getOrders_no() {
		return orders_no;
	}

	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getEvaluate_level() {
		return evaluate_level;
	}

	public void setEvaluate_level(int evaluate_level) {
		this.evaluate_level = evaluate_level;
	}

	public String getEvaluate_content() {
		return evaluate_content;
	}

	public void setEvaluate_content(String evaluate_content) {
		this.evaluate_content = evaluate_content;
	}

}
