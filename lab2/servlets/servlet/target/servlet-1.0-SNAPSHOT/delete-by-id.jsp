<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
        <title>Formular student</title>
        <meta charset="UTF-8">
    </head>
    <body>

        <h3>Ce student doriti sa stergeti?</h3>

         <form action="./delete-student" method="post">
           Id: <input type="number" name="id" />
            <br/>
            <br/>
            <button type="submit" name="submit">Delete</button>
        </form>
    </body>
</html>