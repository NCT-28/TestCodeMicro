package com.service.account.menu.repository;

import com.base.dtos.jpa.BaseEntityDTO;
import com.base.dtos.jpa.ParentDTO;
import com.spring.entity.base.BaseEntity;
import com.spring.jpa.base.repository.BaseRepository;
import com.spring.jpa.base.repository.ManyToManyImplRepository;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.utils.QueryUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public abstract class AbstractManyToManyMenuRepository<T extends BaseEntity, TS extends BaseEntity, J extends BaseEntity, DT extends BaseEntityDTO & ParentDTO<UUID>>
        extends ManyToManyImplRepository<T, TS, J> {

    private final Class<DT> d;

    protected AbstractManyToManyMenuRepository(Class<T> t, Class<TS> ts, Class<J> j, Class<DT> d, EntityManager em, BaseRepository<T, UUID> tR, BaseRepository<J, UUID> jR) {
        super(t, ts, j, em, tR, jR);
        this.d = d;
    }

    public List<DT> getByAuthorities(List<?> a) {
        return EMUtils.listResult(getEM(), getEM().createQuery(
                "select distinct new " + QueryUtils.GetNameEntity(d) + "(b." + baseIDName + ",b.name,b.path,b.icon,b.ordinal,b.parent." + baseIDName + ") " +
                        getQuery("a", "b") + " where a." + getTClassID() + ".authority in :p order by b.name asc", d)
                .setParameter("p", a));
    }

    public boolean deleteAllWithAuthority(UUID uuid) {
        return EMUtils.execute(getEM(), getEM().createQuery("delete " + QueryUtils.GetNameEntity(getDomainClass()) + " where " + getTClassID() + "." + baseIDName + "=:i")
                .setParameter("i", uuid));
    }
}