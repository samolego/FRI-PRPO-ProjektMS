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

    public boolean dodajFilm(FilmDTO filmDTO) {
        var film = filmDTO.toFilm();
        film.ifPresent(filmZrno::insertEntity);

        return film.isPresent();
    }

    public boolean odstraniFilm(FilmDTO filmDTO) {
        var filmData = filmDTO.toFilm();
        var film = filmData.flatMap(f -> filmZrno.getFilmByIme(f.getIme()));

        return film.map(flm -> {
                    filmZrno.deleteFilmById(flm.getId());
                    return true;
                })
                .orElse(false);
    }

    public boolean posodobiFilm(int id, FilmDTO filmData) {
        var film = filmData.toFilm();
        var success = film.flatMap(f -> filmZrno.updateEntity(id, f));

        return success.isPresent();
    }
}
