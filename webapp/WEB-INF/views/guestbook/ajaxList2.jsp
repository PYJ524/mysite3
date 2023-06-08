<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
	<!-- 부트스트랩 css -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css">
	
<!-- jquery -->	
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<!-- 부트스트랩 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>
		<!-- //nav -->

		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li><a href="${pageContext.request.contextPath}/guestbook/guestbookList">일반방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/api/guestbook/addList">AJAX방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/api/guestbook/addList2">AJAX방명록2</a></li>
			</ul>
		</div>
		<!-- //aside -->
		<div id="content">
			<div id="content-head">
				<h3>AJAX방명록</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>방명록</li>
						<li class="last">AJAX방명록</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="guestbook">
				<!--  <form action="${pageContext.request.contextPath}/api/guestbook/add" method="get">-->
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></th>
								<td><input id="input-pass" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea id ="input-content" name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button id ="btnSubmit" type="submit">등록</button></td>
							</tr>
						</tbody>
					</table>
					<!-- //guestWrite -->
					<input type="hidden" name="action" value="add">
				<!-- </form>-->
				<div id="guestbookList">
					
				</div>
				<!-- //guestRead -->
			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->
	
	<!-- 삭제폼 모달창 -->
	<!-- Button trigger modal -->
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">삭제 모달창</h4>
				</div>
				<div class="modal-body">
					<input id="modalPassword" type ="password" name="password"><br>
					<input id="modalNo" type ="text" name="no" value=""> 
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫긔</button>
					<button id="btnDel" type="button" class="btn btn-danger" >삭졔</button>
				</div>
			</div>
		</div>
	</div>

<!-- 삭제폼 모달창 -->
</body>
<script type="text/javascript">

// 돔생성 완성
$(document).ready(function(){
	fetchList();
});

//방명록 저장 버튼 클릭할 때
$("#btnSubmit").on("click",function(){
	console.log("버튼클릭")

	var name = $("[name='name']").val();
	var password = $("[name='password']").val();
	var content = $("[name='content']").val();
	
	var guestbookVo = {
		name: name,
		password: password,
		content: content
	};
	
	var str = JSON.stringify(guestbookVo);

	console.log(str);
	
	// ajax통신 -> 요청은 같은 기술 데이터 응답에만 온다
	
	$.ajax({
		url : "${pageContext.request.contextPath}/api/guestbook/add2",
		type : "post",
		contentType : "application/json",
		data : str,
				
		// 데이터 받은 후 
		dataType : "json",
		success : function(jsonResult){
		console.log(jsonResult);
			// 성공시 ㅊㅓㄹㅣㅎㅐㅇㅑ할 코드
			
			if(jsonResult.result == "success"){
				// 정상 처리
				render(jsonResult.data, "up");	// 리스트에 추가
				
				//등록폼 비우기
				$("[name='name']").val("");
				$("[name='password']").val("");
				$("[name='content']").val("");
				
			}else{
				// 오류 처리
				
			}
		},
		error : function(XHR, status, error){
			// 실패
			
		}
		
	}); 
});

function fetchList(){
	// 전체리스트 호출 가져오기
	// 그리기
	$.ajax({
		url: "${pageContext.request.contextPath}/api/guestbook/list",
		type: "post",
		
		dataType: "json",
		success: function(result){
			console.log(result)
			/* 성공시 처리해야 될 코드 작성 */
			for(var i =0; i<result.data.length; i++){
				render(result.data[i], "down");
			}
		},
		error: function(){
			
		}
	});
	
	
}

// 방명록 리스트 그리기
function render (guestbookVo, dir){
	var str = "";
	str += ' <table id="t-'+ guestbookVo.no +'" class="guestRead">';
	str += ' 	<colgroup>';
	str += ' 		<col style="width: 10%;">';
	str += ' 		<col style="width: 40%;">';
	str += ' 		<col style="width: 40%;">';
	str += ' 		<col style="width: 10%;">';
	str += ' 	</colgroup>';
	str += ' 	<tr>';
	str += ' 		<td>'+ guestbookVo.no +'</td>';
	str += ' 		<td>'+ guestbookVo.name +'</td>';
	str += ' 		<td>'+ guestbookVo.regDate +'</td>';
	str += ' 		<td><button type="button" class="btn btn-danger btn-sm btnModal" data-delno="'+ guestbookVo.no +'">삭제	</button>';
	str += ' 	</tr>';
	str += ' 	<tr>';
	str += ' 		<td colspan=4 class="text-left">'+ guestbookVo.content +'</td>';
	str += ' 	</tr>';
	str += ' </table>';
	
	if(dir == 'up'){
		$("#guestbookList").prepend(str);
	}else if(dir == 'down'){
		$("#guestbookList").append(str);
	}else{
		console.log("에러요");
	}
}


</script>

</html>