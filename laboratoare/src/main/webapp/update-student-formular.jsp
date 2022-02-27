<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
        <title>Formular student</title>
        <meta charset="UTF-8">
    </head>
    <body>
         <jsp:useBean id="studentBean" class="beans.StudentBean" />
         <jsp:setProperty name="studentBean" property="id" value='<%=request.getAttribute("id") %>'/>
         <jsp:setProperty name="studentBean" property="nume" value='<%=request.getAttribute("nume") %>'/>
         <jsp:setProperty name="studentBean" property="prenume" value='<%=request.getAttribute("prenume") %>'/>
         <jsp:setProperty name="studentBean" property="varsta" value='<%=request.getAttribute("varsta") %>'/>
         <jsp:setProperty name="studentBean" property="specializare" value='<%=request.getAttribute("specializare") %>'/>

        <h3>Update student</h3>

         <form action="./update-student" method="post">
            Id:<input type="text" name="id" value=<jsp:getProperty name="studentBean" property="id"/>>
            <br/>
            Nume: <input type="text" name="nume" value=<jsp:getProperty name="studentBean" property="nume"/>>
                <br />
            Prenume: <input type="text" name="prenume" value=<jsp:getProperty name="studentBean" property="prenume"/>>
                <br />
            Varsta: <input type="text" name="varsta" value=<jsp:getProperty name="studentBean" property="varsta"/>>
                <br />
            Specializare: <input type="text" name="specializare" value=<jsp:getProperty name="studentBean" property="specializare"/>>
                <br />
                <br/>
            <button type="submit" name="submit">Update</button>
        </form>
    </body>
</html>