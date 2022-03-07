import ejb.NumbersEntity;
import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MonitorizareServlet extends HttpServlet {
    private   List <NumbersEntity> numbers;
    @Override
    public void init() {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        final EntityManager em = factory.createEntityManager();
        TypedQuery<NumbersEntity> query = em.createQuery("select n from NumbersEntity n", NumbersEntity.class);

        numbers = query.getResultList();

        em.close();
        factory.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final AsyncContext acontext = request.startAsync();

        acontext.start(new Runnable() {
                           public void run() {
                               String col1 = acontext.getRequest().getParameter("c1");
                               String col2 = acontext.getRequest().getParameter("c2");
                               int a = Integer.parseInt(acontext.getRequest().getParameter("a"));
                               int b = Integer.parseInt(acontext.getRequest().getParameter("b"));

                               HttpServletResponse response = (HttpServletResponse) acontext.getResponse();


                               String messaj = "";

                               for (NumbersEntity num : numbers) {

                                   if((a>num.getX()||b< num.getX())&&(col1.equals("x")||col2.equals("x")))
                                   {
                                      messaj += "Atentie valoare lui x=" + num.getX() + "nu este in intervalul [" + a + "," + b + "]!<br>";

                                   }
                                   if((a>num.getY()||b< num.getY())&&(col1.equals("y")||col2.equals("y")))
                                   {

                                       messaj += "Atentie valoare lui y=" + num.getY()+ "nu este in intervalul [" + a + "," + b + "]!<br>";
                                   }
                              }

                               response.setContentType("text/html");
                                try {
                                  response.getWriter().println(messaj + "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
                                } catch (IOException e) {
                                       e.printStackTrace();
                                }
                               acontext.complete();

                           }
                       }
        );
    }
}
