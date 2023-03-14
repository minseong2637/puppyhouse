<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
  <div
    class="d-flex flex-column align-items-center justify-content-center"
    style="min-height: 400px"
  >
    <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">애견수첩</h3>
    <div class="d-inline-flex text-white">
      <p class="m-0"><a class="text-white" href="/">Home</a></p>
      <p class="m-0 px-2">/</p>
      <p class="m-0">애견 수첩</p>
    </div>
  </div>
</div>
<!-- 섹션 1 end -->

<!-- 섹션 2 start -->
<div class="container-fluid">
  <div class="container">
    <div class="mb-4">
      <div>
      <h2 class="mb-1 text-center">소중한 추억들</h2>
   	  </div>
     <div class="col col-lg-12 mb-3 justify-content-center">
        <form action="/petNote/${roles}/saveImg/${petId}" method="POST" enctype="multipart/form-data">
          <input type="text" value="${petId}">
          <div class="border border1 img-fluid" style="border-radius: 70%; width: 230px; height: 220px; overflow: hidden;">
			<img class="img-fluid w-100" id="user_image" src="/auth/images?imgName=${imgName}" alt="다이어리 사진" style="width: 100%; height: 100%; object-fit: cover;">
   		  </div>
        
          <div class="text-center">
			<label for="file" class="border border-2 btn btn-light py-2 px-4" style="padding: 8px; margin-top: 15px;">사진 첨부</label>
		    <input type="file" class="d-none" id="file" name="file" accept="image/jpeg, image/png" multiple="multiple" onchange="PreviewImage();"> 	 	
          </div>
          <button class="btn btn-primary py-2 px-4" type="submit">등록</button>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- 섹션 2 end -->
<script type="text/javascript" >
	function PreviewImage() {
       // 파일리더 생성 
       var preview = new FileReader();
       
       preview.onload = function (e) {
       		// img id 값 
       		document.getElementById("user_image").src = e.target.result;
	   };
	   // input id 값 
	   preview.readAsDataURL(document.getElementById("file").files[0]);
	   };
</script>
<%@ include file="../layout/footer.jsp" %>