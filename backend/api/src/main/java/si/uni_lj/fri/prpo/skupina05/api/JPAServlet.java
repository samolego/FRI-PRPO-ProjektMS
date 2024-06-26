package si.uni_lj.fri.prpo.skupina05.api;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import si.uni_lj.fri.prpo.skupina05.entitete.Film;
import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.FilmZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.KinotekaZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.ZanrZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje.UpravljanjeKinotekZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje.UpravljanjeUporabnikovZrno;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet")
@CrossOrigin(supportedMethods = "GET")
public class JPAServlet extends HttpServlet {

    @Inject
    private FilmZrno filmiZrno;

    @Inject
    private ZanrZrno zanrZrno;

    @Inject
    private KinotekaZrno kinotekaZrno;

    @Inject
    private UpravljanjeKinotekZrno upravljanjeKinotekZrno;

    @Inject
    private UpravljanjeUporabnikovZrno upravljanjeUporabnikovZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Film> filmi = this.filmiZrno.getFilmi();
        final var writer = resp.getWriter();

        // izpis filmov na spletno stran
        writer.println("<html><body><h1>Filmi</h1>");
        for (Film f : filmi) {
            writer.println("<p>" + f.getIme() + " (" + f.getId() + ")</p>");
        }

        writer.println(filmiZrno.getFilmById(1).get().getIme());

        List<Kinoteka> kinoteke = this.kinotekaZrno.getKinoteke();

        // izpis kinotek na spletno stran
        writer.println("<h1>Kinoteke</h1>");
        for (Kinoteka k : kinoteke) {
            writer.println("<p>" + k.getIme() + " <em>(" + k.getSpletnaStran() + ")</em></p>");
        }

        writer.println("</body></html>");
    }
}
