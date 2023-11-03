package si.uni_lj.fri.prpo.skupina05.api;

import si.uni_lj.fri.prpo.skupina05.entitete.Film;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.FilmZrno;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private FilmZrno filmiZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Film> filmi = this.filmiZrno.getFilmi();

        // izpis filmov na spletno stran
        resp.getWriter().println("<html><body><h1>Filmi</h1>");
        for (Film f : filmi) {
            resp.getWriter().println("<p>" + f.getIme() + "</p>");
        }
        resp.getWriter().println("</body></html>");
    }
}