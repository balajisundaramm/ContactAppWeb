<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<title>AddContact</title>
</head>
<body>
	<form action="AddContactInt.jsp" method="post">
		Name:<input type="text" name="name"><br /> <br /> Email:<input
			type="text" name="email"><br /> <br /> (*for more emails<br />type
		emails in comma(,) seperated format)<br /> Phone Number:<input
			type="text" name="phno"><br /> <br /> (*for more phone
		numbers<br />type those in comma(,) seperated format)<br /> Tags:<input
			type="text" name="tag"><br /> <br /> (*for more tags<br />type
		those in comma(,) seperated format)<br /> Gender:<select
			name="gender">
			<option value="male">Male</option>
			<option value="female">Female</option>
			<option value="transGender">TransGender</option>
		</select><br /> <br /> Date Of Birth: <input type="text" name="dob">Format:(yyyy/MM/dd)
		Ex:(2017/11/12) <br /> <input type="submit" value="Add"><br />
	</form>

	<div class="container">
		<form>
			<div class="form-group">
				<label for="email">Email:</label> <input type="text"
					class="form-control" id="email" placeholder="Enter email">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd" placeholder="Enter password">
			</div>
			<div class="form-check">
				<label class="form-check-label"> <input
					class="form-check-input" type="checkbox"> Remember me
				</label>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>

	<b>${errorMsg}</b>
</body>
</html>