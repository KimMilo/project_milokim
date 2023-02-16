<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
	    		<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">로그인 화면</button>
	  		</div>
			<div class="tab-content" id="nav-tabContent">
				<c:url var="loginUrl" value="/login" />
				<form action="${loginUrl }" method="post">
					<div>
						<label class="col-3 col-form-label">아이디</label>
						<div class="col-9">
							<input class="form-control" type="text" name="userId" value="${cookie.remember.value }">
							<c:if test="${not empty requestScope.error }" >
								<span style="color: red;">${requestScope.error }</span>
							</c:if>
						</div>
					</div>
					<div>
						<label class="col-3 col-form-label">패스워드</label>
						<div class="col-9">
							<input class="form-control" type="password" name="password">
						</div>
					</div>
					<div>
						<div class="col-9 d-flex justify-content-between">
							<label class="mt-1" for="id_remember">아이디 기억하기</label>
							<c:choose>
								<c:when test="${empty cookie.remember }">
									<input type="checkbox" id="id_remember" name="remember">
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="id_remember" name="remember" checked>
								</c:otherwise>
							</c:choose>
							<div class="col-9 d-flex justify-content-end">
								<a class="page-link text-muted" href="<%=request.getContextPath() %>/findId" style="font-size: 12px;">아이디 찾기</a>					
							</div>
						</div>
					</div>
					<div class="mt-1">
						<button class="btn btn-outline-primary col-9" type="submit">로그인</button>
					</div>
				</form>
			</div>
		</section>
	</div>
</body>
</html>