import interfaces.BankAccountBeanRemote;
import interfaces.IntervalBean;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProcessIntervalServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // preluare parametri din cererea HTTP
        int a = Integer.parseInt(request.getParameter("a"));
        int b =Integer.parseInt(request.getParameter("b"));

        // se incearca preluarea bean-ului folosind obiectul HttpSession, care pastreaza o sesiune HTTP intre client si server
        IntervalBean interval;
        interval = (IntervalBean)request.getSession().getAttribute("intervalBean");

        // daca nu exista nimic pastrat in sesiunea HTTP, inseamna ca bean-ul se preia prin JNDI lookup
        if (interval == null) {
            try {
                InitialContext ctx = new InitialContext();
                interval = (IntervalBean) ctx.lookup("interval#interfaces.IntervalBean");

                // dupa preluarea bean-ului prin JNDI, obiectul se stocheaza in sesiune pentru a fi refolosit ulterior
                // cererile urmatoare vor utiliza obiectul remote stocat in sesiune
                request.getSession().setAttribute("intervalBean", interval);
            } catch (NamingException e) {
                e.printStackTrace();
                return;
            }
        }
        interval.setA(a);
        interval.setB(b);
        response.getWriter().print("Intervalul introdus este ["+a+","+b+"].");
    }
}
