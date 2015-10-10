<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/product.css">
<link rel="stylesheet" type="text/css" href="css/message.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript">
 $(document).ready(function(){
	 
	 //点击预定
	 var user_id = "${userFront.user_id}";
	 var num = /^\d+$/;
	 $("#addCard").bind('click',function(){
		 if(user_id==''){
			 alert('请先登录');
			 return;
		 }
		 if(!num.exec($("#goods_count").val())){
			 alert('购买数量必须为数字');
			 return;
		 }
		 if(Number($("#goods_count").val()) > Number($("#goods_count_real").val())){
			 alert('购买数量大于库存数量');
			 return;
		 }
		 $("#info").submit();
	 });
}); 
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
<div id="middleBg">
	<!--  产品检索介绍 -->
	 <div id="product_info">
			<div class="productShow">
					<div class="title"><s:property value="#attr.goods.goods_name"/></div>
					<div class="typehr"></div>
					<div class="pictext">
							<div class="pic"><img src="images/goodss/<s:property value='#attr.goods.goods_pic'/>" width="250px" height="250px"/></div>
							<div class="text">
									<form name="info" id="info" action="page_addCard.action" method="post">
									<input type="hidden" name="paramsOrdersDetail.goods_id" id="goods_id" value="<s:property value='#attr.goods.goods_id'/>"/>
									<input type="hidden" name="paramsOrdersDetail.goods_name" id="goods_name" value="<s:property value='#attr.goods.goods_name'/>"/>
									<input type="hidden" name="paramsOrdersDetail.goods_price" id="goods_price" value="<s:property value='#attr.goods.goods_price2'/>"/>
									<input type="hidden" id="goods_count_real" value="<s:property value='#attr.goods.goods_price2'/>"/>
									<div class="textTop" style="height:140px;line-height:35px;">
											商品类型：<span style="color:black"><s:property value="#attr.goods.goods_type_name"/></span>
											<br/><s:if test="#goods.goods_flag==2">原价：<span style="text-decoration: line-through;color:black">￥<s:property value='#attr.goods.goods_price1'/></span>促销价：<span style="color:black">￥<s:property value='#attr.goods.goods_price2'/></span></s:if>
							  					 <s:else>商品价格：<span style="color:black">￥<s:property value='#attr.goods.goods_price2'/></span></s:else>
											<br/>库存数量：<span style="color:black"><s:property value="#attr.goods.goods_count"/></span>
											<br/>购买数量：<input type="text" id="goods_count" name="paramsOrdersDetail.goods_count" value="1" style="width:80px"/>
									</div>
									<div class="textBtn">
										<img id="addCard" src="images/addCard.png" style="border:none;cursor:pointer;vertical-align:middle" />&nbsp;&nbsp;
									</div>
									</form>
							</div>
					</div>
					<div class="typehr"></div>
					<div class="title">商品详情介绍</div>
					<div class="typehr"></div>
					<div class="intro">
						<s:property value="#attr.goods.goods_descShow" escape="false"  />
					</div>
					<div class="typehr"></div>
					<div class="title">用户评价</div>
					<div class="typehr"></div>
					
					<!-- 信息开始 -->
					 <s:if test="totalCount > 0">
					 <s:iterator value="#attr.evaluates" id="sblog">
					 <div class="messages">
						 <div class="messages_left">
							<div class="nickName"><s:property value="#sblog.nick_name" /></div>
							<div class="headphoto"><img src="images/head/head001.gif"/></div>
						</div>
				
						<div class="messages_right">
							<div class="time">
								评价等级：<s:property value="#attr.sblog.evaluate_levelDesc" /> &nbsp;&nbsp;&nbsp;&nbsp;
								<%--<img src="images/time.gif" valign="middle"/>--%> 
								评价时间：<s:property value="#attr.sblog.evaluate_date.substring(0,19)" />　
							</div>
				
							<div class="sblog_title">
								 <s:property value="#attr.sblog.evaluate_content" />
							</div>
							
						</div>
					 </div>
					 </s:iterator>
					 </s:if>
					<!-- 信息结束 -->
					
					<jsp:include page="page.jsp"></jsp:include>
			</div>

			 
			
	 </div>
	 <!--  产品检索 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
var goods_id = "<s:property value='#attr.goods.goods_id' />";
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  window.location.href="page_queryGoods.action?paramsGoods.goods_id="+goods_id+"&paramsEvaluate.goods_id="+goods_id+"&pageNo="+pagenum;
}
function ChangePage(pagenum)
{
	window.location.href="page_queryGoods.action?paramsGoods.goods_id="+goods_id+"&paramsEvaluate.goods_id="+goods_id+"&pageNo="+pagenum;
}
</script>
</body>
</html>