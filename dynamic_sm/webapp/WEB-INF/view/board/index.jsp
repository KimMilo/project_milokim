<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록 페이지</title>
<link type="text/css" rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous">
</script>
</head>
	<style type="text/css">
			th, td {
				text-align: center;
			}
		
			th {
				background-color: gray;
				color: ;
			}

	</style>
<body>
<%@ include file="/WEB-INF/view/module/top_nav.jsp" %>
<section class="container-sm mt-5" style="width: 960px;">
	<div class="nav nav-tabs" id="nav-tab" role="tablist">
  		<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">게시판 화면</button>
	</div>
	<div class="tab-content" id="nav-tabContent">
		<c:url var="boardUrl" value="/board" />
		<div class="my-2 d-flex justify-content-between">
			<div>
			<form action="${boardUrl }">
				<select name="cnt" onchange="submit();">
					<option value="2" ${requestScope.paging.pageLimit eq 2 ? "selected" : ""}>2 개</option>
					<c:forEach var="num" begin="5" end="30" step="5">
						<option value="${num }" ${requestScope.paging.pageLimit eq num ? "selected" : "" }>${num } 개</option>
					</c:forEach>
				</select>
			</form>
			</div>
			<div class="my-2">
				<form action="<%=request.getContextPath()%>/board/add">
					<button class="btn btn-outline-secondary" type="submit">글쓰기</button>
				</form>
			</div>
		</div>
	</div>
	<table class="table">
		<colgroup>
			<col style="width: 150px;">
			<col style="width: 350px;">
			<col style="width: 150px;">
			<col style="width: 250px;">
			<col style="width: 150px;">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일</th>
				<th scope="col">조회수</th>
			</tr>
		</thead>
		<tbody class="table-group-divider">
			<c:forEach var="data" items="${requestScope.paging.page }">
			<tr>
				<c:choose>
					<c:when test="${data.btype eq 'N' }">
						<th scope="row" style="background-color: rgb(255,204,229); color: red;">${data.id }</th>
						<td style="background-color: rgb(255,204,229); color: red;"><a style="color: red;" href="${boardUrl }/detail?id=${data.id }">${data.title }</a></td>
						<td style="background-color: rgb(255,204,229); color: red;">${data.writer }</td>
						<td style="background-color: rgb(255,204,229); color: red;">${data.createDate }</td>
						<td style="background-color: rgb(255,204,229); color: red;">${data.viewCnt }</td>
					</c:when>
					<c:otherwise>
						<th style="color: black;" scope="row">${data.id }</th>
						<td><a style="color: black;" href="${boardUrl }/detail?id=${data.id }">${data.title }</a></td>
						<td>${data.writer }</td>
						<td>${data.createDate }</td>
						<td>${data.viewCnt }</td>
					</c:otherwise>
				</c:choose>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<ul class="mt-2 pagination">
			<c:set var="pageNumber" value="${empty param.p ? 1 : param.p }" />
			<c:choose>
				<c:when test="${requestScope.paging.prevPage eq - 1 }">
					<li class="page-item disabled"><a class="page-link">prev</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${boardUrl }?p=${requestScope.paging.prevPage }">prev</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="pNum" items="${requestScope.paging.pageList }">
				<li class="page-item ${pNum eq pageNumber ? 'active' : '' }"><a class="page-link" href="${boardUrl }?p=${pNum }">${pNum }</a></li>
			</c:forEach>
			<c:choose>
				<c:when test="${requestScope.paging.nextPage eq - 1 }">
					<li class="page-item disabled"><a class="page-link">next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${boardUrl }?p=${requestScope.paging.nextPage}">next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</section>
</body>
</html>