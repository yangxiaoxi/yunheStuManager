<%@page import="cn.yunhe.entity.Role"%>
<%@page import="cn.yunhe.biz.impl.RoleManagerImpl"%>
<%@page import="cn.yunhe.biz.RoleManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
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
   

   List<Map<String,String>> listParam = new ArrayList<Map<String,String>>();
   Map<String,String>  mapParam1 = new HashMap<String,String>();
   mapParam1.put("url", "#");
   mapParam1.put("name", "用户管理");
   
   Map<String,String>  mapParam2 = new HashMap<String,String>();
   mapParam2.put("url", "checkingmanager.jsp");
   mapParam2.put("name", "角色管理");
   
   
   
   listParam.add(mapParam1);
   listParam.add(mapParam2);
   
   JSONObject jo  = new JSONObject();
   jo.put("list", listParam);
   
   pageContext.include("top_nav.jsp?param="+jo.toString());
   
%>

<nav class="navbar navbar-default" role="navigation" style="margin-left: 60px;margin-right: 60px; margin-top:10px" >
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">添加</span>
		</div>
		<div>
			<form class="navbar-form navbar-left" role="search"  action="#">
				<button type="button" class="btn btn-default" onclick="javascript:window.location.href='addrole.jsp'">
					添加角色
				</button>
			</form>
		</div>
		
	</div>
</nav>

<div id="content" style="margin-left: 60px;margin-right: 60px">
    <span>共有 <b>10</b> 条记录</span>
    <table border="1" cellspacing="0" cellpadding="0" class="table table-striped table-hover table-bordered">
        <tr id="theTableTitle">
            <th>角色ID</th>
            <th>角色名称</th>
            <th>操作</th>
        </tr>
		<%
		RoleManager roleManager = new RoleManagerImpl();
		List<Role> listRoles = roleManager.queryAllRole();
		for(Role role:listRoles ){
			
		
		%>
		<tr>
	            <td><%=role.getRole_id() %></td>
	            <td><%=role.getRole_name() %></td>
	            <td>
	            	<a href="javascript:onDelete(<%=role.getRole_id()%>)">[删除]</a>
	                <a href="updaterole.jsp?role_id=<%=role.getRole_id() %>&role_name=<%=role.getRole_name()%>">[修改]</a>
	            </td>
	        </tr>
			<%
		}
			%>
		 

    </table>
</div>
<script type="text/javascript">


function onDelete(role_id){
	  if(confirm("确定要删除该个权限吗？")){
		  window.location.href ="roleServlet?method=delete&role_id="+role_id;
	  }
}
</script>
</body>
</html>
