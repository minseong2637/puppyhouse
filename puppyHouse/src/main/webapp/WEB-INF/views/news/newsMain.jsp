<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <div class="col justify-content-center pb-2">
      <div class="row">
        <div class="col-lg-12 text-center mb-2">
          <ul class="list-inline mb-4" id="portfolio-flters">
            <li class="d-inline-block  m-1"><a class="btn btn-outline-primary" href="/auth/news/NOTICE">공지사항</a></li>
            <li class="d-inline-block  m-1"><a class="btn btn-outline-primary" href="/auth/news/FAQ">FAQ</a></li>
          </ul>
        </div>
      </div>
      <!-- 공지사항 글만 보이는 페이지 -->
      <div class="col-12">
      	<c:forEach var="notice" items="${notices.content}">
      		<ul class="list-inline col-lg-12 portfolio-container">
          		<li class="portfolio-item col-lg-12">
            <i class="pl-2 pr-5 mr-5">${notice.noticeId}</i>
            <a href="/news/${notice.noticeId}">${notice.title}</a>
            <span style="float: right;"><fmt:formatDate value="${notice.createDate}" pattern="yyyy-MM-dd"/></span>
            <p class="help-block text-danger"><hr></p>
          		</li>
        	</ul>
      	</c:forEach>
      </div>
      <div class="col-8 justify-content-center">
      	<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${notices.first}">
					<li class="page-item disabled"><a class="page-link" href="?page=${notices.number-1}">이전</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${notices.number-1}">이전</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startBlockPage}" end="${endBlockPage}">
				<c:choose>
					<c:when test="${notices.pageable.pageNumber+1==i}">
						<li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${notices.last}">
					<li class="page-item disabled"><a class="page-link" href="?page=${notices.number+1}">다음</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${notices.number+1}">다음</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
      </div>
		<br>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
			<div class="col-3 justify-content-center">
				<a href="/news/saveForm" class="btn btn-primary mt-4 py-2 px-4">글 쓰기</a>
			</div>
		</sec:authorize>
    </div>
  </div>
</div>
<script type="text/javascript" src="/js/notice.js"></script>
<!-- 섹션 2 end -->
<%@ include file="../layout/footer.jsp" %>