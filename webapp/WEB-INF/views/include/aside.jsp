<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div id="aside">
		<h2>게시판</h2>
		<ul>
			<li><a href="${pageContext.request.contextPath}/board/list">일반 게시판</a></li>
			<li><a href="${pageContext.request.contextPath}/rBoard/list">댓글 게시판</a></li>
		</ul>
	</div>
</body>
