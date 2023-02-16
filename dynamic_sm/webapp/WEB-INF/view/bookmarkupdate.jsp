<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--@ page import="model.dto.BookmarkDTO" 
	 => JSTL 사용으로 불필요--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%! public String title="즐겨찾기 수정"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title %></title>
<link type="text/css" rel="stylesheet" href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous">
</script>
</head>
<body>
	<%-- # 기존버전 
	<a href="../bookmark">즐겨찾기</a>
	<h2>수정 폼</h2>
	<form action="./update" method="post">
	<div>
		<input type="hidden" name="id" value="<%=((BookmarkDTO)request.getAttribute("data")).getId() %>">
	</div>
	<div>
		<label>수정할 URL 주소 : </label>
		<input type="text" name="url" value="<%=((BookmarkDTO)request.getAttribute("data")).getUrl() %>">
	</div>
	<div>
		<label>별칭 : </label>
		<input type="text" name="name" value="<%=((BookmarkDTO)request.getAttribute("data")).getName() %>">
	</div>
	<div>
		<button type="submit">수정</button>
	</div>
	</form>
	--%>
	
	<%-- # JSTL 버전 --%>
	<c:url var="bookmarkUrl" value="/bookmark" />
	<a href="${bookmarkUrl }">즐겨찾기</a>
	<h2>수정 폼</h2>
	<form action="./update" method="post">
	<div>
		<input type="hidden" name="id" value="${requestScope.data.id }">
	</div>
	<div>
		<label>수정할 URL 주소 : </label>
		<input type="text" name="url" value="${requestScope.data.url }">
	</div>
	<div>
		<label>별칭 : </label>
		<input type="text" name="name" value="${requestScope.data.name }">
	</div>
	<div>
		<button type="submit">수정</button>
	</div>
	</form>
	
</body>
</html>