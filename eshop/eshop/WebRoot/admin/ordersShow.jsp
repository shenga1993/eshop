<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品订单信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listOrderss.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的商品订单！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delOrderss.action?paramsOrders.ids="+ids;
       document.info.submit();
    }
    
}
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
  document.info.action="Admin_listOrderss.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listOrderss.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">商品订单管理&gt;&gt;商品订单查询</span>
</div>
<form name="info" id="info" action="Admin_listOrderss.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">商品订单列表</td>
    <td width="" align="right">
            订单编号：
      <input type="text" id="paramsOrders.orders_no" name="paramsOrders.orders_no" value="${paramsOrders.orders_no}" class="inputstyle" Style="width:100px;"/>&nbsp;      
            用户姓名：
      <input type="text" id="paramsOrders.real_name" name="paramsOrders.real_name" value="${paramsOrders.real_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
            订单状态：
      <s:select id="paramsOrders.orders_flag" name="paramsOrders.orders_flag" value="%{#attr.paramsOrders.orders_flag}" 
      		list="#{'1':'待发货','2':'已发货','3':'已收货','4':'已评价'}" listKey="key" listValue="value" 
      		cssClass="selectstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
      </s:select>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <s:if test="#attr.admin.user_type==3">
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
      </s:if>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <s:if test="#attr.admin.user_type==3">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     </s:if>		    
     <td width="40" align="center">序号</td>
     <td width="" align="center">订单编号</td>
     <td width="" align="center">用户姓名</td>
     <td width="" align="center">订单日期</td>
     <td width="" align="center">收货地址</td>
     <td width="" align="center">联系电话</td>
     <td width="" align="center">订单总额</td>
     <td width="" align="center">配送人</td>
     <td width="" align="center">当前状态</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.orderss!=null && #attr.orderss.size()>0">
   <s:iterator value="#attr.orderss" id="orders" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <s:if test="#attr.admin.user_type==3">
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#orders.orders_id}" cssStyle="vertical-align:text-bottom;"/></td>
     </s:if>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#orders.orders_no"/></td>
     <td width="" align="center"><s:property value="#orders.real_name"/></td>
     <td width="" align="center"><s:property value="#orders.orders_date.substring(0,10)"/></td>
     <td width="" align="center"><s:property value="#orders.user_address"/></td>
     <td width="" align="center"><s:property value="#orders.user_phone"/></td>
     <td width="" align="center"><s:property value="#orders.orders_money"/></td>
     <td width="" align="center"><s:property value="#orders.send_name"/></td>
     <td width="" align="center"><s:property value="#orders.orders_flagDesc"/></td>
     <td width="" align="center">
       <s:a href="Admin_listOrdersDetails.action?paramsOrdersDetail.orders_no=%{#orders.orders_no}">订单明细</s:a>&nbsp;
       <s:if test="#orders.orders_flag==1 && #attr.admin.user_type==3">
       <s:a href="Admin_sendOrdersShow.action?paramsOrders.orders_id=%{#orders.orders_id}">发货</s:a>&nbsp;&nbsp;
       </s:if>
       <s:if test="#orders.orders_flag!=1">
       <s:a href="Admin_listLogisticss.action?paramsLogistics.orders_no=%{#orders.orders_no}">物流信息</s:a>&nbsp;&nbsp;
       </s:if>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="11" align="center"><b>&lt;不存在商品订单信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>