<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--@ page import="java.util.List, model.dto.BookmarkDTO" 
	=> JSTL사용으로 불필요
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%! public String title="즐겨찾기"; %>
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
	<%-- 기존 버전 
	<a href="./">메인</a>
	<h2>즐겨찾기</h2>
	<form action="./bookmark" method="post">
		<div>
			<label>URL 주소 :</label>
			<input type="text" name="url">
		</div>
		<div>
			<label>별칭 :</label>
			<input type="text" name="name">
		</div>
		<div>
			<button type="submit">등록</button>
		</div>
		</form>
		<ul>
		<% for(BookmarkDTO d: (List<BookmarkDTO>)request.getAttribute("data")) { %>
			<li>
				<a href="<%=d.getUrl() %>" target="_blank"><%=d.getName() %></a>
				<button type="button" onclick="location.href='./bookmark/update?id=<%=d.getId() %>'">수정</button>
				<button type="submit" form="deleteForm<%=d.getId() %>">삭제</button>
				<form id="deleteForm<%=d.getId() %>" action="./bookmark/delete" method="post">
				<!-- button에서 form 이름을 지정하면 form id명과 매칭해서 연결할 수 있음. -->
					<input type="hidden" name="id" value="<%=d.getId() %>">
				</form>
			</li>
		<% } %>
	</ul>
	--%>
	
	<%-- # JSTL 버전 --%>
	<%@ include file="./module/top_nav.jsp" %>
	<h2>즐겨찾기</h2>
	<c:url var="bookmarkUrl" value="/bookmark" />
	<form action="${bookmarkUrl }" method="post">
		<div>
			<label>URL 주소 :</label>
			<input type="text" name="url">
		</div>
		<div>
			<label>별칭 :</label>
			<input type="text" name="name">
		</div>
		<div>
			<button type="submit">등록</button>
		</div>
		</form>
		<div>
			<form action="${bookmarkUrl }">
				<select name="cnt" onchange="submit();">
					<option value="2" ${requestScope.paging.pageLimit eq 2 ? "selected" : "" }>2 개</option>
					<c:forEach var="num" begin="5" end="30" step="5">
						<option value="${num }" ${requestScope.paging.pageLimit eq num ? "selected" : "" }>${num } 개</option>
					</c:forEach>
				</select>
			</form>
		</div>
		<ul>
		<c:url var="bookmarkDeleteUrl" value="bookmark/delte" />
		<c:forEach var="d" items="${requestScope.paging.page }">
			<c:url var="bookmarkUpdateUrl" value="bookmark/update">
				<c:param name="id" value="${d.id }" />
			</c:url>
			<li>
				<a href="${d.url }" target="_blank">${d.name }</a>
				<%-- button type="button" onclick="location.href='./bookmark/update?id=${d.id}'">수정</button 
				     c:url 활용 --%>
				
				<button type="button" onclick="location.href='${bookmarkUpdateUrl}'">수정</button>
				<button type="submit" form="deleteForm${d.id }">삭제</button>
				<%-- form id="deleteForm${d.id }" action="./bookmark/delete" method="post"
				     c:url 활용 --%>
				
				<%-- form id="deleteForm${d.id }" action="${bookmarkDeleteUrl }" method="post"
				     기존 c:url var="bookmarkUrl" 이용해서 활용 --%>
				    <form id="deleteForm${d.id }" action="${bookmarkUrl }/delete" method="post">
					<input type="hidden" name="id" value="${d.id }">
				</form>
			</li>
		</c:forEach>
		</ul>
		<ul class="pagination">
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
</body>
</html>