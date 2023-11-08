package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Film;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class FilmZrno implements IEntityBean<Film> {
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

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}