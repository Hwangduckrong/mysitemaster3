<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="../../assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="../../assets/css/gallery.css" rel="stylesheet" type="text/css">
</head>


<body>
	<div id="wrap">

		<div id="header" class="clearfix">
			<h1>
				<a href="">MySite</a>
			</h1>

			<!-- 
			<ul>
				<li>황일영 님 안녕하세요^^</li>
				<li><a href="" class="btn_s">로그아웃</a></li>
				<li><a href="" class="btn_s">회원정보수정</a></li>
			</ul>
			-->
			<ul>
				<li><a href="" class="btn_s">로그인</a></li>
				<li><a href="" class="btn_s">회원가입</a></li>
			</ul>

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
				<h2>갤러리</h2>
				<ul>
					<li><a href="">일반갤러리</a></li>
					<li><a href="">파일첨부연습</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				<div id="content-head">
					<h3>일반갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">일반갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

						<table>														<!-- 작동방식이 다르니 반드시 포스트로 해놔라 -->
							<colgroup>
								<col style="width: 600px;">
								<col style="width: 220px;">
							</colgroup>
							<tr>
								<td class="text-left">
								<input type="file" name="file">
								</td>
								<td class="text-right">
								<button type="submit">파일업로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<!-- //file -->
						<div id="gallery">
							<div id="list">


								<button id="btnImgUpload">이미지올리기</button>
								<div class="clear"></div>


								<ul id="viewArea">

									<!-- 이미지반복영역 -->
									<li>
										<div class="view">
											<img class="imgItem" src="../../assets/image/Gangho-dong.jpg">
											<div class="imgWriter">
												작성자: <strong>유재석</strong>
											</div>
										</div>
									</li>

									<li>
										<div class="view">
											<img class="imgItem" src="../../assets/image/Jeongjae-Lee.jpg">
											<div class="imgWriter">
												작성자: <strong>유재석</strong>
											</div>
										</div>
									</li>

									<li>
										<div class="view">
											<img class="imgItem" src="../../assets/image/JeonSoMin.jpg">
											<div class="imgWriter">
												작성자: <strong>유재석</strong>
											</div>
										</div>
									</li>

									<li>
										<div class="view">
											<img class="imgItem" src="../../assets/image/JiseokJin.jpg">
											<div class="imgWriter">
												작성자: <strong>유재석</strong>
											</div>
										</div>
									</li>

									<li>
										<div class="view">
											<img class="imgItem" src="../../assets/image/JungWooSung.jpg">
											<div class="imgWriter">
												작성자: <strong>유재석</strong>
											</div>
										</div>
									</li>
									<!-- 이미지반복영역 -->
									
								</ul>
							</div>
							<!-- //list -->
						</div>
						<!-- //	gallery -->
				</div>
				<!-- //content  -->

			</div>
			<!-- //container  -->


			<div id="footer">Copyright ⓒ 2024 황덕룡. All right reserved</div>
			<!-- //footer -->
		</div>
		<!-- //wrap -->
</body>





</html>

