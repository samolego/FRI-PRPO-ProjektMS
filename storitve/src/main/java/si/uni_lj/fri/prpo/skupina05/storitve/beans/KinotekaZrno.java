package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class KinotekaZrno extends EntityBean<Kinoteka> {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + this.getClass().getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + this.getClass().getSimpleName() + ".");
    }

    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    public List<Kinoteka> getKinoteke() {
        return this.em.createNamedQuery("Kinoteka.getAll", Kinoteka.class).getResultList();
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
