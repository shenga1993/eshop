package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.page.domain.Evaluate;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class EvaluateDao {

	public int addEvaluate(Evaluate evaluate, Connection conn){
		String sql = "INSERT INTO evaluate(evaluate_id,orders_no,user_id,nick_name,goods_id,evaluate_date,evaluate_level,evaluate_content) values(null,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			evaluate.getOrders_no(),
			evaluate.getUser_id(),
			evaluate.getNick_name(),
			evaluate.getGoods_id(),
			evaluate.getEvaluate_date(),
			evaluate.getEvaluate_level(),
			evaluate.getEvaluate_content()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}
	
	public int addEvaluateBatch(Evaluate evaluate, Connection conn){
		String sql = "INSERT INTO evaluate(orders_no,user_id,nick_name,goods_id,evaluate_date,evaluate_level,evaluate_content) "
				   + " SELECT  '"+evaluate.getOrders_no()+"',"+evaluate.getUser_id()+",'"+evaluate.getNick_name()+"',goods_id,'"
				   + evaluate.getEvaluate_dateShow()+"',"+ evaluate.getEvaluate_level()+",'"+evaluate.getEvaluate_content()
				   + "'  FROM orders_detail WHERE orders_no='"+evaluate.getOrders_no()+"'";
		Object[] params = null;
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delEvaluate(String evaluate_id, Connection conn){
		String sql = "DELETE FROM evaluate WHERE evaluate_id=?";

		Object[] params = new Object[] { new Integer(evaluate_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delEvaluates(String[] evaluate_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <evaluate_ids.length; i++) {
			sBuilder.append("?");
			if (i !=evaluate_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM evaluate WHERE evaluate_id IN(" +sBuilder.toString()+")";

		Object[] params = evaluate_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateEvaluate(Evaluate evaluate, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE evaluate SET evaluate_id = " + evaluate.getEvaluate_id() +" ");
		if (evaluate.getEvaluate_level()!=0) {
			sBuilder.append(" ,evaluate_level = " + evaluate.getEvaluate_level() +" ");
		}
		sBuilder.append(" where evaluate_id = " + evaluate.getEvaluate_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public Evaluate getEvaluate(Evaluate evaluate, Connection conn){
		Evaluate _evaluate=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM evaluate WHERE 1=1");
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getOrders_no())) {
			sBuilder.append(" and orders_no = '" + evaluate.getOrders_no() +"' ");
		}
		if (evaluate.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + evaluate.getUser_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Evaluate.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _evaluate = (Evaluate)list.get(0);
		}
		return _evaluate;
	}

	public List<Evaluate>  listEvaluates(Evaluate evaluate, Connection conn){
		List<Evaluate> evaluates = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM evaluate WHERE 1=1");
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getOrders_no())) {
			sBuilder.append(" and orders_no = '" + evaluate.getOrders_no() +"' ");
		}
		if (evaluate.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + evaluate.getUser_id() +" ");
		}
		if (evaluate.getGoods_id()!=0) {
			sBuilder.append(" and goods_id = " + evaluate.getGoods_id() +" ");
		}
		
		sBuilder.append(" order by evaluate_date desc,evaluate_id asc) t");

		if (evaluate.getStart() != -1) {
			sBuilder.append(" limit " + evaluate.getStart() + "," + evaluate.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Evaluate.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			evaluates = new ArrayList<Evaluate>();
			for (Object object : list) {
				evaluates.add((Evaluate)object);
			}
		}
		return evaluates;
	}

	public int  listEvaluatesCount(Evaluate evaluate, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM evaluate  WHERE 1=1");

		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getOrders_no())) {
			sBuilder.append(" and orders_no = '" + evaluate.getOrders_no() +"' ");
		}
		if (evaluate.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + evaluate.getUser_id() +" ");
		}
		if (evaluate.getGoods_id()!=0) {
			sBuilder.append(" and goods_id = " + evaluate.getGoods_id() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
