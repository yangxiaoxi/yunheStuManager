// JavaScript Document
//鏀寔Enter閿櫥褰�
		document.onkeydown = function(e){
			if($(".bac").length==0)
			{
				if(!e) e = window.event;
				if((e.keyCode || e.which) == 13){
					var obtnLogin=document.getElementById("submit_btn")
					obtnLogin.focus();
				}
			}
		}
		
		function changeCode(){
			//甯﹀弬鏁版槸涓轰簡璁﹕rc涓殑璺緞姣忔閮藉彉鍖栵紝杩欐牱鎵嶈兘璁╁埛鏂伴獙璇佺爜锛堝鏋滄瘡娆＄殑璺緞閮戒竴鏍凤紝灏变笉浼氬啀鍘婚噸鏂拌姹係ervlet锛�
			document.getElementById('captcha_img').src="verifyServlet?d="+new Date().getTime();
		}

    	function  verify(){//楠岃瘉琛ㄥ崟
			//鎻愪氦琛ㄥ崟
				var myReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //閭欢姝ｅ垯
				var emailStr=$('#email').val();
				var pwdStr=$('#password').val();
				var captchaStr=$('#j_captcha').val();
				
				
				if(emailStr == ''){
					show_err_msg('璇疯緭鍏ラ偖绠憋紒');	
					return false;
				}else if(!myReg.test(emailStr)){
					show_err_msg('鎮ㄧ殑閭鏍煎紡閿欏挴锛�');
					return false;
				}else if(pwdStr == ''){
					show_err_msg('璇疯緭鍏ュ瘑鐮侊紒');
					return false;
				}else if(captchaStr == ''){
					show_err_msg('璇疯緭鍏ラ獙璇佺爜锛�');
					 return false;
				}else{
					var j_remember="-1";
					//鍒ゆ柇鏄惁閫変腑鈥滆浣忕櫥褰曠姸鎬佲�
					if($('#j_remember').is(':checked')) {
						j_remember="1";
					}
					return true;
				}
			}
	