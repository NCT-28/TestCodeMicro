package com.service.account.menu;

import com.database.dto.menu.MenuClientDTO;
import com.database.model.account.authority.Authority;
import com.database.model.account.authority.AuthorityMenuClient;
import com.database.model.account.menu.MenuClient;
import com.service.account.menu.repository.AuthorityMenuClientRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityMenuClientService extends AbstractManyToManyMenuService<Authority, AuthorityMenuClient, MenuClient, MenuClientDTO> {
    protected AuthorityMenuClientService(AuthorityMenuClientRepository repository) {
        super(repository);
    }
}