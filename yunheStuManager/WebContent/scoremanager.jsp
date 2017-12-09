<%@page import="cn.yunhe.util.Parameter"%>
<%@page import="cn.yunhe.entity.User"%>
<%@page import="cn.yunhe.biz.impl.ScoreManagerImpl"%>
<%@page import="cn.yunhe.biz.ScoreManager"%>
<%@page import="cn.yunhe.entity.Student"%>
<%@page import="cn.yunhe.biz.impl.StudentManagerImpl"%>
<%@page import="cn.yunhe.biz.StudentManager"%>
<%@page import="cn.yunhe.entity.Type"%>
<%@page import="cn.yunhe.entity.Score"%>
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

<!-- 日期选择器 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js" ></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css" />
<!------------>

<!-- 提示框 -->
<script type="text/javascript" src="js/tooltips.js"></script>
<!-- 提示框 -->

<title>积分信息管理</title>

<script type="text/javascript">
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  $(function() {
	$( "#datepicker2" ).datepicker();
  });
  
</script>

</head>

<body>
   <%
   

   List<Map<String,String>> listParam = new ArrayList<Map<String,String>>();
   Map<String,String>  mapParam1 = new HashMap<String,String>();
   mapParam1.put("url", "#");
   mapParam1.put("name", "分数管理");
   
   Map<String,String>  mapParam2 = new HashMap<String,String>();
   mapParam2.put("url", "checkingmanager.jsp");
   mapParam2.put("name", "分数信息信息管理");
   
   
   listParam.add(mapParam1);
   listParam.add(mapParam2);
   
   JSONObject jo  = new JSONObject();
   jo.put("list", listParam);
   
   pageContext.include("top_nav.jsp?param="+jo.toString());
   
%>
 

<nav class="navbar navbar-default" role="navigation" style="margin-left: 60px;margin-right: 60px; margin-top:10px">
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">筛选</span>
		</div>
		
		<%
		User user = (User) session.getAttribute(Parameter.LOGIN_USER);
		String currentPageStr = request.getParameter("currentPage");
		int currentPage  = currentPageStr==null?1:Integer.parseInt(currentPageStr);
		pageContext.setAttribute("currentPage", currentPage);
		request.setCharacterEncoding("utf-8");
		
	    ScoreManager scoreManager = new ScoreManagerImpl();
		StudentManager stuManager = new StudentManagerImpl();
		
		
		String typeStr = request.getParameter("type_id");
	    int type = typeStr==null||typeStr==""?-1:Integer.parseInt(typeStr);
	    pageContext.setAttribute("type", type);
	    String timeStr = request.getParameter("time");
		String time=timeStr==null?"":timeStr;
		pageContext.setAttribute("time", time);
		String stu_idStr = request.getParameter("stu_id");
		int stu_id =stu_idStr==null||stu_idStr==""?-1:Integer.parseInt(stu_idStr) ;
		pageContext.setAttribute("stu_id", stu_id);
		int pageSize = 15;
		int current=(currentPage-1)*pageSize;
	    
		int total = scoreManager.queryCount(user.getClass_id(),type, time, stu_id);//多条件查询记录数
		pageContext.setAttribute("total", total);
		int totalPage = total%pageSize==0?total/pageSize:total/pageSize+1;
		pageContext.setAttribute("totalPage", totalPage);
		List<Score> listScore  =  scoreManager.queryScorePageMySql(user.getClass_id(),type, time, stu_id,current, pageSize);
		pageContext.setAttribute("listScore", listScore);
		
		
		
		int groupSize = 3;//组数的大小，标示每组可以有几页，此处表示每组有三页；
		int totalGroup = totalPage%groupSize==0?totalPage/groupSize:totalPage/groupSize+1;//总的组数
		System.out.println("totalGroup is" +totalGroup);
		
		int  currentIsWhichGroup =currentPage% groupSize==0?currentPage/groupSize:currentPage/groupSize+1;//当前页属于第几组
		int startPage = (currentIsWhichGroup-1)*groupSize+1;//当前组开始的页数
		pageContext.setAttribute("startPage", startPage);
		int endPage  =currentIsWhichGroup == totalGroup? totalPage: currentIsWhichGroup*groupSize;//当前页所在的组结束的位置，如果是最后一组，则结束的位置是最后一页
		
		pageContext.setAttribute("endPage", endPage);
		
		 List<Type> listTypes =  scoreManager.queryType();
		 pageContext.setAttribute("listTypes", listTypes);
		 List<Student> listStu =   stuManager.queryAllstu();
		 pageContext.setAttribute("listStu", listStu);
		%>
		<div>
			<form class="navbar-form navbar-left" role="search" action="scoreServlet?method=search" method="post" >
				<select class="form-control" name="type_id">
				 <option value="" selected="selected">--请选择类型--</option>
				 <c:forEach items="${pageScope.listTypes}" var="aType">
				    <c:set var="select" value=""/>
				    <c:if test="${pageScope.type==aType.type_id}">
				        <c:set var="select" value="selected='selected'"/>
				    </c:if>
				   <option  ${pageScope.select}  value="${pageScope.aType.type_id}">${pageScope.aType.type_value }</option>
				 </c:forEach>
			    </select>
				<div class="form-group">
					<input type="text" id="datepicker" name="time" class="form-control" placeholder="请选择日期" value="${pageScope.time }" />
				</div>
				<select class="form-control" name="stu_id">
			      <option value="">--请选择姓名--</option>
			      <c:forEach items="${pageScope.listStu }" var="stu">
			           <c:set var="selectStu" value=""/>
			          <c:if test="${pageScope.stu_id==pageScope.stu.stu_id}">
				        <c:set var="selectStu" value="selected='selected'"/>
				    </c:if>
			        <option ${pageScope.selectStu } value="${pageScope.stu.getStu_id() }">${pageScope.stu.getStu_name() }</option>
			      </c:forEach>
			    </select>
				<button type="submit" class="btn btn-default">查询</button>
			</form>
		</div>
		
	</div>
