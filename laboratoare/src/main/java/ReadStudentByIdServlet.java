import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadStudentByIdServlet extends HttpServlet {

    private static final String URL = "jdbc:sqlite:/home/osboxes/Desktop/laboratoare/studenti.db";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        int Id=Integer.parseInt(request.getParameter("id"));

        Connection c = null;
        Statement stmt = null;
        StudentBean student=new StudentBean();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM student where id="+Id+";" );

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  nume = rs.getString("nume");
                String  prenume = rs.getString("prenume");
                int varsta = rs.getInt("varsta");
                String  specializare= rs.getString("specializare");

                student.setId(id);
                student.setNume(nume);
                student.setPrenume(prenume);
                student.setVarsta(varsta);
                student.setSpecializare(specializare);

            }

            StudentBean bean=student;
            request.setAttribute("id", bean.getId());
            request.setAttribute("nume", bean.getNume());
            request.setAttribute("prenume", bean.getPrenume());
            request.setAttribute("varsta", bean.getVarsta());
            request.setAttribute("specializare", bean.getSpecializare());

            rs.close();
            stmt.close();
            c.close();
            request.getRequestDispatcher("./update-student-formular.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e ) {
            response.getWriter().print(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}