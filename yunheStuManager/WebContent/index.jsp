<%@page import="java.util.List"%>
<%@page import="cn.yunhe.biz.impl.PermissionManagerImpl"%>
<%@page import="cn.yunhe.biz.PermissionManager"%>
<%@page import="cn.yunhe.biz.impl.UserBizImpl"%>
<%@page import="cn.yunhe.biz.UserBiz"%>
<%@page import="cn.yunhe.util.Parameter"%>
<%@page import="cn.yunhe.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>云和数据教学管理系统</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/tooltips.js"></script>

<script type="text/javascript">
	function setRightContents(url) {
		document.getElementById('rightContents').src = url;
	}
</script>
</head>

<body>

	<div class="nav-top">
		<span>云和数据教学管理系统</span>
		<%
		  PermissionManager pm = new PermissionManagerImpl();
			User user = (User) session.getAttribute(Parameter.LOGIN_USER);
			UserBiz userbiz = new UserBizImpl();
			 List<Integer> listPermissions = null;
		%>
		<div class="nav-topright">
			<%
				if (user != null) {
					 int role_id = user.getUser_role();
					 listPermissions  =  pm.queryByRole_id(role_id);
					out.print("<span>您好：" + user.getUser_nickname() + "["
							+ user.getRole_name() + "]</span>");
				} else {
					
					int haveCookie = 0;
					Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						for (Cookie cookie : cookies) {
							if (cookie != null) {
								if (Parameter.USER_ID.equals(cookie.getName())) {
									  user = userbiz.selectById(Integer
											.parseInt(cookie.getValue()));
									  session.setAttribute(Parameter.LOGIN_USER, user);
									  int role_id = user.getUser_role();
										 listPermissions  =  pm.queryByRole_id(role_id);
									  out.print("<span>您好：" + user.getUser_nickname()
											+ "[" + user.getRole_name()
											+ "]</span>");
									    break;
									} 
							}else {
								haveCookie++;
							}
						}
						if (haveCookie == cookies.length) {
							out.print(" <span>您 好：请 先  登 录     </span>");
						}
					} else {
						out.print(" <span>您 好：请 先  登 录  </span>");
					}
				}
			%>
			<span><a href="userServlet?method=exit" style="color: #FFFFFF">注销</a></span>
		</div>
	</div>



	<div class="nav-down">

		<div class="leftmenu2">
			<div class="menu-oc1"></div>
			<ul>
			<%
			if(listPermissions.contains(1)){
				
			%>
				<li><a class="j_a_list j_a_list1"></a>
					<div class="j_menu_list j_menu_list_first">
						<span class="sp1"><i></i>学员管理</span> <a class="j_lista_first"
							href="javascript:setRightContents('stuinfomanager.jsp')">学员信息管理</a>
						<a href="excleIn.jsp">导入excel</a>
						<a href="excle?method=excleOut">导出excel</a>
					</div></li>
					<%
					
			}
					%>
					<%
					
					if(listPermissions.contains(2)){
					%>
				<li><a class="j_a_list j_a_list2"></a>
					<div class="j_menu_list">
						<span class="sp2"><i></i>积分管理</span> <a
							href="javascript:setRightContents('scoremanager.jsp')">积分信息管理</a>
					</div></li>
					<%
					}
					%>
					<%
					if(listPermissions.contains(3)){
					%>
				<li><a class="j_a_list j_a_list3"></a>
					<div class="j_menu_list">
						<span class="sp3"><i></i>考勤管理</span> <a
							href="javascript:setRightContents('checkingmanager.jsp')">打卡记录管理</a>
						<a href="#">导入excel</a> <a href="#">导入Access</a>
					</div></li>
					<%
					}
					%>
					<%
					if(listPermissions.contains(4)){
					%>
				<li><a class="j_a_list j_a_list4"></a>
					<div class="j_menu_list">
						<span class="sp4"><i></i>权限管理</span> <a href="javascript:setRightContents('usermanager.jsp')">用户列表管理</a> <a
							href="javascript:setRightContents('rolemanager.jsp') ">角色列表管理</a> <a href="javascript:setRightContents('permissionmanager.jsp') ">权限列表管理</a>
					</div></li>
					<%
					}
					%>
					<%
					if(listPermissions.contains(5)){
					%>
				<li><a class="j_a_list j_a_list5"></a>
					<div class="j_menu_list">
						<span class="sp5"><i></i>班级管理</span> <a
							href="javascript:setRightContents('classmanager.jsp')">班级信息管理</a>
					</div></li>
					<%
					}
					%>
			</ul>
		</div>

		<div class="rightcon">
			<div class="right_con" style="width: 100%; height: 1200px">
				<iframe src="stuinfomanager.jsp" frameBorder="0" scrolling="no"
					width="100%" height="100%" id="rightContents"> </iframe>
			</div>
		</div>
	</div>

</body>
</html>