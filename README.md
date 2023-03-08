# 스터디 공유 게시판 [놀면뭐해 공부하자]

## Contents 
1. 개요
2. 사용기술 및 개발환경
3. 프로젝트 기능 구현
4. 주요기능
5. Document
***
## 개요
* 스터디 관련 공유 게시판
* 위드코로나와 함께 자격증 공부
***

## 사용기술 및 개발환경

Language | Java11, HTML5, Javascript, jQuery, Servlet
------------ | ------------- 
Browser | Chrome, Whale 
Web Application Server | Apache Tomcat 9.0
Database|Oracle DB
Framework|Spring, MyBatis, Ajax, jQuery, Bootstrap
설계분석도구|erdCloud, ppt, starUML
IDE|STS 3.9.12 / DBeaver, sqldeveloper
Version Control System|Git / github
***
## 프로젝트 기능 구현

*  #### 고성화
    # 전체 페이지
       ▷ 게시판 조회(상세조회 포함) 외의 모든 페이지는 회원만 접근 가능
		 ▷ 회원 접근 권한 session 정보 활용
 
    *  서비스별 기능
	 ▷ 회원가입 및 계정
	    - 로그인 : 유효성체크 및 아이디 기억 / 아이디 찾기
	    - 로그아웃
	    - 회원가입 : 아이디 중복 체크 및 필수입력 유효성체크
	    - 개인정보 수정 및 탈퇴 : 탈퇴 시 비밀번호 확인
	▷ 게시판 관리
	- 조회 및 상세조회 : 조회건수 및 페이징
	- 게시글 등록 : 로그인 시에만 화면 제공
	- 게시글 수정 : 등록 계정에게만 화면 제공
	- 게시글 삭제 : 등록 계정에게만 화면 제공
	- 댓글 관리 : 로그인 시에만 댓글등록 가능(대대댓글까지 지원)
	             등록 계정에 따라 수정 및 삭제권한 제공, 페이징
	▷ 즐겨찾기 관리
	- 즐겨찾기 조회 : 로그인 시 해당 계정의 즐겨찾기 조회
	- 즐겨찾기 등록/수정/삭제 : 로그인 시 등록, 수정 및 삭제권한
***
## 주요 기능
* 
![메인](https://user-images.githubusercontent.com/115056845/223767627-098cf001-f655-4b1d-a20d-b992b7098bad.png)
![회원가입](https://user-images.githubusercontent.com/115056845/223767807-c595acb3-4117-418e-9671-4cbecfb75fd0.png)
![로그인](https://user-images.githubusercontent.com/115056845/223767838-4d69b992-74df-4d1b-be28-2f6840947901.png)
![아이디찾기](https://user-images.githubusercontent.com/115056845/223767904-adcc1f78-705a-4c7f-aaae-7349964f6361.png)
![개인정보-수정](https://user-images.githubusercontent.com/115056845/223768063-ed1a5bd5-064b-4824-880e-85d01c876285.png)
![개인정보-탈퇴](https://user-images.githubusercontent.com/115056845/223768094-97d1ebd4-4c5c-4cd6-b501-785d7e34b1ce.png)
![게시판-전체조회](https://user-images.githubusercontent.com/115056845/223768197-38ea8b9f-00d6-4341-adf7-79a56d635ab5.png)
![게시글-등록](https://user-images.githubusercontent.com/115056845/223768242-73c272f2-0241-4d02-af9e-a98e34342e1f.png)
![게시판-상세(수정 및 삭제)](https://user-images.githubusercontent.com/115056845/223768312-d3a5dbd6-925d-4c99-ba78-6f0ab08e383d.png)
![게시판-수정](https://user-images.githubusercontent.com/115056845/223768358-828fdbcc-dbb9-440f-9e3b-1af08a7b78ef.png)
![게시글-상세(댓글-대댓글, 수정 및 삭제)](https://user-images.githubusercontent.com/115056845/223768461-f0fd9a6d-c34f-4bd3-8d16-8ce269c3cb71.png)
![즐겨찾기-조회(수정 및 삭제)](https://user-images.githubusercontent.com/115056845/223768596-c1a410df-79ac-4097-9e59-217247fffa12.png)
![즐겨찾기-수정](https://user-images.githubusercontent.com/115056845/223768605-b6ad0b0c-5923-43bc-b4a1-76110daaadc3.png)
***
## Document
1. 유스케이스


2. ERD


3. SQL문  
[Dclass_almin_almin2021.txt](https://github.com/Seonhea/AlMin/files/7760493/Dclass_almin_almin2021.txt)

4. 보고서 및 발표PPT  
[[알바의 민족] 기획보고서.pdf](https://github.com/Seonhea/AlMin/files/7760484/default.pdf)  
[[알바의 민족] DB설계보고서.pdf](https://github.com/Seonhea/AlMin/files/7760485/DB.pdf)  
[[알바의 민족] 클래스 설계보고서.pdf](https://github.com/Seonhea/AlMin/files/7760486/default.pdf)  
[[알바의 민족] 파이널 발표자료.pdf](https://github.com/Seonhea/AlMin/files/7760479/default.pdf)

