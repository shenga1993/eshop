package com.nkl.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Evaluate;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsType;
import com.nkl.page.domain.Info;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.OrdersDetail;
import com.nkl.page.domain.User;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();

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
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			Param.setSession("admin", admin);
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		setSuccessTip("编辑成功", "modifyInfo.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		setSuccessTip("修改成功", "modifyPwd.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询注册用户
			paramsUser.setUser_type(1);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			 //添加用户
			paramsUser.setUser_type(1);
			adminManager.addUser(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("添加用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("删除用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listSenders
	 * @Description: 查询配送人员
	 * @return String
	 */
	public String listSenders(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			paramsUser.setUser_type(2);
			
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询配送人员列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询配送人员异常", "main.jsp");
			return "infoTip";
		}
		
		return "senderShow";
	}
	
	/**
	 * @Title: addSenderShow
	 * @Description: 显示添加配送人员页面
	 * @return String
	 */
	public String addSenderShow(){
		return "senderEdit";
	}
	
	/**
	 * @Title: addSender
	 * @Description: 添加配送人员
	 * @return String
	 */
	public String addSender(){
		try {
			//检查登录名是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.getUser(user);
			if (user!=null) {
				tip="失败，该用户名已经存在！";
				Param.setAttribute("user", paramsUser);
				return "senderEdit";
			}
			 //添加配送人员
			paramsUser.setUser_type(2);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date()));
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listSenders.action");
		} catch (Exception e) {
			setErrorTip("添加配送人员异常", "Admin_listSenders.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editSender
	 * @Description: 编辑配送人员
	 * @return String
	 */
	public String editSender(){
		try {
			 //得到配送人员
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询配送人员异常", "Admin_listSenders.action");
			return "infoTip";
		}
		
		return "senderEdit";
	}
	
	/**
	 * @Title: saveSender
	 * @Description: 保存编辑配送人员
	 * @return String
	 */
	public String saveSender(){
		try {
			 //保存编辑配送人员
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listSenders.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "senderEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delSenders
	 * @Description: 删除配送人员
	 * @return String
	 */
	public String delSenders(){
		try {
			 //删除配送人员
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除配送人员成功", "Admin_listSenders.action");
		} catch (Exception e) {
			setErrorTip("删除配送人员异常", "Admin_listSenders.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listGoodsTypes
	 * @Description: 查询商品类型
	 * @return String
	 */
	public String listGoodsTypes(){
		try {
			if (paramsGoodsType==null) {
				paramsGoodsType = new GoodsType();
			}
			
			//设置分页信息
			setPagination(paramsGoodsType);
			//总的条数
			int[] sum={0};
			//查询商品类型列表
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(paramsGoodsType,sum); 
			
			Param.setAttribute("goodsTypes", goodsTypes);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询商品类型异常", "main.jsp");
			return "infoTip";
		}
		
		return "goodsTypeShow";
	}
	
	/**
	 * @Title: addGoodsTypeShow
	 * @Description: 显示添加商品类型页面
	 * @return String
	 */
	public String addGoodsTypeShow(){
		return "goodsTypeEdit";
	}
	
	/**
	 * @Title: addGoodsType
	 * @Description: 添加商品类型
	 * @return String
	 */
	public String addGoodsType(){
		try {
			//检查商品类型是否存在
			GoodsType goodsType = new GoodsType();
			goodsType.setGoods_type_name(paramsGoodsType.getGoods_type_name());
			goodsType = adminManager.queryGoodsType(goodsType);
			if (goodsType!=null) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("goodsType", paramsGoodsType);
				return "goodsTypeEdit";
			}
			
			 //添加商品类型
			adminManager.addGoodsType(paramsGoodsType);
			
			setSuccessTip("添加成功", "Admin_listGoodsTypes.action");
		} catch (Exception e) {
			setErrorTip("添加商品类型异常", "Admin_listGoodsTypes.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editGoodsType
	 * @Description: 编辑商品类型
	 * @return String
	 */
	public String editGoodsType(){
		try {
			 //得到商品类型
			GoodsType goodsType = adminManager.queryGoodsType(paramsGoodsType);
			Param.setAttribute("goodsType", goodsType);
			
		} catch (Exception e) {
			setErrorTip("查询商品类型异常", "Admin_listGoodsTypes.action");
			return "infoTip";
		}
		
		return "goodsTypeEdit";
	}
	
	/**
	 * @Title: saveGoodsType
	 * @Description: 保存编辑商品类型
	 * @return String
	 */
	public String saveGoodsType(){
		try {
			//检查商品类型是否存在
			GoodsType goodsType = new GoodsType();
			goodsType.setGoods_type_name(paramsGoodsType.getGoods_type_name());
			goodsType = adminManager.queryGoodsType(goodsType);
			if (goodsType!=null&&goodsType.getGoods_type_id()!=paramsGoodsType.getGoods_type_id()) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("goodsType", paramsGoodsType);
				return "goodsTypeEdit";
			}
			
			 //保存编辑商品类型
			adminManager.updateGoodsType(paramsGoodsType);
			
			setSuccessTip("编辑成功", "Admin_listGoodsTypes.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("goodsType", paramsGoodsType);
			return "goodsTypeEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delGoodsTypes
	 * @Description: 删除商品类型
	 * @return String
	 */
	public String delGoodsTypes(){
		try {
			 //删除商品类型
			adminManager.delGoodsTypes(paramsGoodsType);
			
			setSuccessTip("删除商品类型成功", "Admin_listGoodsTypes.action");
		} catch (Exception e) {
			setErrorTip("删除商品类型异常", "Admin_listGoodsTypes.action");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询商品
	 * @return String
	 */
	public String listGoodss(){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//设置分页信息
			setPagination(paramsGoods);
			int[] sum={0};
			List<Goods> goodss = adminManager.listGoodss(paramsGoods,sum); 
			
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			setErrorTip("查询商品异常", "main.jsp");
			return "infoTip";
		}
		
		return "goodsShow";
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 查看商品
	 * @return String
	 */
	public String queryGoods(){
		try {
			 //得到商品
			Goods goods = adminManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
		} catch (Exception e) {
			setErrorTip("查询商品异常", "Admin_listGoodss.action");
			return "infoTip";
		}
		
		return "goodsDetail";
	}
	
	/**
	 * @Title: addGoodsShow
	 * @Description: 显示添加商品页面
	 * @return String
	 */
	public String addGoodsShow(){
		//查询商品类型
		GoodsType goodsType = new GoodsType();
		goodsType.setStart(-1);
		List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
		Param.setAttribute("goodsTypes", goodsTypes);
		
		return "goodsEdit";
	}
	
	/**
	 * @Title: addGoods
	 * @Description: 添加商品
	 * @return String
	 */
	public String addGoods(){
		try {
			 //添加商品
			adminManager.addGoods(paramsGoods);
			
		} catch (Exception e) {
			setErrorTip("添加商品异常", "Admin_listGoodss.action");
		}
		setSuccessTip("添加商品成功", "Admin_listGoodss.action");
		return "infoTip";
	}
	
	/**
	 * @Title: editGoods
	 * @Description: 编辑商品
	 * @return String
	 */
	public String editGoods(){
		try {
			 //得到商品
			Goods goods = adminManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			setErrorTip("查询商品异常", "Admin_listGoodss.action");
			return "infoTip";
		}
		
		return "goodsEdit";
	}
	
	/**
	 * @Title: saveGoods
	 * @Description: 保存编辑商品
	 * @return String
	 */
	public String saveGoods(){
		try {
			 //保存编辑商品
			adminManager.updateGoods(paramsGoods);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("goods", paramsGoods);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
			return "goodsEdit";
		}
		setSuccessTip("编辑商品成功", "Admin_listGoodss.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delGoodss
	 * @Description: 删除商品
	 * @return String
	 */
	public String delGoodss(){
		try {
			 //删除商品
			adminManager.delGoodss(paramsGoods);
			
		} catch (Exception e) {
			setErrorTip("删除商品异常", "Admin_listGoodss.action");
		}
		setSuccessTip("删除商品成功", "Admin_listGoodss.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 查询商品订单
	 * @return String
	 */
	public String listOrderss(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//用户身份认证
			User admin = (User)Param.getSession("admin");
			if (admin.getUser_type()==2) {
				paramsOrders.setSend_id(admin.getUser_id());
			}
			//查询商品订单列表
			List<Orders> orderss = adminManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询商品订单异常", "main.jsp");
			return "infoTip";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: sendOrdersShow
	 * @Description: 订单发货界面
	 * @return String
	 */
	public String sendOrdersShow(){
		try {
			 //得到订单
			Orders orders = adminManager.queryOrders(paramsOrders);
			Param.setAttribute("orders", orders);
			
			//查询配送人员
			User user = new User();
			user.setUser_type(2);
			user.setUser_flag(2);
			user.setStart(-1);
			List<User> senders = adminManager.listUsers(user, null);
			if (senders==null) {
				senders = new ArrayList<User>();
			}
			Param.setAttribute("senders", senders);
			
		} catch (Exception e) {
			setErrorTip("查询订单异常", "Admin_listOrderss.action");
			return "infoTip";
		}
		
		return "ordersSend";
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @return String
	 */
	public String sendOrders(){
		try {
			 //订单发货
			adminManager.sendOrders(paramsOrders);
			
			setSuccessTip("订单发货成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("订单发货异常", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delOrderss
	 * @Description: 删除商品订单
	 * @return String
	 */
	public String delOrderss(){
		try {
			 //删除商品订单
			adminManager.delOrderss(paramsOrders);
			
			setSuccessTip("删除商品订单成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("删除商品订单异常", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listOrdersDetails
	 * @Description: 查询商品订单明细
	 * @return String
	 */
	public String listOrdersDetails(){
		try {
			if (paramsOrdersDetail==null) {
				paramsOrdersDetail = new OrdersDetail();
			}
			//设置分页信息
			setPagination(paramsOrdersDetail);
			//总的条数
			int[] sum={0};
			//查询商品订单明细
			List<OrdersDetail> ordersDetails = adminManager.listOrdersDetails(paramsOrdersDetail,sum); 
			
			Param.setAttribute("ordersDetails", ordersDetails);
			setTotalCount(sum[0]);
			
			Param.setAttribute("orders_no", paramsOrdersDetail.getOrders_no());
			if (ordersDetails!=null && ordersDetails.size()>0) {
				Param.setAttribute("orders_money", ordersDetails.get(0).getOrders_money());
			}
			
			
		} catch (Exception e) {
			setErrorTip("查询商品订单明细异常", "main.jsp");
			return "infoTip";
		}
		
		return "ordersDetailShow";
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 查询商品订单物流信息
	 * @return String
	 */
	public String listLogisticss(){
		try {
			if (paramsLogistics==null) {
				paramsLogistics = new Logistics();
			}
			//设置分页信息不分页
			paramsLogistics.setStart(-1);
			//查询商品订单物流信息
			List<Logistics> logisticss = adminManager.listLogisticss(paramsLogistics,null); 
			Param.setAttribute("logisticss", logisticss);
			
			Param.setAttribute("orders_no", paramsLogistics.getOrders_no());
			
		} catch (Exception e) {
			setErrorTip("查询商品订单物流信息异常", "main.jsp");
			return "infoTip";
		}
		
		return "logisticsShow";
	}
	
	/**
	 * @Title: addLogisticsShow
	 * @Description: 显示添加物流信息页面
	 * @return String
	 */
	public String addLogisticsShow(){
		Param.setAttribute("orders_no", paramsLogistics.getOrders_no());
		return "logisticsEdit";
	}
	
	/**
	 * @Title: addLogistics
	 * @Description: 添加物流信息
	 * @return String
	 */
	public String addLogistics(){
		try {
			 //添加物流信息
			adminManager.addLogistics(paramsLogistics);
			
		} catch (Exception e) {
			setErrorTip("添加物流信息异常", "Admin_listLogisticss.action?paramsLogistics.orders_no="+paramsLogistics.getOrders_no());
		}
		setSuccessTip("添加物流信息成功", "Admin_listLogisticss.action?paramsLogistics.orders_no="+paramsLogistics.getOrders_no());
		return "infoTip";
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 查询常见帮助
	 * @return String
	 */
	public String listInfos(){
		try {
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			//设置分页信息
			setPagination(paramsInfo);
			int[] sum={0};
			List<Info> infos = adminManager.listInfos(paramsInfo,sum); 
			
			Param.setAttribute("infos", infos);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询常见帮助异常", "main.jsp");
			return "infoTip";
		}
		
		return "infoShow";
	}
	
	/**
	 * @Title: addInfoShow
	 * @Description: 显示添加常见帮助页面
	 * @return String
	 */
	public String addInfoShow(){
		return "infoEdit";
	}
	
	/**
	 * @Title: addInfo
	 * @Description: 添加常见帮助
	 * @return String
	 */
	public String addInfo(){
		try {
			 //添加常见帮助
			adminManager.addInfo(paramsInfo);
			
		} catch (Exception e) {
			setErrorTip("添加常见帮助异常", "Admin_listInfos.action");
		}
		setSuccessTip("添加常见帮助成功", "Admin_listInfos.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editInfo
	 * @Description: 编辑常见帮助
	 * @return String
	 */
	public String editInfo(){
		try {
			 //得到常见帮助
			Info info = adminManager.queryInfo(paramsInfo);
			Param.setAttribute("info", info);
			
		} catch (Exception e) {
			setErrorTip("查询常见帮助异常", "Admin_listInfos.action");
			return "infoTip";
		}
		
		return "infoEdit";
	}
	
	/**
	 * @Title: saveInfo
	 * @Description: 保存编辑常见帮助
	 * @return String
	 */
	public String saveInfo(){
		try {
			 //保存编辑常见帮助
			adminManager.updateInfo(paramsInfo);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("info", paramsInfo);
			return "infoEdit";
		}
		setSuccessTip("编辑常见帮助成功", "Admin_listInfos.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delInfos
	 * @Description: 删除常见帮助
	 * @return String
	 */
	public String delInfos(){
		try {
			 //删除常见帮助
			adminManager.delInfos(paramsInfo);
			
		} catch (Exception e) {
			setErrorTip("删除常见帮助异常", "Admin_listInfos.action");
		}
		setSuccessTip("删除常见帮助成功", "Admin_listInfos.action");
		return "infoTip";
	}
	
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
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

	public Info getParamsInfo() {
		return paramsInfo;
	}

	public void setParamsInfo(Info paramsInfo) {
		this.paramsInfo = paramsInfo;
	}

	public Orders getParamsOrders() {
		return paramsOrders;
	}

	public void setParamsOrders(Orders paramsOrders) {
		this.paramsOrders = paramsOrders;
	}

	public OrdersDetail getParamsOrdersDetail() {
		return paramsOrdersDetail;
	}

	public void setParamsOrdersDetail(OrdersDetail paramsOrdersDetail) {
		this.paramsOrdersDetail = paramsOrdersDetail;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
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
