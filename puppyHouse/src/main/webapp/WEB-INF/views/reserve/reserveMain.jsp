<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
	<!-- 섹션 1 start -->
  <div class="container-fluid bg-primary mb-5">
      <div
        class="d-flex flex-column align-items-center justify-content-center"
        style="min-height: 400px"
      >
        <h3 class="display-3 font-weight-bold text-white" style="font-family: Noto sans Korean">예약</h3>
        <div class="d-inline-flex text-white">
          <p class="m-0"><a class="text-white" href="/">Home</a></p>
          <p class="m-0 px-2">/</p>
          <p class="m-0">예약</p>
        </div>
      </div>
    </div>
	<!-- 섹션 1 end -->   

  <!-- 예약안내 영역 start -->
  <div class="container-fluid">
    <div class="container col">
      <div class="mx-auto col-12 col-xl-10 col-lg-10 mb-5 px-md-5 px-lg-5 px-xl-5">
        <h3 class="mx-auto py-3 text-center">예약 안내</h3>
      </div>
    </div>
  </div>


  <div class="container-fluid py-5">
    <div class="container">
      <div class="row align-items-center justify-content-center">
        <div class="col-5 col-xl-2 col-lg-2 col-md-3 pr-xl-3 pr-lg-3 pr-md-5">
          <img class="img-fluid rounded mb-5" src="../img/search.png" alt="reserveInfoImage1"/>
        </div>
        <div class="col-9 col-xl-7 col-lg-7 col-md-8 pl-xl-5 pl-lg-5 pl-md-4">
          <div class="">
            <h4>유의사항</h4>
          </div>
          <div class="row pt-2 pb-4">
            <div class="col-12 col-md-12 col-sm-12">
              <ul class="list-inline lm-0">
                <li class="py-2"><i class="fa fa-circle text-primary mr-3"></i><strong>유치원 이용시간은 오전 07:00 ~ 오후 08:00 입니다.</strong></li>
                <li class="py-2"><i class="fa fa-circle text-primary mr-3"></i><strong>예약은 이용권 결제 및 애견수첩 등록 후 가능합니다.</strong></li>
                <li class="py-2"><i class="fa fa-circle text-primary mr-3"></i><strong>다견가정의 경우 각 반려견 별로 예약하여 주시기 바랍니다.</strong></li>
                <li class="py-2"><i class="fa fa-circle text-primary mr-3"></i><strong>일일 체험을 원하시는 고객님은 전화로 문의주시기 바랍니다.</strong></li>
              </ul>
            </div>
          </div>
        </div>
        
      </div>
    </div>
  </div>

  <div class="container-fluid py-5">
    <div class="container">
      <div class="row align-items-center justify-content-center">
        <div class="col-5 col-xl-2 col-lg-2 col-md-3 pr-xl-3 pr-lg-3 pr-md-5">
          <img class="img-fluid rounded mb-5" src="../img/dog sign.png" alt="reserveInfoImage1"/>
        </div>
        <div class="col-9 col-xl-7 col-lg-7 col-md-8 pl-xl-5 pl-lg-5 pl-md-4">
          <div class="">
            <h4>이용제한 안내</h4>
          </div>
          <div class="row pt-2 pb-4">
            <div class="col-12 col-md-12 col-sm-12">
              <ul class="list-inline lm-0">
                <li class="py-2"><i class="fa fa-circle text-primary mr-3"></i><strong>반려견 특성에 따라 활동 내용이 변경될 수 있습니다.</strong></li>
                <li class="py-2"><i class="fa fa-circle text-primary mr-3"></i><strong>1년 이내 5가지 접종(종합, 코로나, 켄넬코프, 신종 인플루엔자, 광견병) 받지 않은 반려견은 이용이 제한됩니다.
                  <br>※ 접종기록은 접종병원명이 꼭 필요하므로 증빙 자료는 미리 준비해주세요.</strong></li>
                <li class="py-2"><i class="fa fa-circle text-primary mr-3"></i><strong>1살 미만의 반려견인 경우 항체 검사가 필수 입니다. (단, 1살 이상 시 재접종 기록 확인)</strong></li>
                <li class="py-2"><i class="fa fa-circle text-primary mr-3"></i><strong>내 반려견이 공격성이 있거나 입마개가 필요한 경우 꼭 입마개를 준비해주시기 바랍니다.
                  (입마개 착용이 불가능할 시 일부 활동이 제한될 수 있습니다.)</strong></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="container-fluid py-5">
    <div class="container">
      <div class="row align-items-center justify-content-center">
        <button class="btn btn-primary py-2 px-4" onclick="location.href='/reserve/reserve'">예약하기</button>
      </div>
    </div>
  </div>
  
  <!-- 예약 안내 end -->
  <!-- 메인 end -->
<%@ include file="../layout/footer.jsp" %> 