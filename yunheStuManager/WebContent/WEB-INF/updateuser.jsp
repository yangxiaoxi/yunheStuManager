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


<div class="box">
    <form id="uploadForm" action="#" class="form-horizontal" role="form">
    	<div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">用户ID:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="user_id" 
                id="user_id" disabled="disabled" value="1" />
            </div>
        </div>
		
        <div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">用户类型:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" disabled="disabled" 
                	value="普通用户" />
            </div>
        </div>
        
        <div class="form-group">
	            <label for="user_id" class="col-sm-2 control-label">用户名(邮箱):</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" name="user_name" 
	                id="user_name" value="11223@iyunhe.com" />
	            </div>
	        </div>
	        <div class="form-group">
	            <label for="user_id" class="col-sm-2 control-label">密码:</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" name="user_pwd" 
	                id="user_pwd" value="111111" />
	            </div>
	        </div>
        
        <div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">昵称:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="user_nickname" 
                id="user_nickname" value="admin" />
            </div>
        </div>
        <div class="form-group">
            <label for="bookLogo" class="col-sm-2 control-label">所属角色:</label>
            <div class="col-sm-10">
		        <select class="form-control" name="role_id" id="role_id">
		        	   <option value="-1">超级管理员</option>
					   <option value="2">教务管理</option>
					   <option value="3">授课老师</option>
				</select>
			</div>
		</div>
        <div class="form-group">
            <label for="bookLogo" class="col-sm-2 control-label">所属班级:</label>
            <div class="col-sm-10">
		        <select class="form-control" name="class_id" id="class_id">
		        	   <option value="-1">全部</option>
		        	   <option value="1">云和数据java一班</option>
					   <option value="1">云和数据java二班</option>
					   <option value="1">云和数据java三班</option>
				</select>
			</div>
		</div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-primary"
                        onclick=" return regVertify();">修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改</button>
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
	
</script>

</body>
</html>
