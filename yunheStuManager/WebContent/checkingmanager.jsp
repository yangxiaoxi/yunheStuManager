<%@page import="cn.yunhe.util.Parameter"%>
<%@page import="cn.yunhe.entity.User"%>
<%@page import="cn.yunhe.entity.Student"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="cn.yunhe.biz.impl.StudentManagerImpl"%>
<%@page import="cn.yunhe.biz.StudentManager"%>
<%@page import="cn.yunhe.biz.impl.RecordManagerImpl"%>
<%@page import="cn.yunhe.biz.RecordManager"%>
<%@page import="cn.yunhe.entity.Record"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/bootstrap.min.css" />

<!-- 下拉列表（小箭头） -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 下拉列表（小箭头） -->

<!-- 日期选择器 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css" />
<!------------>

<!-- 提示框 -->
<script type="text/javascript" src="js/tooltips.js"></script>
<!-- 提示框 -->


<title>管理区域</title>
<style type="text/css">
        .my{
            padding: 16px;
        }
</style>

<script type="text/javascript">
  /**日期选择器**/
  $(function() {
    $( "#datepicker" ).datepicker();//设置日期的展示格式
  });
  
  
  /**审核全选、全不选**/
  $(function() {
	  $("#all_ck").change(function(){
		  if ($(this).is(':checked')) {//全选
			  $("[name=ck]:checkbox").each(function(){
				  this.checked=true;
			  });
		  }else{//全不选
			  $("[name=ck]:checkbox").each(function(){
				  this.checked=false;
			  });
		  } 
		});
	});
  
</script>
</head>
<body>
 <%
   

   List<Map<String,String>> listParam = new ArrayList<Map<String,String>>();
   Map<String,String>  mapParam1 = new HashMap<String,String>();
   mapParam1.put("url", "#");
   mapParam1.put("name", "打卡管理");
   
   Map<String,String>  mapParam2 = new HashMap<String,String>();
   mapParam2.put("url", "checkingmanager.jsp");
   mapParam2.put("name", "打卡信息管理");
   
   
   listParam.add(mapParam1);
   listParam.add(mapParam2);
   
   JSONObject jo  = new JSONObject();
   jo.put("list", listParam);
   
   pageContext.include("top_nav.jsp?param="+jo.toString());
   
%>

