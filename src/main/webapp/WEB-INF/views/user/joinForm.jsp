<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="http://localhost:8888/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<div id="header" class="clearfix">
			<h1>
				<a href="">MySite</a>
			</h1>

			<!-- 로그인했을때 -->
			<c:if test="${sessionScope.authUser != null}">
				<ul>
					<li>${sessionScope.authUser.name}님 안녕하세요^^</li>
					<li><a href="http://localhost:8888/mysite/user/logout" class="btn_s">로그아웃</a></li>
					<li><a href="" class="btn_s">회원정보수정</a></li>
				</ul>
			</c:if>

			<!-- 로그인안했을때 -->
			<c:if test="${sessionScope.authUser == null}">
				<ul>
					<li><a href="http://localhost:8888/mysite/user/loginform" class="btn_s">로그인</a></li>
					<li><a href="" class="btn_s">회원가입</a></li>
				</ul>
			</c:if>

		</div>
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">방명록</a></li>
			</ul>
		</div>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="user">
					<div id="joinForm">
						<form action="/mysite/user/join" method="get">

							<!-- 아이디 -->
							<div class="form-group">
								<label  class="form-text" for="input-uid">아이디</label> <input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
								<button type="button" id="btnIdCheck">중복체크</button>
							</div>
							<div id = "message"></div>

							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> <input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요">
							</div>

							<!-- 이메일 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> <input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
							</div>

							<!-- //나이 -->
							<div class="form-group">
								<span class="form-text">성별</span> <label for="rdo-male">남</label> <input type="radio" id="rdo-male" name="gender" value="male"> <label
									for="rdo-female">여</label> <input type="radio" id="rdo-female" name="gender" value="female">

							</div>

							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span> <input type="checkbox" id="chk-agree" value="" name=""> <label for="chk-agree">서비스 약관에 동의합니다.</label>
							</div>

							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원가입</button>
							</div>

						</form>
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<div id="footer">Copyright ⓒ 2024 황덕룡. All right reserved</div>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	<script>
	
    document.addEventListener('DOMContentLoaded', function() {
        console.log("DOM tree 완료");
        
        let btnIdCheck = document.querySelector('#btnIdCheck');
        btnIdCheck.addEventListener('click', function() {
            console.log('클릭');
            
            let txtIdTag = document.querySelector('#input-uid');
            let id = txtIdTag.value;
            
            axios({
                method: 'get',
                url: '/mysite/api/user/idcheck',
                headers: {"Content-Type" : "application/json; charset=utf-8"},
                params: {id: id},
                responseType: 'json'
            }).then(function (response) {
                console.log(response.data);
                
                let can=response.data;
                let messageTag =document.querySelector('#message');
                
                //그리기
                if(can==true){
                 messageTag.textContent="사용할 수 있는 아이디 입니다"
                 messageTag.style.color="#0000ff"
                }else{
                 messageTag.textContent="다른 아이디를 사용해주세요"
                 messageTag.style.color="#ff0000"
                }
               
                
            }).catch(function (error) {
                console.log(error);
            });
        });
    });
</script>

</body>

</html>