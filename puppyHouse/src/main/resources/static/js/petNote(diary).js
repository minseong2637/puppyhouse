function deleteById(petBoardId, petId) {
	$.ajax({
		type:"DELETE",
		url:`/petNote/diary/delete/${petBoardId}`,
		dataType:"json"
	}).done(function(resp){
		alert("삭제가 완료되었습니다.");
		location.href=`/petNote/diary/${petId}`;
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
}