<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
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
   document.info.action="Admin_listGoodss.action";
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
       alert("请至少选择一个要删除的商品！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delGoodss.action?paramsGoods.ids="+ids;
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
  document.info.action="Admin_listGoodss.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listGoodss.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">商品管理&gt;&gt;商品查询</span>
</div>
<form name="info" id="info" action="Admin_listGoodss.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">商品列表</td>
    <td width="" align="right">
            商品名称：
      <input type="text" id="paramsGoods.goods_name" name="paramsGoods.goods_name" value="${paramsGoods.goods_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
            商品类型：
      <s:select id="paramsGoods.goods_type_id" name="paramsGoods.goods_type_id" value="%{#attr.paramsGoods.goods_type_id}" 
      		list="#attr.goodsTypes" listKey="goods_type_id" listValue="goods_type_name" 
      		cssClass="selectstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
      </s:select>&nbsp;
            商品状态：
      <s:select id="paramsGoods.goods_flag" name="paramsGoods.goods_flag" value="%{#attr.paramsGoods.goods_flag}" 
      		list="#{'1':'正常','2':'促销' }" listKey="key" listValue="value" 
      		cssClass="selectstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
      </s:select>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">商品名称</td>
     <td width="" align="center">商品类型</td>
     <td width="" align="center">商品原价</td>
     <td width="" align="center">当前价格</td>
     <td width="" align="center">商品状态</td>
     <td width="" align="center">商品数量</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.goodss!=null && #attr.goodss.size()>0">
   <s:iterator value="#attr.goodss" id="goods" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#goods.goods_id}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#goods.goods_name"/></td>
     <td width="" align="center"><s:property value="#goods.goods_type_name"/></td>
     <td width="" align="center">￥<s:property value="#goods.goods_price1"/></td>
     <td width="" align="center">￥<s:property value="#goods.goods_price2"/></td>
     <td width="" align="center"><s:property value="#goods.goods_flagDesc"/></td>
     <td width="" align="center"><s:property value="#goods.goods_count"/></td>
     <td width="" align="center">
       <s:a href="Admin_queryGoods.action?paramsGoods.goods_id=%{#goods.goods_id}">查看</s:a>&nbsp;
       <s:a href="Admin_editGoods.action?paramsGoods.goods_id=%{#goods.goods_id}">编辑</s:a>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="9" align="center"><b>&lt;不存在商品信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>