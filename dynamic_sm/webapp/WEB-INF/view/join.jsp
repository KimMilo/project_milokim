<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
    		<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">회원가입 화면</button>
  		</div>
		<div class="tab-content" id="nav-tabContent">
			<c:url var="loginUrl" value="/login" />
			<form action="${joinUrl }" method="post" onsubmit="return validCheck(this);">
		<div>
			<div class="mt-2">
				<label class="col-form-label">아이디</label>
				<button class="btn btn-outline-secondary btn-sm" type="button" id="dupId">중복확인</button>
				<span id="dupIdFeedback"></span>
			</div>
			<input class="col-9 form-control" type="text" name="userId" id="idCheck">
			<c:if test="${not empty requestScope.error }">
				<span style="color: red;">${requestScope.error }</span>
			</c:if>
		</div>
		<div>
			<label class="mt-2 col-3 col-form-label">패스워드</label>
			<input class="col-9 form-control" type="password" name="password">
		</div>
		<div>
			<label class="mt-2 col-3 col-form-label">패스워드 재확인</label>
			<input class="col-9 form-control" type="password" name="passwordCheck">
		</div>
		<div>
			<label class="mt-2 col-3 col-form-label">이메일</label>
			<input class="col-9 form-control" type="email" name="email" value="${param.email }">
		</div>
		<div class="mt-3 d-grid">
			<button class="btn btn-outline-success" type="submit">가입</button>
		</div>
			</form>
		</div>
	</section>
</div>	
<script>
$('#dupId').click(checkDupId);
function checkDupId(){
	$.ajax({
			url: "<%=request.getContextPath()%>/dupid"
		  , type : "post"
		  , async : false
		  , data : {userId: $('#idCheck').val()}
		  , success: function(result){
			  if(result == "fail"){
				  $('#dupIdFeedback').html("중복아이디가 있습니다. 다시 입력 해주세요.");
				  $('#dupIdFeedback').css("color", "red");
				  $('#dupIdFeedback').css("font-size", "11px");
			  } else if(result == "ok"){
				  $('#dupIdFeedback').html("중복된 아이디는 없습니다.");
				  $('#dupIdFeedback').css("color", "green");
				  $('#dupIdFeedback').css("font-size", "11px");
			  }
		  }
		  , error: function(request, status, error){
			  alert(request.status);
		  }
	});
}


window.onload = function() {
    form = document.forms[1];
    form.userId.addEventListener("blur", function(e) { requiredValid(e); });
    form.userId.addEventListener("change", function(e) { lengthValid(e, 5, 10); });

    form.password.addEventListener("blur", function(e) { requiredValid(e); });
    form.password.addEventListener("change", function(e) { lengthValid(e, 5, 16); });
    form.passwordCheck.addEventListener("blur", function(e) { requiredValid(e); });
    form.passwordCheck.addEventListener("input", function(e) {passwordCheckValid(e, form.password)});
    form.password.addEventListener("change", function(e) { lengthValid(e, 5, 16); });
    form.email.addEventListener("blur", function(e) { requiredValid(e); });
};
function requiredValid(event) {
    var inputElement = event.target;
    if(inputElement.value === "") {
        errorMessage(inputElement, "필수 입력 입니다.");
    } 
}
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
    var userIdValid = form.userId.classList.contains("is-valid") === true ? true : false;
    var passwordValid = form.password.classList.contains("is-valid") === true ? true : false;
    var passwordCheckValid = form.passwordCheck.getAttribute("is-valid") === "true" ? true : false;
    
    if(userIdValid === true && passwordValid === true && passwordCheckValid === true) {
        return true;
    } else {
    	alert("모든 입력사항을 입력 후 가입 바랍니다.")
        return false;
    }
}
function passwordCheckValid(e, other) {
    var element = e.target;
    if(element.nextElementSibling !== null) {
        element.nextElementSibling.remove();
    }
    if(element.value === other.value) {
        let div = document.createElement("div");
        div.innerText = "비밀번호가 일치합니다."
        div.style.fontSize = "14px";
        div.style.marginBottom = "10px";
        div.style.color = "green";
        element.after(div);
        element.setAttribute("is-valid", true);

    } else {
        let div = document.createElement("div");
        div.innerText = "비밀번호가 일치하지 않습니다.";
        div.style.color = "red";
        div.style.fontSize = "14px";
        div.style.marginBottom = "10px";
        element.after(div);
        element.setAttirbute("is-valid", false);
    } 
}
</script>
</body>
</html>