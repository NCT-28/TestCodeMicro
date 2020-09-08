package com.service.account.account.repository;

import com.database.model.account.Account;
import com.spring.jpa.base.repository.BaseImplRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AccountRepository extends BaseImplRepository<Account> {
    protected AccountRepository(EntityManager entityManager) {
        super(Account.class, entityManager);
    }
}