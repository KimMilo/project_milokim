<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%! public String title="즐겨찾기"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title><%=title %></title>
<link type="text/css" rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous">
</script>
</head>
<body>
<%@ include file="/WEB-INF/view/module/top_nav.jsp" %>
<div class="container-fluid">
	<section class="container-sm mt-5" style="width: 960px;">
  		<div class="nav nav-tabs" id="nav-tab" role="tablist">
    		<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">즐겨찾기 화면</button>
  		</div>
		<div class="tab-content" id="nav-tabContent">
			<c:url var="bookmarkUrl" value="/bookmark" />
			<form action="${bookmarkUrl }" method="post">
				<div>
					<label class="col-3 col-form-label">URL 주소</label>
					<input class="col-9 form-control" type="text" name="url">
				</div>
				<div>
					<label class="col-3 col-form-label">별칭</label>
					<input class="col-9 form-control" type="text" name="name">
				</div>
				<div class="d-grid">
					<button class="mt-2 btn btn-outline-primary" type="submit">등록</button>
				</div>
			</form>
			<div class="my-2">
				<form action="${bookmarkUrl }">
					<select name="cnt" onchange="submit();">
						<option value="2" ${requestScope.paging.pageLimit eq 2 ? "selected" : "" }>2 개</option>
						<c:forEach var="num" begin="5" end="30" step="5">
							<option value="${num }" ${requestScope.paging.pageLimit eq num ? "selected" : "" }>${num } 개</option>
						</c:forEach>
					</select>
				</form>
			</div>
		</div>
		<ul class="list-group">
			<c:url var="bookmarkDeleteUrl" value="bookmark/delte" />
			<c:forEach var="d" items="${requestScope.paging.page }">
				<c:url var="bookmarkUpdateUrl" value="bookmark/update">
					<c:param name="id" value="${d.id }" />
				</c:url>
				<li class="list-group-item">
					<div class="my-2 ms-auto d-flex justify-content-between">
						<div>
							<a href="${d.url }" target="_blank">${d.name }</a>										
						</div>
						<div>
							<button class="btn btn-outline-secondary" type="button" onclick="location.href='${bookmarkUpdateUrl}'">수정</button>
							<button class="btn btn-outline-secondary" type="submit" form="deleteForm${d.id }">삭제</button>							
							<form id="deleteForm${d.id }" action="${bookmarkUrl }/delete" method="post">
								<input type="hidden" name="id" value="${d.id }">
							</form>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
		<ul class="pagination mt-2">
			<c:set var="pageNumber" value="${empty param.p ? 1 : param.p }" />
			<c:choose>
				<c:when test="${requestScope.paging.prevPage eq - 1 }">
					<li class="page-item disabled"><a class="page-link">prev</a>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${bookmarkUrl }?p=${requestScope.paging.prevPage }">prev</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="pNum" items="${requestScope.paging.pageList }" >
				<li class="page-item ${pNum eq pageNumber ? 'active' : '' }"><a  class="page-link" href="${bookmarkUrl }?p=${pNum }">${pNum }</a></li>
			</c:forEach>
			<c:choose>
				<c:when test="${requestScope.paging.nextPage eq - 1 }">
					<li class="page-item disabled"><a class="page-link">next</a></li>			
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${bookmarkUrl }?p=${requestScope.paging.nextPage}">next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</section>
</div>
</body>
</html>