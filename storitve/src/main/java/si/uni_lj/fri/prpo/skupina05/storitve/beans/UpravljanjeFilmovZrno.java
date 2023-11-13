package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.storitve.dtos.FilmDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeFilmovZrno {

    private final Logger LOG = Logger.getLogger(UpravljanjeFilmovZrno.class.getName());
    @Inject
    private FilmZrno filmZrno;

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + UpravljanjeFilmovZrno.class.getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + UpravljanjeFilmovZrno.class.getSimpleName() + ".");
    }

    public void dodajFilm(FilmDTO filmDTO) {
        var film = filmDTO.toFilm();
        film.ifPresent(filmZrno::insertEntity);
    }

    public void odstraniFilm(FilmDTO filmDTO) {
        var film = filmDTO.toFilm();
        film.flatMap(f -> filmZrno.getFilmByIme(f.getIme()))
                .ifPresent(film1 -> filmZrno.deleteFilmById(film1.getId()));
    }
}
