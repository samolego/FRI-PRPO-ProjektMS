package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Film;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class FilmZrno {
    private final Logger LOG = Logger.getLogger(FilmZrno.class.getName());

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + FilmZrno.class.getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + FilmZrno.class.getSimpleName() + ".");
    }

    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    public List<Film> getFilmi() {
        return this.em.createNamedQuery("Film.getAll", Film.class).getResultList();
    }

    public Optional<Film> getFilmById(int id) {
        return Optional.ofNullable(this.em.find(Film.class, id));
    }

    @Transactional
    public void deleteFilmById(int id) {
        final var film = this.getFilmById(id);
        film.ifPresent(this.em::remove);
    }

    @Transactional
    public void addFilm(Film film) {
        if (film != null) {
            this.em.persist(film);
        }
    }


    @Transactional
    public void editFilm(int id, Film film) {
        if (film != null) {
            final var oldFilm = this.getFilmById(id);

            oldFilm.ifPresent(old -> {
                film.setId(old.getId());
                this.em.merge(film);
            });
        }
    }
}