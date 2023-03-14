<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<!-- 섹션 1 start -->
  <div class="container-fluid bg-primary mb-5">
      <div
        class="d-flex flex-column align-items-center justify-content-center"
        style="min-height: 400px"
      >
        <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">애견 수첩</h3>
        <div class="d-inline-flex text-white">
          <p class="m-0"><a class="text-white" href="index.html">Home</a></p>
          <p class="m-0 px-2">/</p>
          <p class="m-0">애견 수첩</p>
        </div>
      </div>
    </div>
	<!-- 섹션 1 end -->
	
	<div class="container-fluid mb-5">
		<div class="container bg-primary">
			<div class="row justify-content-center">
				<div class="col-lg-3 bg-primary">
				     <div
              class="position-relative overflow-hidden mb-3 mt-5"
              style="border-radius: 100%">
				 <img class="img-fluid w-100" src="../img/dog.png" alt="" />
					</div>
					</div>
					<div class="col-lg-12">
					<h2 class="text-white text-center mt-3 mb-4" style="font-family: 'Noto Sans KR'"><strong>등록된 아이가 없어요!</strong></h2>	
					</div>
					<div class="col-lg-12 text-center">
					 <a href="/petNote/petImg" class="btn btn-danger px-5 py-2 mb-5 mx-auto">반려동물 추가하기</a>
					</div>
			</div>
		</div>
	</div>
<%@ include file="../layout/footer.jsp" %> 