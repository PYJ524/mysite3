<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<script type="text/javascript">
	// 아이디 중복체크 버튼 클릭했을 때
	$(window).load(function(){
		//예제
		// 원래의 태그의 기능을 사용하지 않을 때
		$("#naver").on("click", function(event){
			event.preventDefault();
		});
		
		// 정보 입력 관련 에러
		$("#joinSubmitForm").on("submit", function(){
			console.log("전송버튼 클릭");
			
			// 아이디 체크
			var id = $("#input-uid").val();
			if(id.length<1){		// 입력안했으면
				alert("ID를 입력해주세요.");			
				return false;
			}else if(id.length>30){
				alert("ID가 길이를 초과했습니다.(30자)");			
				return false;
			}
			
			// 패스워드 체크
			var pass = $("#input-pass").val();
			console.log(pass)
			if(pass.length<1){
				alert("Password가 입력되지 않았습니다.");			
				return false;
			}else if(pass.length < 6 || pass.length > 30){
				alert("Password가 길이가 맞지 않습니다.(6~30자)");			
				return false;
			}
			
			// 이름 체크
			var name = $("#input-name").val();
			if(name.length<1){
				alert("이름을 입력해주세요.");			
				return false;
			}
			
 			/* rdo-male rdo-female */
			// 성별 체크
			var gender = $(".gender").is(":checked");
 			if(gender == false){
				alert("성별을 체크해 주세요.");
				return false;
 			}
			
			// 약관동의 체크
			var agree = $("#chk-agree").is(":checked");
			if(agree == false){
				alert("약관에 동의해 주세요.");
				return false;
			}
			
			return false;
		});
		
		$("#checkId").on("click",function(){
			console.log("버튼클릭");
			var id = $("[name=id]").val();
			console.log(id);
			$.ajax({
				url : "${pageContext.request.contextPath}/user/checkId" ,
				type : "post",
				/* contentType : "application/json", */
				data : {id : id} , 
				dataType : "json" ,
				success : function(jsonResult){
					console.log(jsonResult);
					if(jsonResult.result == 'success'){	
						if (jsonResult.data == true) {
						//성공시 처리해야될 코드 작성
							$("#resultId").html("<font size ='2' color = 'grey' id='idcheckMsg'>사용할 수 있는 아이디입니다.</font>");
						} else {
							// 여기에 실패 메시지를 출력하는 코드를 추가할 수 있습니다.
							$("#resultId").html("<font size ='2' color = 'red' id='idcheckMsg'>이미 사용중인 아이디입니다.</font>");
						}
					}else{
						var msg = jsonResult.failMsg;
						alert(msg);
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});  
			// html("<font size ='2' color = 'grey' id='idcheckMsg'>사용할 수 없는 아이디입니다.</font>")
		});
	})
</script>

<body>
	<div id="wrap">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>
		<!-- //nav -->

		<div id="aside">
			<h2>회원</h2>
			<ul>
				<li>회원정보</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>회원가입</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>회원</li>
            			<li class="last">회원가입</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="user">
				<div id="joinForm">
					<form id="joinSubmitForm" action="${pageContext.request.contextPath}/user/join" method="get">
						<!-- 아이디 -->
						<div class="form-group">
							<label class="form-text" for="input-uid">아이디</label> 
							<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
							<button type="button" id="checkId">중복체크</button>
						</div>
						<p id="resultId" >
							
						</p>
						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="form-text" for="input-pass">패스워드</label> 
							<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
						</div>

						<!-- 이메일 -->
						<div class="form-group">
							<label class="form-text" for="input-name">이름</label> 
							<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
						</div>

						<!-- //나이 -->
						<div class="form-group">
							<span class="form-text">성별</span> 
							
							<label for="rdo-male">남</label> 
							<input type="radio" id="rdo-male" class="gender" name="gender" value="male" > 
							
							<label for="rdo-female">여</label> 
							<input type="radio" id="rdo-female" class="gender" name="gender" value="female" > 

						</div>

						<!-- 약관동의 -->
						<div class="form-group">
							<span class="form-text">약관동의</span> 
							
							<input type="checkbox" id="chk-agree" value="" name="">
							<label for="chk-agree">서비스 약관에 동의합니다.</label> 
						</div>
						
						<!-- 버튼영역 -->
		                <div class="button-area">
		                    <button type="submit" id="btn-submit">회원가입</button>
		                </div>
					</form>
					
					<a id="naver" href="https://www.naver.com/">네이버</a>
				</div>
				<!-- //joinForm -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>


</html>