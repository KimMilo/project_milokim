<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세 페이지</title>
<link type="text/css" rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous">
</script>
</head>
<body>
<%@ include file="/WEB-INF/view/module/top_nav.jsp" %>
	<h2>게시판 상세</h2>
	<c:url var="boardDetailUrl" value="/board/detail" />
	<form action="${boardDetailUrl }" method="post">
		<div>
			<c:set var="data" value="${requestScope.data }" />
			${data.id } | ${data.title } | ${data.writer } | ${data.updateDate } | ${data.viewCnt }
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="location.href='/board/update'">수정</button>
			<button class="btn btn-outline-secondary btn-sm" type="submit" form="deleteForm${data.id }">삭제</button>
			<form action="./delete" id="deleteForm${data.id }">
				<input type="hidden" name="id" value="${data.id }">
			</form>
		</div>
	</form>
</body>
</html>