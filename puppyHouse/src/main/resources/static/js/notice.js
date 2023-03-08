let index={
	init: function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
		$("#btn-delete").on("click",()=>{
			this.deleteById();
		});
		$("#btn-update").on("click",()=>{
			this.update();
		})
	},
	
	save: function(){
		let roles=$("#roles").val();
		let data={
			title: $("#title").val(),
			roles: $("#roles").val(),
			content: $("#content").val()
		};
		$.ajax({
			type:"POST",
			url:"/api/notice",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href="/auth/news/"+roles;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	deleteById: function() {
		var id=$("#id").val();
		var roles=$("#roles").val();
		console.log(id);
		$.ajax({
			type:"DELETE",
			url:"/api/notice/delete/"+id,
			dataType:"json"
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href="/auth/news/"+roles;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	update: function(){
		let id=$("#id").val();
		
		let data={
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			type:"PUT",
			url:"/api/notice/"+id,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("수정이 완료되었습니다.");
			location.href="/news/"+id;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
}
index.init();