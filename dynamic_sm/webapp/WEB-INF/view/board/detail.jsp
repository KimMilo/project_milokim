<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판-상세</title>
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
			<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">게시판-상세</button>
		</div>
		<div class="tab-content" id="nav-tabContent">
			<c:url var="boardDetailUrl" value="/board/detail"/>
			<form action="${boardDetailUrl }" method="post">
				<div>
					<c:set var="data" value="${requestScope.data }" />
					<div class="mx-3 mt-2">
						${data.id } | ${data.title } | ${data.writer } | ${data.updateDate } | ${data.viewCnt }
						<button class="btn btn-outline-secondary btn-sm" type="button" onclick="location.href='<%=request.getContextPath()%>/board/update?id=${data.id }'">수정</button>
						<button class="btn btn-outline-secondary btn-sm" type="submit" form="deleteForm${data.id }">삭제</button>
						<button class="btn btn-outline-secondary btn-sm" type="button" onclick="location.href='<%=request.getContextPath() %>/board'">목록으로</button>
						<c:if test="${not empty requestScope.error }">
							<span style="color: red;">requestScope.error</span>
						</c:if>
					</div>
					<hr>
					<div class="mx-3 mt-2 mb-6" style="white-space: pre-line;">
						${data.context }				
					</div>
				</div>
			</form>
			<form action="<%=request.getContextPath() %>/board/delete" id="deleteForm${data.id }" method="post">
				<input type="hidden" name="id" value="${data.id }">
			</form>
		</div>
		<br>
		<br>
		<form action="<%=request.getContextPath() %>/board/comment" method="post">
			<div class="my-3 d-flex justify-content-start">
				<input type="hidden" name="bnum" value="${requestScope.data.id }"> 
				<input class="form-control" type="text" name="comment" style="width: 620px;">
				<button class="ms-2 btn btn-outline-secondary" type="submit">댓글</button>
			</div>
		</form>
		<c:forEach var="c" items="${requestScope.cmtPaging.page }">
			<div>
				<div class="mx-3 mt-2">
				<c:if test="${c.cLevel eq 0  }">
				<div style="background-color: rgb(255,204,204);">
					 ${c.writer } | ${c.context } | ${c.createDate }
					 <c:if test="${not empty sessionScope.login}">
						 <button class="addCmt ms-2 btn btn-outline-secondary btn-sm" type="submit">댓글</button>
					 </c:if>
					 <c:if test="${sessionScope.user.userId eq c.writer }">
					 	<button class="updateCmt btn btn-outline-secondary btn-sm" type="button">수정</button>
						<button class="btn btn-outline-secondary btn-sm" type="submit" form="deleteForm${c.id }">삭제</button>
						<form action="<%=request.getContextPath() %>/board/comment/delete" id="deleteForm${c.id }" method="post">
							<input type="hidden" name="id" value="${c.id }">
							<input type="hidden" name="bnum" value="${c.bnum }">
						</form>
					 </c:if>
				</div>
				<form class="ms-1" action="<%=request.getContextPath()%>/board/comment/double" method="post">
					<div class="my-1 d-flex justify-content-start">
						<input type="hidden" name="id" value="${c.id }">
					</div>
				</form>
				<form class="ms-1" action="<%=request.getContextPath()%>/board/comment/update" method="post">
					<div class="my-1 d-flex justify-content-start">
						<input type="hidden" name="id" value="${c.id }">
					</div>
				</form>	
				</c:if>
				<c:if test="${c.cLevel eq 1 }">	
					<div style="background-color: rgb(204,255,204);">
						└─ ${c.writer } | ${c.context } | ${c.createDate }
						 <c:if test="${not empty sessionScope.login}">
							 <button class="addCmt ms-2 btn btn-outline-secondary btn-sm" type="submit">댓글</button>
						 </c:if>
						 <c:if test="${sessionScope.user.userId eq c.writer }">
							<button class="updateCmt btn btn-outline-secondary btn-sm" type="button">수정</button>
							<button class="btn btn-outline-secondary btn-sm" type="submit" form="deleteForm${c.id }">삭제</button>
							<form action="<%=request.getContextPath() %>/board/comment/delete" id="deleteForm${c.id }" method="post">
								<input type="hidden" name="id" value="${c.id }">
								<input type="hidden" name="bnum" value="${c.bnum }">
							</form>
						 </c:if>
					</div>		
					<form action="<%=request.getContextPath()%>/board/comment/double" method="post" style="position:relative; left: 30px;">
						<div class="my-1 d-flex justify-content-start">
							<input type="hidden" name="id" value="${c.id }">
						</div>
					</form>
					<form class="ms-1" action="<%=request.getContextPath()%>/board/comment/update" method="post" style="position:relative; left: 30px;">
						<div class="my-1 d-flex justify-content-start">
							<input type="hidden" name="id" value="${c.id }">
						</div>
					</form>		
				</c:if>
				<c:if test="${c.cLevel eq 2 }">
					<div style="background-color: rgb(204,229,255);">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─ ${c.writer } | ${c.context } | ${c.createDate }
					 	<c:if test="${not empty sessionScope.login}">
							 <button class="addCmt ms-2 btn btn-outline-secondary btn-sm" type="submit">댓글</button>
						 </c:if>
						 <c:if test="${sessionScope.user.userId eq c.writer }">
						 	<button class="updateCmt btn btn-outline-secondary btn-sm" type="button">수정</button>
							<button class="btn btn-outline-secondary btn-sm" type="submit" form="deleteForm${c.id }">삭제</button>
							<form action="<%=request.getContextPath() %>/board/comment/delete" id="deleteForm${c.id }" method="post">
								<input type="hidden" name="id" value="${c.id }">
								<input type="hidden" name="bnum" value="${c.bnum }">
							</form>
					 	</c:if>
					</div>
					<form action="<%=request.getContextPath()%>/board/comment/double" method="post" style="position:relative; left: 60px;">
						<div class="my-1 d-flex justify-content-start">
							<input type="hidden" name="id" value="${c.id }">
						</div>
					</form>	
					<form class="ms-1" action="<%=request.getContextPath()%>/board/comment/update" method="post" style="position:relative; left: 60px;">
						<div class="my-1 d-flex justify-content-start">
							<input type="hidden" name="id" value="${c.id }">
						</div>
					</form>	
				</c:if>
				<c:if test="${c.cLevel gt 2 }">
					<div style="background-color: rgb(224,224,224);">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─ ${c.writer } | ${c.context } | ${c.createDate }
					</div>
				</c:if>
				</div>			 
			</div>	
		</c:forEach>
		<div>
			<ul class="mt-2 pagination">
			<c:set var="pageNumber" value="${empty param.p ? 1 : param.p }" />
			<c:choose>
				<c:when test="${requestScope.cmtPaging.prevPage eq - 1 }">
					<li class="page-item disabled"><a class="page-link">prev</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${boardDetailUrl }?id=${data.id }&p=${requestScope.cmtPaging.prevPage }">prev</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="pNum" items="${requestScope.cmtPaging.pageList }">
				<li class="page-item ${pNum eq pageNumber ? 'active' : '' }"><a class="page-link" href="${boardDetailUrl }?id=${data.id }&p=${pNum }">${pNum }</a></li>
			</c:forEach>
			<c:choose>
				<c:when test="${requestScope.cmtPaging.nextPage eq - 1 }">
					<li class="page-item disabled"><a class="page-link">next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${boardDetailUrl }?id=${data.id }&p=${requestScope.cmtPaging.nextPage}">next</a></li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
	</section>
