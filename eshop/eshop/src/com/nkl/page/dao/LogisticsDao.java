package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.User;

public class LogisticsDao {

	public int addLogistics(Logistics logistics, Connection conn){
		String sql = "INSERT INTO logistics(logistics_id,orders_no,logistics_date,logistics_desc) values(null,?,?,?)";
		Object[] params = new Object[] {
			logistics.getOrders_no(),
			logistics.getLogistics_date(),
			logistics.getLogistics_desc()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delLogistics(String logistics_id, Connection conn){
		String sql = "DELETE FROM logistics WHERE logistics_id=?";

		Object[] params = new Object[] { new Integer(logistics_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delLogisticss(String[] logistics_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <logistics_ids.length; i++) {
			sBuilder.append("?");
			if (i !=logistics_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM logistics WHERE logistics_id IN(" +sBuilder.toString()+")";

		Object[] params = logistics_ids;

		return BaseDao.executeUpdate(sql, params, conn);	
	}
	
	public int updateLogistics(Logistics logistics, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE logistics SET logistics_id = " + logistics.getLogistics_id() +" ");
		sBuilder.append(" where logistics_id = " + logistics.getLogistics_id() );

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);	}

	public Logistics getLogistics(Logistics logistics, Connection conn){
		Logistics _logistics=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT s.* From logistics s ");
		sBuilder.append(" WHERE s.logistics_id = " + logistics.getLogistics_id() );

		List<Object> list = BaseDao.executeQuery(Logistics.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _logistics = (Logistics)list.get(0);
		}
		return _logistics;
	}

	public List<Logistics>  listLogisticss(Logistics logistics, Connection conn){
		List<Logistics> logisticss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT s.* FROM logistics s ");
		sBuilder.append(" WHERE 1=1 ");
		if (logistics.getLogistics_id()!=0) {
			sBuilder.append(" and s.logistics_id = " + logistics.getLogistics_id() );
		}
		if (!StringUtil.isEmptyString(logistics.getOrders_no())) {
			sBuilder.append(" and s.orders_no = '" + logistics.getOrders_no() +"'");
		}
		
		sBuilder.append(" order by s.logistics_date asc,s.logistics_id asc) t");
		
		if (logistics.getStart() != -1) {
			sBuilder.append(" limit " + logistics.getStart() + "," + logistics.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Logistics.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			logisticss = new ArrayList<Logistics>();
			for (Object object : list) {
				logisticss.add((Logistics)object);
			}
		}
		return logisticss;
	}
	
	public int  listLogisticssCount(Logistics logistics, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM logistics s ");
		sBuilder.append(" WHERE 1=1");
		if (logistics.getLogistics_id()!=0) {
			sBuilder.append(" and s.logistics_id = " + logistics.getLogistics_id() );
		}
		if (!StringUtil.isEmptyString(logistics.getOrders_no())) {
			sBuilder.append(" and s.orders_no = '" + logistics.getOrders_no() +"'");
		}
		
		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	public int  getLogisticsId(User user, Connection conn){
		int logistics_id = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT max(logistics_id) FROM logistics WHERE user_id="+user.getUser_id());
		
		long count = (Integer)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		logistics_id = (int)count;
		return logistics_id;
	}

}