<nav class="navbar navbar-default" role="navigation" style="margin-left: 60px;margin-right: 60px; margin-top:10px" >
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">筛选</span>
		</div>
		<%
				request.setCharacterEncoding("utf-8");
				String currentPageStr = request.getParameter("currentPage");
				User user = (User) session.getAttribute(Parameter.LOGIN_USER);
				int currentPage = currentPageStr==null?1:Integer.parseInt(currentPageStr);
				pageContext.setAttribute("currentPage", currentPage);
				RecordManager recordManager = new RecordManagerImpl();
				StudentManager stuManager = new StudentManagerImpl();
				List<Student> stuList =  stuManager.queryAllstu();
				pageContext.setAttribute("stuList", stuList);
				
				String dateStr = request.getParameter("date");
				String date = dateStr==null||"".equals(dateStr)?"":dateStr;
				pageContext.setAttribute("date", date);
				String stu_idStr = request.getParameter("stu_id");
				int stu_id=stu_idStr==null||"".equals(stu_idStr)?-1:Integer.parseInt(stu_idStr);
				pageContext.setAttribute("stu_id", stu_id);
				String statusStrFirst = request.getParameter("status");
				int realStatus =statusStrFirst==null?5:Integer.parseInt(statusStrFirst);
				pageContext.setAttribute("realStatus", realStatus);
				int pageSize=15;
				int current =(currentPage-1) *pageSize;
				int count = recordManager.queryCount(user.getClass_id(),date, stu_id,realStatus);
				pageContext.setAttribute("count", count);
				int totalPage = count%pageSize==0? count/pageSize: count/pageSize+1;
				pageContext.setAttribute("totalPage", totalPage);
				List<Record>  recordList =  recordManager.queryRecordPageMySql(user.getClass_id(),date,stu_id,realStatus,current, pageSize);
				pageContext.setAttribute("recordList", recordList);
				int groupSize = 3;//每组可以放几页
				int totalGroup=totalPage%groupSize==0?totalPage/groupSize:totalPage/groupSize+1;//总的组数
				
				
				int currentPageIsWhichGroup = currentPage%groupSize==0?currentPage/groupSize:currentPage/groupSize+1;//当前页属于第几组
				
				int startPage = (currentPageIsWhichGroup-1)*groupSize+1; //当前页所在的组的开始页号
				pageContext.setAttribute("startPage", startPage);
				int endPage = currentPageIsWhichGroup==totalGroup? totalPage:currentPageIsWhichGroup*groupSize;//当前页所在组的结束的页号，如果是最后一组，结束的位置是最后一页
				pageContext.setAttribute("endPage", endPage);
				%>
		<div>
			<form class="navbar-form navbar-left" role="search"  action="recordServlet?method=search" method="post">
				<div class="form-group">
					<input type="text" id="datepicker" name="date" class="form-control" placeholder="请选择日期" value="<%=date %>" />
				</div>
				<select class="form-control" name="stu_id">
				
			      <option value="" selected="selected">--请选择姓名--</option>
			       <c:forEach items="${pageScope.stuList }" var="stu"> 
			           <c:set var="selectStu" value=""/>
			           <c:if test="${pageScope.stu_id==pageScope.stu.stu_id}">
				        <c:set var="selectStu" value="selected='selected'"/>
				       </c:if>
				       <option  ${pageScope.selectStu } value="${pageScope.stu.getStu_id() }">${pageScope.stu.getStu_name()  }</option>
			       </c:forEach>
			    </select>
				<button type="submit" class="btn btn-default">查询</button>
			</form>
		</div>
		
		<div>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
				    <c:set var="state" value="状态"/>
				    <c:if test="${pageScope.realStatus==1 }">
				       <c:set var="state" value="正常"/>
				    </c:if>
				    
				    <c:if test="${pageScope.realStatus==-1 }">
				       <c:set var="state" value="异常"/>
				    </c:if>
				    <c:if test="${pageScope.realStatus==0 }">
				        <c:set var="state" value="待处理"/>
				    </c:if>
				    
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						${pageScope.state }
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="recordServlet?method=search&date=${pageScope.date  }&stu_id=${pageScope.stu_id }&status=1">正常</a></li>
						<li class="divider"></li>
						<li><a href="recordServlet?method=search&date=${pageScope.date  }&stu_id=${pageScope.stu_id }&status=-1">异常</a></li>
						<li class="divider"></li>
						<li><a href="recordServlet?method=search&date=${pageScope.date  }&stu_id=${pageScope.stu_id }&status=0">待处理</a></li>
					</ul>
				</li>
			</ul>
		</div>
		
	</div>
</nav>

<nav class="navbar navbar-default" role="navigation" style="margin-left: 60px;margin-right: 60px">
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">审核</span>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						标记为 
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="javascript: onChecked(1)">正常</a></li>
						<li class="divider"></li>
						<li><a href="javascript: onChecked(-1)">异常</a></li>
						<li class="divider"></li>
						<li><a href="javascript: onChecked(0)">待处理</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>

