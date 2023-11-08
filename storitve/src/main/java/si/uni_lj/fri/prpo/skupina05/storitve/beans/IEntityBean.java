package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.IdentifiableEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

public interface IEntityBean<T extends IdentifiableEntity> {

    @Transactional
    default void deleteEntityById(int id) {
        final var entity = this.getEntityById(id);
        entity.ifPresent(this.getEntityManager()::remove);
    }

    default Optional<T> getEntityById(int id) {
        return Optional.ofNullable((T) this.getEntityManager().find(Object.class, id));
    }


    default void insertEntity(T entity) {
        this.getEntityManager().persist(entity);
    }


    @Transactional
    default void updateEntity(int id, T entity) {
        if (entity != null) {
            final var oldEntity = this.getEntityById(id);

            oldEntity.ifPresent(old -> {
                entity.setId(old.getId());
                this.getEntityManager().merge(entity);
            });
        }
    }

    EntityManager getEntityManager();
}
