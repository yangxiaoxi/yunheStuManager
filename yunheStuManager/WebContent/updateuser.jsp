<%@page import="cn.yunhe.entity.Role"%>
<%@page import="cn.yunhe.biz.impl.RoleManagerImpl"%>
<%@page import="cn.yunhe.biz.RoleManager"%>
<%@page import="cn.yunhe.entity.Clazz"%>
<%@page import="java.util.List"%>
<%@page import="cn.yunhe.biz.impl.ClazzManagerImpl"%>
<%@page import="cn.yunhe.biz.ClazzManager"%>
<%@page import="cn.yunhe.biz.impl.UserBizImpl"%>
<%@page import="cn.yunhe.biz.UserBiz"%>
<%@page import="cn.yunhe.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<!-- 提示框 -->
<script type="text/javascript" src="js/tooltips.js"></script>
<!-- 提示框 -->

<title>管理区域</title>
  <style type="text/css">
      .box {
          width: 60%;
          margin: 50px auto;
          padding: 20px;
          padding-left: 50px;
          border: 1px #ccc dashed;
      }
      .background-head{
			background-image: url(images/-1.jpg);
			background-attachment: fixed;
			background-repeat: no-repeat;
			background-size: 100%;
			height:210px;
			width:250px
		}
  </style>
</head>


<body>
<!-- 访问路径导航 -->
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">
				<a href="#">权限管理</a>&nbsp;>>&nbsp;
				<a href="#">用户列表管理</a>&nbsp;>>&nbsp;
				<a href="#">修改用户</a>
			</span>
		</div>
	</div>
	
</nav>


<%
UserBiz userBiz = new UserBizImpl();
String user_id = request.getParameter("user_id");
User user = userBiz.selectById(Integer.parseInt(user_id));
%>
<div class="box">
    <form id="uploadForm" action="userServlet?method=update&user_id=<%=user.getUser_id() %>&user_type=<%=user.getUser_type() %>" class="form-horizontal" role="form" method="post">
    	<div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">用户ID:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="user_id" 
                id="user_id" disabled="disabled" value="<%=user.getUser_id() %>" />
            </div>
        </div>
		
        <div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">用户类型:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" disabled="disabled" 
                	value="<%=user.getUser_type()==1?"普通用户":"第三方用户"%>" />
            </div>
        </div>
        <%
        
        if(user.getUser_type()==1){
        %>
        <div class="form-group">
	            <label for="user_id" class="col-sm-2 control-label">用户名(邮箱):</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" name="user_name" 
	                id="user_name" value="<%=user.getUser_name() %>" />
	            </div>
	        </div>
	        <div class="form-group">
	            <label for="user_id" class="col-sm-2 control-label">密码:</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" name="user_pwd" 
	                id="user_pwd" value="<%=user.getUser_pwd() %>" />
	            </div>
	        </div>
        <%
        }
        %>
        <div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">昵称:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="user_nickname" 
                id="user_nickname" value="<%=user.getUser_nickname() %>" />
            </div>
        </div>
        <%
        RoleManager roelManager = new RoleManagerImpl();
        List<Role> listRole = roelManager.queryAllRole();
        int role_id = user.getUser_role();
        %>
        <div class="form-group">
            <label for="bookLogo" class="col-sm-2 control-label">所属角色:</label>
            <div class="col-sm-10">
		        <select class="form-control" name="role_id" id="role_id">
		        <%
		        for(Role role:listRole){
		        	if(role_id==role.getRole_id()){
		        %>
		        	 <option selected="selected"  value="<%=role.getRole_id()%>"><%=role.getRole_name() %>
		        	   <%
		        	}else{
		        	 %>
		        	 <option   value="<%=role.getRole_id()%>"><%=role.getRole_name()%>
		        	<%
		        	}
		        }
		        	   %>
					   
				</select>
			</div>
		</div>
        <div class="form-group">
            <label for="bookLogo" class="col-sm-2 control-label">所属班级:</label>
            <div class="col-sm-10">
            <%
            
            ClazzManager clazzManager = new ClazzManagerImpl();
            List<Clazz> listClazz = clazzManager.query();
            int user_class_id = user.getClass_id();
            System.out.print("user_class_id = "+user_class_id);
            %>
		        <select class="form-control" name="class_id" id="class_id">
		        	   <option value="-1">全部</option>
		        	   <%
		        	   for(Clazz  clazz:listClazz){
		        		   if(user_class_id == clazz.getClazz_id()){
		        			   
		        	   %>
		        	   <option   selected="selected" value="<%=clazz.getClazz_id()%>"><%=clazz.getClazz_name() %></option>
					  <%
		        	   }else{
		        		   %>
		        		      <option   value="<%=clazz.getClazz_id()%>"><%=clazz.getClazz_name() %></option>
		        	  <%
		        	   }
		        	  }
		        	  %>
				</select>
			</div>
		</div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-primary"
                        onclick="regVertify(<%=user.getUser_type()%>)">修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改</button>
                <button type="button" class="btn btn-warning"
                        onclick=" return cancel();">返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</button>
            </div>
        </div>
    </form>
</div>


 <script type="text/javascript">
	
 	function cancel(){
		window.history.go(-1);
	}
 	
 	function regVertify(user_type){
 		alert("jinru");
 		if(user_type==1){
 			 var user_name = $("#user_name").val();
 			 var user_pwd = $("#user_pwd").val();
 			  if(user_name==""){
 				  show_err_msg("邮箱不能为空");
 			  }else if(user_pwd==""){
 				  show_err_msg("密码不能为空");
 			  }
 		}
		  var user_nickname = $("#user_nickname").val();
		  if(user_nickname==""){
			  show_err_msg("昵称不能为空");
		  }else{
			  $("#uploadForm").submit();
		  }
	  }
	
	
</script>

</body>
</html>
