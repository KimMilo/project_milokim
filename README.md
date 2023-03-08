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

***
## Document
1. 유스케이스
<img src="https://user-images.githubusercontent.com/89828294/147045092-4e5bef5f-b9e0-4e48-86c2-a42d8f2e41eb.png">

2. ERD
<img src="https://user-images.githubusercontent.com/89828294/147045496-4f8b6c82-3aa0-440f-96e2-7b5ae9a02559.png">

3. SQL문  
[[알바의 민족] Oracle DB 계정생성.txt](https://github.com/Seonhea/AlMin/files/7760540/Oracle.DB.txt)  
[Dclass_almin_almin2021.txt](https://github.com/Seonhea/AlMin/files/7760493/Dclass_almin_almin2021.txt)

4. 보고서 및 발표PPT  
[[알바의 민족] 기획보고서.pdf](https://github.com/Seonhea/AlMin/files/7760484/default.pdf)  
[[알바의 민족] DB설계보고서.pdf](https://github.com/Seonhea/AlMin/files/7760485/DB.pdf)  
[[알바의 민족] 클래스 설계보고서.pdf](https://github.com/Seonhea/AlMin/files/7760486/default.pdf)  
[[알바의 민족] 파이널 발표자료.pdf](https://github.com/Seonhea/AlMin/files/7760479/default.pdf)

