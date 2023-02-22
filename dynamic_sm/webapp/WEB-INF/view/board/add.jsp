<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판-작성</title>
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
    		<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">게시판-작성</button>
  		</div>
  		<form action="<%=request.getContextPath() %>/board/add" method="post">
			<div class="mt-2">
				<label class="mt-2 col-3 col-form-control">아이디</label>
				<input class="col-9 form-control" type="text" name="userId" value="${sessionScope.user.userId }" disabled>
			</div>
			<div>
				<label class="mt-2 col-3 col-form-label">타입</label>
				<select class="form-select" aria-label="Default select example" name="btype">
					<option value="N">공지게시글</option>
					<option value="B" selected>일반게시글</option>
				</select>
			</div>
			<div>
				<label class="mt-2 col-3 col-form-label">제목</label>
				<input class="col-9 form-control" type="text" name="title">
			</div>
			<div>
				<label class="mt-2 col-3 col-form-label">내용</label>
				<textarea class="form-control" rows="25" name="context"></textarea>
			</div>
			<div class="mt-3 d-grid">
				<button class="btn btn-outline-success" type="submit">작성</button>
			</div>
			<div>
				<c:if test="${not empty requestScope.error }">
					<span style="color: red;">${requestScope.error }</span>
				</c:if>
			</div>
  		</form>
	</section>
</div>	
</body>
</html>