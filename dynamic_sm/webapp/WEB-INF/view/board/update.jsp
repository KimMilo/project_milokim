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
<div class="container-fluid">
	<section class="container-sm mt-5" style="width: 960px;">
  		<div class="nav nav-tabs" id="nav-tab" role="tablist">
    		<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">게시판-수정</button>
  		</div>
  		<c:set var="data" value="${requestScope.data }" />
  		<form action="<%=request.getContextPath()%>/board/update" method="post">
  			<div>
  				<input type="hidden" name="id" value="${param.id }">
  			</div>
	  		<div class="mt-2">
				<label class="mt-2 col-3 col-form-control">아이디</label>
				<input class="col-9 form-control" type="text" name="userId" value="${sessionScope.user.userId }" disabled>
			</div>
			<div>
				<label class="mt-2 col-3 col-form-label">타입</label>
				<select class="form-select" aria-label="Default select example" name="btype">
					<option value="N" ${data.btype eq 'N' ? "selected" : "" }>공지게시글</option>
					<option value="B" ${data.btype eq 'B' ? "selected" : "" }>일반게시글</option>
				</select>
			</div>
			<div class="mt-2">
				<label class="mt-2 col-3 col-form-control">제목</label>
				<input class="form-control" type="text" name="title" value="${data.title}">		
			</div>
			<div class="mt-2">
				<label class="mt-2 col-3 col-form-control">내용</label>
				<textarea class="form-control" cols="25" rows="5" name="context">${data.context }</textarea>
			</div>
			<div>
				<button class="mt-2 btn btn-outline-danger" type="submit">수정</button>
			</div>
			<div>
				<c:if test="${not empty requestScope.error }">
					<span style="color: red;">requestScope.error</span>
				</c:if>
			</div>
  		</form>
	</section>
</div>	
</body>
</html>