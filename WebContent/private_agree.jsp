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
		<div class="container">
		<!-- header -->
		    <header class="header">
		        <div class="hd_logo">
		            <a href="/stt/home.do"><span>진료 음성 저장 시스템</span></a>
		        </div>
		        <div class="hd_info">
					<ul class="">
						<li>진료 : <span>${dto.doctorpartname }</span></li>
						<li>성명 : <span>${dto.name}</span> (<span>${dto.id }_${dto.doctorcode }</span>)
						</li>
					</ul>
				</div>
				<div class="hd_nav">
					<ul class="">
						<li class="ico_logout"><a href="javascript:logout()"><span>logout</span></a></li>
						<li class="ico_custmer"><a href="#"><span>customer</span></a></li>
						<li class="ico_help"><a href="#"><span>help</span></a></li>
					</ul>
				</div>
		    </header>
		    <!--// header -->
		    
		    <!-- article -->
		    <article class="content">
		    
		    	<!-- 진료녹음 -->
		    	<section class="agree">
		        
		        	<h1>진료녹음</h1>
		        
		        	<div class="box_scroll">
		            	<div class="cnt_wrap">
		                	<h3>개인정보처리방침 동의</h3>
		                    <p>개인정보처리방침 수립, 공개의 취지<br/>
		                      (OO 종합 의료센터 이하 '의료센터')은(는) 개인정보보호법 제 30조에 따라 정보주테(환자)의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원<br/>
		                                                  활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리지침을 수립, 공개합니다.<br/>
		                      1. 개인정보의 처리 목적 <br/>
		                      &nbsp;&nbsp;&nbsp;의료센터은(는) 다음의 목적을 위하여 개인정보를 처리하고 있으며, 다음의 목적 이외의 용도로는 이용하지 않습니다. <br/>
		                      &nbsp;&nbsp; -&nbsp; 환자의 진료목적 및 검사 예방접종 및 시간의 경과에 따라 투여하여야 할 주사제가 있는 경우 <br/>
		                      &nbsp;&nbsp; -&nbsp; 진료 프로그램 서버 관리 및 x-ray촬영 장비 관리 및 서버 관리의 경우    <br/>
		                      2. 개인정보의 처리 및 보유기간<br/>
		                      &nbsp;&nbsp;&nbsp;의료센터은(는) 정보주체로부터 개인정보를 수집할 때 동의 받은 개인정보 보유, 이용기간 또는 법령에 따른 개인정보 보유, 이용기간 내에서 개<br/>
		                      &nbsp;&nbsp;&nbsp;인정보를 처리, 보유합니다.<br/>
		                      3. 개인정보의 제3자 제공<br/>
		                      &nbsp;&nbsp;&nbsp;의료센터은(는) 정보 주체의 별도 동의, 법률의 특별한 규정 등 개인정보보호법 제17조에 해당하는 경우 외에는 개인정보를 제3자에게 제공하지 <br/>
							  &nbsp;&nbsp;&nbsp;않습니다.<br/>
							  4. 개인정보 처리의 위탁<br/>  
		                      &nbsp;&nbsp;&nbsp;의료센터은(는) 원활한 개인정보 진료 및 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다. <br/> 	 
		                      &nbsp;&nbsp; -&nbsp; 내원을 통해서 최초 진료회원으로 등록된 경우는 병원관리시스템에 등록되어 운영됩니다. 이 경우는 병원에서 위탁한 C.Side에서 전산관리를<br/>
		                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;맡게되며, 진료 시 개인정보 처리에 대한 동의를 받게됩니다. <br/>
		                      &nbsp;&nbsp;&nbsp;의료센터에서 위탁계약 체결 시, 개인정보보호법 제 26조에 따라 위탁업무 수행목적 외 개인정보 처리 금지, 기술적/관리적 보호조치, 재위탁 제<br/>
		                      &nbsp;&nbsp;&nbsp;한, 수탁자에 대한 관리/감독, 손해배상 등 책임에 관한 사항을 게약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하<br/>
		                      &nbsp;&nbsp;&nbsp;고 있습니다. 
		                    </p>
		                </div>
		            </div>
		            <a href="javascript:diagnosisRecordStart('000001','${patientcode }')" class="btn btn_agree"><span>동의</span></a>       
		        </section>
		        <!--// 진료녹음 -->                
		        
		    </article>
	    <!--// article -->
		</div>
	</form>
	
	<script type="text/javascript">
	
		function diagnosisRecordStart(drcode,patientcode) {
			
			if("${drcode }" == "") {
				logout();
			}
			
			var f = document.myForm;
			f.action = "/stt/recordStart.do?drcode="+drcode+"&patientcode=${patientcode}";
			f.submit();
			
		}
		
		function logout() {
			
			var f = document.myForm;
			f.action = "/stt/logout.do";
			f.submit();
			
		}
	
	</script>
</body>
</html>