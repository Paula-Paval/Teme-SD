import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;

public class ProcessStudentServlet extends HttpServlet {

    private static final String URL="jdbc:sqlite:/home/osboxes/Desktop/laboratoare/studenti.db";
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        String specializare=request.getParameter("specializare");

        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;


        StudentBean bean = new StudentBean();
        bean.setId(id);
        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setSpecializare(specializare);



        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO student(id, nume, prenume, varsta, specializare) " +
                    "VALUES (" +bean.getId()+", '"+bean.getNume()+"', '"+bean.getPrenume()+"',"+bean.getVarsta()+",'"+bean.getSpecializare()+"' );" ;

            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();

            // se trimit datele primite si anul nasterii catre o alta  pagina JSP pentru afisare
            request.setAttribute("id", id);
            request.setAttribute("nume", nume);
            request.setAttribute("prenume", prenume);
            request.setAttribute("varsta", varsta);
            request.setAttribute("anNastere", anNastere);
            request.setAttribute("specializare", specializare);

            request.getRequestDispatcher("./info-student.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e ) {
            response.getWriter().print(e.getClass().getName() + ": " + e.getMessage());
        }

    }
}