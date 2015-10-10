<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>促销商品列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
 
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
	<!--  产品检索展示 -->
	 <div id="product_info">
	 		<!--  产品列表 -->
	 		<form name="info" id="info" action="page_listGoods.action" method="post">
	 		<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"/>
			<div class="list">
					<div class="products">
					<s:if test="#attr.goodss!=null&&#attr.goodss.size()>0">
					<s:iterator value="#attr.goodss" id="goods">
					<div class="product">
						<div class="productPic"><s:a href="page_queryGoods.action?paramsGoods.goods_id=%{#goods.goods_id}"><img src="images/goodss/<s:property value='#goods.goods_pic'/>" /></s:a></div>
						<div class="productText"><span class="title"><s:property value='#goods.goods_name'/></span>
						<br/><s:if test="#goods.goods_flag==2">原价：<span style="text-decoration: line-through;">￥<s:property value='#goods.goods_price1'/></span>　
							  <br/>促销价：￥<s:property value='#goods.goods_price2'/></s:if>
							  <s:else>商品价格：￥<s:property value='#goods.goods_price2'/></s:else>
						</div>
					</div>
					</s:iterator>
					</s:if> 
					</div>
					
					<!--  产品分页 -->
					<jsp:include page="page.jsp"></jsp:include>
				    <!--  产品分页 -->

			</div>
			</form>
			<!--  产品列表 -->
			
	 </div>
	 <!--  产品检索展示 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.submit();
}
function ChangePage(pagenum)
{
	 document.getElementById("pageNo").value=pagenum;
	 document.info.submit();
}	 
function serch()
{
   document.info.action="page_listGoodsHot.action";
   document.info.submit();
}
</script>
</body>
</html>