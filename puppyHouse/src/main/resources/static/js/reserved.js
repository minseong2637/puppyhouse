const makeCalendar = (date) => {
  // 현재 년도와 월 받아오기
  const currentYear = new Date(date).getFullYear();
  const currentMonth = new Date(date).getMonth() + 1;

  // 첫날의 요일 구하기 - 초기 시작위치를 위해서
  const firstDay = new Date(date.setDate(1)).getDay();
  // 마지막 날짜 구하기
  const lastDay = new Date(currentYear, currentMonth, 0).getDate();

  // 남은 박스만큼 다음달 날짜 표시
  const limitDay = firstDay + lastDay;
  const nextDay = Math.ceil(limitDay / 7) * 7;

  let htmlDummy = '';

  let dateNum = '';

  // 한달전 날짜 표시하기
  for (let i = 0; i < firstDay; i++) {
    htmlDummy += `<div class="noColor"></div>`;
  }

  // 이번달 날짜 표시하기
  for (let i = 1; i <= lastDay; i++) {    
    htmlDummy += `<button type="button" class="dateBox" tabindex="${i}"><div class="dateNum">${i}</div></button>`;
  }

  // 다음달 날짜 표시하기
  for (let i = limitDay; i < nextDay; i++) {
    htmlDummy += `<div class="noColor"></div>`;
  }

  document.querySelector(`#calendar_dateBoard`).innerHTML = htmlDummy;
  document.querySelector(`.dateTitle`).innerText = `${currentYear}년 ${currentMonth}월`;
  
  // 예약내역- 날짜 선택 시 상세내역에 날짜 표시하기 
  let selectedNum = 0;

  document.querySelectorAll(`.dateBox`).forEach((el, index) => {
    el.onclick = () => {
      selectedNum = index+1;
      document.querySelector(`.selectedDate`).innerText = `${currentYear}/${currentMonth}/${selectedNum}`
    }
  })

  // 예약하기- 캘린더 날짜 선택하면 폼 인풋에 날짜 표시
  document.querySelectorAll(`.dateBox`).forEach((el, index) => {
    el.onclick = () => {
      selectedNum = index+1;
      document.querySelector(`.reserveDate`).value = `${currentYear}/${currentMonth}/${selectedNum}`
    }
  })

  // 예약 가능한 날
}
const date = new Date();
    
makeCalendar(date);
    
// 이전달 이동
document.querySelector(`.btn-prevDay`).onclick = () => {
  makeCalendar(new Date(date.setMonth(date.getMonth() - 1)));
}
  
// 다음달 이동
document.querySelector(`.btn-nextDay`).onclick = () => {
  makeCalendar(new Date(date.setMonth(date.getMonth() + 1)));
}

