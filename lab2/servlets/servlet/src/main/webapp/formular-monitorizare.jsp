<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>

		Introduceti intervalul si numele coloanelor.
		<form action="./monitorizare" method="get">
			A: <input type="number" name="a" />
			<br>
			B: <input type="number" name="b" />
			<br>
			Coloana1: <input type="text" name="c1" />
            			<br />
            Coloana2: <input type="text" name="c2" />
            <br>
			<br>
			<br>
			<button type="submit" name="submit">Trimite</button>
		</form>
	</body>
</html>