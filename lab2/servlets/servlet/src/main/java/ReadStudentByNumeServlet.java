import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReadStudentByNumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nume = request.getParameter("nume");

        // pregatire EntityManager
        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        // preluare date studenti din baza de date
        TypedQuery<StudentEntity> query = em.createQuery("select student from StudentEntity student where student.nume=\""+nume+"\"", StudentEntity.class);
        List<StudentEntity> results = query.getResultList();
        if(!results.isEmpty())
        {
            request.setAttribute("id", results.get(0).getId());
            request.setAttribute("nume", results.get(0).getNume());
            request.setAttribute("prenume", results.get(0).getPrenume());
            request.setAttribute("varsta", results.get(0).getVarsta());

            request.getRequestDispatcher("./update-student.jsp").forward(request, response);
        }
        else
        {
            request.getRequestDispatcher("./").forward(request, response);

        }


        // inchidere EntityManager
        em.close();
        factory.close();


    }
}
