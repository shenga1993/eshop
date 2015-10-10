package com.nkl.admin.manager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.EvaluateDao;
import com.nkl.page.dao.GoodsDao;
import com.nkl.page.dao.GoodsTypeDao;
import com.nkl.page.dao.InfoDao;
import com.nkl.page.dao.LogisticsDao;
import com.nkl.page.dao.OrdersDao;
import com.nkl.page.dao.OrdersDetailDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsType;
import com.nkl.page.domain.Info;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.OrdersDetail;
import com.nkl.page.domain.User;

public class AdminManager {

	UserDao userDao = new UserDao();
	GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	GoodsDao goodsDao = new GoodsDao();
	OrdersDao ordersDao = new OrdersDao();
	OrdersDetailDao ordersDetailDao = new OrdersDetailDao();
	EvaluateDao evaluateDao = new EvaluateDao();
	InfoDao infoDao = new InfoDao();
	LogisticsDao logisticsDao = new LogisticsDao();
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}
	 
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		Connection conn = BaseDao.getConnection();
		user.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listGoodsTypes
	 * @Description: 商品类型查询
	 * @param goodsType
	 * @return List<GoodsType>
	 */
	public List<GoodsType> listGoodsTypes(GoodsType goodsType, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = goodsTypeDao.listGoodsTypesCount(goodsType, conn);
		}
		List<GoodsType> goodsTypes = goodsTypeDao.listGoodsTypes(goodsType, conn);

		BaseDao.closeDB(null, null, conn);
		return goodsTypes;
	}

	/**
	 * @Title: queryGoodsType
	 * @Description: 商品类型查询
	 * @param goodsType
	 * @return GoodsType
	 */
	public GoodsType queryGoodsType(GoodsType goodsType) {
		Connection conn = BaseDao.getConnection();
		GoodsType _goodsType = goodsTypeDao.getGoodsType(goodsType, conn);
		BaseDao.closeDB(null, null, conn);
		return _goodsType;
	}

	/**
	 * @Title: addGoodsType
	 * @Description: 添加商品类型
	 * @param goodsType
	 * @return void
	 */
	public void addGoodsType(GoodsType goodsType) {
		Connection conn = BaseDao.getConnection();
		goodsTypeDao.addGoodsType(goodsType, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateGoodsType
	 * @Description: 更新商品类型信息
	 * @param goodsType
	 * @return void
	 */
	public void updateGoodsType(GoodsType goodsType) {
		Connection conn = BaseDao.getConnection();
		goodsTypeDao.updateGoodsType(goodsType, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delGoodsType
	 * @Description: 删除商品类型信息
	 * @param goodsType
	 * @return void
	 */
	public void delGoodsTypes(GoodsType goodsType) {
		Connection conn = BaseDao.getConnection();
		goodsTypeDao.delGoodsTypes(goodsType.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 商品查询
	 * @param goods
	 * @return List<Goods>
	 */
	public List<Goods> listGoodss(Goods goods, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = goodsDao.listGoodssCount(goods, conn);
		}
		List<Goods> goodss = goodsDao.listGoodss(goods, conn);

		BaseDao.closeDB(null, null, conn);
		return goodss;
	}

	/**
	 * @Title: queryGoods
	 * @Description: 商品查询
	 * @param goods
	 * @return Goods
	 */
	public Goods queryGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		Goods _goods = goodsDao.getGoods(goods, conn);
		BaseDao.closeDB(null, null, conn);
		return _goods;
	}

	/**
	 * @Title: addGoods
	 * @Description: 添加商品
	 * @param goods
	 * @return void
	 */
	public void addGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			goods.setGoods_desc(Transcode.htmlEncode(goods.getGoods_desc()));
		}
		goods.setGoods_date(DateUtil.dateToDateString(new Date()));
		goodsDao.addGoods(goods, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateGoods
	 * @Description: 更新商品信息
	 * @param goods
	 * @return void
	 */
	public void updateGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			goods.setGoods_desc(Transcode.htmlEncode(goods.getGoods_desc()));
		}
		goodsDao.updateGoods(goods, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delGoods
	 * @Description: 删除商品信息
	 * @param goods
	 * @return void
	 */
	public void delGoodss(Goods goods) {
		Connection conn = BaseDao.getConnection();
		goodsDao.delGoodss(goods.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 商品订单查询
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders>  listOrderss(Orders orders,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = ordersDao.listOrderssCount(orders, conn);
		}
		List<Orders> orderss = ordersDao.listOrderss(orders,conn);
		
		BaseDao.closeDB(null, null, conn);
		return orderss;
	}
	
	/**
	 * @Title: queryOrders
	 * @Description: 商品订单查询
	 * @param orders
	 * @return Orders
	 */
	public Orders  queryOrders(Orders orders){
		Connection conn = BaseDao.getConnection();
		Orders _orders = ordersDao.getOrders(orders, conn);
		BaseDao.closeDB(null, null, conn);
		return _orders;
	}
	 
	
	/**
	 * @Title: delOrderss
	 * @Description: 删除商品订单信息
	 * @param orders
	 * @return void
	 */
	public void  delOrderss(Orders orders){
		Connection conn = BaseDao.getConnection();
		ordersDao.delOrderss(orders.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @param orders
	 * @return void
	 */
	public void sendOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		//确认订单信息
		orders.setOrders_flag(2);//2-已发货 
		ordersDao.updateOrders(orders,conn);
		
		//更新配送员状态
		User user = new User();
		user.setUser_id(orders.getSend_id());
		user.setUser_flag(1);//1：配送中
		userDao.updateUser(user,conn);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listOrdersDetails
	 * @Description: 商品订单明细查询
	 * @param ordersDetail
	 * @return List<OrdersDetail>
	 */
	public List<OrdersDetail> listOrdersDetails(OrdersDetail ordersDetail, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = ordersDetailDao.listOrdersDetailsCount(ordersDetail, conn);
		}
		List<OrdersDetail> ordersDetails = ordersDetailDao.listOrdersDetails(ordersDetail, conn);

		BaseDao.closeDB(null, null, conn);
		return ordersDetails;
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 商品订单物流信息查询
	 * @param logistics
	 * @return List<Logistics>
	 */
	public List<Logistics>  listLogisticss(Logistics logistics,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = logisticsDao.listLogisticssCount(logistics, conn);
		}
		List<Logistics> logisticss = logisticsDao.listLogisticss(logistics,conn);
		
		BaseDao.closeDB(null, null, conn);
		return logisticss;
	}
	
	/**
	 * @Title: addLogistics
	 * @Description: 新增物流信息
	 * @param logistics
	 * @return void
	 */
	public void addLogistics(Logistics logistics) {
		Connection conn = BaseDao.getConnection();
		logisticsDao.addLogistics(logistics, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 常见帮助列表查询
	 * @param info
	 * @return List<Info>
	 */
	public List<Info> listInfos(Info info, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = infoDao.listInfosCount(info, conn);
		}
		List<Info> infos = infoDao.listInfos(info, conn);

		BaseDao.closeDB(null, null, conn);
		return infos;
	}

	/**
	 * @Title: queryInfo
	 * @Description: 常见帮助查询
	 * @param info
	 * @return Info
	 */
	public Info queryInfo(Info info) {
		Connection conn = BaseDao.getConnection();
		Info _info = infoDao.getInfo(info, conn);
		BaseDao.closeDB(null, null, conn);
		return _info;
	}

	/**
	 * @Title: addInfo
	 * @Description: 新增帮助查询信息
	 * @param info
	 * @return void
	 */
	public void addInfo(Info info) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		info.setInfo_date(DateUtil.dateToDateString(new Date()));
		infoDao.addInfo(info, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateInfo
	 * @Description: 更新帮助查询信息
	 * @param info
	 * @return void
	 */
	public void updateInfo(Info info) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		infoDao.updateInfo(info, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delInfos
	 * @Description: 删除常见帮助信息
	 * @param info
	 * @return void
	 */
	public void  delInfos(Info info){
		Connection conn = BaseDao.getConnection();
		infoDao.delInfos(info.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}

}
