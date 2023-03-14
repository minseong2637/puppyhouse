<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
	<div
		class="d-flex flex-column align-items-center justify-content-center"
		style="min-height: 400px">
		<h3 class="display-3 font-weight-bold text-white"
			style="font-family: Noto sans Korean">마이페이지</h3>
		<div class="d-inline-flex text-white">
			<p class="m-0">
				<a class="text-white" href="/">Home</a>
			</p>
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
			<a href="../user/updateForm.jsp" class="btn btn-outline-primary m-1"
				style="color: #17a2b8; background-color: #fff;">회원정보 수정</a> <a
				href="myReserve.jsp" class="btn btn-outline-primary m-1 active"
				style="color: #fff; background-color: #17a2b8;">나의 예약 내역</a>
		</div>
	</div>
</div>
<!-- 마이페이지 메뉴 end -->

<!-- 나의 예약 내역 start -->

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

<!-- 예약 변경하기 start -->
<div class="container-fluid">
	<div class="container">
		<div
			class="mx-auto col-12 col-xl-10 col-lg-10 px-md-5 px-lg-5 px-xl-5">
			<h3 class="mx-auto py-3 text-center">예약 변경</h3>
			<form class="mx-auto col-12">
				<div class="row justify-content-around px-5 px-md-0 px-sm-0">
					<div class="form-group col-4 px-md-0 px-sm-0">
						<label for="reserveDate">방문 날짜</label> <input id="reserveDate"
							type="text" class="form-control reserveDate" placeholder="날짜를 선택하세요" value="${reserve.reservdate}">
					</div>
					<input type="hidden" id="reservId" value="${reserve.reservId}">
					<div class="form-group col-3 px-md-0 px-sm-0">
						<label for="dogname">애견명</label>
						<select id="dogname"class="form-control">
						<c:forEach var="pet" items="${loginUserPet}">
							<option value="${pet.petId}"<c:if test="${reserve.pet.petId eq pet.petId}">selected</c:if>>${pet.petName}</option>
						</c:forEach>
						</select>
					</div>
					<div class="form-group col-3 px-md-0 px-sm-0">
						<label for="trainer">담당 트레이너</label> 
						<select id="trainer"class="form-control">
							<c:forEach var="teacher" items="${user}">
								<c:if test="${teacher.roles eq 'MANAGER'}">
									<option value="${teacher.id}" <c:if test="${reserve.teacher.id eq teacher.id}">selected</c:if>>${teacher.username}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<br>
				<div class="row-cols-2 mx-auto d-flex justify-content-center">
					<button class="btn btn-danger col-2  mx-2 py-2 px-4 " type="submit"
						id="reserve_delete_btn">예약 삭제</button>
					<button class="btn btn-primary col-2 mx-2 py-2 px-4" type="submit"
						id="reserve_modify_btn">변경완료</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 예약 변경하기 end -->

<!-- 나의 예약 내역 end -->
<!-- 캘린더 자바스크립트 -->
<script src="/js/reserved.js"></script>
<script src="/js/reserveForm.js"></script>

<!-- 메인 end -->
<%@ include file="../layout/footer.jsp"%>
