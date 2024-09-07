<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- Axios 라이브러리 포함 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<style>
/* 모달창 배경 회색부분 */
.modal {
   width: 100%; /* 가로전체 */
   height: 100%; /* 세로전체 */
   display: none; /* 시작할때 숨김처리 */
   position: fixed; /* 화면에 고정 */
   left: 0; /* 왼쪽에서 0에서 시작 */
   top: 0; /* 위쪽에서 0에서 시작 */
   z-index: 999; /* 제일위에 */
   overflow: auto; /* 내용이 많으면 스크롤 생김 */
   background-color: rgba(0, 0, 0, 0.4); /* 배경이 검정색에 반투명 */
}

/* 모달창 내용 흰색부분 */
.modal .modal-content {
   width: 400px;
   margin: 100px auto; /* 상하 100px, 좌우 가운데 */
   padding: 0px 20px 20px 20px; /* 안쪽여백 */
   background-color: #ffffff; /* 배경색 흰색 */
   border: 1px solid #888888; /* 테두리 모양 색 */
}

/* 닫기버튼 */
.modal .modal-content .closeBtn {
   text-align: right;
   color: #aaaaaa;
   font-size: 28px;
   font-weight: bold;
   cursor: pointer;
   border: 0px solid #000000;
   background-color:#ffffff;
}
</style>

</head>

<body>
	<div id="wrap">

		<!-- //header + nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header + nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form id="guestbookForm" action="${pageContext.request.contextPath}/api/guestbook/write" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name" value=""></td>

									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="pass" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">

					</form>
					
					 <!-- 모달 창 컨텐츠 -->
					<div id="myModal" class="modal">             
                  	<div id="guestbook" class="modal-content">
                     <div class="closeBtn">×</div>
                     <div class="m-header">패스워드를 입력하세요</div>
                     <div class="m-body">
                        <input id="modalPw" class="m-password" type="password" name="password" value=""><br> <input  id="modalno" class="m-no" type="text" name="no" value="">
                     </div>
                     <div class="m-footer">
                        <button class="btnDelete" type="button">삭제</button>
                     </div>
                  </div>

               </div>

					<!-- 리스트자리 -->
					<div id="guestbookListArea">
						<!-- afterbegin(시작후)  -->
					</div>
					<!-- 리스트자리 -->


				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- //footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

	<script>
	//로딩될때 
	document.addEventListener('DOMContentLoaded', function() {
    console.log("DOM tree 완료");

    // 리스트 가져와서 그리기
    getListRender();

    // 서버로 데이터 요청
   
    // 글 저장하고 그리기 이벤트 등록
    let ListArea = document.querySelector('#guestbookForm');
    ListArea.addEventListener('submit', addRender);
  	
    // 모달창 띄우기 버튼 클릭 이벤트 등록
    let listArea = document.querySelector("#guestbookListArea"); //이건 자식이 아니라 부모한테 걸어야 한다
    listArea.addEventListener('click', callModal);

    // 모달창 닫기 버튼 이벤트 등록
    let closeBtn = document.querySelector(".closeBtn");
    closeBtn.addEventListener('click', closeModal);

    //"모달창의 삭제버튼 클릭할 때" 이벤트 등록

    let btnDelete =document.querySelector("#btnDelete");
    console.log(btnDelete);
    btnDelete.addEventListener('click', deleteRemove);
    
    
});
//메소드 정의/////////////////////////////////////////////////

//삭제(삭제 폼 아님) 메소드

function deleteRemove(){
    console.log('삭제');

    let modalPwTag = document.querySelector("#modalPw");
    let modalNoTag = document.querySelector("#modalno");
    
    let password = modalPwTag.value;  // 대소문자 오타 수정
    let no = modalNoTag.value;
    
    let guestbookVo = {
        no: no,
        password: password
    };

    console.log(guestbookVo);



}
  




//모달창 닫기 메소드


function closeModal(){
	console.log('모달창 닫기')
	let modalTag=document.querySelector('#myModal');
	modalTag.style.display='none';
		
}

//모달창 보이기 메소드

function callModal(){
	console.log(this);
	console.log(event.target.tagName);
		if(event.target.tagName == 'BUTTON'){
			console.log("클릭")
		
			let no =event.target.dataset.no;
			console.log(no);
			
			//모달창의 input 의 value = no
			
			let txtNoTag=document.querySelector("#modalno");
			txtNoTag.value= no;
			let modalTag=document.querySelector('#myModal')
				modalTag.style.display='block';

			}	
	}