<div id="content" style="margin-left: 60px;margin-right: 60px">
    <span>共有 <b>${pageScope.count }</b> 条记录</span>
    <table border="1" cellspacing="0" cellpadding="0" class="table table-striped table-hover table-bordered">
        <tr id="theTableTitle">
       		<th><input type="checkbox" id="all_ck"  />审核
       		</th>
            <th>日期</th>
            <th>姓名</th>
            <th>上班打卡时间</th>
            <th>下班打卡时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pageScope.recordList }" var="record">
        
		          <tr>
		        	<td><input type="checkbox" id="ck" name="ck" value="${pageScope.record.getRecord_id() }" /></td>
		            <td>${pageScope.record.getDate() }</td>
		            <td>${pageScope.record.getStu_name() }</td>
		            <td>${pageScope.record.getStart_time() }</td>
		            <td>${pageScope.record.getEnd_time() }</td>
		            <td>
			               <c:set var="statusStr" value=""/>
			               <c:set var="color" value=""/>
			               
			               <c:if test="${ pageScope.record.getStatus()==0}">
					           <c:set var="statusStr" value="待处理"/>
					           <c:set var="color" value="color='black'"/>
					       </c:if>
					       <c:if test="${ pageScope.record.getStatus()==-1}">
					           <c:set var="statusStr" value="异常"/>
					           <c:set var="color" value="color='red'"/>
					       </c:if>
					       <c:if test="${pageScope.record.getStatus()==1}">
					           <c:set var="statusStr" value="正常"/>
					           <c:set var="color" value= "color='#00FF00'"/>
					       </c:if>
			               
			            	<font ${color }>${statusStr }</font>
					</td>
		            <td>
		            	<a href="javascript:onDelete(${pageScope.record.getRecord_id() })">[删除]</a>
		                <a href="updaterecord.jsp?record_id=${pageScope.record.getRecord_id() }">
		                	[修改]
		                </a>
		            </td>
		        </tr>
        </c:forEach>
    </table>
</div>

<div class="footer" style="margin-left: 60px;margin-right: 60px">
    <ul class="pagination pagination myul">
    
        <li ><a  href="checkingmanager.jsp?currentPage=1&date=${pageScope.date }&stu_id=${pageScope.stu_id }&status=${pageScope.realStatus}">&laquo;首页</a></li>
        
        <c:if test="${pageScope. currentPage==1}">
         <li class='disabled'><a>上一页</a></li>
        </c:if>
		 <c:if test="${pageScope. currentPage!=1}">
           <li ><a href="checkingmanager.jsp?currentPage=${pageScope.currentPage-1 }&date=${pageScope.date }&stu_id=${pageScope.stu_id }&status=${pageScope.realStatus}">上一页</a></li>
        </c:if>
			<c:forEach  var="pageCurrent" varStatus="status" begin="${pageScope.startPage }" end="${pageScope.endPage }">
			   <c:set var="isCurrentPage" value=""/>
			   <c:if test="${pageScope.pageCurrent==pageScope.currentPage }">
			      <c:set var="isCurrentPage" value="class='active'"/>
			   </c:if>
			   <li ${pageScope. isCurrentPage}><a
				   href='checkingmanager.jsp?currentPage=${pageScope.pageCurrent} '>${pageScope.pageCurrent } </a></li>
			</c:forEach>
		
		<c:if test="${pageScope. currentPage==pageScope.totalPage}">
           <li class='disabled'><a >下一页 </a></li>
        </c:if>
		 <c:if test="${pageScope. currentPage!=pageScope.totalPage}">
           <li ><a href="checkingmanager.jsp?currentPage=${pageScope.currentPage+1 }&date=${pageScope.date }&stu_id=${pageScope.stu_id }&status=${pageScope.realStatus}">下一页 </a></li>
        </c:if>
		<li class=''><a href="checkingmanager.jsp?currentPage=${pageScope.totalPage }&date=${pageScope.date }&stu_id=${pageScope.stu_id }&status=${pageScope.realStatus}">末页 </a></li>
        
        
    </ul>
</div>
<script type="text/javascript">
  function onDelete(record_id){
	  if(confirm("确定要删除该条打卡记录吗？")){
		  window.location.href = "recordServlet?method=delete&record_id="+record_id;
	  }
  }
</script>

<script type="text/javascript">
  function onChecked(update_status){
	 var $record_id = $("input[type='checkbox']:checked");
	 if($record_id.length==0){
		 show_err_msg("请选择要审核的记录");
	 }else{
		var  record_ids="";
		for(i=0;i<$record_id.length;i++){
			if(i==$record_id.length-1){
			 record_ids =record_ids+$record_id[i].value;
			}else{
				record_ids =record_ids+$record_id[i].value+",";
			}
		 }
		 window.location.href="recordServlet?method=updateStatus"+"&update_status="+update_status+"&record_ids="+record_ids;
	 }
  }
</script>
</body>
</html>