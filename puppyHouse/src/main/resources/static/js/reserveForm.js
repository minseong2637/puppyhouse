let index={
	init: function(){
		$("#reserve_btn").on("click",()=>{
			this.save();
		});
		
		$("#reserve_modify_btn").on("click",()=>{
			this.update();
		});
		
		$("#reserve_delete_btn").on("click",()=>{
			this.deleteById();
		});
		
	},
	
	save: function(){
		
		let	petId= $("#dogname").val();
		let	teacherId= $("#trainer").val();
		let data={
			reservdate: $("#reserveDate").val()
			};
			console.log(data);
			$.ajax({
			type:"POST",
			url:"/api/petReserve/"+petId+"/"+teacherId,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("예약이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
		update: function(){
		
		let	reservId= $("#reservId").val();
		let pet= $("#dogname").val();
		let	teacher= $("#trainer").val();

		let data={
			reservdate: $("#reserveDate").val()
			};
			console.log(reservId,pet,teacher);
			$.ajax({
			type:"PUT",
			url:"/api/revser/"+reservId+"/"+pet+"/"+teacher,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("예약 수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	deleteById: function(){
		
		var	reservId= $("#reservId").val();
		
			$.ajax({
			type:"DELETE",
			url:"/api/revserdelete/"+reservId,
			dataType:"json"
		}).done(function(resp){
			alert("예약 취소가 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
}
index.init();