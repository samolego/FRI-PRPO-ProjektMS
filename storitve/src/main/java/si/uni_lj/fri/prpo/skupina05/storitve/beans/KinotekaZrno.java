package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;
import si.uni_lj.fri.prpo.skupina05.storitve.anotacije.BeleziKlice;

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
@BeleziKlice
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

    public Optional<Kinoteka> getKinotekaById(int id) { return this.getEntityById(id, Kinoteka.class); }

    @Transactional
    public Optional<Kinoteka> getKinotekaBySpletnaStran(String spletnaStran) {
        Query q = this.em.createNamedQuery("Kinoteka.getBySpletnaStran");
        q.setParameter("spletnaStran", spletnaStran);
        return q.getResultStream().findFirst();
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

    public List<Kinoteka> getKinoteke(QueryParameters query) {
        return this.getEntities(query, Kinoteka.class);
    }

    public long getKinotekeCount(QueryParameters query) {
        return this.getEntitiesCount(query, Kinoteka.class);
    }
}
