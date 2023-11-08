package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;

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
public class KinotekaZrno {

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

    public Optional<Kinoteka> getKinotekaById(int id) {
        return Optional.ofNullable(this.em.find(Kinoteka.class, id));
    }
    @Transactional
    public void deleteKinotekaById(int id) {
        final var kinoteka = this.getKinotekaById(id);
        kinoteka.ifPresent(this.em::remove);
    }
    @Transactional
    public void addKinoteka(Kinoteka kinoteka) {
        if (kinoteka != null) {
            this.em.persist(kinoteka);
        }
    }

    @Transactional
    public void editKinoteka(int id, Kinoteka kinoteka) {
        if (kinoteka != null) {
            final var oldKinoteka = this.getKinotekaById(id);

            oldKinoteka.ifPresent(old -> {
                kinoteka.setId(old.getId());
                this.em.merge(kinoteka);
            });
        }
    }
}
