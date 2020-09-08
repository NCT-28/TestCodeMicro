package com.service.account.account.repository;

import com.database.model.account.Account;
import com.database.model.account.authority.AccountAuthority;
import com.database.model.account.authority.Authority;
import com.spring.jpa.base.repository.ManyToManyImplRepository;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.utils.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
public class AccountAuthorityRepository extends ManyToManyImplRepository<Account, AccountAuthority, Authority> {

    protected AccountAuthorityRepository(EntityManager e, AccountRepository a, AuthorityRepository y) {
        super(Account.class, AccountAuthority.class, Authority.class, e, a, y);
    }

    /**
     * Xóa tất cả vai trò theo id {@link Account}
     *
     * @param id id {@link Account}
     */
    public void deleteAllWithIdAccount(UUID id) {
        EMUtils.execute(getEM(), getEM().createQuery("delete " + QueryUtils.GetNameEntity(getDomainClass()) + " where account." + baseIDName + "=:i")
                .setParameter("i", id));
    }
}