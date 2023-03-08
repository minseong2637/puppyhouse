<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>index</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <!-- 아이콘 -->
    <link href="img/favicon.ico" rel="icon" />

    <!-- 구글 웹 폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Handlee&family=Nunito&display=swap"
      rel="stylesheet"
    />

    <!-- Font Awesome -->
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
      rel="stylesheet"
    />

    <!-- Flaticon Font -->
    <link href="lib/flaticon/font/flaticon.css" rel="stylesheet" />

    <!-- 라이브러리 스타일시트 -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet" />
    <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet" />
    
        <!-- 캘린더 스타일시트 -->
    <link href="css/myReservation.css" rel="stylesheet" />
</head>

<body>
	<!-- 메뉴 Start -->
    <div class="container-fluid position-relative shadow">
      <nav
        class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0 px-lg-5"
      >
          <a
          href="index.jsp"
          class="navbar-brand font-weight-bold text-secondary"
          style="font-size: 50px"
        >
      	<img class="img-fluid" src="img/logo.png" alt="logo">
        </a>
        <button
          type="button"
          class="navbar-toggler"
          data-toggle="collapse"
          data-target="#navbarCollapse"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
          <div class="navbar-nav font-weight-bold mx-auto">
            <a href="index.jsp" class="nav-item nav-link active mr-3">Home</a>
            <a href="introduce.jsp" class="nav-item nav-link mr-3">소개</a>
            <a href="reserve/reserveMain.jsp" class="nav-item nav-link mr-3">예약</a>
			<a href="commuBoard/commuMain.jsp" class="nav-item nav-link mr-3">커뮤니티</a>
			<a href="news/newsMain.jsp" class="nav-item nav-link mr-3">고객센터</a>
          </div>
          <a href="../user/loginForm" class="btn btn-primary px-3">로그인</a>
		  <a href="user/joinForm.jsp" class="btn btn-primary px-3 ml-4">회원가입</a>
        </div>
      </nav>
    </div>
    <!-- 메뉴 End --> 