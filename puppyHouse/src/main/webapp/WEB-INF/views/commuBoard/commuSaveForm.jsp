<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

  <!-- 섹션 1 start -->
  <div class="container-fluid bg-primary mb-5">
    <div
      class="d-flex flex-column align-items-center justify-content-center"
      style="min-height: 400px"
    >
      <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">커뮤니티</h3>
      <div class="d-inline-flex text-white">
        <p class="m-0"><a class="text-white" href="/">Home</a></p>
        <p class="m-0 px-2">/</p>
        <p class="m-0">커뮤니티</p>
      </div>
    </div>
  </div>
  <!-- 섹션 1 end -->

  <!-- 섹션 2 start -->
  <div class="container-fluid">
    <div class="container">
      <div class="row pb-2 justify-content-center">
        <div class="col-lg-9">
         <form action="/commu/saveImg" method="POST" enctype="multipart/form-data">
           <div class="control-group">
          
             <input type="text" class="form-control" id="title" name="title" placeholder="Title">
             <p class="help-block text-dnager"></p>
           </div>
           <div class="control-group">
             <input type="text" class="form-control" id="username" value="${principal.user.userid}" readonly>
             <p class="help-block text-danger"></p>
           </div>
           <div class="control-group">
             <textarea class="form-control summernote" id="content" name="content" rows="10"></textarea>
             <p class="help-block text-dnager"></p>
           </div>
           <div class="border border1 img-fluid" style="border-radius: 70%; width: 230px; height: 220px; overflow: hidden;">
	 			<img class="img-fluid w-100" id="user_image" src="/img/dog.png" alt="사진" style="width: 100%; height: 100%; object-fit: cover;">
		   </div>
          
          	<div class="text-center">
          	    <label for="file" class="border border-2 btn btn-light py-2 px-4" style="padding: 8px; margin-top: 15px;">사진 첨부</label>
				<input type="file" class="d-none" id="file" name="file" accept="image/jpeg, image/png" multiple="multiple" onchange="PreviewImage();">  
				<input type="submit" class="border border-2 btn btn-light py-2 px-4" value="등록하기">    		 	 	
          	</div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <script>
    $('.summernote').summernote({
      tabsize: 2,
      height: 300
    })
  </script>
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
  <script type="text/javascript" src="/js/commu.js"></script>
  <!-- 섹션 2 end -->

<%@ include file="../layout/footer.jsp" %>