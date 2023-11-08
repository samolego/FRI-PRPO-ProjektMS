package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Zanr;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ZanrZrno implements IEntityBean<Zanr> {
    private final Logger LOG = Logger.getLogger(ZanrZrno.class.getName());
    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + ZanrZrno.class.getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + ZanrZrno.class.getSimpleName() + ".");
    }

    public List<Zanr> getZanri() {
        return this.em.createNamedQuery("Zanr.getAll", Zanr.class).getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}