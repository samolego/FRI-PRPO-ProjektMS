package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Zanr;

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
public class ZanrZrno extends EntityBean<Zanr> {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + this.getClass().getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + this.getClass().getSimpleName() + ".");
    }

    public List<Zanr> getZanri() {
        return this.em.createNamedQuery("Zanr.getAll", Zanr.class).getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Transactional
    public void deleteZanrById(int id) {
        this.deleteEntityById(id, Zanr.class);
    }

    public Optional<Zanr> getZanrById(int id) {
        return this.getEntityById(id, Zanr.class);
    }
}