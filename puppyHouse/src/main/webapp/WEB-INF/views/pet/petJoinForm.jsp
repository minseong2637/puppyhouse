<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
	<div
		class="d-flex flex-column align-items-center justify-content-center"
		style="min-height: 400px">
		<h3 class="display-3 font-weight-bold text-white"
			style="font-family: Noto sans Korean">애견수첩 등록하기</h3>
		<div class="d-inline-flex text-white">
			<p class="m-0">
				<a class="text-white" href="index.html">Dog Note</a>
			</p>
			<p class="m-0 px-2">/</p>
			<p class="m-0">애견수첩</p>
		</div>
	</div>
</div>
<!-- 섹션 1 end -->
<!-- 컨텐츠 start -->
<div class="container-fluid pt-5">
	<div class="container">
		<div class="bg-light p-5 rounded">
			<div class="text-center pb-2">
				<h1 class="mb-4">반려동물 등록</h1>
			</div>
			<div class="row justify-content-center">
				<div class="col-lg-7 mb-5">
					<div class="contact-form">
						<div id="success"></div>
						<form>
							<input type="hidden" id="petId" value="${pets.petId}" />

							<div class="row justify-content-center">
								<div class="control-group">
									<div class="border border-2 img-fluid mb-3"
										style="border-radius: 70%; width: 150px; height: 150px; overflow: hidden; background-color: white;">
										<c:choose>
											<c:when test="${empty pets.imgOriName}">
												<img src="/img/dogImg/icon/paws.png" alt="프로필 사진"
													style="width: 100%; height: 100%; object-fit: cover;">
											</c:when>
											<c:otherwise>
												<img src="/images/${pets.imgName}" alt="프로필 사진"
													style="width: 100%; height: 100%; object-fit: cover;">
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="petName"
									name="petName" placeholder="강아지 이름" value="${pets.petName}"
									required="required">
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="radio" id="birthday" name="agetype" value="1"
									checked="checked"> 생일
								<input type="radio" id="dogage" name="agetype" value="2"> 나이
								<div>
									<input type="date" class="form-control birthday" name="birthday"
										id="petbirthday" size="20" value="${pets.age}"
										required="required">
								</div>
								<div>
									<input type="text" class="form-control dogage" name="age"
										id="petage" size="20" placeholder="강아지 나이" required="required" style="display: none;">
								</div>
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="species"
									name="species" value="${pets.species}" placeholder="강아지 품종"
									required="required">
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<select class="form-control" id="gender" name="gender">
									<option disabled selected>성별</option>
									<option value="male">남아</option>
									<option value="female">여아</option>
								</select>
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<select class="form-control" id="neutered" name="neutered">
									<option disabled selected>중성화 여부</option>
									<option value="yes">했어요</option>
									<option value="no">안 했어요</option>
								</select>
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="weight"
									name="weight" placeholder="강아지 몸무게" required="required">
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="allergy"
									name="allergy" placeholder="알러지">
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="etc" name="etc"
									placeholder="특이사항">
								<p class="help-block text-danger"></p>
							</div>
						</form>
						<div class="row">
							<div class="col text-center">
								<button type="submit" class="btn btn-primary py-2 px-4"
									id="pet_btn_save">등록하기</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/js/jquery.js"></script>
<script type="text/javascript">
$('input[type=radio][name=agetype]').on('click', function() {
	var chkValue = $('input[type=radio][name=agetype]:checked').val();
	if (chkValue == '1') {
	$('#petbirthday').css('display', 'block');
	$('#petage').css('display', 'none');
	} else if (chkValue == '2') {
	$('#petbirthday').css('display', 'none');
	$('#petage').css('display', 'block');
	}
	});
</script>
<script type="text/javascript" src="/js/pet.js"></script>
<!-- 컨텐츠 end -->
<%@ include file="../layout/footer.jsp"%>