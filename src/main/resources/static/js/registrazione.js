var check = false;
// Populated Object
function getAllCustomerInfo() {
	var username = $('.username').val();
	var password = $('.password').val();
	var email = $('.email').val();
	var citta = $('.country').val();
	var confermaPassword = $('.conferma_password').val();
	var telefono = $('.telefono').val();
	var userInfo = {
		"username" : username,
		"password" : password,
		"email" : email,
		"country" : citta,
	// "confermaPassword" : confermaPassword,
	// "telefono" : telefono
	}
	return userInfo;
}

// Check for password
$(function() {
	$("#password").bind("keyup", function() {
		if ($(this).val().length == 0) {
			$("#password_strength").html("");
			$("#result").attr("value", '0');
			$('.strength').css('color', 'white');
			return;
		}
		// Regular Expressions.
		// var regex = new Array();
		var regex = [];
		regex.push("[A-Z]"); // Uppercase Alphabet.
		regex.push("[a-z]"); // Lowercase Alphabet.
		regex.push("[0-9]"); // Digit.
		regex.push("[$@$!%*#?&]"); // Special Character.
		var passed = 0;
		// Validate for each Regular Expression.
		for (var i = 0; i < regex.length; i++) {
			if (new RegExp(regex[i]).test($(this).val())) {
				passed++;
			}
		}
		// Validate for length of Password.
		if (passed > 2 && $(this).val().length > 8) {
			passed++;
		}
		// Display status.
		var color = "";
		var strength = "";
		var value = "0";
		switch (passed) {
		case 0:
			value = "0";
		case 1:
			strength = "Debole";
			color = "red";
			value = "1";
			break;
		case 2:
			strength = "Buono";
			color = "darkorange";
			value = "2";
			break;
		case 3:
			value = "3";
		case 4:
			strength = "Molto buono";
			color = "green";
			value = "4";
			break;
		case 5:
			strength = "Ottimo";
			color = "darkgreen";
			value = "5";
			break;
		}
		$('.strength').html(strength);
		$('.strength').css('color', color);
		$("#result").attr("value", value);
		$("#result").attr("max", "5");
	});
});
// Persist Customer
$(function() {
	$(".form").submit(function(e) {
		var userInfo = getAllCustomerInfo();
		if (check === false) {
			if (userInfo.password != null && userInfo.password != "") {
				$.ajax({
					url : '/rest/saveUserInfo',
					type : 'POST',
					contentType : 'application/json',
					data : JSON.stringify(userInfo),
					cache : 'false',
					success : function(data) {
						console.log("Data: " + data);
						check = true;
						$('.form').submit();
					},
					error : function(data) {
						if (data.responseJSON.code == 612) {
							console.log(data.responseJSON.reason);
						}
					}
				});
			}
			e.preventDefault();
		}
	});
});
// Validate FORM
$(function() {
	$(".form").validate({
		rules : {
			email : {
				required : true,
				email : true
			},
			username : {
				required : true,
				minlength : 4
			},
			password : {
				required : true
			},
		},
		messages : {
			email : {
				required : "Questo campo &egrave; obbligatorio",
				email : "Inserisci una mail valida"
			},
			username : {
				required : "Questo campo &egrave; obbligatorio",
				minlength : "Inserisci un username con almeno 4 caratteri"
			},
			password : {
				required : "Questo campo &egrave; obbligatorio",
			}
		}
	});
});

 $(function() {
	$('#email').blur(function() {
		var username = $('.username').val();
		var password = $('.password').val();
		var email = $('.email').val();
		var userInfo = {
			"username" : username,
			"password" : password,
			"email" : email
		}
		$.ajax({
			url : '/rest/check/user/info',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(userInfo),
			success : function(data) {
				console.log("Data: " + data);
				$('#email').css("border-color", "#ced4da");
				$('.registrati').prop('disabled', false);
				$('#username').css("border-color", "#ced4da");
				$('.registrati').prop('disabled', false);
			},
			error : function(data) {
				if (data.status != 400) {
					alert("C'è stato un errore al server");
				} else if (data.responseJSON.code == 610) {
					$('#email').css("border-color", "red");
					alert(data.responseJSON.reason);
					$('.registrati').prop('disabled', true);
				} else if (data.responseJSON.code == 611) {
					$('#username').css("border-color", "red");
					alert(data.responseJSON.reason);
					$('.registrati').prop('disabled', true);
				}
			}
		});
	});
});
