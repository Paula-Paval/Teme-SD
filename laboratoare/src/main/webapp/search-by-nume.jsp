<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
        <title>Formular student</title>
        <meta charset="UTF-8">
    </head>
    <body>

        <h3>Ce studenti doriti sa cautati?</h3>

         <form action="./read-by-nume" method="get">

           Nume: <input type="text" name="nume" />
            <br/>
            <br/>
            <button type="submit" name="submit">Cauta</button>
        </form>
    </body>
</html>