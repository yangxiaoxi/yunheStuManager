<%@page import="cn.yunhe.biz.impl.ScoreManagerImpl"%>
<%@page import="cn.yunhe.biz.ScoreManager"%>
<%@page import="cn.yunhe.entity.Score"%>
<%@page import="cn.yunhe.entity.Student"%>
<%@page import="cn.yunhe.biz.impl.StudentManagerImpl"%>
<%@page import="cn.yunhe.biz.StudentManager"%>
<%@page import="cn.yunhe.entity.Type"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 日期选择器 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js" ></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css" />
<!------------>

<!-- 提示框 -->
<script type="text/javascript" src="js/tooltips.js"></script>
<!-- 提示框 -->

<title>管理区域</title>
<style type="text/css">
.box {
	width: 44%;
	margin: 50px auto;
	padding: 20px;
	padding-left: 50px;
	border: 1px #ccc dashed;
}
</style>

<script  type="text/javascript">
	$(function() {
    	$( "#datepicker" ).datepicker();
  	});

</script>

</head>
<%

  ScoreManager scoreManager = new ScoreManagerImpl();
   StudentManager stuManager = new StudentManagerImpl();
  List<Type> listTypes =  scoreManager.queryType();
  pageContext.setAttribute("listTypes", listTypes);
  String score_idStr = request.getParameter("score_id");
  int score_id = Integer.parseInt(score_idStr);
  pageContext.setAttribute("score_id", score_id);
  Score score = scoreManager.queryById(score_id);
  pageContext.setAttribute("score", score);
  List<Student> listStus =  stuManager.queryAllstu();
  pageContext.setAttribute("listStus", listStus);
  
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


	<div class="box" style="width: 60%">
		<form action="scoreServlet?method=update" class="form-horizontal" role="form" method="post" onsubmit="return onRevify()">
		<input  type="hidden" name = "score_id" value="${pageScope.score_id }"/>
			<div class="form-group">
				<label for="question" class="col-sm-2 control-label">类型：</label>
				<div class="col-sm-10">
					<select name="status" class="textinput" id="type_id" style="width: 180px; height: 30px">
					
						
						
						<c:forEach items="${pageScope.listTypes }" var="scoreType">
							<c:set  var="select" value=""/>
							<c:if test="${pageScope.score.score_type == pageScope.scoreType.getType_id()}">
							   <c:set  var="select" value="selected='selected'"/>
							</c:if>
							    <option  ${ pageScope.select} value="${pageScope.scoreType.getType_id()}">${pageScope.scoreType.getType_value()}</option>
						</c:forEach>
						 
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="user_id" class="col-sm-2 control-label">日期:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="datepicker" name="datepicker" value="<%=score.getTime() %>" />
				</div>
			</div>
			<div class="form-group">
				<label for="question" class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-10">
					<select class="form-control" name="stu_id" id="stu_id">
					 <option value="${pageScope.score.getStu_id() }" selected="selected">${pageScope.score.getStu_name() }</option>
					
			      		  <c:forEach items="${pageScope.listStus }" var="stu">
			      		  
			      		      <option value="${pageScope.stu.getStu_id() }">${pageScope.stu.getStu_name() }</option>
			      		  </c:forEach>
			    </select>
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">描述:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="score_contents" id="score_contents" value="<%=score.getScore_contents() %>" />
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">分值:</label>
				<div class="col-sm-10">
				    <c:set  var="scoreValue" value=""/>
				    <c:if test="${pageScope.score.getScore_values()>0 }">
				        <c:set  var="scoreValue" value="+${pageScope.score.getScore_values()}"/>
				    </c:if>
				    <c:if test="${pageScope.score.getScore_values()<=0 }">
				        <c:set  var="scoreValue" value="${pageScope.score.getScore_values()}"/>
				    </c:if>
				  
					<input type="text" class="form-control" name="score_value" id="score_value" value="${pageScope.scoreValue }" />
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
	function cancel(){
		window.history.go(-1);
	}
</script>

<script type="text/javascript">
	
	function onRevify() {
		var datepicker = $("#datepicker").val();
		var stu_id = $("#stu_id").val();
		var score_contents = $("#score_contents").val();
		var score_value = $("#score_value").val();
		 
		if(datepicker==""){
			show_err_msg("请选择日期");
			return false;
		}else if(stu_id==""){
			show_err_msg("请选择学生");
			return false;
		}else if(score_contents==""){
			show_err_msg("请输入描述");
			return false;
		}else if(score_value==""){
			show_err_msg("请输入分值");
			return false;
		}else {
			return true;
		}
		
	}
	
	</script>


</body>
</html>
