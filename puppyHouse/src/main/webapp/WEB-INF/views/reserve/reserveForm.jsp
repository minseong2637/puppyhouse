<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
	<!-- 섹션 1 start -->
  <div class="container-fluid bg-primary mb-5">
      <div
        class="d-flex flex-column align-items-center justify-content-center"
        style="min-height: 400px;"
      >
        <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">예약</h3>
        <div class="d-inline-flex text-white">
          <p class="m-0"><a class="text-white" href="/">Home</a></p>
          <p class="m-0 px-2">/</p>
          <p class="m-0">예약</p>
        </div>
      </div>
    </div>
	<!-- 섹션 1 end -->   

  <!-- 예약하기 영역 start -->

  <div class="container-fluid">
    <div class="container">
      <h3 class="mx-auto py-3 text-center">예약하기</h3>
    </div>
  </div>

  
  <!-- 캘린더 start -->
  <div class="container-fluid">
    <div class="container p-5 mb-2 d-flex justify-content-center">
      <div id="calendar_section" class="myPageContent">
        <div class="calendar_header">
           <div class="btn-prevDay"></div>
          <h2 class="dateTitle"></h2>
          <div class="btn-nextDay"></div>
        </div>
        
        <div id="calendar_dateHead">
          <div>일</div>
          <div>월</div>
          <div>화</div>
          <div>수</div>
          <div>목</div>
          <div>금</div>
          <div>토</div>
        </div>
    
        <div id="calendar_dateBoard"></div>
      </div>
    </div>
  </div>
  <!-- 캘린더 end -->

  <!-- 예약 폼 start -->
  <div class="container-fluid">
    <div class="container">
      <div class="mx-auto col-12 col-xl-10 col-lg-10 px-md-5 px-lg-5 px-xl-5">
        <form class="mx-auto col-12">
          <div class="row justify-content-around px-5 px-md-0 px-sm-0">
            <div class="form-group col-4 px-md-0 px-sm-0">
              <label for="reserveDate">방문 날짜</label>
              <input id="reserveDate" type="text" class="form-control reserveDate" 
                  value="날짜를 선택하세요">
            </div>
            <div class="form-group col-3 px-md-0 px-sm-0">
              <label for="dogname">애견명</label>
              <select id="dogname" class="form-control">
              <c:forEach var="pet" items="${loginUserPet}">
                <option value="${pet.petId}">${pet.petName}</option>
              </c:forEach>
              </select>
            </div>
            <div class="form-group col-3 px-md-0 px-sm-0">
              <label for="trainer">담당 트레이너</label>
              <select id="trainer" class="form-control">
              <c:forEach var="teacher" items="${user}">
              	<c:if test="${teacher.roles eq 'MANAGER'}">
                	<option value="${teacher.id}">${teacher.username}</option>
                </c:if>
              </c:forEach>
              </select>
            </div>
          </div>
          <br>
          </form>
          <div class="mx-auto col-4 d-flex justify-content-center">
            <button class="btn btn-primary py-2 px-4" type="submit" id="reserve_btn">예약하기</button>
          </div>
      </div>
    </div>
  </div>
  <!-- 예약 폼 end -->

  <!-- 예약 영역 end -->
<!-- 캘린더 자바스크립트 -->
<script src="../js/reserved.js"></script>
<script type="text/javascript" src="/js/reserveForm.js"></script>
 
  <!-- 메인 end -->
<%@ include file="../layout/footer.jsp" %> 