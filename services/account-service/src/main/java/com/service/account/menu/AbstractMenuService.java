package com.service.account.menu;

import com.service.account.menu.repository.AbstractMenuRepository;
import com.spring.entity.base.BaseEntity;
import com.spring.jpa.base.service.BaseImplService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractMenuService<T extends BaseEntity> extends BaseImplService<T> {

    private final AbstractMenuRepository<T> repository;

    protected AbstractMenuService(AbstractMenuRepository<T> repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<T> getListMenu() {
        return repository.getListWithCheckParent();
    }
}