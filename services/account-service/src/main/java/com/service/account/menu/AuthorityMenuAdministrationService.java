package com.service.account.menu;

import com.database.dto.menu.MenuAdministrationDTO;
import com.database.model.account.authority.Authority;
import com.database.model.account.authority.AuthorityMenuAdministration;
import com.database.model.account.menu.MenuAdministration;

import com.service.account.menu.repository.AuthorityMenuAdministrationRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityMenuAdministrationService extends AbstractManyToManyMenuService<Authority, AuthorityMenuAdministration, MenuAdministration, MenuAdministrationDTO> {
    protected AuthorityMenuAdministrationService(AuthorityMenuAdministrationRepository repository) {
        super(repository);
    }
}