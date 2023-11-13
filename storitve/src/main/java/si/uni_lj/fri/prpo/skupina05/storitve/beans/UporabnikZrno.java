package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikZrno extends EntityBean<Uporabnik> {
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
