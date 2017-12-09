<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.yunhe.entity.Permission"%>
<%@page import="java.util.List"%>
<%@page import="cn.yunhe.biz.impl.PermissionManagerImpl"%>
<%@page import="cn.yunhe.biz.PermissionManager"%>
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
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">
				<a href="#">用户管理</a>&nbsp;>>&nbsp;
				<a href="#">XXXXXXX</a>
			</span>
		</div>
	</div>
	
</nav>


<div class="box">
    <form id="uploadForm" action="roleServlet?method=add" class="form-horizontal" method="post" role="form">
        <div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">角色名称:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="role_name" 
                id="role_name" />
            </div>
        </div>
        <div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">拥有权限:</label>
            <%
            PermissionManager pManager = new PermissionManagerImpl();
            List<Permission> listPermission = pManager.queryAllPermission();
            for(Permission  p:listPermission){
            %>
            <input  type="checkbox" name="permission" value="<%=p.getPermissin_id() %>"/><%=p.getPermission_name() %>&nbsp;&nbsp;
            <%
            }
            %>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-primary"
                        onclick="regVertify();">添&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;加</button>
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
	
	function regVertify(){
		  var role_name = $("#role_name").val();
		  var ck = $("input[name='permission']:checked");
		  if(role_name==""){
			  show_err_msg("请输入角色名称");
		  }else if(ck.length==0){
			  show_err_msg("请选择权限类型");
		  }else{
			  $("#uploadForm").submit();
		  }
	  }
</script>

</body>
</html>

</body>
</html>