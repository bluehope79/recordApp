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
<meta charset="UTF-8">
<title>진료 음성 저장 시스템</title>
<link href="/ui_common/css/default.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src='/js/annyang.js'></script>
</head>
<body>
	<form action="" name="myForm" method="post" style="height: 100%" enctype="multipart/form-data">
		<div class="container cnt_record">
			<!-- header -->
		    <header class="header">
		        <div class="hd_logo">
		            <a href="/stt/home.do"><span>진료 음성 저장 시스템</span></a>
		        </div>
		        <div class="hd_info">
					<ul class="">
						<li>진료 : <span>${dto.doctorpartname }</span></li>
						<li>성명 : <span>${dto.name}</span> (<span>${dto.id }_${drcode }</span>)
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
		    	<section class="record">
		        
		        	<h1>진료녹음</h1>
		        
		        	<div class="record_box">
		            	<div class="cnt_wrap">
		                	<div class="timer" style="visibility: hidden;"></div><!-- 활성 시간 만큼 em으로 감싸기 -->
		                    <div class="recordWrap">
		                    	<div class="recordIns">
		                            <!-- 녹음 삽입 부분 -->
		                            
		                            <!-- 샘플용 -->
		                            <div class="recordInsWrap "> <!-- play버튼이 활성화 되면 클래스 'pause' 제거 / 일시정지, 정지 버튼활성화 시 클래스 'pause' 삽입  -->
		                                <p class="recordBar"><span style=""></span></p> 
		                                <p class="recordImg"></p>
		                                <p class="recordImg2"></p>
		                            </div>
		                            <!--// 샘플용 -->
		                            
		                        </div>
		                    </div>
		                </div>
		                <div class="btnWrap btnPlayer">
							<button type="button" id="recordButton">녹음</button>
					   		<button type="button" id="pauseButton" disabled>일시중지</button>
					   		<button type="button" id="stopButton" disabled>중지</button>
						</div>
						
		            </div>
		            <!-- text 출력 box -->
					<div class="record_box1" style="display:block;">
						<div class="cnt_wrap">
							<p id="recordText"></p>
						</div>
					</div>
		        </section>
		        <!--// 진료녹음 -->                
		        
		    </article>
		    <!--// article -->
		    
		</div>
	</form>
	
	<script src='https://cdn.rawgit.com/mattdiamond/Recorderjs/08e7abd9/dist/recorder.js'></script>
	<script type="text/javascript">
	
		var fn_timer;
		var time = 0;
		var recordData = "";
		var recordCheck = false;
		var pauseFlag = true;
		
		//webkitURL is deprecated but nevertheless
		URL = window.URL || window.webkitURL;

		var gumStream; 						//stream from getUserMedia()
		var rec; 							//Recorder.js object
		var input; 							//MediaStreamAudioSourceNode we'll be recording

		// shim for AudioContext when it's not avb. 
		var AudioContext = window.AudioContext || window.webkitAudioContext;
		var audioContext //audio context to help us record

		var recordButton = document.getElementById("recordButton");
		var stopButton = document.getElementById("stopButton");
		var pauseButton = document.getElementById("pauseButton");

		//add events to those 2 buttons
		recordButton.addEventListener("click", startRecording);
		stopButton.addEventListener("click", stopRecording);
		pauseButton.addEventListener("click", pauseRecording);

		function startRecording() {
			
			if("${dto.doctorcode }" == "") {
				//logout();
			}
			
			$('.timer').css('visibility','visible');
			
			fn_timer = setInterval("timer()", 1000);
			
			$("#startBtn").css("background-color","silver");
			$("#pauseBtn").css("background-color","#54597E");
			$("#stopBtn").css("background-color","#54597E");
				
			recordCheck = true;
			/*
				Simple constraints object, for more advanced audio features see
				https://addpipe.com/blog/audio-constraints-getusermedia/
			*/
		    
		    var constraints = { audio: true, video:false }

		 	/*
		    	Disable the record button until we get a success or fail from getUserMedia() 
			*/

			recordButton.disabled = true;
			stopButton.disabled = false;
			pauseButton.disabled = false

			/*
		    	We're using the standard promise based getUserMedia() 
		    	https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia
			*/
			
			navigator.mediaDevices.getUserMedia(constraints).then(function(stream) {
				console.log("getUserMedia() success, stream created, initializing Recorder.js ...");

				/*
					create an audio context after getUserMedia is called
					sampleRate might change after getUserMedia is called, like it does on macOS when recording through AirPods
					the sampleRate defaults to the one set in your OS for your playback device

				*/
				audioContext = new AudioContext();

				//update the format 
				//document.getElementById("formats").innerHTML="Format: 1 channel pcm @ "+audioContext.sampleRate/1000+"kHz"

				/*  assign to gumStream for later use  */
				gumStream = stream;
				
				/* use the stream */
				input = audioContext.createMediaStreamSource(stream);

				/* 
					Create the Recorder object and configure to record mono sound (1 channel)
					Recording 2 channels  will double the file size
				*/
				rec = new Recorder(input,{numChannels:1})

				//start the recording process
				rec.record() 
				
				console.log("Recording started");
				
				annyang.start({ autoRestart: false, continuous: true })
			    var recognition = annyang.getSpeechRecognizer();
			    var final_transcript = '';
			    recognition.interimResults = true;
			    recognition.onresult = function(event) {
			        var interim_transcript = '';
			        final_transcript = '';
			        for (var i = event.resultIndex; i < event.results.length; ++i) {
			            if (event.results[i].isFinal) {
			                final_transcript += event.results[i][0].transcript;
			                //console.log("final_transcript="+final_transcript);
			                //annyang.trigger(final_transcript); //If the sentence is "final" for the Web Speech API, we can try to trigger the sentence
			            } else {
			                interim_transcript += event.results[i][0].transcript;
			                //console.log("interim_transcript="+interim_transcript);
			            }
			        }
			        
			        $('#recordText').text($('#recordText').text() + final_transcript);
			        //console.log('interim='+interim_transcript+'|final='+final_transcript);
			    };

			});
		}

		function pauseRecording(){
			console.log("pauseButton clicked rec.recording=",rec.recording );
			if (rec.recording){
				//pause
				clearInterval(fn_timer);
				annyang.abort();
				rec.stop();
				pauseButton.innerHTML="계속";
				//$('#pauseBtn').text('계속');
			}else{
				//resume
				fn_timer = setInterval("timer()", 1000);
				annyang.start({autoRestart: false, continuous: true});
				rec.record()
				pauseButton.innerHTML="일시중지";
				//$('#pauseBtn').text('일시중지');
			}
		}

		function stopRecording() {
			console.log("stopButton clicked");

			//disable the stop button, enable the record too allow for new recordings
			stopButton.disabled = true;
			recordButton.disabled = false;
			pauseButton.disabled = true;

			//reset button just in case the recording is stopped while paused
			pauseButton.innerHTML="일시중지";
			
			clearInterval(fn_timer);
			annyang.pause();
			
			//tell the recorder to stop the recording
			rec.stop();

			//stop microphone access
			gumStream.getAudioTracks()[0].stop();

			//create the wav blob and pass it on to createDownloadLink
			rec.exportWAV(createDownloadLink);
		}

		function createDownloadLink(blob) {
			
			
			var url = URL.createObjectURL(blob);
			var au = document.createElement('audio');
			var li = document.createElement('li');
			var link = document.createElement('a');

			console.log(blob);
			
			//name of .wav file to use during upload and download (without extendion)
			var filename = new Date().toISOString();
			
			var mFile = blobToFile(blob, filename);
			
	        var formData = new FormData();
	        formData.append("file", mFile);
	        formData.append("drcode", "000001");
	        formData.append("patientcode", "${patientcode}");
	        formData.append("content", $('#recordText').text());
	        
			$.ajax({
				url : "stt/saveFile.do",
				type : "POST",
				cache : false,
				dataType : "text",
				data : formData,
				contentType : false,
		        processData : false,
				success : function(data) {
					$('#startBtn').attr("disabled",false);
					$("#startBtn").css("background-color","#54597E");
					
					$('#pauseBtn').attr("disabled",true);
					$("#pauseBtn").css("background-color","silver");
					
					$('#stopBtn').attr("disabled",true);
					$("#stopBtn").css("background-color","silver");
					
					alert("녹음된 내용을 텍스트 파일로 변환하였습니다.");
					
					var f = document.myForm;
					f.action = "/stt/history.do?drcode=${drcode}&patientcode=${patientcode}";
					f.submit();
				},
				error : function(request, status, error) {
					var msg = "ERROR : " + request.status + "<br>"
					msg += +"내용 : " + request.responseText + "<br>"
							+ error;
					console.log(msg);
				}
			});

		}
		
		function blobToFile(blob, filename){
			blob.lastModifiedDate = new Date();
			blob.name = "record.wav";
			
			return blob;
		}
		
		function timer() {
			time ++;
			
			var spanText = "";
			var emText1 = "";
			var emText2 = "";
			var emText3 = "";
			
			var hour = 0;
			var min = 0;
			var sec = 0;

			// 정수로부터 남은 시, 분, 초 단위 계산

			hour = Math.floor(time / 3600);
			min = Math.floor( (time-(hour*3600)) / 60 );
			sec = time - (hour*3600) - (min*60);
			
			if(hour > 0) {
				
				if(hour>0 && hour<10) {
					emText1 = '0<em>' + hour + ":" + '</em>';
				} else {
					emText1 = '<em>' + hour + ":" + '</em>';
				}
				
			} else {
				emText1 = "00:";
			}
			
			if(min > 0) {
				
				if(hour>0) {
					if(min>0 && min<10) {
						emText2 = '<em>0' + min + ":" + '</em>';
					} else {
						emText2 = '<em>' + min + ":" + '</em>';
					}
				} else if(min>0 && min<10) {
					emText2 = '0<em>' + min + ":" + '</em>';
				} else {
					emText2 = '<em>' + min + ":" + '</em>';
				}
				
			} else {
				if(hour>0) {
					emText2 = '<em>00:</em>';
				} else {
					emText2 = "00:";
				}
			}
			
			if(sec > 0) {
	
				
				if(hour>0 || min>0) {
					if(sec>0 && sec<10) {
						emText3 = '<em>0' + sec + '</em>';
					} else {
						emText3 = '<em>' + sec + '</em>';
					}
				} else if(sec>0 && sec<10) {
					emText3 = '0<em>' + sec + '</em>';
				} else {
					emText3 = '<em>' + sec + '</em>';
				}
	
			} else {
				
				if(hour>0 || min>0) {
					emText3 = '<em>00</em>';
				} else {
					emText3 = "00";
				}
			}
			
			
			$('.timer').html('<span>' + emText1 + emText2 + emText3 + '</span>');
		}
		
		function logout() {
			
			var f = document.myForm;
			f.action = "/stt/logout.do";
			f.submit();
			
		}
		
		
	</script>
</body>
</html>