let index={
	init: function(){
		$("#note-save").on("click",()=>{
			this.save();
		});
	},
	
	save: function(){
		console.log(petid)
		let	petid= $("#test").val();
		let data={
			roles: $("#roles").val(),
			content: $("#content").val()
			};
			$.ajax({
			type:"POST",
			url:"/api/petboard/"+petid,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	}
	
	function deleteById(petBoardId,petId) {
		
		$.ajax({
			type:"DELETE",
			url:`/api/petboard/${petBoardId}`,
			dataType:"json"
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href=`/petNote/${petId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
}
index.init();