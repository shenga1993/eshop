package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Goods;

public class GoodsDao {

	public int addGoods(Goods goods, Connection conn){
		String sql = "INSERT INTO goods(goods_id,goods_type_id,goods_name,goods_pic,goods_price1,goods_price2,goods_count,goods_date,goods_desc,goods_flag) values(null,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			goods.getGoods_type_id(),
			goods.getGoods_name(),
			goods.getGoods_pic(),
			goods.getGoods_price1(),
			goods.getGoods_price2(),
			goods.getGoods_count(),
			goods.getGoods_date(),
			goods.getGoods_desc(),
			goods.getGoods_flag()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delGoods(String goods_id, Connection conn){
		String sql = "DELETE FROM goods WHERE goods_id=?";

		Object[] params = new Object[] { new Integer(goods_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delGoodss(String[] goods_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <goods_ids.length; i++) {
			sBuilder.append("?");
			if (i !=goods_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM goods WHERE goods_id IN(" +sBuilder.toString()+")";

		Object[] params = goods_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateGoods(Goods goods, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE goods SET goods_id = " + goods.getGoods_id() +" ");
		if (goods.getGoods_type_id()!=0) {
			sBuilder.append(" ,goods_type_id = " + goods.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_name())) {
			sBuilder.append(" ,goods_name = '" + goods.getGoods_name() +"' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_pic())) {
			sBuilder.append(" ,goods_pic = '" + goods.getGoods_pic() +"' ");
		}
		if (goods.getGoods_price1()!=0) {
			sBuilder.append(" ,goods_price1 = " + goods.getGoods_price1() +" ");
		}
		if (goods.getGoods_flag()!=0) {
			sBuilder.append(" ,goods_flag = " + goods.getGoods_flag() +" ");
		}
		if (goods.getGoods_price2()!=0) {
			sBuilder.append(" ,goods_price2 = " + goods.getGoods_price2() +" ");
		}
		if (goods.getGoods_count()!=0) {
			sBuilder.append(" ,goods_count = " + goods.getGoods_count() +" ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			sBuilder.append(" ,goods_desc = '" + goods.getGoods_desc() +"' ");
		}
		
		sBuilder.append(" where goods_id = " + goods.getGoods_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public int updateGoodsCount(Goods goods, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE goods SET goods_id = " + goods.getGoods_id() +" ");
		if (goods.getGoods_count()!=-1) {
			sBuilder.append(" ,goods_count = " + goods.getGoods_count() +" ");
		}
		sBuilder.append(" where goods_id = " + goods.getGoods_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Goods getGoods(Goods goods, Connection conn){
		Goods _goods=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT b.*,bt.goods_type_name FROM goods b left join goods_type bt on b.goods_type_id=bt.goods_type_id WHERE 1=1");
		if (goods.getGoods_id()!=0) {
			sBuilder.append(" and goods_id = " + goods.getGoods_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Goods.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _goods = (Goods)list.get(0);
		}
		return _goods;
	}

	public List<Goods>  listGoodss(Goods goods, Connection conn){
		List<Goods> goodss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT b.*,bt.goods_type_name FROM goods b left join goods_type bt on b.goods_type_id=bt.goods_type_id WHERE 1=1");

		if (goods.getGoods_id()!=0) {
			sBuilder.append(" and goods_id = " + goods.getGoods_id() +" ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_name())) {
			sBuilder.append(" and goods_name like '%" + goods.getGoods_name() +"%' ");
		}
		if (goods.getGoods_type_id()!=0) {
			sBuilder.append(" and b.goods_type_id = " + goods.getGoods_type_id() +" ");
		}
		if (goods.getGoods_flag()!=0) {
			sBuilder.append(" and goods_flag = " + goods.getGoods_flag() +" ");
		}
		
		sBuilder.append(" order by goods_id asc) t");

		if (goods.getStart() != -1) {
			sBuilder.append(" limit " + goods.getStart() + "," + goods.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Goods.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			goodss = new ArrayList<Goods>();
			for (Object object : list) {
				goodss.add((Goods)object);
			}
		}
		return goodss;
	}

	public int  listGoodssCount(Goods goods, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM goods b WHERE 1=1");

		if (goods.getGoods_id()!=0) {
			sBuilder.append(" and goods_id = " + goods.getGoods_id() +" ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_name())) {
			sBuilder.append(" and goods_name like '%" + goods.getGoods_name() +"%' ");
		}
		if (goods.getGoods_type_id()!=0) {
			sBuilder.append(" and goods_type_id = " + goods.getGoods_type_id() +" ");
		}
		if (goods.getGoods_flag()!=0) {
			sBuilder.append(" and goods_flag = " + goods.getGoods_flag() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	public List<Goods>  listGoodssTop(Goods goods, Connection conn){
		List<Goods> goodss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT b.*,bt.goods_type_name FROM goods b left join goods_type bt on b.goods_type_id=bt.goods_type_id WHERE 1=1");

		if (goods.getGoods_id()!=0) {
			sBuilder.append(" and goods_id = " + goods.getGoods_id() +" ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_name())) {
			sBuilder.append(" and goods_name like '%" + goods.getGoods_name() +"%' ");
		}
		if (goods.getGoods_type_id()!=0) {
			sBuilder.append(" and b.goods_type_id = " + goods.getGoods_type_id() +" ");
		}
		if (goods.getGoods_flag()!=0) {
			sBuilder.append(" and goods_flag = " + goods.getGoods_flag() +" ");
		}
		
		sBuilder.append(" order by goods_date desc,goods_id asc) t");

		if (goods.getStart() != -1) {
			sBuilder.append(" limit " + goods.getStart() + "," + goods.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Goods.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			goodss = new ArrayList<Goods>();
			for (Object object : list) {
				goodss.add((Goods)object);
			}
		}
		return goodss;
	}

}
