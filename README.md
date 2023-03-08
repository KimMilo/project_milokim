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
    * 전체 페이지
       - 게시판 조회(상세조회 포함) 외의 모든 페이지는 회원만 접근 가능
       - 회원 접근 권한 session 정보 활용
 
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
![0 메인](https://user-images.githubusercontent.com/115056845/223771633-0ab355e5-f819-42da-8ec5-c242c03586a5.png)<br>
![1 회원가입](https://user-images.githubusercontent.com/115056845/223771640-4d253bc7-4893-4bfd-9a0e-a66d922d955d.png)<br>
![2 로그인](https://user-images.githubusercontent.com/115056845/223771644-96253b2b-163d-470e-a2d2-96d17eaa53a1.png)<br>
![3 아이디찾기](https://user-images.githubusercontent.com/115056845/223771647-c5de15d3-9f3c-4ee7-aef5-4dbc0fe2d2ca.png)<br>
![4 개인정보-수정](https://user-images.githubusercontent.com/115056845/223771649-050a8bf4-24f2-470e-9eda-c2df01fdf031.png)<br>
![5 개인정보-탈퇴](https://user-images.githubusercontent.com/115056845/223771650-604c63c8-55a2-4d22-8e1e-790afb49690f.png)<br>
![6 게시판-전체조회](https://user-images.githubusercontent.com/115056845/223771653-0b533243-19bf-405c-8751-a23cde2ddfd3.png)<br>
![7 게시글-등록](https://user-images.githubusercontent.com/115056845/223771655-4819ecfe-28dc-4102-8fb7-6d5d9a54bc35.png)<br>
![8 게시판-상세(수정 및 삭제)](https://user-images.githubusercontent.com/115056845/223771657-aa47bca4-7f32-44a1-a19f-b43af3e02d1b.png)<br>
![9 게시판-수정](https://user-images.githubusercontent.com/115056845/223771659-74708e3f-4567-4534-bad8-7de436164269.png)<br>
![10 게시글-상세(댓글-대댓글, 수정 및 삭제)](https://user-images.githubusercontent.com/115056845/223771661-94b6cc54-7e67-49d7-a6a4-ead04c86fa6a.png)<br>
![11 즐겨찾기-조회(수정 및 삭제)](https://user-images.githubusercontent.com/115056845/223771662-a486a639-e3d7-4862-b8ee-b2df893718e3.png)<br>
![12 즐겨찾기-수정](https://user-images.githubusercontent.com/115056845/223771664-ffcbc810-be7b-4893-8f13-a157d60946a4.png)<br>
***
## Document
1. 유스케이스
![UseCaseDiagram(semi_project)](https://user-images.githubusercontent.com/115056845/223771810-7aebcb25-38ee-42f5-a3ad-f1160dae6a12.png)

2. ERD
![ERDiagram(semi_project)](https://user-images.githubusercontent.com/115056845/223771796-085b6b57-1244-4ece-95fa-a13271c54189.png)


3. SQL문  
[web_admin_admin(dynamic_sm).txt](https://github.com/KimMilo/project_milokim/files/10922789/web_admin_admin.dynamic_sm.txt)

