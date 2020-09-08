package com.service.account.menu.repository;

import com.database.model.account.menu.MenuAdministration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MenuAdministrationRepository extends AbstractMenuRepository<MenuAdministration> {
    protected MenuAdministrationRepository(EntityManager em) {
        super(MenuAdministration.class, em);
    }
}