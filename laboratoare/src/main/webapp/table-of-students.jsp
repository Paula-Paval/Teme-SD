<%@page import="beans.StudentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student List</title>
</head>
<body>
<h1>Studentii sunt:</h1>
	<table border ="1" width="500" align="center">
	    <th><b>Student Id</b></th>
		<th><b>Student Nume</b></th>
		<th><b>Student Prenume</b></th>
		<th><b>Student Varsta</b></th>
        <th><b>Student Specializare</b></th>

		</tr>

		<%ArrayList<StudentBean> std =
			(ArrayList<StudentBean>)request.getAttribute("students");
		for(StudentBean s:std){%>
			<tr>
				<td><%=s.getId()%></td>
				<td><%=s.getNume()%></td>
				<td><%=s.getPrenume()%></td>
                <td><%=s.getVarsta()%></td>
                <td><%=s.getSpecializare()%></td>
			</tr>
			<%}%>
		</table>
		<hr/>
	</body>
</html>
