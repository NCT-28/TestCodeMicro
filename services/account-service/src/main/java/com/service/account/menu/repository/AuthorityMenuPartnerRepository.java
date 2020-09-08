package com.service.account.menu.repository;

import com.database.dto.menu.MenuPartnerDTO;
import com.database.model.account.authority.Authority;
import com.database.model.account.authority.AuthorityMenuPartner;
import com.database.model.account.menu.MenuPartner;
import com.service.account.account.repository.AuthorityRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthorityMenuPartnerRepository extends AbstractManyToManyMenuRepository<Authority, AuthorityMenuPartner, MenuPartner, MenuPartnerDTO> {
    protected AuthorityMenuPartnerRepository(EntityManager entityManager, AuthorityRepository tRepository, MenuPartnerRepository jRepository) {
        super(Authority.class, AuthorityMenuPartner.class, MenuPartner.class, MenuPartnerDTO.class, entityManager, tRepository, jRepository);
    }
}