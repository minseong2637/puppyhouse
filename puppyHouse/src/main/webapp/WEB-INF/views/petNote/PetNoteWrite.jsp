<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cos.puppyHouse.model.PetBoardRoleType"%>


<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
	<div
		class="d-flex flex-column align-items-center justify-content-center"
		style="min-height: 400px">
		<h3 class="display-3 font-weight-bold text-white"
			style="font-family: Noto sans Korean">애견수첩 글등록</h3>
		<div class="d-inline-flex text-white">
			<p class="m-0">
				<a class="text-white" href="index.html">Home</a>
			</p>
			<p class="m-0 px-2">/</p>
			<p class="m-0">애견수첩</p>
		</div>
	</div>
</div>
<!-- 섹션 1 end -->

<!-- 섹션 2 start -->
<div class="container-fluid">
	<div class="container">
		<div class="row pb-2 justify-content-center">
			<div class="col-lg-9">
				<form action="">
					<div class="control-group">
						<input type="text" class="form-control" id="username" value="관리자"
							readonly="readonly">
						<p class="help-block text-danger"></p>
					</div>
					<div class="row ml-1">
						<div class="control-group">
							<select id="roles">
								<option disabled selected>애견수첩 선택</option>
								<option value="NOTE">알림장</option>
								<option value="FOOD">오늘의 식단</option>
								<option value="ACTIVITY">오늘의 활동</option>
								<option value="HEALTH">건강체크</option>
							</select>
						</div>
						<div class="control-group ml-1">
							<select id="reserveDate" name="reserveDate">
							<option disabled selected>예약 날짜</option>
								<c:forEach var="reservdate" items="${reservdate}">
									<option value="${reservdate}">${reservdate}</option>
								</c:forEach>
							</select>
						</div>
						<div class="control-group ml-1">
							<select class="bg-light" id="reserveDog" name="reserveDog">
							<option disabled selected>강아지 이름</option>
							</select>
							<div id="reserveDogId"></div>
						</div>
					</div>
					<div class="control-group">
						<textarea id="content" name="content" class="form-control"
							rows="10" style="resize: none;"></textarea>
						<p class="help-block text-danger"></p>
					</div>
				</form>
				<button class="btn btn-primary py-2 px-4" type="submit"
					id="note-save">등록</button>
				<button class="btn btn-primary py-2 px-4 ml-4" type="reset">초기화</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/js/reserveSave.js"></script>
<%@ include file="../layout/footer.jsp"%>