package com.service.account.menu.repository;

import com.database.model.account.menu.MenuClient;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MenuClientRepository extends AbstractMenuRepository<MenuClient> {
    protected MenuClientRepository(EntityManager em) {
        super(MenuClient.class, em);
    }
}