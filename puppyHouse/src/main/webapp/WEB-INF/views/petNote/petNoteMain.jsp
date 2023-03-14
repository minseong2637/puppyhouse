<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
	<div
		class="d-flex flex-column align-items-center justify-content-center"
		style="min-height: 400px">
		<h3 class="display-3 font-weight-bold text-white"
			style="font-family: Noto sans Korean">애견수첩</h3>
		<div class="d-inline-flex text-white">
			<p class="m-0">
				<a class="text-white" href="index.html">Home</a>
			</p>
			<p class="m-0 px-2">/</p>
			<p class="m-0">애견 수첩</p>
		</div>
	</div>
</div>
<!-- 섹션 1 end -->
<!-- dogMain -->
<div class="container-fluid pt-5">
	<div class="container">
		<div class="p-5 rounded bg-light">
			<div class="col-lg-12 mb-12">
				<div class="contact-form">
					<form name="sentMessage" id="contactForm" novalidate="novalidate">
						<div class="row">
							<div class="col-12 mb-3 ml-4">
								<span id="petinfo" hidden>${petId}</span>
								<sec:authorize access="hasAnyRole('ROLE_USER')">
									<button class="btn btn-info btn float-right ml-1"
										id="pet-delete">삭제</button>
									<button type="button" class="btn btn-info btn float-right"
										onclick="location.href='/petNote/petUpdateForm/${petId}'">수정</button>
									<button type="button" class="btn btn-info btn float-right mr-1"
										onclick="location.href='/petNote/petImg'">추가하기</button>
								</sec:authorize>
								<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
									<a href="/reserve/petReserveInfo"
										class="btn btn-primary float-right px-3 mr-4">애견수첩 글쓰기</a>
								</sec:authorize>
							</div>
						</div>
						<div class="row justify-content-center mb-3">
							<div class="control-group">
								<div class="border border-2 img-fluid"
									style="border-radius: 70%; width: 150px; height: 150px; overflow: hidden;">
									<c:choose>
										<c:when test="${empty pets.imgOriName}">
											<img src="/img/dogImg/icon/paws.png" alt="프로필 사진"
												style="width: 100%; height: 100%; object-fit: cover; background-color: white;">
										</c:when>
										<c:otherwise>
											<img src="/images/${pets.imgName}" alt="프로필 사진"
												style="width: 100%; height: 100%; object-fit: cover; background-color: white;">
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
							<div class="text-center pb-2">
								<h5 class="mb-4 font-weight-bold fs-10">
									<select class="bg-light" id="reserveDate" name="reserveDate"
										style="border: none; outline: none; text-align: center; text-align-last: center;"
										onchange=changepet()>
										<c:forEach var="reservpet" items="${reservdog}">
											<option value="/petNote/${reservpet.petId}"
												<c:if test='${reservpet.petId eq petId}'>selected</c:if>>${reservpet.petName}
												- ${reservpet.users.username}</option>
										</c:forEach>
									</select>
								</h5>
							</div>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('ROLE_USER')">
							<div class="text-center pb-2 ml-4">
								<h5 class="mb-4 font-weight-bold fs-10">
									<select class="bg-light" id="sel" name="sel"
										style="border: none; outline: none; text-align: center; text-align-last: center;"
										onchange=changeval()>
										<c:forEach var="pet" items="${loginUserPet}">
											<option value="/petNote/${pet.petId}"
												<c:if test='${pet.petId eq petId}'>selected</c:if>>${pet.petName}
												 </option>
										</c:forEach>
									</select>
								</h5>
							</div>
						</sec:authorize>
						<div class="row">
							<div class="row justify-content-center">
								<div class="col-lg-5 col-md-6 pb-1">
									<button type="button" class="d-flex shadow-sm rounded mb-4"
										style="padding: 30px; background-color: white; border: none;"
										onclick="location.href='/petNote/petNotice/NOTE/${petId}'">
										<img src="/img/dogImg/icon/강아지채크.png" alt="일기장"
											class="h1 font-weight-normal text-primary mb-3"
											style="width: 15%;">
										<div class="pl-4">
											<h4>알림장</h4>
										</div>
									</button>
								</div>
								<div class="col-lg-5 col-md-6 pb-1">
									<button type="button" class="d-flex shadow-sm rounded mb-4"
										style="padding: 30px; background-color: white; border: none;"
										onclick="location.href='/petNote/petNotice/ACTIVITY/${petId}'">
										<img src="/img/dogImg/icon/활동.png" alt="활동"
											class="h1 font-weight-normal text-primary mb-3"
											style="width: 15%;">
										<div class="pl-4">
											<h4>오늘의 활동</h4>
										</div>
									</button>
								</div>
								<div class="col-lg-5 col-md-6 pb-1">
									<button type="button" class="d-flex shadow-sm rounded mb-4"
										style="padding: 30px; background-color: white; border: none;"
										onclick="location.href='/petNote/petNotice/FOOD/${petId}'">
										<img src="/img/dogImg/icon/pet-food.png" alt="식단"
											class="h1 font-weight-normal text-primary mb-3"
											style="width: 15%;">
										<div class="pl-4">
											<h4>오늘의 식단</h4>
										</div>
									</button>
								</div>
								<div class="col-lg-5 col-md-6 pb-1">
									<button type="button" class="d-flex shadow-sm rounded mb-4"
										style="padding: 30px; background-color: white; border: none;"
										onclick="location.href='/petNote/petNotice/HEALTH/${petId}'">
										<img src="/img/dogImg/icon/veterinary (1).png" alt="건강"
											class="h1 font-weight-normal text-primary mb-3"
											style="width: 15%;">
										<div class="pl-4">
											<h4>건강체크</h4>
										</div>
									</button>
								</div>
								<div class="col-lg-10 col-md-12 pb-1">
									<button type="button" class="d-flex shadow-sm rounded mb-4"
										style="width: 100%; padding: 30px; background-color: white; border: none;"
										onclick="location.href='/petNote/diary/${petId}'">
										<img src="/img/dogImg/icon/하트강아지.png" alt="건강"
											class="h1 font-weight-normal text-primary mb-3"
											style="width: 15%;">
										<div class="pl-4">
											<h4>소중한 추억들</h4>
										</div>
									</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- dogMain end -->
<script type="text/javascript" src="/js/pet.js"></script>
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>

<script type="text/javascript">
function changeval(){
	var value = document.getElementById("sel").value;
	location.href= value;
}
function changepet(){
	var value = document.getElementById("reserveDate").value;
	location.href= value;
}
</script>

<%@ include file="../layout/footer.jsp"%>