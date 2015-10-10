package com.nkl.page.action;

import java.util.List;

import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Evaluate;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsType;
import com.nkl.page.domain.Info;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.OrdersDetail;
import com.nkl.page.domain.User;
import com.nkl.page.manager.IndexManager;

public class IndexAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	IndexManager indexManager = new IndexManager();

	//抓取页面参数
	Goods paramsGoods;
	GoodsType paramsGoodsType;
	Info paramsInfo;
	Orders paramsOrders;
	OrdersDetail paramsOrdersDetail;
	Evaluate paramsEvaluate;
	Logistics paramsLogistics;
	User paramsUser;
	String tip;
	
	/**
	 * @Title: index
	 * @Description: 展示商品列表
	 * @return String
	 */
	public String index(){
		try {
			//查询新品上市
			Goods goods = new Goods();
			goods.setStart(0);
			goods.setLimit(8);
			List<Goods> goodss = indexManager.listGoodssTop(goods);
			Param.setAttribute("goodss", goodss);
			
			//查询常见帮助
			Info info = new Info();
			info.setStart(0);
			info.setLimit(8);
			List<Info> infos = indexManager.listInfos(info,null);
			Param.setAttribute("infos", infos);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "index";
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 展示商品列表
	 * @return String
	 */
	public String listGoodss(){
		try {
			//查询商品信息集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			setPagination(paramsGoods);
			int[] sum={0};
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = indexManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goods";
	}
	
	/**
	 * @Title: listGoodssHot
	 * @Description: 展示促销商品列表
	 * @return String
	 */
	public String listGoodssHot(){
		try {
			//查询商品信息集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			setPagination(paramsGoods);
			int[] sum={0};
			//设置为促销
			paramsGoods.setGoods_flag(2);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = indexManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsHot";
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 查询商品详情
	 * @return String
	 */
	public String queryGoods(){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
			//查询评价集合
			if (paramsEvaluate==null) {
				paramsEvaluate = new Evaluate();
				paramsEvaluate.setGoods_id(goods.getGoods_id());
			}
			//分页信息设置
			setPagination(paramsEvaluate);
			int[] sum={0};
			List<Evaluate> evaluates = indexManager.listEvaluates(paramsEvaluate,sum); 
			Param.setAttribute("evaluates", evaluates);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsDetail";
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 展示帮助列表
	 * @return String
	 */
	public String listInfos(){
		try {
			//查询帮助信息集合
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			//分页信息设置
			setLimit(6);
			setPagination(paramsInfo);
			int[] sum={0};
			List<Info> infos = indexManager.listInfos(paramsInfo,sum); 
			Param.setAttribute("infos", infos);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "info";
	}
	
	/**
	 * @Title: queryInfo
	 * @Description: 查询帮助
	 * @return String
	 */
	public String queryInfo(){
		try {
			 //得到帮助
			Info info = indexManager.queryInfo(paramsInfo);
			Param.setAttribute("info", info);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "infoDetail";
	}
	
	/**
	 * @Title: listCard
	 * @Description: 查询购物车
	 * @return String
	 */
	public String listCard(){
		try {
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard();
			Param.setAttribute("ordersDetails", ordersDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: addCard
	 * @Description: 添加到购物车
	 * @return String
	 */
	public String addCard(){
		try {
			//添加到购物车
			indexManager.addCard(paramsOrdersDetail);
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard();
			Param.setAttribute("ordersDetails", ordersDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: modifyCard
	 * @Description: 修改购物车
	 * @return String
	 */
	public String modifyCard(){
		try {
			//修改购物车
			indexManager.modifyCard(paramsOrdersDetail);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("修改数量失败！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: delGoodsFromCard
	 * @Description: 从购物车删除
	 * @return String
	 */
	public String delGoodsFromCard(){
		try {
			//从购物车删除
			indexManager.delGoodsFromCard(paramsOrdersDetail.getGoods_id());
			
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard();
			Param.setAttribute("ordersDetails", ordersDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: clearCard
	 * @Description: 清空购物车
	 * @return String
	 */
	public String clearCard(){
		try {
			//清空购物车
			indexManager.clearCard();
			//查询购物车
			Param.setAttribute("ordersDetails", null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: addOrdersShow
	 * @Description: 新增订单页面
	 * @return String
	 */
	public String addOrdersShow(){
		try {
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard();
			Param.setAttribute("ordersDetails", ordersDetails);
			
			//查询订单总额
			double orders_money=0;
			for (int i = 0; i < ordersDetails.size(); i++) {
				OrdersDetail ordersDetail = ordersDetails.get(i);
				orders_money+=ordersDetail.getGoods_money();//累计总金额
			}
			Param.setAttribute("orders_money", orders_money);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersAdd";
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 新增订单
	 * @return String
	 */
	public String addOrders(){
		try {
			//新增订单
			indexManager.addOrders(paramsOrders);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("提交订单失败！");
			return "error2";
		}
		
		return "success";
	}
	
	 
	/**
	 * @Title: saveUserFront
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveUserFront(){
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User userFront = new User();
			userFront.setUser_id(paramsUser.getUser_id());
			userFront = indexManager.getUser(userFront);
			Param.setSession("userFront", userFront);
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userInfo";
		}
		tip = "修改成功";
		return "userInfo";
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveUserFrontPass(){
		try {
			 //保存修改个人密码
			indexManager.updateUser(paramsUser);
			//更新session
			User UserFront = (User)Param.getSession("UserFront");
			if (UserFront!=null) {
				UserFront.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("UserFront", UserFront);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userPwd";
		}
		tip = "修改成功";
		return "userPwd";
	}
	
	/**
	 * @Title: listMyOrderss
	 * @Description: 查询我的商品订单
	 * @return String
	 */
	public String listMyOrderss(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//获取用户,用户只能查询自己的订单
			User userFront = (User)Param.getSession("userFront");
			if (userFront.getUser_type()==1) {
				paramsOrders.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Orders> orderss = indexManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: listMyOrdersDetails
	 * @Description: 查询我的订单明细
	 * @return String
	 */
	public String listMyOrdersDetails(){
		try {
			if (paramsOrdersDetail==null) {
				paramsOrdersDetail = new OrdersDetail();
			}
			//设置分页信息
			paramsOrdersDetail.setStart(-1);
			//查询订单明细
			List<OrdersDetail> ordersDetails = indexManager.listOrdersDetails(paramsOrdersDetail,null); 
			Param.setAttribute("ordersDetails", ordersDetails);
			
			//订单信息
			Param.setAttribute("orders_no", paramsOrdersDetail.getOrders_no());
			if (ordersDetails!=null && ordersDetails.size()>0) {
				Param.setAttribute("orders_money", ordersDetails.get(0).getOrders_money());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersDetailShow";
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 展示物流信息列表
	 * @return String
	 */
	public String listLogisticss(){
		try {
			//查询物流信息集合
			if (paramsLogistics==null) {
				paramsLogistics = new Logistics();
			}
			//设置分页信息
			paramsLogistics.setStart(-1);
			List<Logistics> logisticss = indexManager.listLogisticss(paramsLogistics,null); 
			Param.setAttribute("logisticss", logisticss);
			
			//订单信息
			Param.setAttribute("orders_no", paramsLogistics.getOrders_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "logisticsShow";
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @return String
	 */
	public String finishOrders(){
		try {
			//确认收货
			indexManager.finishOrders(paramsOrders);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("确认收货失败！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: addEvaluateShow
	 * @Description: 评价商品界面
	 * @return String
	 */
	public String addEvaluateShow(){
		try {
			//查询订单
			Orders orders = indexManager.queryOrders(paramsOrders);
			Param.setAttribute("orders", orders);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "evaluateAdd";
	}

	/**
	 * @Title: addEvaluate
	 * @Description: 评价商品
	 * @return String
	 */
	public String addEvaluate(){
		try {
			//新增商品评价
			indexManager.addEvaluate(paramsEvaluate);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("评价商品失败！");
			return "error2";
		}
		
		return "success";
	}
	
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	public String myInfo(){
		return "userInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	public String myPwd(){
		return "userPwd";
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Goods getParamsGoods() {
		return paramsGoods;
	}

	public void setParamsGoods(Goods paramsGoods) {
		this.paramsGoods = paramsGoods;
	}

	public GoodsType getParamsGoodsType() {
		return paramsGoodsType;
	}

	public void setParamsGoodsType(GoodsType paramsGoodsType) {
		this.paramsGoodsType = paramsGoodsType;
	}

	public OrdersDetail getParamsOrdersDetail() {
		return paramsOrdersDetail;
	}

	public void setParamsOrdersDetail(OrdersDetail paramsOrdersDetail) {
		this.paramsOrdersDetail = paramsOrdersDetail;
	}

	public Orders getParamsOrders() {
		return paramsOrders;
	}

	public void setParamsOrders(Orders paramsOrders) {
		this.paramsOrders = paramsOrders;
	}

	public Info getParamsInfo() {
		return paramsInfo;
	}

	public void setParamsInfo(Info paramsInfo) {
		this.paramsInfo = paramsInfo;
	}

	public Evaluate getParamsEvaluate() {
		return paramsEvaluate;
	}

	public void setParamsEvaluate(Evaluate paramsEvaluate) {
		this.paramsEvaluate = paramsEvaluate;
	}

	public Logistics getParamsLogistics() {
		return paramsLogistics;
	}

	public void setParamsLogistics(Logistics paramsLogistics) {
		this.paramsLogistics = paramsLogistics;
	}

}
