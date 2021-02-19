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
<title>검색결과-진료 음성 저장 시스템</title>
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
						<li>   <span>내과 </span></li>
						<li> <span>${dto.name}</span> (<span>${drcode }</span>)
						</li>
					</ul>
				</div>
				<div class="hd_nav">
					<ul class="">
						<li class="ico_logout"><a href="javascript:logout()"><span>logout</span></a></li>
						<li class="ico_custmer"><a href="/stt/home.do?id=test01&pw=1234"><span>홈</span></a></li>
						<li class="ico_help"><a href=""><span>help</span></a></li>
					</ul>
				</div>
		    </header>
		    <!--// header -->
		    
		    <!-- article -->
		    <article class="content">
		    
		    	<!-- 환자검색 -->
		    	<section class="sch">
		        
		        	<h1>환자 진료기록 검색결과</h1>
		        
		        	<div class="schWrap">
		            
		            	<table class="tb_set">
		                <colgroup>
		                    <col width="*">                    
		                    <col width="*">
		                    <col width="125px">
		                </colgroup> 
		                <tbody>
		                <tr>
		                    <td>
		                    	<span class="lb-wrap">
		                    	<label for="">이름</label>
		                        </span>
		                        <span class="inp-wrap">
		                        <input type="text" class="" id="" title="이름 입력" value="" >
		                        </span>
		                    </td>
		                    <td>
		                    	<span class="lb-wrap">
		                    	<label for="">생년월일</label>
		                        </span>
		                        <span class="inp-wrap">
		                        <input type="date" class="" id="" title="생년월일 입력" value="">
		                        </span>
		                    </td>
		                    <td rowspan="2" ><a href="" class="btn btn_sch"><span>search</span></a>  </td>
		                </tr>
		                <tr>
		                	<td>
		                    	<span class="lb-wrap">
		                    	<label for="">성별</label>
		                        </span>
		                        <span class="inp-wrap">
		                        <select class="" id="">
		                        	<option value=""></option>
		                            <option value="M">남</option>
									<option value="F">여</option>
		                        </select>
		                        </span>
		                    </td>                    
		                    <td>
								<span class="lb-wrap"> 
									<label for="">휴대폰번호</label>
								</span> 
								<span class="inp-wrap"> 
									<input type="text" class="" id="cellphone" title="휴대폰번호 입력" value="">
								</span>
							</td>                
		                </tr>                
		                </tbody>
		                </table>                              
		            </div>
		            
		        </section>
		        <!--// 환자검색 -->
		        
		        <section class="listWrap sch_patient">
		        	<div class="tbBox tb_scroll">
		                <table class="tb_set tb_row">
			                <colgroup>
			                    <col width="17%">
			                    <col width="17%">
			                    <col width="*">
			                    <col width="*">
			                    <col width="*">
			                </colgroup> 
			                <thead>           
				                <tr>
				                    <th>이름</th>
				                    <th>의사명</th>
				                    <th>음성파일</th>
				                    <th>녹취파일</th>
				                    <th>삭제</th>
				                </tr>
			                </thead>
			                <tbody id="patientfileList">
			                </tbody>
		                </table>
		            </div>
		            
		            <div class="tb_paging" id="paging" style="visibility: hidden;">
		            	<a href="#" class="pg_icon first"><span>처음</span></a> 
						<a href="#" class="pg_icon before"><span>이전</span></a> 
						<a href="#" class="ac">1</a>
						<a href="#">2</a> 
						<a href="#">3</a> 
						<a href="#"	class="pg_icon next"><span>다음</span></a> 
						<a href="#" class="pg_icon last"><span>마지막</span></a>
		            </div>
		            
		             <div class="progWrap" style="display: none">
						<div class="loading">
							<!-- 로딩 animation 추가 -->
							<div class="loadingImg" style="display: none">
								<span></span> <span></span> <span></span> <span></span> <span></span>
								<span></span> <span></span> <span></span>
							</div>
							<!--// 로딩 animation 추가 -->
							<p class="txt" id="lodingTxt" style="display: none">	처리중입니다. <br> 잠시만 기다려주십시오.</p>
						</div>
					</div>
		        </section>
		
				<!-- 진료 시작 버튼 -->
				<section class="btn_area treatWrap" id="diagnosis">
		        	<a href="javascript:diagnosisStart('000001','${patientcode }')" class="btn btn_treat" ><span>진료시작</span></a>
		        </section>
		        <!--// 진료 시작 버튼 -->
		        
		    </article>
		    <!--// article -->
		    
		    
		</div>
		 
		<!-- 진료 시작 확인 팝업 -->
		<section class="pop pop_start" id="diagnosisStart" style="display:none;">
		    <div class="popWrap">
		        <p class="txt">진료를 시작하시겠습니까?</p>
		        <div class="btnWrap">
		            <a href="javascript:sttStart('${drcode }','${patientcode }')" class="pop_btn btn_ok"><span>확인</span></a>
		            <a href="javascript:sttCancle()" class="pop_btn btn_cancel"><span>취소</span></a>
		        </div>
		    </div>
		</section>
		<!--// 진료 시작 확인 팝업 -->
		
			
		<!-- 녹음파일 듣기 팝업 -->
			<div class="pop pop_audioF" style="display:none;">
			    <div class="popWrap" align="center">
			        <div class="pop_hd">
			            <p class="txt_patient">환자명 : <em id="patientName"></em></p>
			            <p class="txt_file" id="wavfileName"></p>
			            <a href="" class="file_download" id="wavfileDownload"><span>DownLoad</span></a>
			        </div>
			        <div class="pop_cnt">
			            <div class="audWrap"> 
			            	<div class="audIns">
			                
			                    <div class="audInsWrap">
			                       <audio controls="controls" src="" id="wavAudio"></audio>
			                    </div>
			                    
			                </div>           	
			                
			            </div>
			        </div>
			        <div class="btn_icon btn_close"  onclick="closeWavPlayer();"><span>close</span></div>
			    </div>
			</div>
		        <!--// 녹음파일 듣기 팝업 -->
			
		<!-- 환자 등록 확인 팝업 -->
		<!-- <section class="pop pop_start1"  style="display:none;">
		    <div class="popWrap">
		        <p class="txt">이 정보로 환자를 등록하시겠습니까?</p>
		    	<table></table>
		        <div class="btnWrap">
		            <a href="" class="pop_btn btn_ok"><span>확인</span></a>
		            <a href="" class="pop_btn btn_cancel"><span>취소</span></a>
		       	</div>
		    </div>
		</section> -->
		<!--//  환자 등록 확인 팝업 -->	
	</form>
	
	<script type="text/javascript">
	
		console.log("${drcode }");
		var drcode = "${drcode }";
	
		search(1);
	
		function search(page) {
			
			if("${drcode }" == "") {
				logout();
			} 
	
			$('.progWrap').css('display','block');
			$('.loadingImg').css('display','block');
			$('#lodingTxt').css('display','block');
			
			var patientcode = "${patientcode}";
			
			$.ajax({
				url : "stt/historyList.do",
				type : "GET",
				cache : false,
				dataType : "json",
				data : {
					"page" : page,
					"patientcode" : patientcode
				},
				success : function(data) {
					
					if(data.length == 0) {
						
						$('.loadingImg').css('display','none');
						$('#lodingTxt').css('display','none');
						$('.progWrap').css('display','none');
							
						alert("검색결과 자료가 존재하지 않습니다.");
						
						return;
						
					}
					
					$('#patientfileList').children().remove();
					$('#paging').children().remove();
					
					var appendText = "";
					
					$('.loadingImg').css('display','none');
					$('#lodingTxt').css('display','none');
					$('.progWrap').css('display','none');
					
					$('#patientfileList').children().remove();
					
					$.each(data,function(key,value) {
						
						//var patientcode = '\'' + value.patientcode + '\''
						if(drcode == value.drcode) {
							appendText += '<tr>'
							+ '<td>' + value.patientname + '</td>'
							+ '<td>' + value.doctorname + '</td>'
							+ '<td><a href="javascript:openWavPlayer(\'' + value.patientname + '\',\'' + value.wavfilename + '\',\'' + value.wavfileloc + '\')">' + value.wavfilename + '</a></td>'
							+ '<td><a href="javascript:fileDownload(\'' + value.txtfilename + '\',\'txt\')">' + value.txtfilename + '</a></td>'
							+ '<td><a href="javascript:deleteData(\'${patientcode}\',' + value.seq + ')" class="btn btn_del"><span>[삭제]</span></a></td>'
							+ '</tr>'
						} else {
							appendText += '<tr>'
							+ '<td>' + value.patientname + '</td>'
							+ '<td>' + value.doctorname + '</td>'
							+ '<td><a href="javascript:openWavPlayer(\'' + value.patientname + '\',\'' + value.wavfilename + '\',\'' + value.wavfileloc + '\')">' + value.wavfilename + '</a></td>'
							+ '<td><a href="javascript:fileDownload(\'' + value.txtfilename + '\',\'txt\')">' + value.txtfilename + '</a></td>'
							+ '<td><a href="javascript:deleteData(\'${patientcode}\',' + value.seq + ')" class="btn btn_del"><span>[삭제]</span></a></td>'
							+ '</tr>'
						}
						
					});
					
					$('#patientfileList').html(appendText);
					$('#paging').css('visibility','visible');
					$('#paging').html(data[0]['patientPagingText']);
					
				},
				error : function(request, status, error) {
					var msg = "ERROR : " + request.status + "<br>"
					msg += +"내용 : " + request.responseText + "<br>"
							+ error;
					console.log(msg);
				}
			});
			
		}//end of search 
		
		function diagnosisStart(drcode,patientcode) {
			
			if("${drcode }" == "") {
			//	logout();
			}
			
			$('#diagnosisStart').css('display','block');
			
		}
		
		function sttStart(drcode,patcd) {
			
			if("${drcode }" == "") {
				logout();
			}
			
			$('#diagnosisStart').css('display','none');
			
			var f = document.myForm;
			f.action = "/stt/agree.do?drcode="+drcode+"&patientcode=${patientcode}";
			f.submit();
			
		}
		
		function sttCancle() {
	
			$('#diagnosisStart').css('display','none');
	
		}
		
		function deleteData(patientcode, seq) {
			
			if("${drcode }" == "") {
				logout();
			}
			
			$.ajax({
				url : "stt/deleteData.do",
				type : "GET",
				cache : false,
				dataType : "text",
				data : {
					"patientcode" : patientcode,
					"seq" : seq
				},
				success : function(data) {
					search(1);
				},
				error : function(request, status, error) {
					var msg = "ERROR : " + request.status + "<br>"
					msg += +"내용 : " + request.responseText + "<br>"
							+ error;
					console.log(msg);
				}
			});
		}
		
		function logout() {
			
			var f = document.myForm;
			f.action = "/stt/logout.do";
			f.submit();
			
		}
		
		function openWavPlayer(patientName, wavfileName, wavfileloc) {
			
			if("${drcode }" == "") {
				logout();
			}
			
			$('.pop_audioF').css('display','block');
			
			console.log("${patientcode}");
			
			var patientcode = "${patientcode}";
			
			$('#patientName').text(patientName);
			$('#wavfileName').text(wavfileName);
			
			var srcValue = "http://" + location.host + "/" + wavfileloc + "/" + wavfileName;
			console.log(srcValue);
			
			$('#wavAudio').attr('src', srcValue);
			
			$('#wavfileDownload').prop('href', 'javascript:fileDownload(\'' + wavfileName + '\',\'wav\')');
			
		}
		
		function closeWavPlayer() {
			$('.pop_audioF').css('display','none');
		}
		
		function fileDownload(fileName, flag) {
			
			if("${drcode }" == "") {
				logout();
			}
			
			var f = document.myForm;
			f.action = "/stt/fileDownload.do?fileName=" + fileName + "&flag=" + flag;
			f.submit();
		}
		
	
	</script>
</body>
</html>