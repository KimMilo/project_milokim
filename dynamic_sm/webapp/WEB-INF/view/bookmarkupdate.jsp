<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%! public String title="즐겨찾기 수정"; %>
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
			<c:url var="bookmarkUpdateUrl" value="/bookmark/update" />
			<form action="${bookmarkUpdateUrl }" method="post">
				<div>
					<input type="hidden" name="id" value="${requestScope.data.id }">
				</div>
				<div class="mt-2">
					<label class="col-3 col-form-label">수정할 URL 주소</label>
					<input class="col-9 form-control" type="text" name="url" value="${requestScope.data.url }">
				</div>
				<div>
					<label class="col-3 col-form-label">별칭</label>
					<input class="col-9 form-control" type="text" name="name" value="${requestScope.data.name }">
				</div>
				<div>
					<button class="mt-2 btn btn-outline-secondary" type="submit">수정</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>