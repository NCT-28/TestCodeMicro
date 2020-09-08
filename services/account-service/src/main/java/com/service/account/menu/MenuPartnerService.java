package com.service.account.menu;

import com.database.model.account.menu.MenuPartner;

import com.service.account.menu.repository.MenuPartnerRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuPartnerService extends AbstractMenuService<MenuPartner> {
    protected MenuPartnerService(MenuPartnerRepository repository) {
        super(repository);
    }
}