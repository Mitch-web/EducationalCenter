let add_new_employee = document.querySelector('.add_new_employee');
let add_new_employee_popup = document.querySelector('.add_new_employee_popup');
add_new_employee.addEventListener('click', ()=>{
	add_new_employee_popup.style.display = 'block';
	setTimeout(()=>{
		add_new_employee_popup.style.opacity = 1;
		add_new_employee_popup.style.transition = '.5s';
	},100)
	add_new_empl();
});

function add_new_empl() {
	let add_new_employee_but = document.querySelector('.add_new_employee_but');
	add_new_employee_but.addEventListener('click', ()=> {
		let employee_name = document.querySelector('.employee_name');
		let employee_lastname = document.querySelector('.employee_lastname');
		let employee_login = document.querySelector('.login');
		let employee_password = document.querySelector('.password');
		if(employee_name.value == '' || employee_lastname.value == '' || employee_login.value == '' || employee_password.value == ''){
			alert('Заполните все поля!')
		} else {
			var settings = {
				"url": `${settings_site.url}/registration`,
				"method": "POST",
				"timeout": 0,
				"headers": {
					"Content-Type": "application/x-www-form-urlencoded"
				},
				"data": {
					"login": `${employee_login.value}`,
					"password": `${employee_password.value}`,
					"firstName": `${employee_name.value}`,
					"lastName": `${employee_lastname.value}`
				},
				success: function(response) {
					alert('All right');
				},
				"error": function(response){
					console.log(response);
				}
			};

			$.ajax(settings).done(function (response) {
				console.log(response);
			});
		}
	})


	let close_employee_add_popup = document.querySelector('.close_employee_add_popup');
	close_employee_add_popup.addEventListener('click', ()=>{
			add_new_employee_popup.style.opacity = 0;
			add_new_employee_popup.style.transition = '.5s';
			setTimeout(()=>{
				add_new_employee_popup.style.display = 'none';
			},100);

	});
}