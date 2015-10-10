<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>提交订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/reg.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<div id="list">
		 <div class="nav">当前位置: 主页 > 提交订单 </div>
		 <div class="list_info">
		 	 <form name="info" id="info" style="width:100%;min-height:500px" action="LoginRegSystem.action" method="post">
		     <input type="hidden" name="paramsOrders.user_id" value="${userFront.user_id}"/>
		     <input type="hidden" name="paramsOrders.real_name" value="${userFront.real_name}"/>
			 <table class="regTable">
			 	<tr class="theme">
					<td width="" align="center">商品名称</td>
				    <td width="" align="center">商品单价</td>
				    <td width="" align="center">商品数量</td>
				    <td width="" align="center">商品总额</td>
				</tr>
			 	<s:if test="#attr.ordersDetails!=null && #attr.ordersDetails.size()>0">
				<s:iterator value="#attr.ordersDetails" id="ordersDetail" status="status">
				<tr>
					<td width="" align="center"><s:property value="#ordersDetail.goods_name"/></td>
					<td width="" align="center">￥<s:property value="#ordersDetail.goods_price"/></td>
					<td width="" align="center"><s:property value="#ordersDetail.goods_count"/></td>
					<td width="" align="center">￥<s:property value="#ordersDetail.goods_money"/></td>
			    </tr> 
			    </s:iterator>
			    <tr>
					<td colspan="4" align="right" style="padding-right:50px;font-weight:bold">
						订单总额：￥<s:property value="#attr.orders_money"/>
					</td>
			    </tr> 
			    <tr>
					<td align="right"><span style="color:red">*</span> 联系电话：</td>
					<td align="left" colspan="3">
						<input type="text" name="paramsOrders.user_phone" id="user_phone" value="${userFront.user_phone}" style="width:200px;" class="inputstyle"/>
					</td>
			    </tr> 
			    <tr>
					<td align="right"><span style="color:red">*</span> 收货地址：</td>
					<td align="left" colspan="3">
						<input type="text" name="paramsOrders.user_address" id="user_address" value="${userFront.user_address}" style="width:200px;" class="inputstyle"/>
					</td>
			    </tr> 
			    <tr>
			    	<td align="right"></td>
					<td align="left" colspan="3">
						<input type="button" id="addBtn" class="btnstyle" value="确认支付"/>
						
					</td>
				</tr>
				</s:if>
			    <s:else>
			    <tr>
			      <td height="60" colspan="4" align="center"><b>&lt;不存在订单信息&gt;</b></td>
			    </tr>
		    	</s:else>
		 	 </table>
		 </div>
	</div>
	<div id="Picture"></div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	var addBtn = $("#addBtn");
	//提交订单
	addBtn.bind("click",function(){
		if($("#user_phone").val()==''){
			alert("联系电话不能为空");
			return;
		}
		if($("#user_address").val()==''){
			alert("收货地址不能为空");
			return;
		}
		var aQuery = $("#info").serialize();  
		$.post('page_addOrders.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('支付成功！');
						 window.location.href="page_listMyOrderss.action";
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
	
	 
});
</script>
</body>
</html>