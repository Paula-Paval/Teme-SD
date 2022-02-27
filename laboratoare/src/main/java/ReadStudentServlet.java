import beans.StudentBean;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReadStudentServlet extends HttpServlet {

    private static final String URL="jdbc:sqlite:/home/osboxes/Desktop/laboratoare/studenti.db";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        Connection c = null;
        Statement stmt = null;
        List<StudentBean> students=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM student;" );

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  nume = rs.getString("nume");
                String  prenume = rs.getString("prenume");
                int varsta = rs.getInt("varsta");
                String  specializare= rs.getString("specializare");
                StudentBean student=new StudentBean();
                student.setId(id);
                student.setNume(nume);
                student.setPrenume(prenume);
                student.setVarsta(varsta);
                student.setSpecializare(specializare);
                students.add(student);
            }

            request.setAttribute("students", students);
            rs.close();
            stmt.close();
            c.close();
            request.getRequestDispatcher("./table-of-students.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e ) {
           response.getWriter().print(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}