</div>
<script>
$(".addCmt").on("click", addInput);
function addInput(element){
	var e = element.target;

	if(e.parentElement.nextElementSibling.firstElementChild.firstElementChild.nextElementSibling != null){
		e.parentElement.nextElementSibling.firstElementChild.ElementChildren.remove();
	}
	
	var input = document.createElement("input");
	input.type = "text";
	input.name = "comment";
	input.className = "form-control";
	input.style.width = "420px";
	
	var input2 = document.createElement("input");
	input2.type = "hidden";
	input2.name = "bnum";
	input2.value = "${requestScope.data.id }";
	
	var button = document.createElement("button");
	button.className = "ms-2 btn btn-outline-primary btn-sm";
	button.type = "submit";
	button.innerText = "저장";
	
	e.parentElement.nextElementSibling.firstElementChild.append(input);
	e.parentElement.nextElementSibling.firstElementChild.append(input2);
	e.parentElement.nextElementSibling.firstElementChild.append(button);
}


$(".updateCmt").on("click", updateInput);
function updateInput(element){
	var e = element.target;

	if(e.parentElement.nextElementSibling.nextElementSibling.firstElementChild.firstElementChild.nextElementSibling != null){
		e.parentElement.nextElementSibling.nextElementSibling.firstElementChild.ElementChildren.remove();
	}
	
	var input = document.createElement("input");
	input.type = "text";
	input.name = "comment";
	input.className = "form-control";
	input.style.width = "420px";
	
	var input2 = document.createElement("input");
	input2.type = "hidden";
	input2.name = "bnum";
	input2.value = "${requestScope.data.id }";
	
	var button = document.createElement("button");
	button.className = "ms-2 btn btn-outline-danger btn-sm";
	button.type = "submit";
	button.innerText = "수정";
	
	e.parentElement.nextElementSibling.nextElementSibling.firstElementChild.append(input);
	e.parentElement.nextElementSibling.nextElementSibling.firstElementChild.append(input2);
	e.parentElement.nextElementSibling.nextElementSibling.firstElementChild.append(button);
}

</script>
</body>
</html>