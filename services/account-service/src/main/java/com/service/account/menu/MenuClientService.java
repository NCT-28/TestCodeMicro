package com.service.account.menu;

import com.database.model.account.menu.MenuClient;
import com.service.account.menu.repository.MenuClientRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuClientService extends AbstractMenuService<MenuClient> {
    protected MenuClientService(MenuClientRepository repository) {
        super(repository);
    }
}