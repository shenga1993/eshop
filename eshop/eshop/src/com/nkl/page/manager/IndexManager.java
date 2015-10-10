package com.nkl.page.manager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.Param;
import com.nkl.common.util.StringUtil;
import com.nkl.page.dao.EvaluateDao;
import com.nkl.page.dao.GoodsDao;
import com.nkl.page.dao.GoodsTypeDao;
import com.nkl.page.dao.InfoDao;
import com.nkl.page.dao.LogisticsDao;
import com.nkl.page.dao.OrdersDao;
import com.nkl.page.dao.OrdersDetailDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Evaluate;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsType;
import com.nkl.page.domain.Info;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.OrdersDetail;
import com.nkl.page.domain.User;

public class IndexManager {

	UserDao userDao = new UserDao();
	GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	GoodsDao goodsDao = new GoodsDao();
	OrdersDao ordersDao = new OrdersDao();
	OrdersDetailDao ordersDetailDao = new OrdersDetailDao();
	EvaluateDao evaluateDao = new EvaluateDao();
	InfoDao infoDao = new InfoDao();
	LogisticsDao logisticsDao = new LogisticsDao();
	
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
	 * @Title: listGoodss
	 * @Description: 查询商品信息
	 * @param goods
	 * @return List<Goods>
	 */
	public List<Goods>  listGoodss(Goods goods,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = goodsDao.listGoodssCount(goods, conn);
		}
		List<Goods> goodss = goodsDao.listGoodss(goods,conn);
		BaseDao.closeDB(null, null, conn);
		return goodss;
	}
	
	public List<Goods>  listGoodssTop(Goods goods){
		Connection conn = BaseDao.getConnection();
		List<Goods> goodss = goodsDao.listGoodssTop(goods,conn);
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
	 * @Title: listEvaluates
	 * @Description: 查询商品评价信息
	 * @param evaluate
	 * @return List<Evaluate>
	 */
	public List<Evaluate>  listEvaluates(Evaluate evaluate,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate, conn);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluates(evaluate,conn);
		BaseDao.closeDB(null, null, conn);
		return evaluates;
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 查询商品物流信息
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
	 * @Title: listInfos
	 * @Description: 查询帮助信息集合
	 * @param infos
	 * @return List<Infos>
	 */
	public List<Info>  listInfos(Info info,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = infoDao.listInfosCount(info, conn);
		}
		List<Info> infos = infoDao.listInfos(info,conn);
		BaseDao.closeDB(null, null, conn);
		return infos;
	}
	
	/**
	 * @Title: queryInfo
	 * @Description: 查询帮助信息
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
	 * @Title: addCard
	 * @Description: 添加购物车
	 * @param ordersDetail
	 */
	@SuppressWarnings("unchecked")
	public void addCard(OrdersDetail ordersDetail) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		if (card==null) {
			card = new ArrayList<OrdersDetail>();
		}
		OrdersDetail oldDetail = getGoodsFromCard(ordersDetail.getGoods_id());
		if (oldDetail==null) {//新增商品
			//计算总额
			double goods_money = ordersDetail.getGoods_price()*ordersDetail.getGoods_count();
			ordersDetail.setGoods_money(goods_money);
			card.add(ordersDetail);
		}else {//修改购物车商品
			card.remove(oldDetail);
			oldDetail.setGoods_count(oldDetail.getGoods_count()+ordersDetail.getGoods_count());
			double goods_money = oldDetail.getGoods_price()*oldDetail.getGoods_count();
			oldDetail.setGoods_money(goods_money);
			card.add(oldDetail);
		}
		Param.setSession("card", card);
		
	}
	
	/**
	 * @Title: modifyCard
	 * @Description: 修改购物车商品
	 * @param ordersDetail
	 */
	@SuppressWarnings("unchecked")
	public void modifyCard(OrdersDetail ordersDetail) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		OrdersDetail oldDetail = getGoodsFromCard(ordersDetail.getGoods_id());
		//修改购物车商品
		card.remove(oldDetail);
		oldDetail.setGoods_count(ordersDetail.getGoods_count());
		double goods_money = oldDetail.getGoods_price()*oldDetail.getGoods_count();
		oldDetail.setGoods_money(goods_money);
		card.add(oldDetail);
		Param.setSession("card", card);
		
	}
	
	/**
	 * @Title: delGoodsFromCard
	 * @Description: 从购物车删除商品
	 * @param goods_id
	 */
	@SuppressWarnings("unchecked")
	public void delGoodsFromCard(int goods_id) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		if (card!=null) {
			for (OrdersDetail ordersDetail : card) {
				if (ordersDetail.getGoods_id()==goods_id) {
					card.remove(ordersDetail);
					break;
				}
			}
		}
		Param.setSession("card", card);
		
	}
	
	/**
	 * @Title: clearCard
	 * @Description: 清空购物车
	 */
	public void clearCard() {
		//清空购物车
		Param.removeSession("card");
		
	}
	
	@SuppressWarnings("unchecked")
	private OrdersDetail getGoodsFromCard(int goods_id) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		if (card!=null) {
			for (OrdersDetail ordersDetail : card) {
				if (ordersDetail.getGoods_id()==goods_id) {
					return ordersDetail;
				}
			}
		}else {
			return null;
		}
		return null;
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 添加商品订单
	 * @param orders
	 * @return Orders
	 */
	@SuppressWarnings("unchecked")
	public void addOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		//生成订单号
		String orders_no = DateUtil.dateToDateString(new Date(), "yyyyMMddHHmmss")+orders.getUser_id();
		orders.setOrders_no(orders_no);
		//订单日期
		orders.setOrders_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd"));
		//1：待发货
		orders.setOrders_flag(1);
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		double orders_money=0;
		for (int i = 0; i < card.size(); i++) {
			OrdersDetail ordersDetail = card.get(i);
			orders_money+=ordersDetail.getGoods_money();//累计总金额
			ordersDetail.setOrders_no(orders_no);//设置订单号
			//保存订单明细
			ordersDetailDao.addOrdersDetail(ordersDetail, conn);
			
			//更新库存数量
			Goods goods = new Goods();
			goods.setGoods_id(ordersDetail.getGoods_id());
			goods = goodsDao.getGoods(goods,conn);
			goods.setGoods_count(goods.getGoods_count() - ordersDetail.getGoods_count());
			goodsDao.updateGoodsCount(goods,conn);
		}
		//设置总额
		orders.setOrders_money(orders_money);
		//保存订单
		ordersDao.addOrders(orders, conn);
		
		//清空购物车
		Param.removeSession("card");
		
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: listCard
	 * @Description: 查询购物车
	 * @return List<OrdersDetail>
	 */
	@SuppressWarnings("unchecked")
	public List<OrdersDetail> listCard() {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		if (card==null) {
			card = new ArrayList<OrdersDetail>();
		}
		return card;
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
	 * @Title: listOrdersDetails
	 * @Description: 订单明细查询
	 * @param ordersDetail
	 * @return List<Borrow>
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
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @param Orders
	 * @return void
	 */
	public void finishOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		//确认收货
		orders.setOrders_flag(3);
		ordersDao.updateOrders(orders, conn);
		
		//更新配送员状态
		orders = ordersDao.getOrders(orders, conn);
		User user = new User();
		user.setUser_id(orders.getSend_id());
		user.setUser_flag(2);//1：无任务
		userDao.updateUser(user,conn);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: addEvaluate
	 * @Description: 添加商品评价
	 * @param evaluate
	 * @return void
	 */
	public void addEvaluate(Evaluate evaluate) {
		Connection conn = BaseDao.getConnection();
		//添加商品评价
		evaluate.setEvaluate_date(DateUtil.dateToDateString(new Date()));
		evaluateDao.addEvaluateBatch(evaluate, conn);
		
		//更新订单为已评价
		Orders orders = new Orders();
		orders.setOrders_no(evaluate.getOrders_no());
		orders.setOrders_flag(4);
		ordersDao.updateOrders(orders, conn);
		
		BaseDao.closeDB(null, null, conn);
	}
	
  
}
