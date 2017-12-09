<%@page import="java.util.List"%>
<%@page import="cn.yunhe.entity.Record"%>
<%@page import="cn.yunhe.biz.impl.RecordManagerImpl"%>
<%@page import="cn.yunhe.biz.RecordManager"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

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

</head>
<%

request.setCharacterEncoding("utf-8");
RecordManager recordManager = new RecordManagerImpl();
String record_idStr = request.getParameter("record_id");
int recore_id = Integer.parseInt(record_idStr);
pageContext.setAttribute("recore_id", recore_id);
Record record = recordManager.queryById(recore_id);
pageContext.setAttribute("record", record);

%>
<body>
  <%
   
   List<Map<String,String>> listParam = new ArrayList<Map<String,String>>();
   Map<String,String>  mapParam1 = new HashMap<String,String>();
   mapParam1.put("url", "#");
   mapParam1.put("name", "打卡管理");
   
   Map<String,String>  mapParam2 = new HashMap<String,String>();
   mapParam2.put("url", "checkingmanager.jsp");
   mapParam2.put("name", "打卡信息管理");
   
   Map<String,String>  mapParam3 = new HashMap<String,String>();
   mapParam3.put("url", "#");
   mapParam3.put("name", "修改打卡记录");
   
   
   listParam.add(mapParam1);
   listParam.add(mapParam2);
   listParam.add(mapParam3);
   
   JSONObject jo  = new JSONObject();
   jo.put("list", listParam);
   
   pageContext.include("top_nav.jsp?param="+jo.toString());
   
%>
	<div class="box" style="width: 80%">
		<form action="recordServlet?method=update" class="form-horizontal" role="form" method="post"  onsubmit="return onRevify()">
		    <input type="hidden" name ="record_id" value="${pageScope.recore_id }"/>
			<div class="form-group">
				<label for="user_id" class="col-sm-2 control-label">日期:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="date" id="user_id"
						value="${pageScope.record.getDate() }" disabled="disabled" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">姓名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name" id="username"
						value="${pageScope.record.getStu_name() }" disabled="disabled" />
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">上班打卡时间:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="start_time" id="start_time"
						value="${pageScope.record.getStart_time() }" />
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">下班打卡时间:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="end_time" id="end_time"
						value="${pageScope.record.getEnd_time() }" />
				</div>
			</div>
			<div class="form-group">
				<label for="question" class="col-sm-2 control-label">审核状态：</label>
				<div class="col-sm-10">
					<select name="status" class="textinput" id="status" style="width: 180px; height: 30px">
					<c:set var="selectZc" value=""/>
					<c:set var="selectYc" value=""/>
					<c:set var="selectDcl" value=""/>
					
					<c:if test="${pageScope.record.getStatus()==1 }">
					   	<c:set var="selectZc" value="selected='selected'"/>
					</c:if>
					<c:if test="${pageScope.record.getStatus()==-1 }">
					   	<c:set var="selectYc" value="selected='selected'"/>
					</c:if>
					
					<c:if test="${pageScope.record.getStatus()==0 }">
					   	<c:set var="selectDcl" value="selected='selected'"/>
					</c:if>
						<option value="1" ${ pageScope.selectZc }>正常</option>
						<option value="-1"  ${pageScope.selectYc }>异常</option>
						<option value="0"  ${pageScope. selectDcl }>待处理</option>
					</select>
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
</script>

	<script type="text/javascript">
	
	function onRevify() {
		var start_time = $("#start_time").val();
		var end_time = $("#end_time").val();
		 
		if(start_time==""){
			show_err_msg("请输入上班打卡时间");
			return false;
		}else if(end_time==""){
			show_err_msg("请输入下班打卡时间");
			return false;
		}else {
			return true;
		}
		
	}
	
	</script>


</body>
</html>
