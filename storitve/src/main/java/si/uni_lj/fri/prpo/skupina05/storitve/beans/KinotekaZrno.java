package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class KinotekaZrno implements IEntityBean<Kinoteka> {

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
