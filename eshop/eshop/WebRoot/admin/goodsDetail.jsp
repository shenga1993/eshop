<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息详情</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">商品信息详情</span>
</div>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">商品信息详情</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="40%" align="center">
          	<img src="../images/goodss/<s:property value='%{#attr.goods.goods_pic}'/>" width="100" /> 
          </td>
          <td width="60%" style="line-height:22px;">
          	商品名称：<s:property value="%{#attr.goods.goods_name}"/>
          	<br/>商品类型：<s:property value="%{#attr.goods.goods_type_name}"/>
          	<br/>商品状态：<s:property value="%{#attr.goods.goods_flagDesc}"/> 
            <br/>商品原价：￥<s:property value="%{#attr.goods.goods_price1}"/> 元
            <br/>当前价格：￥<s:property value="%{#attr.goods.goods_price2}"/> 元
          	<br/>商品数量：<s:property value="%{#attr.goods.goods_count}"/> 
          	<br/>商品介绍：<s:property value="%{#attr.goods.goods_desc}" escape="false"/>
          </td>
        </tr> 
     </table>
     </td>
   </tr> 
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<input type="button" id="editBtn" Class="btnstyle" value="返 回" onclick="window.history.back(-1);"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr> 
</table>
</body>
</html>