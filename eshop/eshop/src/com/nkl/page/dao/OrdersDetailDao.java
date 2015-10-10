package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.page.domain.OrdersDetail;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class OrdersDetailDao {

	public int addOrdersDetail(OrdersDetail ordersDetail, Connection conn){
		String sql = "INSERT INTO orders_detail(detail_id,orders_no,goods_id,goods_name,goods_price,goods_count,goods_money) values(null,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			ordersDetail.getOrders_no(),
			ordersDetail.getGoods_id(),
			ordersDetail.getGoods_name(),
			ordersDetail.getGoods_price(),
			ordersDetail.getGoods_count(),
			ordersDetail.getGoods_money()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delOrdersDetail(String detail_id, Connection conn){
		String sql = "DELETE FROM orders_detail WHERE detail_id=?";

		Object[] params = new Object[] { new Integer(detail_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delOrdersDetails(String[] detail_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <detail_ids.length; i++) {
			sBuilder.append("?");
			if (i !=detail_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM orders_detail WHERE detail_id IN(" +sBuilder.toString()+")";

		Object[] params = detail_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateOrdersDetail(OrdersDetail ordersDetail, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE orders_detail SET detail_id = " + ordersDetail.getDetail_id() +" ");
		if (ordersDetail.getGoods_price()!=0) {
			sBuilder.append(" ,goods_price = " + ordersDetail.getGoods_price() +" ");
		}
		if (ordersDetail.getGoods_count()!=0) {
			sBuilder.append(" ,goods_count = " + ordersDetail.getGoods_count() +" ");
		}
		if (ordersDetail.getGoods_money()!=0) {
			sBuilder.append(" ,goods_money = " + ordersDetail.getGoods_money() +" ");
		}
		
		sBuilder.append(" where detail_id = " + ordersDetail.getDetail_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public OrdersDetail getOrdersDetail(OrdersDetail ordersDetail, Connection conn){
		OrdersDetail _ordersDetail=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT od.*,o.real_name,o.orders_money FROM orders_detail od join orders o on od.orders_no=o.orders_no  WHERE 1=1");
		if (ordersDetail.getDetail_id()!=0) {
			sBuilder.append(" and detail_id = " + ordersDetail.getDetail_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(OrdersDetail.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _ordersDetail = (OrdersDetail)list.get(0);
		}
		return _ordersDetail;
	}

	public List<OrdersDetail>  listOrdersDetails(OrdersDetail ordersDetail, Connection conn){
		List<OrdersDetail> ordersDetails = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT od.*,o.real_name,o.orders_money FROM orders_detail od join orders o on od.orders_no=o.orders_no  WHERE 1=1");

		if (ordersDetail.getDetail_id()!=0) {
			sBuilder.append(" and detail_id = " + ordersDetail.getDetail_id() +" ");
		}
		if (!StringUtil.isEmptyString(ordersDetail.getOrders_no())) {
			sBuilder.append(" and od.orders_no like '%" + ordersDetail.getOrders_no() +"%' ");
		}
		sBuilder.append(" order by detail_id asc) t");

		if (ordersDetail.getStart() != -1) {
			sBuilder.append(" limit " + ordersDetail.getStart() + "," + ordersDetail.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(OrdersDetail.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			ordersDetails = new ArrayList<OrdersDetail>();
			for (Object object : list) {
				ordersDetails.add((OrdersDetail)object);
			}
		}
		return ordersDetails;
	}

	public int  listOrdersDetailsCount(OrdersDetail ordersDetail, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM orders_detail od join orders o on od.orders_no=o.orders_no  WHERE 1=1");

		if (ordersDetail.getDetail_id()!=0) {
			sBuilder.append(" and detail_id = " + ordersDetail.getDetail_id() +" ");
		}
		if (!StringUtil.isEmptyString(ordersDetail.getOrders_no())) {
			sBuilder.append(" and od.orders_no like '%" + ordersDetail.getOrders_no() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
