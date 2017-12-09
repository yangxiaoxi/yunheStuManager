<%@page import="cn.yunhe.util.Parameter"%>
<%@page import="cn.yunhe.entity.User"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="cn.yunhe.entity.Student"%>
<%@page import="java.util.List"%>
<%@page import="cn.yunhe.biz.impl.StudentManagerImpl"%>
<%@page import="cn.yunhe.biz.StudentManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学员信息管理</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<!-- 提示框 -->
<script type="text/javascript" src="js/tooltips.js"></script>
<!-- 提示框 -->

</head>

<%
	StudentManager stuManger = new StudentManagerImpl();
pageContext.setAttribute("stuManger", stuManger);
String currentPage1 = request.getParameter("currentPage")==null?"1":request.getParameter("currentPage");
String currentPageStr = new String(currentPage1.getBytes("ISO-8859-1"),"utf-8");

User user = (User) session.getAttribute(Parameter.LOGIN_USER);

pageContext.setAttribute("user", user);

int currentPage =Integer.parseInt(currentPageStr);//当前页面
pageContext.setAttribute("currentPage", currentPage);

String stu_nameStr =request.getParameter("stu_name");
String stu_professionStr = request.getParameter("stu_profession");
String stu_sexStr = request.getParameter("stu_sex");


String totalPageStr = request.getParameter("totalPage");
String countStr = request.getParameter("count");


String stu_nameBefore = stu_nameStr==null?"":stu_nameStr;
String stu_professionBefore = stu_professionStr==null?"":stu_professionStr;
String stu_sexBefore = stu_sexStr==null?"":stu_sexStr;

String stu_name = new String(stu_nameBefore.getBytes("ISO-8859-1"),"utf-8").trim();
pageContext.setAttribute("stu_name", stu_name);
String stu_profession = new String(stu_professionBefore.getBytes("ISO-8859-1"),"utf-8").trim();
pageContext.setAttribute("stu_profession", stu_profession);
String stu_sex = new String(stu_sexBefore.getBytes("ISO-8859-1"),"utf-8").trim();
pageContext.setAttribute("stu_sex", stu_sex);
int pageSize = 15;
int current = (currentPage-1)*pageSize; //根据当前页面算出分页记录开始的位置
int count = stuManger.queryCount(user.getClass_id(),stu_name,stu_profession, stu_sex);// 根据条件查询总 记录数
pageContext.setAttribute("count", count);
int totalPage= count%pageSize==0?count/pageSize:count/pageSize+1;//根据总记录数计算总页数
pageContext.setAttribute("totalPage", totalPage);



int groupSize = 3;//组的大小，表示每组可以放3页
int totalGroup = totalPage%groupSize==0?totalPage/groupSize:totalPage/groupSize+1;//总的组数


//计算当前页属于第几组
int  currentPageIsWhichGroup = currentPage%groupSize==0?currentPage/groupSize:currentPage/groupSize+1;
//计算当前页所在的组从那一页开始 
int startPage = (currentPageIsWhichGroup-1)*groupSize+1;
pageContext.setAttribute("startPage", startPage);
//就算当前页所在的组结束的位置      如果是最后一组，则结束的位置是总的页数
int endPage  =currentPageIsWhichGroup==totalGroup? totalPage: currentPageIsWhichGroup*groupSize;
pageContext.setAttribute("endPage", endPage);

List<Student> stuLists = stuManger.queryStuPageMySql(user.getClass_id(),stu_name,stu_profession,stu_sex,current,pageSize);
session.setAttribute("stuLists", stuLists);
pageContext.setAttribute("stuLists", stuLists);
%>

