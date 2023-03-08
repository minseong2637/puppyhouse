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
						<form action="/pet/updateImg/${pet.petId}" method="POST"
							enctype="multipart/form-data">
							<input type="hidden" id="petId" value="${pet.petId}" />

							<div class="row justify-content-center">
								<div class="control-group">
									<div class="border border-2 img-fluid mb-3 mx-auto"
										style="border-radius: 70%; width: 150px; height: 150px; overflow: hidden; background-color: white;">
										<c:choose>
											<c:when test="${empty pet.imgOriName}">
												<img src="/img/dogImg/icon/paws.png" alt="프로필 사진" id="user_image"
													style="width: 100%; height: 100%; object-fit: cover;" onchange="PreviewImage();">
											</c:when>
											<c:otherwise>
												<img src="/images/${pet.imgName}" alt="프로필 사진" id="user_image"
													style="width: 100%; height: 100%; object-fit: cover;" onchange="PreviewImage();">
											</c:otherwise>
										</c:choose>
									</div>
									<div class="col-lg-12 text-center">
										<label for="file"
											class="border border-2 btn btn-light py-2 px-4"
											style="padding: 8px; margin-top: 15px;">프로필 사진 업로드</label> <input
											type="file" class="d-none" id="file" name="file"
											accept="image/jpeg, image/png" onchange="PreviewImage();">
										<p class="help-block text-danger"></p>
									</div>
								</div>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="petName"
									name="petName" placeholder="강아지 이름" value="${pet.petName}"
									required="required">
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="radio" id="birthday" name="agetype" value="1"
									<c:if test='${pet.agetype == "1"}'>checked</c:if>> 생일 <input type="radio" id="dogage"
									name="agetype" value="2"<c:if test='${pet.agetype == "2"}'>checked</c:if>> 나이
								<div>
									<input type="date" class="form-control birthday" name="birthday"
										id="petbirthday" size="20" value="${pet.birthday}"
										>
								</div>
								<div>
									<input type="text" class="form-control dogage" name="age"
										id="petage" size="20" placeholder="강아지 나이"
										style="display: none;" value="${pet.age}">
								</div>
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="species"
									name="species" value="${pet.species}" placeholder="강아지 품종"
									required="required">
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<select class="form-control" id="gender" name="gender">
									<option disabled selected>성별</option>
									<option value="male" <c:if test='${pet.gender eq "male"}'>selected</c:if>>남아</option>
									<option value="female" <c:if test='${pet.gender eq "female"}'>selected</c:if>>여아</option>
								</select>
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<select class="form-control" id="neutered" name="neutered">
									<option disabled selected>중성화 여부</option>
									<option value="yes" <c:if test='${pet.neutered eq "yes"}'>selected</c:if>>했어요</option>
									<option value="no" <c:if test='${pet.neutered eq "no"}'>selected</c:if>>안 했어요</option>
								</select>
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="weight"
									name="weight" placeholder="강아지 몸무게" required="required" value="${pet.weight}">
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="allergy"
									name="allergy" placeholder="알러지" value="${pet.allergy}">
								<p class="help-block text-danger"></p>
							</div>
							<div class="control-group">
								<input type="text" class="form-control" id="etc" name="etc"
									placeholder="특이사항" value="${pet.etc}">
								<p class="help-block text-danger"></p>
							</div>
							<div class="row">
								<div class="col text-center">
									<input type="submit" class="btn btn-primary py-2 px-4"
										id="pet_btn_Modify" value="등록하기">
								</div>
							</div>
						</form>
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
			
			$('#petage').val('');

		} else if (chkValue == '2') {

			$('#petbirthday').css('display', 'none');

			$('#petage').css('display', 'block');
			
			$('#petbirthday').val('');

		}

	});
	
	function PreviewImage() {
		// 파일리더 생성 
		var preview = new FileReader();
		preview.onload = function(e) {
			// img id 값 
			document.getElementById("user_image").src = e.target.result;
		};
		// input id 값 
		preview.readAsDataURL(document.getElementById("file").files[0]);
	};
</script>

<!-- 컨텐츠 end -->
<%@ include file="../layout/footer.jsp"%>
