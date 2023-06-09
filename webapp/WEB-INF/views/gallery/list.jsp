<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<!-- nav -->
		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>
		<!-- //nav -->
		
		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">

					<c:if test="${sessionScope.uInfo.no != null}">
						<button id="btnImgUpload">이미지올리기</button>
					</c:if>
					<div class="clear"></div>


					<ul id="viewArea">
						<!-- 이미지반복영역 // 반복문 예정 -->
						<c:forEach items="${galleryList}" var="galleryVo" >
						<li>
							<div class="view">
								<div>
									<input type ="hidden" class ="userNO" value="${galleryVo.userNo}">
									<input type ="hidden" class ="loginNO" value="${sessionScope.uInfo.no}">
									<input type ="hidden" class ="content" value="${galleryVo.content}">
									<img id="btnImg" class="imgItem" src="${pageContext.request.contextPath}/list/${galleryVo.saveName}">
									<input type ="hidden" class ="no" value="${galleryVo.no}">
									<div class="imgWriter">
										작성자: <strong>${galleryVo.name}</strong>
									</div>
								</div>
							</div>
						</li>
						</c:forEach>
						<!-- 이미지반복영역 -->
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->



	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form method="post" action="${pageContext.request.contextPath}/gallery/write" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> 
							<input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> 
							<input id="file" type="file" name="file" value="">
						</div>
						<div>
							<input type="hidden" name ="userNo" value ="${sessionScope.uInfo.no}">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
				<form method="" action="">
					<div class="modal-footer">
						<input id="modalNo" type="hidden" value="">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
					</div>
				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">
$(document).ready(function() {
	
	// 삭제 이벤트
	/* $(".btn-danger").on("click", function(){
	console.log("삭제버튼 클릭");
	
	//서버에 데이타 보내기
		//데이타 모으기
		 var no = $("#modalNo").val();
		
		//객체로 만들기
		var galleryVo = {
			no: no
		};
		
		console.log(guestVo);
	
		 $.ajax({
			url : "${pageContext.request.contextPath}/api/guestbook/remove",
			type : "post",
			// contentType : "application/json",
			data : {no:no},
			// 데이터 받은 후 
			dataType : "json",
			success : function(jsonResult){
				console.log(jsonResult);
				// 성공시 ㅊㅓㄹㅣㅎㅐㅇㅑ할 코드
				if(jsonResult > 0 ){
					console.log($("#t-"+guestVo.no).remove());
					$("#viewModal").modal("hide");
				}else{
					alert("비번틀림 ㅋ")
				}
			},
			error : function(XHR, status, error){
				// 실패
			}
		}); 
	}); */
	
	// 이미지 클릭 이벤트
  $("#viewArea").on("click", ".imgItem", function() {
    var imageUrl = $(this).attr("src");
    var content = $(this).siblings(".content").val();
    $("#viewModelImg").attr("src", imageUrl);
    $('#viewModal').modal('show');
    $("#viewModelContent").text(content);
    var userNo = $(this).siblings(".userNO").val();
    var loginNo = $(this).siblings(".loginNO").val();
 	
 	if(userNo != loginNo){
 		$("#btnDel").hide();
 	}else if(userNo == loginNo){
 		$("#btnDel").show();
 	}else{
 		console.log("5류입니다");
 	}
  });
	
	// 이미지 등록 이벤트
  $("#btnImgUpload").on("click", function() {
    console.log("버튼클릭");
    $('#addModal').modal('show');
  });
});
	
</script>




</html>

