package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.page.domain.GoodsType;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class GoodsTypeDao {

	public int addGoodsType(GoodsType goodsType, Connection conn){
		String sql = "INSERT INTO goods_type(goods_type_id,goods_type_name) values(null,?)";
		Object[] params = new Object[] {
			goodsType.getGoods_type_name()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delGoodsType(String goods_type_id, Connection conn){
		String sql = "DELETE FROM goods_type WHERE goods_type_id=?";

		Object[] params = new Object[] { new Integer(goods_type_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delGoodsTypes(String[] goods_type_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <goods_type_ids.length; i++) {
			sBuilder.append("?");
			if (i !=goods_type_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM goods_type WHERE goods_type_id IN(" +sBuilder.toString()+")";

		Object[] params = goods_type_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateGoodsType(GoodsType goodsType, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE goods_type SET goods_type_id = " + goodsType.getGoods_type_id() +" ");
		if (!StringUtil.isEmptyString(goodsType.getGoods_type_name())) {
			sBuilder.append(" ,goods_type_name = '" + goodsType.getGoods_type_name() +"' ");
		}
		sBuilder.append(" where goods_type_id = " + goodsType.getGoods_type_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public GoodsType getGoodsType(GoodsType goodsType, Connection conn){
		GoodsType _goodsType=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM goods_type WHERE 1=1");
		if (goodsType.getGoods_type_id()!=0) {
			sBuilder.append(" and goods_type_id = " + goodsType.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(goodsType.getGoods_type_name())) {
			sBuilder.append(" and goods_type_name = '" + goodsType.getGoods_type_name() +"' ");
		}

		List<Object> list = BaseDao.executeQuery(GoodsType.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _goodsType = (GoodsType)list.get(0);
		}
		return _goodsType;
	}

	public List<GoodsType>  listGoodsTypes(GoodsType goodsType, Connection conn){
		List<GoodsType> goodsTypes = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM goods_type WHERE 1=1");

		if (goodsType.getGoods_type_id()!=0) {
			sBuilder.append(" and goods_type_id = " + goodsType.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(goodsType.getGoods_type_name())) {
			sBuilder.append(" and goods_type_name like '%" + goodsType.getGoods_type_name() +"%' ");
		}
		sBuilder.append(" order by goods_type_id asc) t");

		if (goodsType.getStart() != -1) {
			sBuilder.append(" limit " + goodsType.getStart() + "," + goodsType.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(GoodsType.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			goodsTypes = new ArrayList<GoodsType>();
			for (Object object : list) {
				goodsTypes.add((GoodsType)object);
			}
		}
		return goodsTypes;
	}

	public int  listGoodsTypesCount(GoodsType goodsType, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM goods_type WHERE 1=1");

		if (goodsType.getGoods_type_id()!=0) {
			sBuilder.append(" and goods_type_id = " + goodsType.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(goodsType.getGoods_type_name())) {
			sBuilder.append(" and goods_type_name like '%" + goodsType.getGoods_type_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
