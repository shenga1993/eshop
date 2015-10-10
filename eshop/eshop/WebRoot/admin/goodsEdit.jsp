<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">编辑</s:if><s:else>添加</s:else>商品信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	 var num = /^\d+(\.\d+)?$/;
	 var num2 = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		KE.sync('noticeContent');
		if($("#paramsGoods\\.goods_name").val()==''){
			alert('商品名称不能为空');
			return;
		}
		if($("#paramsGoods\\.goods_type_id").val()=='0'){
			alert('商品类型不能为空');
			return;
		}
		if($("#paramsGoods\\.goods_pic").val()==''){
			alert('商品图片不能为空');
			return;
		}
		if($("#paramsGoods\\.goods_flag").val()=='0'){
			alert('商品状态不能为空');
			return;
		}
		if($("#paramsGoods\\.goods_flag").val()=='2'){
			if(!num.exec($("#paramsGoods\\.goods_price1").val())){
				alert('商品原价必须为数字');
				return;
			}
			if(!num.exec($("#paramsGoods\\.goods_price2").val())){
				alert('当前价格必须为数字');
				return;
			}
		}else{
			if(!num.exec($("#paramsGoods\\.goods_price2").val())){
				alert('当前价格必须为数字');
				return;
			}
			$("#paramsGoods\\.goods_price1").val($("#paramsGoods\\.goods_price2").val());
		}
		if(!num2.exec($("#paramsGoods\\.goods_count").val())){
			alert('商品数量必须为数字');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('商品介绍不能为空');
			return;
		}
		$("#paramsGoods\\.goods_id").val(0);
		$("#info").attr('action','Admin_addGoods.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		    KE.sync('noticeContent');
		    if($("#paramsGoods\\.goods_name").val()==''){
				alert('商品名称不能为空');
				return;
			}
			if($("#paramsGoods\\.goods_type_id").val()=='0'){
				alert('商品类型不能为空');
				return;
			}
			if($("#paramsGoods\\.goods_pic").val()==''){
				alert('商品图片不能为空');
				return;
			}
			if($("#paramsGoods\\.goods_flag").val()=='0'){
				alert('商品状态不能为空');
				return;
			}
			if($("#paramsGoods\\.goods_flag").val()=='2'){
				if(!num.exec($("#paramsGoods\\.goods_price1").val())){
					alert('商品原价必须为数字');
					return;
				}
				if(!num.exec($("#paramsGoods\\.goods_price2").val())){
					alert('当前价格必须为数字');
					return;
				}
			}else{
				if(!num.exec($("#paramsGoods\\.goods_price2").val())){
					alert('当前价格必须为数字');
					return;
				}
				$("#paramsGoods\\.goods_price1").val($("#paramsGoods\\.goods_price2").val());
			}
			if(!num2.exec($("#paramsGoods\\.goods_count").val())){
				alert('商品数量必须为数字');
				return;
			}
			if($("#noticeContent").val()==''){
				alert('商品介绍不能为空');
				return;
			}
			$("#info").attr('action','Admin_saveGoods.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">商品管理&gt;&gt;<s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">编辑</s:if><s:else>添加</s:else>商品</span>
</div>
<form id="info" name="info" action="Admin_addGoods.action" method="post">   
<s:hidden id="paramsGoods.goods_id" name="paramsGoods.goods_id" value="%{#attr.goods.goods_id}" /> 
<input type="hidden" name="paramsGoods.goods_pic" id="paramsGoods.goods_pic" value='<s:property value="#attr.goods.goods_pic"/>'/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">编辑</s:if><s:else>添加</s:else>商品</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 商品名称：</td>
          <td width="65%">
          	<s:textfield name="paramsGoods.goods_name" id="paramsGoods.goods_name" value="%{#attr.goods.goods_name}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 商品类型：</td>
          <td width="65%">
          	<s:select id="paramsGoods.goods_type_id" name="paramsGoods.goods_type_id" value="%{#attr.goods.goods_type_id}" 
	      		list="#attr.goodsTypes" listKey="goods_type_id" listValue="goods_type_name" 
	      		class="selectstyle" cssStyle="width:150px;" headerKey="0" headerValue="请选择">
	        </s:select>
          </td>
        </tr> 
        <tr>
		  <td align="right" style="padding-right:5px">商品图片：</td>
		  <td align="left" colspan="3">
		    <img id="userImg" src="../images/goodss/<s:property value='#attr.goods.goods_pic'/>" width="70" height="80" style="border:0px;"/>
	        &nbsp;<span id="op" style="display:none"><img src="images/loading004.gif"  height="80" /></span>
	      </td>
	    </tr>
	     <tr>
		  <td align="right" style="padding-right:5px"></td>
	      <td align="left" colspan="3">
	          <iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
	    <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 商品状态：</td>
          <td width="65%">
		      <s:select id="paramsGoods.goods_flag" name="paramsGoods.goods_flag" value="%{#attr.goods.goods_flag}" 
		      		list="#{'1':'正常','2':'促销' }" listKey="key" listValue="value" 
		      		cssClass="selectstyle" cssStyle="width:155px;" headerKey="0" headerValue="请选择">
		      </s:select>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 商品原价：</td>
          <td width="65%">
          	<s:textfield name="paramsGoods.goods_price1" id="paramsGoods.goods_price1" value="%{#attr.goods.goods_price1}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 当前价格：</td>
          <td width="65%">
          	<s:textfield name="paramsGoods.goods_price2" id="paramsGoods.goods_price2" value="%{#attr.goods.goods_price2}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 商品数量：</td>
          <td width="65%">
          	<s:textfield name="paramsGoods.goods_count" id="paramsGoods.goods_count" value="%{#attr.goods.goods_count}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font>商品介绍：</td>
          <td width="65%">
          	<textarea style="width:350px;height:100px" name="paramsGoods.goods_desc" id="noticeContent">
          	<s:property value="#attr.goods.goods_desc" escape="false"/>
          	</textarea>
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
          	<s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
<script>        
	   KE.show({ 
	            id : 'noticeContent',
	            items:['plainpaste', '|', 'selectall', 'bold','italic'],
	            resizeMode:1
	            
	                    
	   });
	   
</script>
</body>
</html>