<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>

		Introduceti intervalul dorit
		<form action="./process-interval" method="post">
			A: <input type="number" name="a" />
			<br />
			B: <input type="number" name="b" />
			<br>
			<br>
			<button type="submit" name="submit">Trimite</button>
		</form>
	</body>
</html>