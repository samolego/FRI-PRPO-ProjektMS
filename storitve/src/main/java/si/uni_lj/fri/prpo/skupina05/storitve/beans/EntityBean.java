package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.IdentifiableEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

public abstract class EntityBean<T extends IdentifiableEntity> {

    @Transactional(Transactional.TxType.MANDATORY)
    protected void deleteEntityById(int id, Class<T> clas) {
        final var entity = this.getEntityById(id, clas);
        entity.ifPresent(this.getEntityManager()::remove);
    }

    @Transactional(Transactional.TxType.MANDATORY)
    protected Optional<T> getEntityById(int id, Class<T> clas) {
        return Optional.ofNullable(this.getEntityManager().find(clas, id));
    }

    @Transactional
    public void insertEntity(T entity) {
        this.getEntityManager().persist(entity);
    }


    @Transactional
    public Optional<T> updateEntity(int id, T entity) {
        if (entity != null) {
            final var oldEntity = this.getEntityById(id, (Class<T>) entity.getClass());

            return oldEntity.map(old -> {
                entity.setId(old.getId());
                return this.getEntityManager().merge(entity);
            });
        }

        return Optional.empty();
    }

    protected abstract EntityManager getEntityManager();
}
