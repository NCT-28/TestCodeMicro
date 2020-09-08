package com.service.account.menu.repository;

import com.database.dto.menu.MenuClientDTO;
import com.database.model.account.authority.Authority;
import com.database.model.account.authority.AuthorityMenuClient;
import com.database.model.account.menu.MenuClient;
import com.service.account.account.repository.AuthorityRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthorityMenuClientRepository extends AbstractManyToManyMenuRepository<Authority, AuthorityMenuClient, MenuClient, MenuClientDTO> {
    protected AuthorityMenuClientRepository(EntityManager entityManager, AuthorityRepository tRepository, MenuClientRepository jRepository) {
        super(Authority.class, AuthorityMenuClient.class, MenuClient.class, MenuClientDTO.class, entityManager, tRepository, jRepository);
    }
}