</nav>


<nav class="navbar navbar-default" role="navigation" style="margin-left: 60px;margin-right: 60px">
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">添加</span>
		</div>
		<div>
			<form class="navbar-form navbar-left" role="search" method="post" action="scoreServlet?method=add" onsubmit="return regVertify(); ">
				<select class="form-control" name="i_type_id" id="i_type_id">
			      <option value="" selected="selected">--请选择类型--</option>
			      <c:forEach items="${pageScope.listTypes }" var="typeAdd">
			            <option value="${pageScope.typeAdd.getType_id() }">${pageScope.typeAdd.getType_value() }</option>
			      </c:forEach>
			    </select>
				<div class="form-group">
					<input type="text" id="datepicker2" class="form-control" placeholder="请选择日期" name="i_time" id="i_time"/>
				</div>
				<select class="form-control" name="i_stu_id" id="i_stu_id">
			      <option value="" selected="selected">--请选择姓名--</option>
			      
			      <c:forEach items="${pageScope. listStu}" var="stuAdd">
			            <option value="${pageScope.stuAdd.stu_id }">${pageScope.stuAdd.stu_name }</option>
			      </c:forEach>
			      
			    </select>
			    <input type="text" class="form-control" placeholder="请输入描述" name="i_score_contents" id="i_score_contents" />
			    <input type="text" class="form-control" placeholder="请输入分值" name="i_score_value" id="i_score_value" />
				<button type="submit" class="btn btn-default" >添加</button>
			</form>
		</div>
	</div>
</nav>

