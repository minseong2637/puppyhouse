<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- 섹션 1 start -->
<div class="container-fluid bg-primary mb-5">
	<div
		class="d-flex flex-column align-items-center justify-content-center"
		style="min-height: 400px">
		<h3 class="display-3 font-weight-bold text-white"
			style="font-family: Noto sans Korean">애견수첩</h3>
		<div class="d-inline-flex text-white">
			<p class="m-0">
				<a class="text-white" href="index.html">Dog Note</a>
			</p>
			<p class="m-0 px-2">/</p>
			<p class="m-0">애견수첩</p>
		</div>
	</div>
</div>
<!-- 섹션 1 end -->
<!-- 섹션 2 Start -->
<div class="container-fluid pt-5">
	<div class="container">
		<div class="bg-light p-5 rounded">
			<div class="mb-5">
				<h2 class="mb-4">알림장</h2>
				<c:forEach var="notice" items="${petnotices.content}">
					<c:if test="${pets.petId eq notice.pet.petId}">
					
						<div class="media mb-4 border-bottom">
							<img src="/img/dogImg/icon/main3-removebg-preview.png"
								alt="선생님 이미지" class="img-fluid rounded-circle mr-3 mt-1"
								style="width: 45px" />
							<div class="media-body">
								<h6>
									${notice.user.username} <small><fmt:formatDate value="${notice.createDate}" pattern="yyyy-MM-dd"/></small>
								</h6>
								<p>${notice.content}</p>
							</div>
							<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
						<button class="btn btn-danger float-right " onclick="deleteById(${notice.petBoardId},${notice.pet.petId})">삭제
						</button>
						</sec:authorize>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<div class="col-12 justify-content-center">
				<ul class="pagination justify-content-center">
					<c:choose>
						<c:when test="${petnotices.pageable.pageNumber+1<=10}">
							<li class="page-item"><a class="page-link"
								href="?page=${startBlockPage-2}" style="display: none;">이전</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="?page=${startBlockPage-2}">이전</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${startBlockPage}" end="${endBlockPage}">
						<c:choose>
							<c:when test="${petnotices.pageable.pageNumber+1==i}">
								<li class="page-item"><a class="page-link"
									href="?page=${i-1}">${i}</a>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="?page=${i-1}">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${totalPages==endBlockPage+1}">
							<li class="page-item"><a class="page-link"
								href="?page=${endBlockPage}" style="display: none;">다음</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="?page=${endBlockPage}">다음</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 섹션 2 end -->
<script type="text/javascript" src="/js/petNote.js"></script>
<%@ include file="../layout/footer.jsp"%>