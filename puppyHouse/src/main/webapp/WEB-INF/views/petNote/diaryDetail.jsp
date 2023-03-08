<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../layout/header.jsp" %>  


	<!-- 섹션 1 start -->
  <div class="container-fluid bg-primary mb-5">
    <div
      class="d-flex flex-column align-items-center justify-content-center"
      style="min-height: 400px"
    >
      <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">애견수첩</h3>
      <div class="d-inline-flex text-white">
        <p class="m-0"><a class="text-white" href="index.html">Dog Note</a></p>
        <p class="m-0 px-2">/</p>
        <p class="m-0">애견수첩</p>
      </div>
    </div>
  </div>
<!-- 섹션 1 end -->


 <!-- 섹션 2 Start -->
<div class="container-fluid pt-5 bg-light p-5 rounded">
<div class="container">
    <div class="mb-4">
      <div class="my-3">
      <h2 class="my-1 text-center">소중한 추억들</h2>
  	  </div>
     
      <div class="row d-flex justify-content-start">
      	<c:forEach var="diary" items="${diary}">
           	<c:if test="${diary.pet.petId eq pets.petId}">
           		<a class="justify-content-center mx-auto my-5 diaryImg" style="width: 320px;" onclick="saveImg(${diary.petBoardId})">
	             	<img class="diaryImg${diary.petBoardId} diaryImg2" src="/auth/images?imgName=${diary.imgName}" alt="" style="width: 300px; height: 250px">
	           	</a>
           				
	    	 <div class="diaryModal text-center col">
	 	 	 	<span class="modalClose">&times;</span>
	 	 		 <strong style="font-size: 2rem;"><fmt:formatDate value="${diary.createDate}" pattern="yyyy.MM.dd"/></strong>
	    	 	<img class="diaryModal_content my-4">
	    	 	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
	    	 		<div class="row justify-content-center w-100">
	    	 			<a class="btn btn-danger" style id="btn-delete" onclick="deleteById(${diary.petBoardId},${diary.pet.petId})">삭제</a>
	    	 		</div>
				 </sec:authorize>
	 	   	 </div>
  	  	 	</c:if>
  	 	 </c:forEach>
  	   </div>
  	 
        <div class="col text-center mt-5">
          <button onclick="location.href='/petNote/diarySaveForm/${petId}'" class="btn btn-info">등록하기</button>
        </div>
        
        </div>      
</div>
</div>
<!-- 섹션 2 end -->
<!-- 이미지 모달 스크립트 -->
<script>
	function saveImg(petBoardId) {
		let img = document.querySelector(".diaryImg"+petBoardId);
		let modal = document.querySelector(".diaryModal");
	  	let modal_img = document.querySelector(".diaryModal_content");
	  	let span = document.querySelector(".modalClose");
	 	//let deleteBtn = document.querySelector("#btn-delete");
	  	
	 	img.addEventListener('click', ()=>{
		  modalDisplay("block");
		  modal_img.src = img.src;
		});
		span.addEventListener('click', ()=>{
		  modalDisplay("none");
		});
		modal.addEventListener('click', ()=>{
		  modalDisplay("none");
		});
		function modalDisplay(text){
		  modal.style.display = text;
		}
	  	
	}
</script>
<script type="text/javascript" src="/js/petNote(diary).js"></script>
    <%@ include file="../layout/footer.jsp" %> 