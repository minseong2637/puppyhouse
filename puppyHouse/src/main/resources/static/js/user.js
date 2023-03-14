let code;
let index = {
	init: function() {
		$("#btn-save").on("click",()=>{
			this.save();
		}),
		$("#btn-update").on("click",()=>{
			this.update();
		}),
		$("#btn-findUserId").on("click",()=>{
			this.findUserId();
		}),
		$("#btn-findPassword").on("click",()=>{
			this.findUserPassword();
		}),
		$("#btn-resetPassword").on("click",()=>{
			this.resetUserPassword();
		}),
		$("#mail-Check-Btn").on("click",()=>{
			this.findUserMail();
		}),
		$('#Num-Check-Btn').on("click",()=>{
			this.checkNum();
		});
		$('#mail-Check-Btn2').on("click",()=>{
			this.findUserMail2();
		});
	},
	

	save: function() {
		let data={
			username: $("#username").val(),
			userid: $("#userid").val(),
			userpassword: $("#userpwd").val(),
			email: $("#useremail1").val(),
			phone: $("#userphone").val(),
			addr: $("#useraddr").val(),
			addrdetail: $("#useraddrdetail").val()
		};
		console.log("namecheck"+nameCheck());
		console.log("idCheck"+idCheck());
		console.log("pwdCheck"+pwdCheck());
		console.log("pwdCheck2"+pwdCheck2());
		console.log("emailCheck"+emailCheck());
		console.log("phoneCheck"+phoneCheck());
		console.log("addrCheck"+addrCheck());
		console.log("addrCheck2"+addrcheeeck());
		console.log("numCheck"+numCheck());
		let test = 	nameCheck()&&idCheck()&&pwdCheck()&&pwdCheck2()&&emailCheck()&&phoneCheck()&&addrCheck()&&addrcheeeck();
		
		if(test){		
			$.ajax({
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
			
			}).done(function(resp){
				alert("회원가입이 완료되었습니다.");
				location.href="/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});	
		}
		else {
			alert("정보를 확인해 주세요")
		}
	},
	
	update: function(){
		let id=$("#id").val();
		let data={  
			id: $("#id").val(),
			username: $("#username").val(),
			userid: $("#userid").val(),
			userpassword: $("#userpassword").val(),
			phone: $("#userphone").val(),
			email: $("#useremail1").val(),
			addr: $("#useraddr").val(),
			addrdetail: $("#useraddrdetail").val()
		};
		console.log("pwdCheck"+pwdCheck());
		console.log("pwdCheck2"+pwdCheck2());
		console.log("emailCheck"+emailCheck());
		console.log("phoneCheck"+phoneCheck());
		console.log("addrcheeeck"+addrcheeeck());
		console.log("addrCheck"+addrCheck());
		let test = pwdCheck()&&pwdCheck2()&&emailCheck()&&phoneCheck()&&addrcheeeck()&&addrCheck();
		console.log("test =" + test);
		if(test) {
			$.ajax({
			type:"PUT",
			url:"/user/"+id,
			data:JSON.stringify(data),  
			contentType:"application/json; charset=utf-8",
			dataType:"json"
			}).done(function(resp){
				alert("회원수정이 완료되었습니다.");
				location.href="/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		}
		else {
			alert("정보를 확인해 주세요.")
		}
		
	},

	findUserId: function(){
		let data={
			username: $("#username").val(),
			phone: $("#phone").val()
		};
		console.log(data);
		$.ajax({
			type:"POST",
			url:"/auth/user/findUserId",
			data:JSON.stringify(data),  
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			if(resp.data.userid!=undefined){
				$("#findIdForm").remove();
				$("#findbtn").remove();
				$(".changeDiv").attr('class','col-lg-7 mb-5 changeDiv justify-content-center text-center');
				$(".changeDiv").append("<div id='foundId' class='control-group'><i class='fa fa-circle text-primary mr-3'></i>"+
					"<strong>내 아이디: "+resp.data.userid+"</strong></div>");
				$("#foundId").after("<br><br><div class=''><a href='/auth/loginForm' class='btn btn-primary py-2 px-4'>"+
					 "로그인하기</a><a href='/auth/findUserPassword' class='btn btn-primary py-2 px-4 ml-4'>비밀번호찾기</a></div>");
			}else{
				alert("일치하는 회원 정보가 없습니다.");
			}
			console.log(resp.data.userid);
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	findUserMail: function(){
		let data={
			email: $('#useremail1').val()
		};
		let checkInput = $('#mail-check-input');
		console.log('완성된 이메일:'+data.email);
		$.ajax({
			type:"POST",
			url:"/auth/user/findUserEmail",
			data:JSON.stringify(data),  
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			if(resp.data.email!=undefined) {
				sendMail(resp.data);
				checkInput.attr('disabled',false);
			} else {
				alert("일치하는 회원 정보가 없습니다.");
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
		
	findUserMail2: function() {
		let data={
			email: $('#useremail1').val()
		};
		let checkInput = $('#mail-check-input');
		console.log('완성된 이메일:'+data.email);
		$.ajax({
			type:"POST",
			url:"/auth/user/findUserEmail",
			data:JSON.stringify(data),  
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			if(resp.data.email!=undefined) {
				alert("이미사용중인 이메일입니다.");
			} else {
				sendMail2(data.email);
				checkInput.attr('disabled',false);
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	// 인증 번호 비교 
	checkNum: function () {
		const inputCode = $('#mail-check-input').val();
		const userpwd = $('#userpwd');
		const chkUserpwd = $('#userpwdChk');
		
		$.ajax({
			type : "GET",
			success : function (data) {
				if(inputCode === code){
					alert('인증번호 확인이 완료되었습니다.');
					userpwd.attr('disabled',false);
					chkUserpwd.attr('disabled',false); 
				}else{
					alert('인증번호가 불일치합니다.');
				}
			}
		});
	},
	
	resetUserPassword: function(){
		console.log("resetUserPassword함수 호출");
		let data={
			email: $('#userEmail1').val()+$('#userEmail2').val(),
			userpassword: $("#userpwd").val()
		};
		console.log(data);
		$.ajax({
			type:"PUT",
			url:"/auth/user/resetUserPassword",
			data:JSON.stringify(data),  
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("비밀번호 변경이 완료되었습니다.");
			location.href="/auth/loginForm";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	
}

	
index.init();

// 유효성 여부를 저장할 변수를 만들고 초기값 false 부여
   let isNickValid=false;
   let isIdValid=false;
   let isPwdValid=false;
   let isMailValid=false;
   let isPhoneValid=false;
   let isAddrValid=false;	

//이름
function nameCheck(){ 
	let inputNick = document.getElementById("username").value;
	let f = document.getElementById("username");
	let x = document.getElementById("namechk");
	
	var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크
	var pattern1 = /[0-9]/; // 숫자
	var pattern2 = /[a-zA-Z]/; // 영문자
	var pattern3 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
	
	var nametest = (pattern_kor.test(inputNick) || pattern2.test(inputNick));
	if(!nametest || pattern3.test(inputNick) || pattern1.test(inputNick) || inputNick.length > 10) {
		x.innerText = "10글자 이하로 입력해주세요.(숫자와 특수문자를 사용할 수 없습니다.)"
		isNickValid = false;
	}
	else {
		isNickValid = true;
	}		
 
    if(isNickValid) {
    	f.classList.remove("is-invalid");
        f.classList.add("is-valid");
        return true;
        }
    else {
    	f.classList.remove("is-valid");
    	f.classList.add("is-invalid");
    	
    	if(inputNick == "") {
			x.innerText = "이름은 필수 입력 값입니다."
		}
		else {
			x.innerText = "10글자 이하로 입력해주세요.(숫자와 특수문자를 사용할 수 없습니다.)"
		}
		return false;
	}
}

//아이디
function idCheck() {
	let inputId = document.getElementById("userid").value;
		
	let x = document.getElementById("idchk");
	let y = document.getElementById("idchk2");
	let f = document.getElementById("userid");
     
 	var pattern1 = /[0-9]/; // 숫자
	var pattern2 = /[a-zA-Z]/; // 문자
	var pattern3 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
     
 	var numtextyn = (pattern1.test(inputId) || pattern2.test(inputId));
	if(!numtextyn || pattern3.test(inputId) || inputId.length > 14) {
		x.innerText = "아이디는 14자리 이하 문자 또는 숫자로 구성하여야 합니다."
		isIdValid = false;	
	}
	else {
		isIdValid = true;
	}
     
	if(isIdValid){
		let id=$("#userid").val();
	
		$.ajax({
			type:"POST",
			url:"/auth/idCheck/"+id,
			contentType:"application/json; charset=utf-8",
			dataType:"json"
			}).done(function(resp){
				console.log(resp.data)
				if(resp.data == 1) {
					 x.innerText = "이미 사용중인 아이디 입니다."
					 f.classList.remove("is-valid");
	     			 f.classList.add("is-invalid");
	     			 return false;
				}
				else {
					y.innerText = "사용 가능한 아이디 입니다."
					f.classList.remove("is-invalid");
	     			f.classList.add("is-valid");
	     			return true;
				}
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
  return true;
  }
  else {
     f.classList.remove("is-valid");
     f.classList.add("is-invalid");
     
     if(inputId == ""){
		x.innerText = "아이디는 필수 입력 값입니다."	
	}
     else {
		x.innerText = "아이디는 14자리 이하 문자 또는 숫자로 구성하여야 합니다."
	}
	return false;
  }
}

//비밀번호
function pwdCheck() {
	let inputPwd = document.getElementById("userpwd").value;
	let f = document.getElementById("userpwd");
	let x = document.getElementById("pwdchk");
	
	var pattern1 = /[0-9]/; // 숫자
	var pattern2 = /[a-zA-Z]/; // 문자
	var pattern3 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
    console.log(inputPwd);
      if(!pattern1.test(inputPwd) || !pattern2.test(inputPwd) || !pattern3.test(inputPwd) || inputPwd.length < 8) {
	  	x.innerText = "비밀번호는 8자리 이상 문자, 숫자, 특수문자로 구성하여야 합니다."
	  	isPwdValid = false;
	  }
	  else {
		isPwdValid = true;
	  }
	  
      if(isPwdValid){
         f.classList.remove("is-invalid");
         f.classList.add("is-valid");
         return true;
      }
      else {
         f.classList.remove("is-valid");
         f.classList.add("is-invalid");
         if(inputPwd == ""){
			x.innerText = "비밀번호는 필수 입력 값입니다."	
		}
		return false;
      }
} 
   
//비밀번호 확인
function pwdCheck2() {
	let inputPwdChk = document.getElementById("userpwdChk").value;
	let f = document.getElementById("userpwdChk");	
	let inputPwd = document.getElementById("userpwd").value;
	console.log(inputPwdChk);
	if(inputPwd != inputPwdChk) {
		 f.classList.remove("is-valid");
     	 f.classList.add("is-invalid");
     	 return false;
	}
	else {
		f.classList.remove("is-invalid");
     	f.classList.add("is-valid");
     	return true;
	}
}  

//이메일
function emailCheck() {
	let x = document.getElementById("emailchk");
	let inputEmail = document.getElementById("useremail1").value;
	let f = document.getElementById("useremail1");

	//2. 유효성(5글자이상 10글자 이하)을 검증한다.
	  isMailValid = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(inputEmail);
	  //3. 유효하다면 input 요소에 is-valid 클래스 추가, 아니라면 is-invalid 클래스 추가
	  if(isMailValid){
		
		let data={
			email: $('#useremail1').val()
		};
		let checkInput = $('#mail-check-input');
		console.log('완성된 이메일:'+data.email);
		$.ajax({
			type:"POST",
			url:"/auth/user/findUserEmail",
			data:JSON.stringify(data),  
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			console.log(resp.data.email);
			if(resp.data.email!=undefined) {
				f.classList.remove("is-valid");
	     		f.classList.add("is-invalid");
	     		x.innerText = "이미 사용중인 이메일입니다."
			} else {
				
				console.log(data.email);
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		
	     f.classList.remove("is-invalid");
	     f.classList.add("is-valid");
	     return true;
	  }
	  else {
	     f.classList.remove("is-valid");
	     f.classList.add("is-invalid");
	     if(inputEmail == "") {
			x.innerText = "이메일은 필수 입력 값입니다."
		}
		 else{
			x.innerText = "올바른 형식으로 작성해 주세요"
		}
		return false;
	  }
	 	  
} 

function emailCheck2() {
	let x = document.getElementById("emailchk");
	let inputEmail = document.getElementById("useremail1").value;
	let f = document.getElementById("useremail1");

	//2. 유효성(5글자이상 10글자 이하)을 검증한다.
	  isMailValid = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(inputEmail);
	  //3. 유효하다면 input 요소에 is-valid 클래스 추가, 아니라면 is-invalid 클래스 추가
	  if(isMailValid){
		
		let data={
			email: $('#useremail1').val()
		};
		let checkInput = $('#mail-check-input');
		console.log('완성된 이메일:'+data.email);
		$.ajax({
			type:"POST",
			url:"/auth/user/findUserEmail",
			data:JSON.stringify(data),  
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			console.log(resp.data.email);
			if(resp.data.email!=undefined) {
				
				console.log(data.email);
			} else {
				f.classList.remove("is-valid");
	     		f.classList.add("is-invalid");
	     		x.innerText = "이미 사용중인 이메일입니다."
				
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		
	     f.classList.remove("is-invalid");
	     f.classList.add("is-valid");
	     return true;
	  }
	  else {
	     f.classList.remove("is-valid");
	     f.classList.add("is-invalid");
	     if(inputEmail == "") {
			x.innerText = "이메일은 필수 입력 값입니다."
		}
		 else{
			x.innerText = "올바른 형식으로 작성해 주세요"
		}
		return false;
	  }
	 	  
} 



//이메일 인증번호 확인
function numCheck() {
	const inputCode = $('#mail-check-input').val();
	const userpwd = $('#userpwd');
	const chkUserpwd = $('#userpwdChk');
	
	let f = document.getElementById("mail-check-input");
	
	$.ajax({
		type : "GET",
		success : function (data) {
			if(inputCode === code){
				alert('인증번호 확인이 완료되었습니다.');
				userpwd.attr('disabled',false);
				chkUserpwd.attr('disabled',false); 
				f.classList.remove("is-invalid");
    			f.classList.add("is-valid");
				return true;	         
			}else{
				alert('인증번호가 불일치합니다.');
				f.classList.remove("is-valid");
    			f.classList.add("is-invalid");
				return false;
			}
			
		},
		error : function(error) {
			return false;
		}
	});
}


//전화번호
function phoneCheck() {

	  let inputPhone = document.getElementById("userphone").value;
	  let f = document.getElementById("userphone");
	  
	  isPhoneValid = /^01(?:0|1|[6-9])(?:\d{4})\d{4}$/.test(inputPhone);
     
      if(isPhoneValid){
         f.classList.remove("is-invalid");
         f.classList.add("is-valid");
         return true;
      }
      else {
         f.classList.remove("is-valid");
         f.classList.add("is-invalid");
         return false;
      }
} 
 
//주소
function addr() {
	let inputAddr = document.getElementById("useraddr").value;
	 new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("useraddr").value = data.address; // 주소 넣기
                document.querySelector("input[name=useraddr]").focus(); //상세입력 포커싱
            }
        }).open();
    
	
	let f = document.getElementById("useraddr");
	if(inputAddr == "") {
		f.classList.remove("is-valid");
		f.classList.add("is-invalid");
		return false;
		
	}
	else {
		f.classList.remove("is-invalid");
        f.classList.add("is-valid");
		return true;
	}
    
}
function addrcheeeck() {

	let inputAddr = document.getElementById("useraddr").value;
	let f = document.getElementById("useraddr");
	if(inputAddr == "") {
		f.classList.remove("is-valid");
		f.classList.add("is-invalid");
		return false;
		
	}
	else {
		f.classList.remove("is-invalid");
        f.classList.add("is-valid");
		return true;
	}
}


//상세주소
function addrCheck() {
	  
      let isAddrValid = document.getElementById("useraddrdetail").value;
      let f = document.getElementById("useraddrdetail")
     	console.log("상세주소: " + isAddrValid);
      if(isAddrValid == ""){
         f.classList.remove("is-valid");
         f.classList.add("is-invalid");
         return false;
      }else{
         f.classList.remove("is-invalid");
         f.classList.add("is-valid");
         return true;
      }
}

function sendMail(foundData) {
		let data = {email: foundData.email};
		let checkInput = $('#mail-check-input');
		$.ajax({
			type : "GET",
			url : "/auth/mailCheck/"+data.email,
			success : function (data) {
				console.log("data : " +  data);
				checkInput.attr('disabled',false);
				alert('인증번호가 전송되었습니다.');
				code =data;
			}			
		});
	}
function sendMail2(email) {
		let data = {email: email};
		let checkInput = $('#mail-check-input');
		$.ajax({
			type : "GET",
			url : "/auth/mailCheck/"+data.email,
			success : function (data) {
				console.log("data : " +  data);
				checkInput.attr('disabled',false);
				alert('인증번호가 전송되었습니다.');
				code =data;
			}			
		});
	}