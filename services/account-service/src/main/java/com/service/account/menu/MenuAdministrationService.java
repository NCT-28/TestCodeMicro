package com.service.account.menu;

import com.database.model.account.menu.MenuAdministration;

import com.service.account.menu.repository.MenuAdministrationRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuAdministrationService extends AbstractMenuService<MenuAdministration> {
    protected MenuAdministrationService(MenuAdministrationRepository repository) {
        super(repository);
    }
}