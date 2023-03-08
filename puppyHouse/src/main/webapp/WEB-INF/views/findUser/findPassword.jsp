<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
  <div
    class="d-flex flex-column align-items-center justify-content-center"
    style="min-height: 400px"
  >
    <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">비밀번호찾기</h3>
    <div class="d-inline-flex text-white">
      <p class="m-0"><a class="text-white" href="/">Home</a></p>
      <p class="m-0 px-2">/</p>
      <p class="m-0">비밀번호찾기</p>
    </div>
  </div>
</div>
<!-- 섹션 1 end -->

<!-- 섹션 2 start -->
<div class="container-fluid">    
  <div class="container">
    <div class="row justify-content-center pb-2">
      <div class="col-6 mb-5 changeDiv">
      <div class="mb-2"><strong>이메일</strong></div>
      <div class="col px-0">
        <form class="form-group email-form">
	 			<input onkeyup="emailCheck2();" class="form-control" type="text" class="form-control" name="useremail1" id="useremail1" placeholder="이메일" >
	 			<div class="invalid-feedback my-2" id="emailchk">이메일은 필수 입력 값입니다.</div>
	           <div class="valid-feedback my-2">사용 가능한 이메일입니다.</div>
		</form>
        <div class="input-group-addon align-middle">
			<button type="button" class="btn btn-primary" id="mail-Check-Btn">인증번호 받기</button>
		</div>
        </div>
		<Br>
		<br>
		<div class="mb-2"><strong>인증번호</strong></div>
		<div class="mail-check-box">
			<div class="col-12 px-0">
				<form class="form-group">
					<input id="mail-check-input" class="form-control mb-2" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
				</form>
				<button class="btn btn-primary py-1 px-4" onclick="numCheck()">확인</button>
			</div>
		</div>
		<Br>
		<Br>
		<div class="mb-2"><strong>비밀번호 재설정</strong></div>
		<div class="mail-check-box row  justify-content-start">
			<div class="col-12">
				<form name="resetPwdForm" id="resetPwdForm">
		          <div class="control-group">
		            <input type="password" class="form-control" id="userpwd" disabled="disabled" placeholder="새 비밀번호" onkeyup="pwdCheck(); pwdCheck2();">
			            <div class="invalid-feedback my-2" id="pwdchk">비밀번호는 필수 입력 값입니다.</div>
	              		<div class="valid-feedback my-2">사용 가능한 비밀번호입니다.</div>
		            <p class="help-block text-danger"></p>
		          </div>
		          <div class="control-group">
		            <input type="password" class="form-control" id="userpwdChk" disabled="disabled" placeholder="새 비밀번호 확인" onkeyup="pwdCheck2()">
			            <div class="invalid-feedback my-2" id="pwdchk2">일지하지 않습니다.</div>
	              		<div class="valid-feedback my-2">비밀번호 일치!</div>
		            <p class="help-block text-danger"></p>
		          </div>
		        </form>
        	</div>
        </div>
        <div>
            <button class="btn btn-primary py-2 px-4" id="btn-resetPassword">확인</button>
        </div>    
      </div>
    </div>
  </div>
</div>
<!-- 섹션 2 end -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>