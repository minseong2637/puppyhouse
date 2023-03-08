<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container-fluid bg-primary mb-5">
	<div
		class="d-flex flex-column align-items-center justify-content-center"
		style="min-height: 400px">
		<h3 class="display-3 font-weight-bold text-white"
			style="font-family: Noto sans Korean">애견 수첩</h3>
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

<div class="container-fluid mb-5">
	<div class="container bg-primary">
		<div class="col">
			<div class="text-center pb-2" style="padding-top: 50px;">
				<h1 class="mb-4" style="color: white;">프로필 등록하기</h1>
			</div>
			<div class="col-lg-3 bg-primary mx-auto">
				<div class="border border1 img-fluid"
					style="border-radius: 70%; width: 230px; height: 220px; overflow: hidden;">
					<img class="img-fluid w-100" id="user_image" src="/img/dog.png"
						alt="프로필 사진" style="width: 100%; height: 100%; object-fit: cover;">
				</div>
			</div>
			<form action="/pet/saveImg" method="POST"
				enctype="multipart/form-data">
				<div class="col-lg-3 bg-primary mx-auto"
					style="padding-bottom: 50px">
					<div class="col-lg-12 text-center">
						<label for="file" class="border border-2 btn btn-light py-2 px-4"
							style="padding: 8px; margin-top: 15px;">프로필 사진 업로드</label> <input
							type="file" class="d-none" id="file" name="file"
							accept="image/jpeg, image/png" onchange="PreviewImage();">
						<p class="help-block text-danger"></p>
					</div>
					<div class="col-lg-12 text-center">
						<input type="submit"
							class="border border-2 btn btn-light py-2 px-4"
							style="padding: 8px;" value="상세정보 등록하기">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
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
<%@ include file="../layout/footer.jsp"%>
