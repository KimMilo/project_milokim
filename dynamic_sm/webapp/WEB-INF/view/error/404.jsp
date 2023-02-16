<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 - 해당 페이지는 존재하지 않습니다.</title>
</head>
<body>
	<%-- 서버에서 제공되는 에러페이지에는 클라이언트가 알 필요가 없는 정보(에러코드, 서버종류 등)가 
	     노출 되기 때문에 별도의 에러메시지를 만들고자 할 경우 html 또는 jsp파일로 만들고
	     web.xml <error-page>태그를 이용하여 등록한다. --%>
	<h1>404 에러</h1>
	<h2>해당 페이지는 존재하지 않습니다.</h2>
	<h3>URL 주소를 다시 확인하세요.</h3>
</body>
</html>