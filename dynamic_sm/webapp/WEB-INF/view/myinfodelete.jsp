<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보-탈퇴</title>
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
    		<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">개인정보 수정</button>
  		</div>
		<div class="tab-content" id="nav-tabContent">
			<c:url var="myinfoDeleteUrl" value="/myinfo/delete" />
			<form action="${myinfoDeleteUrl }" method="post">
				<div>
					<label class="mt-2 col-3 col-form-control">아이디</label>
					<input class="col-9 form-control" type="text" name="id" value="${sessionScope.user.userId }" disabled>
				</div>
				<div>
					<label class="mt-2 col-3 col-form-control">패스워드 재입력</label>
					<input class="col-9 form-control" type="password" name="password" value="">
				</div>
				<div>
					<button class="mt-3 btn btn-outline-danger" type="submit">탈퇴</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>