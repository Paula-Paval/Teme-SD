import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class DeleteStudentServlet extends HttpServlet {

    private static final String URL="jdbc:sqlite:/home/osboxes/Desktop/laboratoare/studenti.db";
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        int id=Integer.parseInt(request.getParameter("id"));

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DELETE from student where ID="+id+";";
            stmt.executeUpdate(sql);
            c.commit();
            request.getRequestDispatcher("./index.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e ) {
            response.getWriter().print(e.getClass().getName() + ": " + e.getMessage());
        }

    }
}
