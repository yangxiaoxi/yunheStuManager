<%@page import="java.util.List"%>
<%@page import="cn.yunhe.entity.Student"%>
<%@page import="cn.yunhe.biz.impl.StudentManagerImpl"%>
<%@page import="cn.yunhe.biz.StudentManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

.background-head {
	background-image: url(images/-1.jpg);
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: 100%;
	height: 210px;
	width: 250px
}
</style>
</head>
<%
		StudentManager stuManager = new StudentManagerImpl();
		String stu_idStr = request.getParameter("stu_id");
		int stu_id = Integer.parseInt(stu_idStr);
		pageContext.setAttribute("stu_id", stu_id);
		Student stu = stuManager.querystuBystuid(stu_id);
		pageContext.setAttribute("stu", stu);
	%>
<body>
   <%
   

   List<Map<String,String>> listParam = new ArrayList<Map<String,String>>();
   Map<String,String>  mapParam1 = new HashMap<String,String>();
   mapParam1.put("url", "#");
   mapParam1.put("name", "积分管理");
   
   Map<String,String>  mapParam2 = new HashMap<String,String>();
   mapParam2.put("url", "checkingmanager.jsp");
   mapParam2.put("name", "积分信息管理");
   
   Map<String,String>  mapParam3 = new HashMap<String,String>();
   mapParam3.put("url", "#");
   mapParam3.put("name", "修改积分");
   
   
   listParam.add(mapParam1);
   listParam.add(mapParam2);
   listParam.add(mapParam3);
   
   JSONObject jo  = new JSONObject();
   jo.put("list", listParam);
   
   pageContext.include("top_nav.jsp?param="+jo.toString());
%>
 
	<%
	String host = request.getServerName();//获取主机名
	int port = request.getServerPort();//获取端口号
	String imgUrl = "stuimgs/"+stu.getStu_imag();
	
	pageContext.setAttribute("imgUrl", imgUrl);
	%>
	<div class="box">
		<div class="box"
			style="background: url(${pageScope.imgUrl}); width: 210px; height: 250px; background-repeat: no-repeat; background-size: 210px 250px;">
		</div>
		<form id="uploadForm" action="studentServlet?method=update"
			class="form-horizontal" role="form" method="post"  onsubmit="return onRevify()" enctype="multipart/form-data">
			<input type="hidden" value="${pageScope.stu_id}" name="stu_id" />
			
			<div class="form-group">
				<label for="bookLogo" class="col-sm-2 control-label">头像:</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" name="stu_head"
						id="stu_head" placeholder="请选择头像" />
				</div>
			</div>
			<div class="form-group">
				<label for="user_id" class="col-sm-2 control-label">所属班级:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="stu_class_name"
						id="stu_no" disabled="disabled" value="${pageScope.stu.getClass_name()}" />
				</div>
			</div>
			<div class="form-group">
				<label for="user_id" class="col-sm-2 control-label">学号:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="stu_no" id="stu_no"
						disabled="disabled" value="${pageScope.stu.getStu_no() }" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">姓名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="stu_name"
						id="stu_name" value="${pageScope.stu.getStu_name() }" />
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别:</label>
				<div class="col-sm-10">
					
					<c:if test="${pageScope.stu.getStu_sex() eq '男' }">
					     <c:set var="men" value="checked='checked'" scope="page">
					     </c:set>
					</c:if>
					<c:if test="${!pageScope.stu.getStu_sex() eq '男' }">
					     <c:set var="men" value="" scope="page">
					     </c:set>
					</c:if>
					
					<c:if test="${pageScope.stu.getStu_sex() eq '女' }">
					     <c:set var="women" value="checked='checked'" scope="page">
					     </c:set>
					</c:if>
					<c:if test="${!pageScope.stu.getStu_sex() eq '女'}">
					     <c:set var="women" value="" scope="page">
					     </c:set>
					</c:if>
					<input type="radio" name="stu_sex" id="stu_sex" value="男" ${pageScope.men  } />男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <input type="radio" name="stu_sex" id="stu_sex" value="女"  ${pageScope. women } />女
				
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">专业:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="stu_profession"
						id="stu_profession" value="${pageScope.stu.getStu_profession() }" />
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">身份证:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="stu_icno"
						id="stu_icno" value="${pageScope.stu.getStu_icno() }" />
				</div>
			</div>
			<div class="form-group">
				<label for="tel" class="col-sm-2 control-label">电话:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="stu_phone"
						id="stu_phone" value="${pageScope.stu.getStu_phone() }" />
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-sm-2 control-label">生日:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="stu_birth"
						id="stu_birth" value="${pageScope.stu.getStu_birth() }" />
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-sm-2 control-label">QQ:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="stu_qq" id="stu_qq"
						value="${pageScope.stu.getStu_qq() }" />
				</div>
			</div>
			<div class="form-group">
				<label for="bookLogo" class="col-sm-2 control-label">学历:</label>
				<div class="col-sm-10">
					<select class="form-control" name="stu_edu">
						 
						<c:if test="${pageScope.stu.getStu_edu() eq '高中' }">
						  <c:set  var="gaozhong" value="selected='selected'" scope="page"/>
						</c:if>
						
						<c:if test="${pageScope.stu.getStu_edu() eq '大专' }">
						  <c:set  var="dazhuan" value="selected='selected'" scope="page"/>
						</c:if>
						<c:if test="${pageScope.stu.getStu_edu() eq '硕士' }">
						  <c:set  var="shuoshi" value="selected='selected'" scope="page"/>
						</c:if>
						<c:if test="${pageScope.stu.getStu_edu() eq '本科' }">
						  <c:set  var="benke" value="selected='selected'" scope="page"/>
						</c:if>
						
						<option value="硕士" ${pageScope.shuoshi }>硕士</option>
						<option value="大专"  ${pageScope.dazhuan }>大专</option>
					   <option value="高中" ${pageScope.gaozhong }>高中</option>
						<option value="本科" ${pageScope.benke }>本科</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-sm-2 control-label">学校:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="stu_school"
						id="stu_school" value="${pageScope.stu.getStu_school() }" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改</button>
					<button type="button" class="btn btn-warning"
						onclick=" return cancel();">返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function cancel() {
			window.history.go(-1);
		}
	</script>
	

<script type="text/javascript">
     function onRevify() {
 		var stu_name = $("#stu_name").val();
 		var stu_profession = $("#stu_profession").val();
 		var stu_icno = $("#stu_icno").val();
 		var stu_phone = $("#stu_phone").val();
 		var datepicker  = $("#datepicker").val();
 		var stu_qq = $("#stu_qq").val();
 		var stu_school = $("#stu_school").val();
 		  if(stu_name==""){
 			show_err_msg("请输入姓名");
 			return false;
 		}else if(stu_profession==""){
 			show_err_msg("请输入专业");
 			return false;
 		}else if(stu_icno==""){
 			show_err_msg("请输入身份证号");
 			return false;
 		}else if(stu_phone==""){
 			show_err_msg("请输入电话");
 			return false;
 		}else if(datepicker==""){
 			show_err_msg("请输入生日");
 			return false;
 		}else if(stu_name==""){
 			show_err_msg("请输入姓名");
 			return false;
 		}else if(stu_qq==""){
 			show_err_msg("请输入qq");
 			return false;
 		}else if(stu_school==""){
 			show_err_msg("请输入学校");
 			return false;
 		}else {
 			return true;
 		}
	}
</script>
</body>
</html>
