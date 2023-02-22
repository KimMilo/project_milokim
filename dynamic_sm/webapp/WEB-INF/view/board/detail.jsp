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
						<c:if test="${not empty requestScope.error }">
							<span style="color: red;">requestScope.error</span>
						</c:if>
					</div>
					<hr>
					<div class="mx-3 mt-2" style="white-space: pre-line;">
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
		<form>
			<div class="d-flex justify-content-start">
				<input class="form-control" type="text" name="comment" style="width: 500px;">
				<button class="ms-2 btn btn-outline-secondary" type="button" id="commentBtn">댓글</button>	
			</div>
			<div class="commentLine">
			
			</div>
		</form>
	</section>
</div>
<script>

$("#commentBtn").on("click", addComment);
function addComment(element) {
    let e = $(element);
    let tagText = $(element).value;

    let addressForm = $(tagText);
    addressForm.find("input, select").on("change", function(e) { saveButtonEnable(e, element.nextElementSibling); });
    e.parent().before(addressForm);
}
</script>
</body>
</html>