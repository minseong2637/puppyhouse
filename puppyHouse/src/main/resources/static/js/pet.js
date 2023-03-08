let index={
	init: function(){
		$("#pet_btn_save").on("click",()=>{
			this.save();
		});
		$("#pet-delete").on("click",()=>{
			this.deleteByPet();
		});
	},
	
	
	
	save: function(){
		let birth = $("#birthday").val();
		let birthage = $("#dogage").val();

		let agetype;
		if(birthday.length !=0 ){
			agetype = birth;
		}
		if(dogage.length !=0 ){
			agetype = birthage;
		}
		let data={
			petId: $("#petId").val(),
			petName: $("#petName").val(),
			agetype: agetype,
			species: $("#species").val(),
			gender: $("#gender").val(),
			neutered: $("#neutered").val(),
			weight: $("#weight").val(),
			allergy: $("#allergy").val(),
			etc: $("#etc").val(),
			age: $("#petage").val(),
			birthday: $("#petbirthday").val(),
		};
		$.ajax({
			type:"put",
			url:"/api/pet",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json",
		}).done(function(resp){
			alert("강아지 정보가 등록되었습니다.");
			location.href="/";
			
		}).fail(function(error){
			alert(JSON.stringify(error));
		})
	},
	
	deleteByPet: function(){
		var id = $("#petinfo").text();
		
		$.ajax({
			type:"DELETE",
			url:"/api/petNoteInfo/"+id,
			dataType:"json"
		}).done(function(resp){
			alert("삭제 완료");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});

	},
	
}
index.init();