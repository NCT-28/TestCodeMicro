package com.service.account.menu.repository;

import com.database.dto.menu.MenuAdministrationDTO;
import com.database.model.account.authority.Authority;
import com.database.model.account.authority.AuthorityMenuAdministration;
import com.database.model.account.menu.MenuAdministration;
import com.service.account.account.repository.AuthorityRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthorityMenuAdministrationRepository extends AbstractManyToManyMenuRepository<Authority, AuthorityMenuAdministration, MenuAdministration, MenuAdministrationDTO> {
    protected AuthorityMenuAdministrationRepository(EntityManager em, AuthorityRepository tRepository, MenuAdministrationRepository jRepository) {
        super(Authority.class, AuthorityMenuAdministration.class, MenuAdministration.class, MenuAdministrationDTO.class, em, tRepository, jRepository);
    }
}