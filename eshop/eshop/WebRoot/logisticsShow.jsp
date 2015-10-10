<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单物流信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
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
	 <div id="product_menu">
	 	<table class="ptable_menu">
			<tr class="ptable_menu_title">
				<td>个人中心</td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_myInfo.action">修改个人信息</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_myPwd.action">修改登录密码</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_listMyOrderss.action">我的订单信息</a></td>
			</tr>
		 </table>
	 </div>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  订单物流信息</div>
			<div style="margin-top:5px">
				 <table class="ptable" style="margin-bottom:5px;">
				 	<tr>
					     <td colspan="5" align="left">
					     	订单编号：<s:property value="#attr.orders_no"/>&nbsp;&nbsp;
					     </td>
					</tr>
					<tr class="head_text">
					     <td width="" align="center">物流日期</td>
					     <td width="" align="center">物流信息</td>
					</tr>
					<s:if test="#attr.logisticss!=null && #attr.logisticss.size()>0">
					   <s:iterator value="#attr.logisticss" id="logistics" status="status">
					   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
					     <td width="" align="center"><s:property value="#logistics.logistics_date.substring(0,19)"/></td>
					     <td width="" align="center"><s:property value="#logistics.logistics_desc"/></td>
					   </tr> 
					   </s:iterator>
					</s:if>
				    <s:else>
				    <tr>
				      <td height="60" colspan="2" align="center"><b>&lt;不存在订单物流信息&gt;</b></td>
				    </tr>
				    </s:else>
				 </table>
			</div>
			<div class="pages">
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
    var orders_no = '<s:property value="#attr.orders_no"/>';
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  window.location.href="page_listLogisticss.action?pageNo="+pagenum+"&paramsLogistics.orders_no="+orders_no;
	}
	function ChangePage(pagenum)
	{
		window.location.href="page_listLogisticss.action?pageNo="+pagenum+"&paramsLogistics.orders_no="+orders_no;
	}
</script>
</body>
</html>