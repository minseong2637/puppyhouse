<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
  <div
    class="d-flex flex-column align-items-center justify-content-center"
    style="min-height: 400px"
  >
    <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">아이디찾기</h3>
    <div class="d-inline-flex text-white">
      <p class="m-0"><a class="text-white" href="/">Home</a></p>
      <p class="m-0 px-2">/</p>
      <p class="m-0">아이디찾기</p>
    </div>
  </div>
</div>
<!-- 섹션 1 end -->
<!-- 섹션 2 start -->
<div class="container-fluid">
  <div class="container">
    <div class="row justify-content-center pb-2">
      <div class="col-lg-7 mb-5 changeDiv">
        <form name="findId" id="findIdForm">
          <div class="control-group">
            <input type="text" class="form-control" id="username" placeholder="이름을 입력하세요">
            <p class="help-block text-danger"></p>
          </div>
          <div class="control-group">
            <input type="text" class="form-control" id="phone" placeholder="휴대폰번호를 입력하세요">
            <p class="help-block text-danger"></p>
          </div>
        </form>
          <div id="findbtn">
            <button class="btn btn-primary py-2 px-4" id="btn-findUserId" data-target="#idResultModal">다음</button>
          </div>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="idResultModal" tabindex="-1" role="dialog" aria-labelledby="historyModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
   		<div class="modal-content">
   		</div>
  	</div>
</div>
   		
<script src="/js/user.js"></script>
<script>
	/*모달*/
	function fnModal(){
   		$('.modal-content').load("/auth/IdModal");
   		$('#idResultModal').modal();
	}
</script>
<!-- 섹션 2 end -->
<%@ include file="../layout/footer.jsp" %>