function addRender(event){
	   
  	event.preventDefault();
   	console.log('전송');
   
   	//폼의 데이터 수집(이름 패스워드 본문)
   	let nameTag = document.querySelector('#input-uname');   //공부차원에서 id로 가져옴
   	let passwordTag = document.querySelector('[name="pass"]'); //일관성있게 속성값으로 찾기
   	let contentTag = document.querySelector('[name="content"]'); 
   
   	let name = nameTag.value;
   	let password = passwordTag.value;
 	let content = contentTag.value
	   
   	let guestbookVo = {
    	name: name,
       	password: password,
       	content: content
   	};
	console.log(guestbookVo);   

	//db삭제(서버에 전송해서 삭제)
	axios({
		  method: 'get',           // put, post, delete                   
		  url: '${pageContext.request.contextPath}/api/guestbook/delete',
		  headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
		  params: guestbookVo,  //get방식 파라미터로 값이 전달
		//data: guestbookVo,   //put, post, delete 방식 자동으로 JSON으로 변환 전달
		    
		  responseType: 'json' //수신타입
	   }).then(function (response) {
		     console.log(response); //수신데이타
		     console.log(response.data); //수신데이타
		    
		  if(response.data ==1){
			  
			  let delId ='#t-'+ no;
			  console.log(delId);
			  let removetable=document.querySelector(delID)
			  removetable.remove();
			  modalTag.style.display='none';
		  }
			}).catch(function (error) {
		        console.log(error);
		    
		    });
			   
		}
	
	
	
   	//전송
   	axios({
    	method: 'get',           // put, post, delete                   
     	url: '${pageContext.request.contextPath}/api/guestbook/write',
     	headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
     	params: guestbookVo,  //get방식 파라미터로 값이 전달
     	//data: guestbookVo,   //put, post, delete 방식 자동으로 JSON으로 변환 전달
    
	    responseType: 'json' //수신타입
	}).then(function (response) {
     	console.log(response); //수신데이타
     	console.log(response.data); //수신데이타
	    
      	//리스트에 추가(그리기)
		render(response.data, 'up');
	      
	      
		//폼 초기화
		nameTag.value = '';
		passwordTag.value = '';
		contentTag.value = '';
		
		//guestbookForm.reset();
	      
	}).catch(function (error) {
        console.log(error);
    
    });
	   




//리스트 가져와서 그리기메소드
function getListRender(){
 
	axios({
		method: 'get',           // put, post, delete                   
		url: '${pageContext.request.contextPath}/api/guestbook/list',
		headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
		//params: guestbookVo,  //get방식 파라미터로 값이 전달
        //data: guestbookVo,   //put, post, delete 방식 자동으로 JSON으로 변환 전달

		responseType: 'json' //수신타입  
   	}).then(function (response) {
        console.log(response.data); //수신데이타
		
		
		for(let i=0; i<response.data.length; i++){
			//console.log(response.data[i].name);
			let guestbookVo = response.data[i]
			render(guestbookVo,'down');
		}
    
    }).catch(function (error) {
        console.log(error);
    
    });
};

//그리기
function render(guestbookVo,dir){
	console.log(guestbookVo);
	//태그 가져오기
	let listArea = document.querySelector('#guestbookListArea');
	
	let str = '';
	str += '<table id = "t-'+guestbookVo.no+'" class="guestRead">';
	str += '	<colgroup>';
	str += '		<col style="width: 10%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 10%;">';
	str += '	</colgroup>';
	str += '	<tr>';
	str += '		<td>'+guestbookVo.no+'</td>';
	str += '		<td>'+guestbookVo.name+'</td>';
	str += '		<td>'+guestbookVo.regDate+'</td>';
	str += '		<td><button class="callModal" type="button" data-no="'+guestbookVo.no+'" >삭제</button></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
	str += '	</tr>';
	str += '</table>';
	
	
	if(dir=='down'){
		listArea.insertAdjacentHTML('beforeend', str);
	}else if(dir=='up'){
		listArea.insertAdjacentHTML('afterbegin', str);
	}else{
		console.log("완료")
	}
	
};	
</script>


</body>

</html>