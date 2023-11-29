package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.uni_lj.fri.prpo.skupina05.entitete.IdentifiableEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
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

    @Transactional(Transactional.TxType.MANDATORY)
    public void insertEntity(T entity) {
        this.getEntityManager().persist(entity);
    }


    @Transactional(Transactional.TxType.MANDATORY)
    public Optional<T> updateEntity(int id, T entity) {
        if (entity != null) {
            //noinspection unchecked
            final var oldEntity = this.getEntityById(id, (Class<T>) entity.getClass());

            return oldEntity.map(old -> {
                entity.setId(old.getId());
                return this.getEntityManager().merge(entity);
            });
        }

        return Optional.empty();
    }

    protected abstract EntityManager getEntityManager();

    protected List<T> getEntities(QueryParameters query, Class<T> clas) {
        return JPAUtils.queryEntities(this.getEntityManager(), clas, query);
    }
}
