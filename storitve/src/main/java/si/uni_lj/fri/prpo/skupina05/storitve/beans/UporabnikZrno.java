package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Uporabnik;
import si.uni_lj.fri.prpo.skupina05.storitve.anotacije.BeleziKlice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@ApplicationScoped
@BeleziKlice
public class UporabnikZrno extends EntityBean<Uporabnik> {
    private final Logger LOG = Logger.getLogger(UporabnikZrno.class.getName());

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + UporabnikZrno.class.getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + UporabnikZrno.class.getSimpleName() + ".");
    }

    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
