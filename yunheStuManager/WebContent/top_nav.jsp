<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
   request.setCharacterEncoding("utf-8");
   
   String str=request.getParameter("param");
  
   JSONObject jo = JSONObject.fromObject(str);
   JSONArray jsonArry =  jo.getJSONArray("list");
   
%>
<!-- 访问路径导航 -->
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid"> 
		<div class="navbar-header">
			<span class="navbar-brand">
			<%
			for(int i=0;i<jsonArry.size();i++){
				String url = jsonArry.getJSONObject(i).getString("url");
				String name = jsonArry.getJSONObject(i).getString("name");
				out.println("<a href="+url+">"+name+"</a>");
				if(i!=jsonArry.size()-1){
					out.println("&nbsp;>>&nbsp;");
				}
			}
			%>
			</span>
		</div>
	</div>
	
</nav>
</body>
</html>