let index = {
	init: function() {
		$("#reserveDate").on("change", () => {
			this.reserv();
		})
	},

	reserv: function() {
		//alert("reserv자바스크립트");
		let reserveDate = document.querySelector('#reserveDate');
		let data = {
			reservdate: reserveDate.options[reserveDate.selectedIndex].innerText
		};
		var petIdText;
		//alert(data.reservdate);
		$.ajax({
			type: "POST",
			url: "/api/petReserve/test",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			$('#reserveDog').empty();
			$('#reserveDogId').empty();
			var idsplit = resp.id;
			var optionsplit = resp.data;
			for (var i = 0; i < optionsplit.length; i++) {
				var option = $("<option value=" + idsplit[i] + ">" + optionsplit[i] + "</option>");
				$('#reserveDog').append(option);
				petIdText = $("<input id='reservePetId' type='hidden' value=" + idsplit[i] + ">");
				$('#reserveDogId').append(petIdText);
			}

			$("#note-save").on("click", () => {
				save();
			});

			function save() {
				let petid =$("#reservePetId").val();
				console.log(petid)
				let data = {
					roles: $("#roles").val(),
					content: $("#content").val()
				};
				console.log(data);
				$.ajax({
					type: "POST",
					url: "/api/petboard/" + petid,
					data: JSON.stringify(data),
					contentType: "application/json; charset=utf-8",
					dataType: "json"
				}).done(function(resp) {
					alert("글쓰기가 완료되었습니다.");
					location.href = "/";
				}).fail(function(error) {
					alert(JSON.stringify(error));
				});
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}
index.init();