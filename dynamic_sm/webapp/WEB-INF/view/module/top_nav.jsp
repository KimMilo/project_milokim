<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상단메뉴</title>
</head>
<body>
<nav class="navbar navbar-expand-lg my-2 mx-2" style="background-color: #e3f2fd;">
  <div class="container-fluid">
    <a class="navbar-brand col-3" href="#" style="font-size: 30px; font-weight: bold;">SM_Board</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="font-size: 24px; font-weight: bold;">
        <li class="nav-item">
         	<a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>">메인</a>
        </li>
        <li class="nav-item">
        	<a class="nav-link" href="<%=request.getContextPath()%>/board">게시판</a>
        </li>
        <c:choose>
			<c:when test="${sessionScope.login }">
				<li class="nav-item">
         			 <a class="nav-link" href="<%=request.getContextPath()%>/bookmark">즐겨찾기</a>
        		</li>
        		<li class="nav-item">
         			 <a class="nav-link" href="<%=request.getContextPath()%>/logout">로그아웃</a>
        		</li>
        		<li class="nav-item dropdown">
          			<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            			개인정보
          			</a>
          			<ul class="dropdown-menu" style="left: 2em; z-index: 99999;">
            			<li class="nav-item" style="">
         			 		<a class="nav-link" href="<%=request.getContextPath()%>/myinfo">수정</a>
        				</li>
        				<li class="nav-item">
         			 		<a class="nav-link" href="<%=request.getContextPath()%>/myinfo/delete">탈퇴</a>
        				</li>
          			</ul>
       			 </li>
			</c:when>
			<c:otherwise>
				<li class="nav-item">
         			 <a class="nav-link" href="<%=request.getContextPath()%>/login">로그인</a>
        		</li>
        		<li class="nav-item">
         			 <a class="nav-link" href="<%=request.getContextPath()%>/join">회원가입</a>
        		</li>
			</c:otherwise>
		</c:choose>
      </ul>
      <form class="" role="search">
      	 <div class="row mt-2">
             <div class="ms-auto">
                 <div class="input-group">
                     <input class="form-control" type="text">
                     <button class="btn btn-outline-secondary" type="button">검색</button>
                </div>
               </div>
           </div>
      </form>
    </div>
  </div>
</nav>
</body>
</html>