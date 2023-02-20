<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판-수정</title>
<link type="text/css" rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous">
</script>
</head>
<body>
<%@ include file="/WEB-INF/view/module/top_nav.jsp" %>
	<h2>게시판 수정</h2>
		<c:set var="data" value="${requestScope.data }" />
		<div>
			<label>제목</label>
			<input type="text" name="title" value="${data.title}">		
		</div>
		<div>
			<label>내용</label>
			<textarea cols="25" rows="5" name="context">${data.context }</textarea>
		</div>
	<form action="../board/delete" id="deleteForm${data.id }">
		<input type="hidden" name="id" value="${data.id }">
	</form>	
</body>
</html>