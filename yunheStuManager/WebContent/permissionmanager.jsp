<%@page import="cn.yunhe.entity.Permission"%>
<%@page import="cn.yunhe.biz.impl.PermissionManagerImpl"%>
<%@page import="cn.yunhe.biz.PermissionManager"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>班级信息管理</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<!-- 提示框 -->
<script type="text/javascript" src="js/tooltips.js"></script>
<!-- 提示框 -->

</head>

<body>

  <%
   request.setCharacterEncoding("utf-8");

   List<Map<String,String>> listParam = new ArrayList<Map<String,String>>();
   Map<String,String>  mapParam1 = new HashMap<String,String>();
   mapParam1.put("url", "#");
   mapParam1.put("name", "用户管理");
   
   Map<String,String>  mapParam2 = new HashMap<String,String>();
   mapParam2.put("url", "checkingmanager.jsp");
   mapParam2.put("name", "权限管理");
   
   
   
   listParam.add(mapParam1);
   listParam.add(mapParam2);
   
   JSONObject jo  = new JSONObject();
   jo.put("list", listParam);
   System.out.println(jo.toString());
   
   pageContext.include("top_nav.jsp?param="+jo.toString());
   
%>

<nav class="navbar navbar-default" role="navigation" style="margin-left: 60px;margin-right: 60px; margin-top:10px" >
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">添加</span>
		</div>
		
		<div>
			<form class="navbar-form navbar-left" id = "addform" role="search"  action="permissionServlet?method=add" method="post">
				<input type="text" class="form-control" placeholder="请输入权限名称" name="class_name" id ="permission_name"/>
				<button type="button" class="btn btn-default" onclick="return regVertify();">添加权限</button>
			</form>
		</div>
		
	</div>
</nav>

<div id="content" style="margin-left: 60px;margin-right: 60px">
    <span>共有 <b>10</b> 条记录</span>
    <table border="1" cellspacing="0" cellpadding="0" class="table table-striped table-hover table-bordered">
        <tr id="theTableTitle">
            <th>权限ID</th>
            <th>权限名称</th>
            <th>操作</th>
        </tr>

<%
   PermissionManager permissionManager = new PermissionManagerImpl();
          List<Permission> listPermissions=  permissionManager.queryAllPermission();
          for(Permission p:listPermissions){
        	  
         
%>
		<tr>
	            <td><%=p.getPermissin_id() %></td>
	            <td><%=p.getPermission_name() %></td>
	            <td>
	            	<a href="javascript:onDelete(<%=p.getPermissin_id()%>)">[删除]</a>
	                <a href="updatepermission.jsp?permission_id=<%=p.getPermissin_id()%>&permission_name=<%=p.getPermission_name()%>">[修改]</a>
	            </td>
	   </tr>
<%
          }
%>


    </table>
</div>
<script type="text/javascript">

 function regVertify() {
	var permission_name = $("#permission_name").val();
	if(permission_name==""){
		show_err_msg("请输入权限名称")
	}else{
		$("#addform").submit();
	}
	
}
 function onDelete(permission_id){
	  if(confirm("确定要删除该个权限吗？")){
		  window.location.href ="permissionServlet?method=delete&permission_id="+permission_id;
	  }
 }
</script>
</body>
</html>
