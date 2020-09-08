package com.service.account.account.repository;

import com.database.model.account.authority.Authority;
import com.spring.jpa.base.repository.BaseImplRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthorityRepository extends BaseImplRepository<Authority> {
    protected AuthorityRepository(EntityManager entityManager) {
        super(Authority.class, entityManager);
    }
}