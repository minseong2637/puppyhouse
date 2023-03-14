function changeClassName() {
	let x = document.getElementById("ex");
	let data = document.getElementById("userpwd");
	
	//document.getElementById("ex").className = "invalid-feedback";
	
}

//비밀번호 체크
function CV_checkPasswordPattern() {
	let x = document.getElementById("pwdchk");
	var str = document.getElementById("userpwd").value;
	var pattern1 = /[0-9]/; // 숫자
	var pattern2 = /[a-zA-Z]/; // 문자
	var pattern3 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
	
	if(!pattern1.test(str) || !pattern2.test(str) || !pattern3.test(str) || str.length < 8) {
		x.innerText = "비밀번호는 8자리 이상 문자, 숫자, 특수문자로 구성하여야 합니다."
		document.getElementById("pwdchk").className = "invalid-feedback";
		return false;
	} else {
		x.innerText = "사용가능한 비밀번호입니다."
		document.getElementById("pwdchk").className = "valid-feedback";
		return true;
	}
}


//아이디 체크
function CV_checkIdPattern(){
	let x = document.getElementById("idchk");
	var str = document.getElementById("userid").value;
	let isIdValid=this.value;
	
	var pattern1 = /[0-9]/; // 숫자
	var pattern2 = /[a-zA-Z]/; // 문자
	var pattern3 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
	
	var numtextyn = (pattern1.test(str) || pattern2.test(str));
	if(!numtextyn || pattern3.test(str) || str.length > 14) {
		x.innerText = "아이디는 14자리 이하 문자 또는 숫자로 구성하여야 합니다."
		document.getElementById("idchk").className = "invalid-feedback";
		return false;
	} else {
		let id=$("#userid").val();
		console.log(id);
		$.ajax({
			type:"POST",
			url:"/auth/idCheck/"+id,
			contentType:"application/json; charset=utf-8",
			dataType:"json"
			}).done(function(resp){
				console.log(resp.data)
				if(resp.data == 1) {
					x.innerText = "이미 사용중인 아이디 입니다."
					 this.classList.remove("is-valid");
         			 this.classList.add("is-invalid");
				}
				else {
					x.innerText = "굿!"
					this.classList.remove("is-invalid");
         			this.classList.add("is-valid");
				}
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
			
		return true;
	}
}


