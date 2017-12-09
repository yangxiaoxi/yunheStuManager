<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
<meta charset="utf-8">
<title>云和数据教学管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->

<link rel="stylesheet" href="css/supersized.css">
<link rel="stylesheet" href="css/login.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="js/html5.js"></script>
<![endif]-->
<script src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/tooltips.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101442192" data-redirecturi="http://120.77.85.217/yunheStuManager/otherServlet" charset="utf-8"></script>

</head>

<body>

<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<h1>云和数据教学管理系统</h1>
			</div>
			
			<!-- 其他管理员登录 -->
			<div id="otherLogin">
				<div class="box" 
				style="background:url(images/qq.jpg);width:200px;height:200px;
				background-repeat:no-repeat;background-size:200px 200px;margin-left: 30%;margin-right: 30%;">
				</div>
				<div class="form-group">
							<label class="t"></label>
							<span id="qqLoginBtn" class="box" style="background:url(images/qq.jpg);width:200px;height:50px;
								background-repeat:no-repeat;background-size:200px 50px;margin-left: 34%;margin-right: 34%;">
							</span>
				</div>
				
				<div class="bottom">Copyright &copy; 2014 - 2015 <a href="javascript:replaceLoginType(1)">系统登陆</a></div>
			</div>
			
		
			<!-- 超级管理员登录 -->
			<div class="login_form" id="normalLogin" style="display: none">
			<!-- userServlet?method=checkLogin -->
				<form action="userServlet?method=checkLogin" id="login_form" method="post" onsubmit="return verify()">
					<div class="form-group">
						<label for="j_username" class="t">邮　箱：</label> 
						<input id="email" value="" name="email" type="text" class="form-control x319 in" 
						autocomplete="off">
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="password" value="" name="password" type="password" 
						class="password form-control x319 in" />
					</div>
					<div class="form-group">
						<label for="j_captcha" class="t">验证码：</label>
						 <input id="j_captcha" name="j_captcha" type="text" class="form-control x164 in" />
						 <a href="javascript: verifycode()"><img id="img" src="verifyCodeServlet"  ></img></a>
					</div>
					<div class="form-group">
						<label class="t"></label>
						<label for="j_remember" class="m">
						<input id="j_remember"  name ="j_remember" type="checkbox" value="true">&nbsp;记住登陆状态</label>
					</div>
					<div class="form-group space">
						<label class="t"></label>　　
						<button type="submit"  id="submit_btn" class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp </button>
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
					</div>
				</form>
				
				<div class="bottom">Copyright &copy; 2014 - 2015 <a href="javascript:replaceLoginType(2)">使用QQ登陆</a></div>
				<span id="qqLoginBtn"></span>
<script type="text/javascript">
//qq登陆成功回调信息
   $(function() {
	   QC.Login({
		   //btnId：插入按钮的节点id，必选
	       btnId:"qqLoginBtn",    
	       //用户需要确认的scope授权项，可选，默认all
	       scope:"all",
	       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
	       size: "A_XL"   //插入按钮的节点id
	},function(oInfo, oOpts){
		  QC.Login.getMe(function(openId, accessToken){
				//ajax请求是否数据库是否存在该用户 ,如果返回200 表示用户存在，直接跳转至登录成功的界面，
				//如果返回500 则用户不存在，提示用户没有权限
				var url="userServlet";
			   var data={
				    method :"checkLoginThree",
				    open_id:openId,
				    nick_name:oInfo.nickname
			   };
				$.post(url,data,function(response){
						 var jsonObject=$.parseJSON(response);
						 if(jsonObject.status==200){
							 show_msg("登陆成功", "index.jsp");
						 }
                         if(jsonObject.status==500){
							 show_err_msg("该用户没有权限");
						 }
				
				alert("openid is"+openId);
				alert("accessToken is"+accessToken);
			});
		  });
		},function(){
			QC.Login.signOut();
		    alert("注销成功!");
		});
});
   
</script>
			</div>
		</div>
		
	</div>
</div>

<!-- Javascript -->

<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<script src="js/scripts.js"></script>

<script type="text/javascript">
	function replaceLoginType(type) {
		if(type==1){//使用管理员登录
			$("#normalLogin").show();
			$("#otherLogin").hide();
		}else{//使用QQ登录
			$("#normalLogin").hide();
			$("#otherLogin").show();
		}
	}
	
	function  verifycode(){
		document.getElementById("img").src="verifyCodeServlet?code="+new Date().getTime();
	}
	
</script>

</body>
</html>