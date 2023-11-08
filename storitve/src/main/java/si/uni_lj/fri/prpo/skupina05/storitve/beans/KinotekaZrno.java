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
public class KinotekaZrno implements IEntityBean<Kinoteka> {

    private final Logger LOG = Logger.getLogger(KinotekaZrno.class.getName());

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + KinotekaZrno.class.getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + KinotekaZrno.class.getSimpleName() + ".");
    }

    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    public List<Kinoteka> getKinoteke() {
        return this.em.createNamedQuery("Kinoteka.getAll", Kinoteka.class).getResultList();
    }
    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
