package si.uni_lj.fri.prpo.skupina05.api;

import si.uni_lj.fri.prpo.skupina05.entitete.Film;
import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.FilmZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.KinotekaZrno;

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
    @Inject
    private KinotekaZrno kinotekaZrno;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Film> filmi = this.filmiZrno.getFilmi();

        // izpis filmov na spletno stran
        resp.getWriter().println("<html><body><h1>Filmi</h1>");
        for (Film f : filmi) {
            resp.getWriter().println("<p>" + f.getIme() + "</p>");
        }

        List<Kinoteka> kinoteke = this.kinotekaZrno.getKinoteke();

        // izpis kinotek na spletno stran
        resp.getWriter().println("<h1>Kinoteke</h1>");
        for (Kinoteka k : kinoteke) {
            resp.getWriter().println("<p>" + k.getIme() + " <em>(" + k.getSpletnaStran() +")</em></p>");
        }

        resp.getWriter().println("</body></html>");
    }
}
