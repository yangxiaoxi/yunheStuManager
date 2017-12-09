<%@page import="java.util.List"%>
<%@page import="cn.yunhe.biz.impl.ClazzManagerImpl"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="cn.yunhe.biz.ClazzManager"%>
<%@page import="cn.yunhe.entity.Clazz"%>
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
   <%
   

   List<Map<String,String>> listParam = new ArrayList<Map<String,String>>();
   Map<String,String>  mapParam1 = new HashMap<String,String>();
   mapParam1.put("url", "#");
   mapParam1.put("name", "班级管理");
   
   Map<String,String>  mapParam2 = new HashMap<String,String>();
   mapParam2.put("url", "checkingmanager.jsp");
   mapParam2.put("name", "班级信息管理");
   
   Map<String,String>  mapParam3 = new HashMap<String,String>();
   mapParam3.put("url", "#");
   mapParam3.put("name", "修改班级");
   
   
   listParam.add(mapParam1);
   listParam.add(mapParam2);
   listParam.add(mapParam3);
   
   JSONObject jo  = new JSONObject();
   jo.put("list", listParam);
   
   pageContext.include("top_nav.jsp?param="+jo.toString());
   
%>
 
<%
request.setCharacterEncoding("utf-8");
String class_idStr = request.getParameter("class_id");
int class_id = Integer.parseInt(class_idStr);
pageContext.setAttribute("class_id", class_id);
ClazzManager clazzManager = new ClazzManagerImpl();
 Clazz  clazz =  clazzManager.queryClassById(class_id);
 pageContext.setAttribute("clazz", clazz);

%>
<div class="box">
    <form id="uploadForm" method="post" action="classServlet?method=update&class_id=${pageScope.class_id }" class="form-horizontal" role="form">
        <div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">班级ID:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="class_id" 
                id="class_id" disabled="disabled" value="${pageScope.class_id }" />
            </div>
        </div>
        <div class="form-group">
            <label for="user_id" class="col-sm-2 control-label">班级名称:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="class_name" 
                id="class_name" value="${pageScope.clazz.getClazz_name() }" />
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

</body>
</html>
