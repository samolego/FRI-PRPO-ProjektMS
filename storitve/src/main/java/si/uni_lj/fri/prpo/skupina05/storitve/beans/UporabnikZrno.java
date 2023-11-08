package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Uporabnik;

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
public class UporabnikZrno {
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

    public Optional<Uporabnik> getUporabnikById(int id) {
        return Optional.ofNullable(this.em.find(Uporabnik.class, id));
    }
    @Transactional
    public void deleteUporabnikById(int id) {
        final var uporabnik = this.getUporabnikById(id);
        uporabnik.ifPresent(this.em::remove);
    }
    @Transactional
    public void addUporabnik(Uporabnik uporabnik) {
        if (uporabnik != null) {
            this.em.persist(uporabnik);
        }
    }

    @Transactional
    public void editUporabnik(int id, Uporabnik uporabnik) {
        if (uporabnik != null) {
            final var oldUporabnik = this.getUporabnikById(id);

            oldUporabnik.ifPresent(old -> {
                uporabnik.setId(old.getId());
                this.em.merge(uporabnik);
            });
        }
    }
}