<body>
	<%
		List<Map<String,String>> listParam = new ArrayList<Map<String,String>>();
	   Map<String,String>  mapParam1 = new HashMap<String,String>();
	   mapParam1.put("url", "#");
	   mapParam1.put("name", "学员管理");
	   
	   Map<String,String>  mapParam2 = new HashMap<String,String>();
	   mapParam2.put("url", "stuinfomanager.jsp");
	   mapParam2.put("name","学员信息管理");
	   
	   
	   listParam.add(mapParam1);
	   listParam.add(mapParam2);
	   
	   JSONObject jo  = new JSONObject();
	   jo.put("list", listParam);
	   
	   pageContext.include("top_nav.jsp?param="+jo.toString());
	%>
	<nav class="navbar navbar-default" role="navigation"
		style="margin-left: 60px;margin-right: 60px; margin-top:10px">
	<div class="container-fluid">
		<div class="navbar-header">
			<span class="navbar-brand">筛选</span>
		</div>
		<div>
			<form class="navbar-form navbar-left" role="search"
				action="studentServlet?method=search" method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="请输入姓名"
						name="stu_name" value="${pageScope.stu_name}" /> <input
						type="text" class="form-control" placeholder="请输入专业"
						name="stu_profession" value="${pageScope.stu_profession }" /> <select
						class="form-control" name="stu_sex">
						 <c:if test="${pageScope.stu_sex eq ''}">
						     <c:set var = "sex1" value="selected='selected'" scope="page"></c:set>
						</c:if>
						<c:if test="${pageScope.stu_sex  eq '男' }">
						  <c:set var = "sex2" value="selected='selected'"></c:set>
						</c:if>
						<c:if test="${pageScope.stu_sex  eq '女'}">
							  <c:set var = "sex3" value="selected='selected'"></c:set>
						</c:if>
					    <option value=""  ${pageScope. sex1} >--请选择性别--</option>
			        	<option value="男"  ${pageScope.sex2}  >男</option>
			        	<option value="女" ${pageScope.sex3 }>女</option> 
					</select>
				</div>
				<button type="submit" class="btn btn-default">查询</button>
			</form>
		</div>

	</div>
	</nav>

	<nav class="navbar navbar-default" role="navigation"
		style="margin-left: 60px;margin-right: 60px; margin-top:10px">
	<div class="container-fluid">
		<div class="navbar-header">
			<span class="navbar-brand">添加</span>
		</div>
		<div>
			<form class="navbar-form navbar-left" role="search"
				action="addstu.jsp" method="post">
				<button type="submit" class="btn btn-default">添加学员</button>
			</form>
		</div>

	</div>
	</nav>


	<div id="content" style="margin-left: 60px; margin-right: 60px">
		<span>共有 <b>${pageScope.count}</b> 条记录
		</span>
		<table border="1" cellspacing="0" cellpadding="0"
			class="table table-striped table-hover table-bordered">
			<tr id="theTableTitle">
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>专业</th>
				<th>身份证</th>
				<th>电话</th>
				<th>生日</th>
				<th>QQ</th>
				<th>所属班级</th>
				<th>操作</th>
			</tr>
		  	<c:forEach items="${pageScope.stuLists }" var="stu">
					  <tr>
						<td>${pageScope.stu.getStu_no()}</td>
						<td>${pageScope.stu.getStu_name() }</td>
						<td>${pageScope.stu.getStu_sex() }</td>
						<td>${pageScope.stu.getStu_profession() }</td>
						<td>${pageScope.stu.getStu_icno() }</td>
						<td>${pageScope.stu.getStu_phone() }</td>
						<td>${pageScope.stu.getStu_birth() }</td>
						<td>${pageScope.stu.getStu_qq() }</td>
						<td>${pageScope.stu.getClass_name() }</td>
						<td><a href="javascript:onDelete(${pageScope.stu.getStu_id() }) ">[删除]</a>
							<a href="updatestu.jsp?stu_id=${pageScope.stu.getStu_id() }">[修改]</a></td>
					</tr>
			</c:forEach>
		</table>
	</div>
	<div class="footer" style="margin-left: 60px; margin-right: 60px">
		<ul class="pagination pagination myul">
			<!--class='disabled'  禁用样式-->
			<li><a
				href="stuinfomanager.jsp?currentPage=1&stu_name=${pageScope.stu_name }&stu_profession=${pageScope.stu_profession }&stu_sex=${pageScope.stu_sex}">&laquo;首页</a></li>
			<c:if test="${pageScope.currentPage ==1}">
			    <li class='disabled'><a>上一页</a></li>
			</c:if>
			<c:if test="${pageScope.currentPage !=1}">
			
			<li><a
				href="stuinfomanager.jsp?currentPage=${pageScope.currentPage-1}&stu_name=${pageScope.stu_name }&stu_profession=${pageScope.stu_profession }&stu_sex=${pageScope.stu_sex}">上一页</a></li>
			</c:if>
			
			<c:forEach  var="pageCurrent" varStatus="status" begin="${pageScope.startPage }" end="${pageScope.endPage }">
			   <c:set var="isCurrentPage" value=""/>
			   <c:if test="${pageScope.pageCurrent==pageScope.currentPage }">
			      <c:set var="isCurrentPage" value="class='active'"/>
			   </c:if>
			   <li ${pageScope. isCurrentPage}><a
				   href='stuinfomanager.jsp?currentPage=${pageScope.pageCurrent } '>${pageScope.pageCurrent } </a></li>
			</c:forEach>
		 
			<c:if test="${pageScope.currentPage ==pageScope.totalPage}">
			    <li class='disabled'><a>下一页 </a></li>
			</c:if>
			<c:if test="${pageScope.currentPage !=pageScope.totalPage}">
			
					<li><a
						href="stuinfomanager.jsp?currentPage=${pageScope.currentPage+1 }&stu_name=${pageScope.stu_name }&stu_profession=${paegScope.stu_profession }&stu_sex=${paegScope.stu_sex}">下一页
					</a></li>
			</c:if>
			<li class=''><a
				href="stuinfomanager.jsp?currentPage=${pageScope.totalPage }&stu_name=${pageScope.stu_name }&stu_profession=${pageScope.stu_profession }&stu_sex=${pageScope.stu_sex}">末页
			</a></li>
		</ul>
	</div>

	<script type="text/javascript">
		function onDelete(stu_id) {
			if (confirm("确定要删除该学生吗？")) {
				window.location.href = "studentServlet?method=delete&stu_id="
						+ stu_id;
			}

		}
	</script>
</body>
</html>
