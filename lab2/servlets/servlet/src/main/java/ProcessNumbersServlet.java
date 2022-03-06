
import ejb.NumbersEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




public class ProcessNumbersServlet extends HttpServlet {


@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    final AsyncContext acontext = request.startAsync();

    acontext.start(new Runnable() {
         public void run() {
                int x = Integer.parseInt(acontext.getRequest().getParameter("x"));
                int y = Integer.parseInt(acontext.getRequest().getParameter("y"));
                int a = Integer.parseInt(acontext.getRequest().getParameter("a"));
                int b = Integer.parseInt(acontext.getRequest().getParameter("b"));
                HttpServletResponse response = (HttpServletResponse) acontext.getResponse();
                HttpServletRequest request = (HttpServletRequest) acontext.getRequest();

                final EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
                final EntityManager em = factory.createEntityManager();

                final NumbersEntity numbers = new NumbersEntity();

                String messaj = "";
                if (a<=x) {
                    numbers.setX(x);
                } else {
                    messaj += "Atentie valoare lui x=" + x + "nu este in intervalul [" + a + "," + b + "]!<br>";
                }

                if (b>=y) {
                    numbers.setY(y);
                } else {
                    messaj += "Atentie valoare lui y=" + y + "nu este in intervalul [" + a + "," + b + "]!<br>";
                }

                if (a<=x&&b>=y) {


                    EntityTransaction transaction = em.getTransaction();

                    transaction.begin();
                    em.persist(numbers);
                    transaction.commit();

                    em.close();
                    factory.close();


                    response.setContentType("text/html");
                    try {
                        response.getWriter().println("Datele au fost adaugate in baza de date." +
                                "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    response.setContentType("text/html");
                    try {
                        response.getWriter().println(messaj + "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                acontext.complete();

            }
        }
        );
    }
}
