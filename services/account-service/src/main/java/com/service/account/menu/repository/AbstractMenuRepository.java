package com.service.account.menu.repository;

import com.spring.jpa.base.repository.BaseImplRepository;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.utils.QueryUtils;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractMenuRepository<T> extends BaseImplRepository<T> {

    protected AbstractMenuRepository(Class<T> t, EntityManager em) {
        super(t, em);
    }

    public List<T> getListWithCheckParent() {
        return EMUtils.listResult(getEM(), getEM().createQuery("from " + QueryUtils.GetNameEntity(getDomainClass()) + " where parent=NULL", getDomainClass()));
    }
}