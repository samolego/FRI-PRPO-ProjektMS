package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class KinotekaZrno extends EntityBean<Kinoteka> {

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
    protected EntityManager getEntityManager() {
        return em;
    }

    @Transactional
    public void deleteKinotekaById(int id) { this.deleteEntityById(id, Kinoteka.class);}

    public Optional<Kinoteka> getKinotekaByIme(String ime) {
        return this.em.createNamedQuery("Kinoteka.getByIme", Kinoteka.class).setParameter("ime", ime).getResultStream().findFirst();
    }

    @Transactional
    public Kinoteka getKinotekaBySpletnaStran(String spletnaStran) {
        Query q = this.em.createNamedQuery("Kinoteka.getBySpletnaStran");
        q.setParameter("spletnaStran", spletnaStran);
        Optional<Kinoteka> kinoteka = q.getResultStream().findFirst();
        return kinoteka.get();
    }

    @Transactional
    public Optional<Kinoteka> getKinotekaWithSpletnaStran(String spletnaStran) {
        Query q = this.em.createNamedQuery("Kinoteka.getBySpletnaStran");
        q.setParameter("spletnaStran", spletnaStran);
        return q.getResultStream().findFirst();
    }

    @Transactional
    public void updateKinoteka(int id, Kinoteka kinoteka) {
        this.updateEntity(id, kinoteka);
    }

}
