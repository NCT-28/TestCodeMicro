package com.service.account.account.repository;

import com.database.model.account.AccountDetails;
import com.spring.jpa.base.repository.BaseImplRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AccountDetailsRepository extends BaseImplRepository<AccountDetails> {
    protected AccountDetailsRepository(EntityManager entityManager) {
        super(AccountDetails.class, entityManager);
    }
}