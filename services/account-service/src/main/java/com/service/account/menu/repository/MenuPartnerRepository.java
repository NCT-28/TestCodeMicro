package com.service.account.menu.repository;

import com.database.model.account.menu.MenuPartner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MenuPartnerRepository extends AbstractMenuRepository<MenuPartner> {
    protected MenuPartnerRepository(EntityManager em) {
        super(MenuPartner.class, em);
    }
}