$(function(){
	var id = getCookie("Cookie_id");
	
	if(id){
		$("#userid").val();
		$("#rememberid").attr("checked", true);
	}
});

function loginProcess(){
	var id = $("#userid").val();
	var idChk = $("#rememberid").is(":checked");
	
	if(idChk){
		setCookie("Cookie_id", id, 7);
	}
	else {
		deleteCookie("Cookie_id");
	}
	
};

function setCookie(cookieName, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	
	document.cookie = cookieName + "=" + cookieValue;
}

function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	
	if(start != -1){
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if(end == -1) {
			end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
	}
	return unescape(cookieValue);
}

function deleteCookie(cookieName){
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expores=" + expireDate.toGMTString();
}