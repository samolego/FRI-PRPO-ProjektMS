package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.uni_lj.fri.prpo.skupina05.entitete.Film;
import si.uni_lj.fri.prpo.skupina05.storitve.anotacije.BeleziKlice;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.izjeme.IzjemaNotFoundDTO;

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
@BeleziKlice
public class FilmZrno extends EntityBean<Film> {
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
        final var query = new QueryParameters();
        return this.getFilmi(query);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Optional<Film> getFilmById(int id) throws IzjemaNotFoundDTO {
        if(!this.getEntityById(id, Film.class).isPresent()) {
            throw new IzjemaNotFoundDTO("Ne najdem filma.");
        }
        return this.getEntityById(id, Film.class);
    }

    public Optional<Film> getFilmByIme(String ime) {
        return this.em.createNamedQuery("Film.getByIme", Film.class).setParameter("ime", ime).getResultStream().findFirst();
    }

    @Transactional
    public void deleteFilmById(int id) {
        this.deleteEntityById(id, Film.class);
    }

    public List<Film> getFilmi(QueryParameters query) {
        return this.getEntities(query, Film.class);
    }
}