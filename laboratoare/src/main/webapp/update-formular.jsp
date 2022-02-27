<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
        <title>Formular student</title>
        <meta charset="UTF-8">
    </head>
    <body>

        <h3>Ce student doriti sa actualizati?</h3>

         <form action="./read-by-id" method="get">
           Id: <input type="number" name="id" />
            <br/>
            <br/>
            <button type="submit" name="submit">Update</button>
        </form>
    </body>
</html>