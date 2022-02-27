import beans.StudentBean;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExportJSONServlet extends HttpServlet {
    private static final String URL="jdbc:sqlite:/home/osboxes/Desktop/laboratoare/studenti.db";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        Connection c = null;
        Statement stmt = null;
        String jsonContent="[";
        ArrayList<StudentBean> studentBeans=new ArrayList<>();

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
                studentBeans.add(student);
                jsonContent+=student.toString();
                jsonContent+=",";
            }

            StringBuffer sb= new StringBuffer(jsonContent);
            sb.deleteCharAt(sb.length()-1);
            sb.append("]");

            rs.close();
            stmt.close();
            c.close();


            Gson gson = new Gson();
            String json = gson.toJson(studentBeans);

            response.getWriter().print(sb);

        } catch (SQLException | ClassNotFoundException e ) {
            response.getWriter().print(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
