package com.service.account.menu;

import com.database.dto.menu.MenuPartnerDTO;
import com.database.model.account.authority.Authority;
import com.database.model.account.authority.AuthorityMenuPartner;
import com.database.model.account.menu.MenuPartner;
import com.service.account.menu.repository.AuthorityMenuPartnerRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityMenuPartnerService extends AbstractManyToManyMenuService<Authority, AuthorityMenuPartner, MenuPartner, MenuPartnerDTO> {
    protected AuthorityMenuPartnerService(AuthorityMenuPartnerRepository repository) {
        super(repository);
    }
}