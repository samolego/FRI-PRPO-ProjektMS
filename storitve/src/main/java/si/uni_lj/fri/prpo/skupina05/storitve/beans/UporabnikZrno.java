package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.uni_lj.fri.prpo.skupina05.entitete.Uporabnik;
import si.uni_lj.fri.prpo.skupina05.storitve.anotacije.BeleziKlice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
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

    public Optional<Uporabnik> getUporabnikById(int id) { return this.getEntityById(id, Uporabnik.class); }

    public List<Uporabnik> getUporabniki() { return this.em.createNamedQuery("Uporabnik.getAll", Uporabnik.class).getResultList(); }

    public void deleteUporabnikById(int id) {
        this.deleteEntityById(id, Uporabnik.class);
    }

    public void dodajUporabnika(Uporabnik uporabnik) {
        insertEntity(uporabnik);
    }

    public List<Uporabnik> getUporabniki(QueryParameters query) {
        return this.getEntities(query, Uporabnik.class);
    }
}
