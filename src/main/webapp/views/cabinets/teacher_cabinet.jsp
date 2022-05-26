<%@ page pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<title>Кабинет Владельца</title>
	<link rel="stylesheet" href="../assets/owner_cabinet.css">
</head>
<body>
	<h1>Кабинет владельца</h1>
	<input class="add_new_employee" type="submit" value="Добавить сотрудника">
	<div class="add_new_employee_popup">
		<form:form class="form">
			<p class="close_employee_add_popup">х</p>
			<label for="employee_name">Введите имя сотрудника:</label>
			<input type="text" name="employee_name" class="employee_name"/>
			<label for="employee_name">Введите фамилию сотрудника:</label>
            <input type="text" name="employee_lastname" class="employee_lastname"/>
			<label for="login">Придумайте логин сотруднику:</label>
			<input type="text" name="login" class="login"/>
			<label for="password">Придумайте пароль сотруднику:</label>
			<input type="text" name="password" class="password"/>
			<select name="Выберите тип сотрудника" id="choose_employee_type">
				<option disabled>Выбрать тип:</option>
				<option value="employee">Работник</option>
				<option value="admin">Администратор</option>
			</select>
			<input class="add_new_employee_but" type="submit" value="Добавить"/>
			
		</form:form>
	</div>
	<h4>${newUser}</h4>
	
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="../assets/settings.js"></script>
    <script src="../assets/owner_cabinet.js"></script>
    <script src="../assets/script.js"></script>
</body>
</html>