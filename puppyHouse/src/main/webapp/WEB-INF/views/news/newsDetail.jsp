<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
  <div
    class="d-flex flex-column align-items-center justify-content-center"
    style="min-height: 400px"
  >
    <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">고객센터</h3>
    <div class="d-inline-flex text-white">
      <p class="m-0"><a class="text-white" href="/">Home</a></p>
      <p class="m-0 px-2">/</p>
      <p class="m-0">고객센터</p>
    </div>
  </div>
</div>
<!-- 섹션 1 end -->

<!-- 섹션 2 start -->
<div class="container-fluid">
  <div class="container">
    <div class="row pb-2">
      <div class="col-lg-12 mb-2">
        <h3 class="col-lg-12" style="font-family: Nanum Gothic;">${notice.title}</h3>
        <hr>
        <div class="col-lg-12 mb-2">
          <input id="roles" type="hidden" value="${notice.roles}"></input>
          <input id="id" type="hidden" value="${notice.noticeId}"></input>
          <p style="float: left;">작성자 : ${notice.users.username}</p>
          <p style="float: right;">작성일자 : <fmt:formatDate value="${notice.createDate}" pattern="yyyy-MM-dd"/></p>
          <br>
        </div>
        <hr>
        <div class="col-lg-12">
          <p>${notice.content}</p>
        </div>
        <hr>
        <a href="/auth/news/NOTICE" class="btn btn-primary">뒤로</a>
        <div class="float-right">
        	<c:if test="${notice.users.id==principal.user.id}">
				<a href="/news/${notice.noticeId}/updateForm" class="btn btn-warning">수정</a>
          		<a class="btn btn-danger" id="btn-delete">삭제</a>
			</c:if>
        </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="/js/notice.js"></script>
<!-- 섹션 2 end -->
<%@ include file="../layout/footer.jsp" %>