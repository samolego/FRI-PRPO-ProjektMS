package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class KinotekaZrno {

    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    public List<Kinoteka> getKinoteke() {
        return this.em.createNamedQuery("Kinoteka.getAll", Kinoteka.class).getResultList();
    }
    @Override
    public EntityManager getEntityManager() {
        return em;
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
