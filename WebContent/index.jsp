<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>진료 음성 저장 시스템</title>
<link href="/ui_common/css/default.css" rel="stylesheet" type="text/css">
</head>

<body>
	<form action="" name="myForm" method="post" style="height: 100%">
		<div class="container member">
			<!-- header -->
			<header class="header">
				<div class="hd_logo">
					<a href="/stt"><span>진료 음성 저장 시스템</span></a>
				</div>
			</header>
			<!--// header -->

			<!-- article -->
			<article class="content">

				<!-- login -->
				<section class="login">

					<div class="loginWrap">
						<div class="txt">
							<h2>사용자인증</h2>
						</div>
						<div class="loginbox">
							<span class="form-text form-id"> 
								<label for="id" class="id"><span>아이디</span></label> 
								<input type="text" id="id" name="id" required>
							</span> 
							<span class="form-text form-pw"> 
								<label for="pw" class="pw"><span>비밀번호</span></label>
								<input type="password" id="pw" name="pw" required>
							</span>
							<div class="btnWrap">
								<a href="javascript:loginChk();" class="btn btn_login"><em>Login</em></a>
							</div>
						</div>
					</div>

					<div class="progWrap">
						<div class="loading" style="display : none;">
							<!-- 로딩 animation 추가 -->
							<div class="loadingImg">
								<span></span> <span></span> <span></span> <span></span> <span></span>
								<span></span> <span></span> <span></span>
							</div>
							<!--// 로딩 animation 추가 -->
							<p class="txt">
								처리중입니다. <br> 잠시만 기다려주십시오.
							</p>
						</div>
						<div class="prog" style="display : none;">
							<p>
								계정 또는 패스워드가 맞지 않습니다.<br>다시 시도하십시오.
							</p>
						</div>
					</div>



				</section>
				<!--// login -->



			</article>
			<!--// article -->

			<!-- background animation -->
			<div class="idxBgW">
				<div class="idxBg">
					<p></p>
				</div>
			</div>
			<!--// background animation -->

		</div>
	</form>
	
	<script type="text/javascript">

		var check = "${check }";
		
		if(check == "false") {
			$('.prog').css('display','block');
		}
		
		function loginChk() {
	
			if($('#id').val() == null || $('#id').val() == "") {
				alert("아이디를 입력해주세요");
				return;
			}
			
			if($('#pw').val() == null || $('#pw').val() == "") {
				alert("비밀번호를 입력해주세요");
				return;
			}
			
			$('.prog').css('display','none');
			$('.loading').css('display','block');
			
			
			var f = document.myForm;
			f.action = "/stt/home.do";
			f.submit();
			
		}
	
		$(document).ready(function() {
	
		});
	</script>
</body>
</html>
