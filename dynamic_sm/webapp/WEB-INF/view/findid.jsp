<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
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
    		<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">아이디 찾기</button>
  		</div>
		<div class="tab-content" id="nav-tabContent">
			<c:url var="findIdUrl" value="/findId" />
			<form action="${findIdUrl }" method="post">
				<div>
					<label class="mt-2 col-3 col-form-control">이메일 주소 입력</label>
					<input class="col-9 form-control" id="emailCheck" type="email" name="email" value="">
					<c:if test="${not empty requestScope.error }">
						<span style="color: red;">${requestScope.error }</span>
					</c:if>
					<c:if test="${not empty requestScope.success }">
						<span style="color: green;">${requestScope.success }</span>
					</c:if>
				</div>
				<div class="d-flex justify-content-between">
					<button class="mt-3 btn btn-outline-primary" id="find" type="submit">아이디 찾기</button>
					<button class="mt-3 btn btn-outline-primary" id="loginBtn" type="button">로그인</button>
				</div>
			</form>
		</div>
	</section>
</div>
<script>
$("#loginBtn").on("click", login)
function login(){
	location.href="<%=request.getContextPath()%>/login";
}
</script>
</body>
</html>