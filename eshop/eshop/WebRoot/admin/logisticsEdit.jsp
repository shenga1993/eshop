<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加物流信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
	 $("#addBtn").bind('click',function(){
		if($("#paramsLogistics\\.logistics_date").val()==''){
			alert('物流日期不能为空');
			return;
		}
		if($("#paramsLogistics\\.logistics_desc").val()==''){
			alert('物流内容不能为空');
			return;
		}
		$("#paramsLogistics\\.logistics_id").val(0);
		$("#info").attr('action','Admin_addLogistics.action').submit();
		 
	 });
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">添加物流信息<</span>
</div>
<form id="info" name="info" action="Admin_addLogistics.action" method="post">   
<s:hidden id="paramsLogistics.logistics_id" name="paramsLogistics.logistics_id" value="%{#attr.goodsType.logistics_id}" /> 
<s:hidden id="paramsLogistics.orders_no" name="paramsLogistics.orders_no" value="%{#attr.orders_no}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">添加物流信息</TD>
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
          <td width="35%" align="right" style="padding-right:5px">订单编号：</td>
          <td width="65%">
          	<s:property value="#attr.orders_no"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 物流日期：</td>
          <td width="65%">
          	<s:textfield name="paramsLogistics.logistics_date" id="paramsLogistics.logistics_date" 
          						onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 物流内容：</td>
          <td width="65%">
          	<s:textfield name="paramsLogistics.logistics_desc" id="paramsLogistics.logistics_desc" />
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
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>