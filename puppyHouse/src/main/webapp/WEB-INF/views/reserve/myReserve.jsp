<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>  
	<!-- 섹션 1 start -->
  <div class="container-fluid bg-primary mb-5">
      <div
        class="d-flex flex-column align-items-center justify-content-center"
        style="min-height: 400px"
      >
        <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">마이페이지</h3>
        <div class="d-inline-flex text-white">
          <p class="m-0"><a class="text-white" href="/">Home</a></p>
          <p class="m-0 px-2">/</p>
          <p class="m-0">마이페이지</p>
        </div>
      </div>
    </div>
	<!-- 섹션 1 end -->   
	

  <!-- 마이페이지 메뉴 start -->
  <div class="container-fluid">
    <div class="container p-5">
     <div class="col-12 text-center mb-2">
      <ul class="list-inline mb-4" id="portfolio-flters">
        <li class="d-inline-block m-1">
         <a href="/user/updateForm" class="btn btn-outline-primary">
           회원정보 수정</a>
       </li>
       <li class="d-inline-block m-1">
         <a href="/reserve/reserveInfo" class="btn btn-outline-primary active">나의 예약 내역</a>
        </li>
       </ul>
     </div>
    </div>
  </div>
  <!-- 마이페이지 메뉴 end -->

  <!-- 나의 예약 내역 start -->
  

  <!-- 예약 상세 내역 start -->
  <div class="container-fluid">
    <div class="container">
      <div class="col mx-auto col-12 col-xl-10 col-lg-10 px-md-5 px-lg-5 px-xl-5">
        <div class="mx-auto py-2 text-center">
          <h3 style="margin-bottom: 0;">예약 상세 내역</h3>
        </div>
        <div class="row">
          <div class="col-12 mb-3">
           
          </div>

        </div>
        <div class="px-2">
          <div class="row justify-content-around h-50 text-center" style="border-bottom: 3px solid #dee2e6;">
            <div class="px-2 col-3">방문 날짜</div>
            <div class="px-2 col-2">애견명</div>
            <div class="px-2 col-2">트레이너</div>
            <div class="px-2 col-4">이용권 기간</div>
            <div class="px-2 col-1"></div>
          </div>
         <c:forEach var="reserve" items="${reserve}">
          <c:forEach var="pet" items="${loginUserPet}">
          	<c:if test="${reserve.pet.petId eq pet.petId}">
	          	<div class="row border-bottom justify-content-around h-25 text-center">
		            <div class="px-2 pt-2 col-3">${reserve.reservdate}</div>
		            <div class="px-2 pt-2 col-2">${reserve.pet.petName}</div>
		            <div class="px-2 pt-2 col-2">${reserve.teacher.username}</div>
		            <div class="px-2 pt-2 col-4">2022/12/01(목)~2023/02/28(화)</div>
		            <button class="btn btn-outline-info px-2 col-1" onclick="location.href='/reserve/${reserve.reservId}/updateForm'">변경</button>         
	         	</div>
	         </c:if>
	        </c:forEach>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
  <!-- 예약 상세 내역 end -->

  <!-- 메인 end -->
<%@ include file="../layout/footer.jsp" %> 