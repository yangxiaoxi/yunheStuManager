<%@page import="cn.yunhe.biz.impl.ClazzManagerImpl"%>
<%@page import="cn.yunhe.biz.ClazzManager"%>
<%@page import="cn.yunhe.entity.Clazz"%>
<%@page import="java.util.List"%>
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
<title>班级信息管理</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<!-- 提示框 -->
<script type="text/javascript" src="js/tooltips.js"></script>
<!-- 提示框 -->

</head>

<body>
  <%
   

   List<Map<String,String>> listParam = new ArrayList<Map<String,String>>();
   Map<String,String>  mapParam1 = new HashMap<String,String>();
   mapParam1.put("url", "#");
   mapParam1.put("name", "班级管理");
   
   Map<String,String>  mapParam2 = new HashMap<String,String>();
   mapParam2.put("url", "checkingmanager.jsp");
   mapParam2.put("name", "班级信息管理");
   
   
   listParam.add(mapParam1);
   listParam.add(mapParam2);
   
   JSONObject jo  = new JSONObject();
   jo.put("list", listParam);
   
   pageContext.include("top_nav.jsp?param="+jo.toString());
   
%>
 

<nav class="navbar navbar-default" role="navigation" style="margin-left: 60px;margin-right: 60px; margin-top:10px" >
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">添加</span>
		</div>
		<div>
			<form class="navbar-form navbar-left" role="search"  action="classServlet?method=add" method="post" onsubmit="return onRevify()">
			    <input type="text" class="form-control" placeholder="请输入班级名称" name="class_name" id = "class_name"/>
				<button type="submit" class="btn btn-default">添加班级</button>
			</form>
		</div>
		
	</div>
</nav>
<%

ClazzManager clazzManager = new ClazzManagerImpl();
List<Clazz> classlists =   clazzManager.query();
pageContext.setAttribute("classlists", classlists);
int count = clazzManager.queryCount();
pageContext.setAttribute("count", count);
String currentPageStr = request.getParameter("currentPage");
int currentPage = currentPageStr==null?1:Integer.parseInt(currentPageStr);
pageContext.setAttribute("currentPage", currentPage);
int pageSize = 15;
int totalPage = count%pageSize==0?count/pageSize:count/pageSize+1;
pageContext.setAttribute("totalPage", totalPage);
int groupSize = 3;//组的大小
int totalGroup = totalPage%groupSize==0?totalPage/groupSize:totalPage/groupSize+1;//总的组数


int currentPageIsWhichGroup = currentPage%groupSize==0?currentPage/groupSize:currentPage/groupSize+1;//计算当前页属于哪一组

int startPage = (currentPageIsWhichGroup-1)*groupSize+1;//当前页所在的组的开始的页号
pageContext.setAttribute("startPage", startPage);

int endPage = currentPageIsWhichGroup==totalGroup?totalPage:currentPageIsWhichGroup*groupSize;//当前页所在的组结束的页号，如果是最后一组，则结束位置是最后一页
pageContext.setAttribute("endPage", endPage);

%>
<div id="content" style="margin-left: 60px;margin-right: 60px">
    <span>共有 <b>${pageScope.count}</b> 条记录</span>
    <table border="1" cellspacing="0" cellpadding="0" class="table table-striped table-hover table-bordered">
        <tr id="theTableTitle">
            <th>班级ID</th>
            <th>班级名称</th>
            <th>操作</th>
        </tr>
       <c:forEach items="${pageScope.classlists}" var="clazz">
         <tr>
            <td>${pageScope.clazz.getClazz_id() }</td>
            <td>${pageScope.clazz.getClazz_name() }</td>
            <td>
            	<a href="javascript:onDelete(${pageScope.clazz.getClazz_id() })">[删除]</a>
                <a href="updateclass.jsp?class_id=${pageScope.clazz.getClazz_id() }">[修改]</a>
            </td>
        </tr>
       </c:forEach>
    </table>
</div>
<div class="footer"  style="margin-left: 60px;margin-right: 60px">
    <ul class="pagination pagination myul">
        <li><a href='classmanager.jsp?currentPage=1'>&laquo;首页</a></li>
        <c:if test="${pageScope. currentPage==1}">
        
           <li class='disabled'><a>上一页</a></li>
        </c:if>
        
          <c:if test="${pageScope. currentPage!=1}">
             <li class='disabled'><a href='classmanager.jsp?currentPage=${pageScope.currentPage-1 }'>上一页</a></li>
          </c:if>
			<c:forEach  var="pageCurrent" varStatus="status" begin="${pageScope.startPage }" end="${pageScope.endPage }">
			   <c:set var="isCurrentPage" value=""/>
			   <c:if test="${pageScope.pageCurrent==pageScope.currentPage }">
			      <c:set var="isCurrentPage" value="class='active'"/>
			   </c:if>
			   <li ${pageScope. isCurrentPage}><a
				   href='classmanager.jsp?currentPage=${pageScope.pageCurrent} '>${pageScope.pageCurrent } </a></li>
			</c:forEach>
		
		
		<c:if test="${pageScope. currentPage==pageScope.totalPage}">
          <li class='disabled'><a  >下一页 </a></li>
        </c:if>
        
          <c:if test="${pageScope. currentPage!=pageScope.totalPage}">
             <li class=''><a href='classmanager.jsp?currentPage=${pageScope.currentPage+1 }'>下一页 </a></li>
          </c:if>
		<li><a href='classmanager.jsp?currentPage=${pageScope.totalPage }'>末页 </a></li>    
    </ul>
</div>

<script type="text/javascript">
  function onDelete(class_id){
	  if(confirm("确定要删除该班级吗？")){
		  window.location.href = "classServlet?method=delete&class_id="+class_id;
	  }
	 
  }
  
  function onRevify(){
	  var class_name = $("#class_name").val();
	  if(class_name==""){
		  show_err_msg("请输入班级名称")
		  return false;
	  }else{
		  return true;
	  }
  }

</script>
</body>
</html>
