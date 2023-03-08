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
      <div class="row pb-2">
        <div class="col-lg-12 mb-2">
          <span id="commuId" hidden>${commu.commuId}</span>
          <span id="userId" hidden>${principal.user.id}</span> 
          <h3 class="col-lg-12 mb-2">${commu.title}</h3>
          <hr>
          <div class="col-lg-12 mb-2 row justify-content-between">
            <p>작성자 : ${commu.users.userid}</p>
            <p>조회수 : ${commu.count}</p>
          </div>
          <hr>
          <div class="col-lg-12 mb-2">
            <div>${commu.content}</div>
          </div>
          <hr>
          <div class="col-lg-12 mb-2">
          	<c:choose>
          		<c:when test="${commu.imgOriName == null}">
          			<div class="border border-2 img-fluid" style="display: none;">
		           		<img src="/auth/image?imgName=${commu.imgName}" alt="프로필 사진" style="width: 100%; height: 100%; object-fit: cover;">
		         	</div>
          		</c:when>
          		<c:otherwise>
          			<div class="border border-2 img-fluid" style="width: 300px; height: 300px; overflow: hidden; background-color: white;">
		           		<img src="/auth/image?imgName=${commu.imgName}" alt="사진" style="width: 100%; height: 100%; object-fit: cover;">
		         	</div>
		         	<hr>
          		</c:otherwise>
          	</c:choose>
          </div>	
          
        </div>
        <div class="col-lg-12">
        	<c:set var="like_state" value="false" />
        	<c:forEach	var="likes" items="${commu.likes}">
        		<c:if test="${likes.users.userid == principal.user.userid}">
        			<c:set var="like_state" value="true" />
        			<span id="likesId" hidden>${likes.id}</span>
        		</c:if>
        	</c:forEach>
        	<c:choose>
        		<c:when test="${like_state == true}">
        			<button class="text-primary font-weight-bold navbar-brand mb-3 btn" id="btn-unliked" style="font-size: 25px;">
		            	<i class="fas fa-heart"></i>
		          	</button>  	
        		</c:when>
        		<c:otherwise>
	        		<button class="text-primary font-weight-bold navbar-brand mb-3 btn" id="btn-liked" style="font-size: 25px;">
		            	<i class="far fa-heart"></i>
		          	</button>
        		</c:otherwise>
        	</c:choose>
          <em>${commu.likescount}</em> &nbsp;&nbsp;&nbsp;&nbsp;
          <i class="text-primary font-weight-bold navbar-brand fas fa-comment" style="font-size: 25px;"></i>
          <em>${commu.replycount}</em>
          <c:if test="${commu.users.userid==principal.user.userid}">
          	<div class="float-lg-right float-md-right">
	            <a href="/commuBoard/${commu.commuId}/commuUpdate" class="btn btn-link" style="text-decoration: none; background-color: #dee2e6">수정</a>
	            <a class="btn btn-link" id="btn-delete" style="text-decoration: none; background-color: #dee2e6">삭제</a>
          	</div>
          </c:if>          
          <hr>
        </div>
        <div class="col-lg-12">
        <c:choose>
        	<c:when test="${empty principal}">
        		<a href="/commuBoard/${commu.commuId}">
        			<div class="control-group"> 
		            <input type="text" class="form-control" id="reply-content" placeholder="댓글을 입력해주세요">
		            <button class="btn btn-outline-primary" id="btn-reply-save" style="position: absolute; right: 15px; top: 0;">등록</button>
              		<hr>
        		</a>
        	</c:when>
        	<c:otherwise>
        		<div class="control-group"> 
	            <input type="text" class="form-control" id="reply-content" placeholder="댓글을 입력해주세요">
	            <button class="btn btn-outline-primary" id="btn-reply-save" style="position: absolute; right: 15px; top: 0;">등록</button>
           		<hr>
        	</c:otherwise>    	
        </c:choose>
          <!-- 댓글 반복 출력 -->
          <div class="card">
          	<div class="card-header">댓글 목록</div>
          		<div class="list-group">
          			<c:forEach var="reply" items="${commu.reply}">
          				<div class="list-group-item d-flex justify-content-between">
          					<span class="replyId" id="replyId">${reply.replyId}</span>
	          				<div>${reply.users.userid}</div>
	          				<div>${reply.content}</div>
	          				<div>
	          					<fmt:formatDate value="${reply.createDate}" pattern="yyyy-MM-dd" />
	          					<c:if test="${reply.users.userid==principal.user.userid}">
	          						&nbsp;<button class="text-dark fas fa-window-close float-right btn" style="text-decoration: none;"
	          									onclick="replyDeleteById(${commu.commuId},${reply.replyId})"></button>
	          					</c:if>	
	          				</div>
          				</div>
          			</c:forEach>
          		</div> 
          </div>
        </div>
      </div>
    </div>
  </div>
   <script type="text/javascript" src="/js/commu.js"></script>
  <!-- 섹션 2 end -->
<%@ include file="../layout/footer.jsp" %>