<div id="content" style="margin-left: 60px;margin-right: 60px">
    <span>共有 <b>${pageScope.total}</b> 条记录</span>
    <table border="1" cellspacing="0" cellpadding="0" class="table table-striped table-hover table-bordered">
        <tr id="theTableTitle">
       		<th>类型</th>
            <th>日期</th>
            <th>姓名</th>
            <th>描述</th>
            <th>分值</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pageScope.listScore }" var="score">
           <tr>
        	<td>${pageScope.score.getType_value() }</td>
            <td>${pageScope.score.getTime()}</td>
            <td>${pageScope.score.getStu_name() }</td>
            <td>${pageScope.score.getScore_contents() }</td>
            <c:if test="${pageScope.score.getScore_values()>0 }">
               <td>+${pageScope.score.getScore_values()}</td>
            </c:if>
             <c:if test="${pageScope.score.getScore_values()<=0 }">
               <td>${pageScope.score.getScore_values() }</td>
            </c:if>
            <td>
            	<a href="javascript:onDelete(${pageScope.score.getScore_id()})">[删除]</a>
                <a href="updatescore.jsp?score_id=${pageScope.score.getScore_id()}">[修改]</a>
            </td>
        </tr>
        
        </c:forEach>
    </table>
</div>

<div class="footer" style="margin-left: 60px;margin-right: 60px">
    <ul class="pagination pagination myul">
    <!-- class='disabled' -->
   
		<li ><a href='scoremanager.jsp?currentPage=1&type_id=${pageScope.type }&time=${pageScopre.time }&stu_id=${pageScope.stu_id}'>&laquo;首页</a></li>
		 
		<c:if test="${pageScope.currentPage==1 }">
		   	<li class='disabled' ><a>上一页</a></li>
		</c:if>
	    <c:if test="${pageScope.currentPage!=1 }">
		    <li ><a href='scoremanager.jsp?currentPage=${pageScope.currentPage-1 }&type_id=${pageScope.type }&time=${ pageScope.time }&stu_id=${pageScope.stu_id}'>上一页</a></li>
		</c:if>
		
		<c:forEach  var="pageCurrent" varStatus="status" begin="${pageScope.startPage }" end="${pageScope.endPage }">
			   <c:set var="isCurrentPage" value=""/>
			   <c:if test="${pageScope.pageCurrent==pageScope.currentPage }">
			      <c:set var="isCurrentPage" value="class='active'"/>
			   </c:if>
			   <li ${pageScope. isCurrentPage}><a
				   href='scoremanager.jsp?currentPage=${pageScope.pageCurrent } '>${pageScope.pageCurrent } </a></li>
		</c:forEach>
		 
		 <c:if test="${pageScope.currentPage==pageScope.totalPage }">
		    	<li class='disabled'><a >下一页 </a></li>
		 </c:if>
		 
		 <c:if test="${pageScope.currentPage!=pageScope.totalPage }">
		    	<li ><a href='scoremanager.jsp?currentPage=${pageScope.currentPage+1 }&type_id=${pageScope.type }&time=${pageScope.time }&stu_id=${pageScope.stu_id}'>下一页 </a></li>
		 </c:if>
		<li class=''><a href='scoremanager.jsp?currentPage=${pageScope.totalPage }&type_id=${pageScope.type }&time=${pageScope.time }&stu_id=${pageScope.stu_id}'>末页 </a></li>
        
    </ul>
</div>
<script type="text/javascript">
  function onDelete(score_id){
	  if(confirm("确定要删除该记录吗？")){
		  window.location.href = "scoreServlet?method=delete&score_id="+score_id;
	  }
  }
</script>

<script type="text/javascript">
	
	function regVertify() {
		var score_type = $("#i_type_id").val();
		var time = $("#datepicker2").val();
		var stu_name = $("#i_stu_id").val();
		var score_contents = $("#i_score_contents").val();
		var score_value = $("#i_score_value").val();
		if(score_type==""){
			show_err_msg("请选择分数类型");
			return false;
		}else if(time==""){
			show_err_msg("请选择日期");
			return false;
		}else if(stu_name==""){
			show_err_msg("请选择学生姓名");
			return false;
		}else if(score_contents==""){
			show_err_msg("请输入分数描述");
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
 