//Отправка данных
function sendAuthData() {
  let login_inp = document.querySelector('.login_inp');
  let pass_inp = document.querySelector('.pass_inp');
  let error_message = document.querySelector('.error_message');
	if(login_inp.value!='' && pass_inp.value!='') {
	    var addPostUrl = window.location.href;
        addPostUrl = addPostUrl.replace("#", "") + "/login";

		var settings = {
			"url": addPostUrl,
			"method": "POST",
			"timeout": 0,
			"headers": {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			"data": {
				"login": `${login_inp.value}`,
				"password": `${pass_inp.value}`
			},
			success: function(response) {
			    location.replace(window.location.origin + response)
			},
			error: function(response) {
				errorAnimation(`${response.responseText}`);
			}
		};
		
		$.ajax(settings).done(function (response) {
			console.log(response);
			console.log(window.location.origin + response);
		});
	}else{
		errorAnimation('Пожалуйста, заполните оба поля');
	}
	// Анимация ошибки
	function errorAnimation(text){
		error_message.innerHTML = text;
		error_message.style.display = 'block';
		setTimeout(function(){
			error_message.style.opacity = 1;
			error_message.style.transition = '1s';
		},100);
		setTimeout(function(){
			error_message.style.opacity = 0;
			error_message.style.transition = '1s';
		},3000);
		setTimeout(function(){
			error_message.style.display = 'none';
		},4100);
	}
}


//Прослушиватель энтер
document.addEventListener('keydown', function(e) {
    if (e.keyCode === 13) {
      sendAuthData();
    }
  });
 
