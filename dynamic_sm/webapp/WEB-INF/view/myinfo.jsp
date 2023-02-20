<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보-수정</title>
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
			<c:url var="myinfoUpdateUrl" value="/myinfo" />
			<form action="${myinfoUpdateUrl }" method="post" onsubmit="return validCheck(this);">
				<div>
					<label class="mt-2 col-3 col-form-control">아이디</label>
					<input class="col-9 form-control" type="text" name="userId" value="${sessionScope.user.userId }" disabled>
				</div>
				<div>
					<label class="mt-2 col-3 col-form-control">현재 패스워드</label>
					<input class="col-9 form-control" type="password" name="password" value="">
					<span style="color: red;">${requestScope.error }</span>
				</div>
				<div>
					<label class="mt-2 col-3 col-form-control">변경 할 패스워드</label>
					<input class="col-9 form-control" type="password" name="changePass" value="">
				</div>
				<div>
					<label class="mt-2 col-3 col-form-control">이메일 주소</label>
					<input class="col-9 form-control" type="email" name="email" value="${sessionScope.user.email }">
				</div>
				<div>
					<button class="mt-3 btn btn-outline-danger" type="submit">수정</button>
				</div>
			</form>
		</div>
	</section>
</div>
<script>
window.onload = function() {
    form = document.forms[1];

    form.changePass.addEventListener("change", function(e) { lengthValid(e, 5, 16); });
};
function lengthValid(event, min, max) {
    var inputElement = event.target;
    if(inputElement.value.length < min || inputElement.value.length > max) {
        errorMessage(inputElement, min + "~" + max + "자로 사용해야 합니다.");
    } else {
        validMessage(inputElement, "정상입니다.");
    }
}
function errorMessage(element, message) {
    var divError = document.createElement("div");
    if(element.parentElement.lastElementChild.classList.contains("invalid-feedback") ||
       element.parentElement.lastElementChild.classList.contains("valid-feedback")) {
        divError = element.parentElement.lastElementChild;
    }
    divError.innerText = message;
    divError.className = "invalid-feedback";
    element.parentElement.append(divError); 
    element.classList.remove("is-valid");
    element.classList.add("is-invalid");

}
function validMessage(element, message) {
    var divValid = document.createElement("div");
    if(element.parentElement.lastElementChild.classList.contains("invalid-feedback") ||
       element.parentElement.lastElementChild.classList.contains("valid-feedback")) {
        divValid = element.parentElement.lastElementChild;
    }
    divValid.innerText = message;
    divValid.className = "valid-feedback";
    element.parentElement.append(divValid);
    element.classList.remove("is-invalid");
    element.classList.add("is-valid");

}
function validCheck(form) {
	passwordCheckValid = form.changePass.classList.contains("is-valid") === true ? true : false;
    
    if(passwordCheckValid === true) {
        return true;
    } else {
    	alert("정확하게 입력 후 수정해주세요~")
        return false;
    }
}
</script>	
</body>
</html>