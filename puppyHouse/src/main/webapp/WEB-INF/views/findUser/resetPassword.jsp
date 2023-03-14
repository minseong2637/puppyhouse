<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<!-- 섹션 1 start -->
  <div class="container-fluid bg-primary mb-5">
    <div
      class="d-flex flex-column align-items-center justify-content-center"
      style="min-height: 400px"
    >
      <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">비밀번호 재설정</h3>
      <div class="d-inline-flex text-white">
        <p class="m-0"><a class="text-white" href="/">Home</a></p>
        <p class="m-0 px-2">/</p>
        <p class="m-0">비밀번호 재설정</p>
      </div>
    </div>
  </div>
<!-- 섹션 1 end -->
<!-- 섹션 2 start -->
<div class="container-fluid">
  <div class="container">
    <div class="row justify-content-center pb-2">
      <div class="col-lg-7 mb-5">
        <form name="resetPwdForm" id="resetPwdForm">
          	<input type="hidden" class="form-control" id="id" value="${selectedId}">
          <div class="control-group">
            <input type="password" class="form-control" id="userpwd" placeholder="새 비밀번호">
            <p class="help-block text-danger"></p>
          </div>
          <div class="control-group">
            <input type="password" class="form-control" id="chk-userpwd" placeholder="새 비밀번호 확인">
            <p class="help-block text-danger"></p>
          </div>
        </form>
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