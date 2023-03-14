<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
  <div
    class="d-flex flex-column align-items-center justify-content-center"
    style="min-height: 400px"
  >
    <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">고객센터</h3>
    <div class="d-inline-flex text-white">
      <p class="m-0"><a class="text-white" href="index.html">Home</a></p>
      <p class="m-0 px-2">/</p>
      <p class="m-0">고객센터</p>
    </div>
  </div>
</div>
<!-- 섹션 1 end -->

<!-- 섹션 2 start -->
<div class="container-fluid">
  <div class="container">
    <div class="row pb-2 justify-content-center">
      <div class="col-lg-9">
        <form action="">
            <input type="hidden" id="id" value="${notice.noticeId}"></input>
          <div class="control-group">
            <input type="text" class="form-control" id="title" placeholder="Title" value="${notice.title}">
            <p class="help-block text-danger"></p> 
          </div>
          <div class="control-group">
            <input type="text" class="form-control" id="username" value="관리자" readonly="readonly">
            <p class="help-block text-danger"></p> 
          </div>
          <div class="control-group">
          	<select id="roles">
          		<option value="NOTICE"> 공지사항</option>
          		<option value="FAQ"> FAQ</option>
          	</select>         	
            <p class="help-block text-danger"></p>
          </div>
          <div class="control-group">
            <textarea id="content" class="form-control" rows="10" style="resize: none;">${notice.content}</textarea>
            <p class="help-block text-danger"></p> 
          </div>
         </form>
         <button class="btn btn-primary py-2 px-4" type="submit" id="btn-update">수정완료</button>
         <button class="btn btn-primary py-2 px-4 ml-4" type="reset">초기화</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="/js/notice.js"></script>
<!-- 섹션 2 end -->
<%@ include file="../layout/footer.jsp" %>