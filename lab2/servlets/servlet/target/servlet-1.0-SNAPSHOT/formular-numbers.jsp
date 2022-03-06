<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>

		Introduceti numerele:
		<form action="./process-numbers" method="post">
			X: <input type="number" name="x" />
			<br />
			Y: <input type="number" name="y" />
			<br>
			A: <input type="number" name="a" />
			<br>
			B: <input type="number" name="b" />
			<br>
			<br>
			<button type="submit" name="submit">Trimite</button>
		</form>
	</body>
</html>