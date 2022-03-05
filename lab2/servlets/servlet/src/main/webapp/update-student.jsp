<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
        <title>Formular student</title>
        <meta charset="UTF-8">
    </head>
    <body>
         <jsp:useBean id="student" class="ejb.StudentEntity" />
         <jsp:setProperty name="student" property="id" value='<%=request.getAttribute("id") %>'/>
         <jsp:setProperty name="student" property="nume" value='<%=request.getAttribute("nume")%>'/>
         <jsp:setProperty name="student" property="prenume" value='<%=request.getAttribute("prenume") %>'/>
         <jsp:setProperty name="student" property="varsta" value='<%=request.getAttribute("varsta") %>'/>

        <h3>Update student</h3>

         <form action="./update-student" method="post">
            Id:<input type="text" name="id" value=<jsp:getProperty name="student" property="id"/>>
            <br/>
            Nume: <input type="text" name="nume" value=<jsp:getProperty name="student" property="nume"/>>
                <br />
            Prenume: <input type="text" name="prenume" value=<jsp:getProperty name="student" property="prenume"/>>
                <br />
            Varsta: <input type="text" name="varsta" value=<jsp:getProperty name="student" property="varsta"/>>
                <br />
          <button type="submit" name="submit">Update</button>
        </form>
    </body>
</html>