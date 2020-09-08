package com.service.account.account;

import com.base.dtos.jpa.BaseEntityDTO;
import com.base.dtos.others.UuidNameDTO;
import com.base.dtos.userinfo.AuthorityInfo;
import com.database.dto.account.UpdateAuthorities;
import com.database.model.account.Account;
import com.database.model.account.authority.AccountAuthority;
import com.database.model.account.authority.Authority;
import com.original.tag.models.account.TagAccount;
import com.original.tag.models.account.TagAuthority;
import com.original.tag.models.menu.TagMenuAdministration;
import com.original.tag.models.menu.TagMenuClient;
import com.original.tag.models.menu.TagMenuPartner;
import com.service.account.account.repository.AccountAuthorityRepository;
import com.spring.jpa.base.repository.IRepository;
import com.spring.jpa.base.service.ManyToManyImplService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AccountAuthorityService extends ManyToManyImplService<Account, AccountAuthority, Authority> {

    private final AccountAuthorityRepository repository;
    private final AuthorityService authorityService;

    protected AccountAuthorityService(AccountAuthorityRepository repository, AuthorityService authorityService) {
        super(repository);
        this.repository = repository;
        this.authorityService = authorityService;
    }

    @Transactional
    public Account addAccountAndAuthorityInfo(Account account, Set<AuthorityInfo> authorityInfos) {
        Set<Authority> authorities = this.authorityService.saveAuthorityInfo(authorityInfos);
        if (authorities == null) {
            return null;
        }
        return this.saveEntityWithAllJoinTableSaved(account, authorities);
    }

    /**
     * Lấy danh sách tên vài trò theo id Tài khoản
     *
     * @param idAccount id tài khoản
     * @return danh sách tên vài trò
     */
    @Transactional
    @Cacheable(value = TagAccount.name + TagAuthority.name + "names", key = "#idAccount")
    public List<?> getNames(UUID idAccount) {
        return repository.findAllJoinTableByID(idAccount, "name", String.class);
    }

    /**
     * Lấy danh sách tên vài trò theo id Tài khoản
     *
     * @param idAccount id tài khoản
     * @return danh sách tên vài trò
     */
    @Transactional
    @Cacheable(value = TagAccount.name + TagAuthority.name + "idNames", key = "#idAccount")
    public List<UuidNameDTO> getUUIDNames(UUID idAccount) {
        return repository.findAllJoinTableByID(idAccount, UuidNameDTO.class, IRepository.baseIDName, "name");
    }

    /**
     * Lấy danh sách vai trò theo id tài khoản
     *
     * @param idAccount id tài khoản
     * @return danh sách tên vài trò
     */
    @Transactional(readOnly = true)
    @Cacheable(value = TagAccount.name + TagAuthority.name + "enables", key = "#idAccount")
    public List<AuthorityInfo> findAllEnable(UUID idAccount) {
        return repository.findAllJoinTableByID(idAccount, AuthorityInfo.class, "authority", "enableAdmin", "enableClient", "enablePartner");
    }

    /**
     * Cập nhật danh sách vai trò theo id tài khoản
     *
     * @param updateAuthorities Thông tin cần cập nhật
     * @return true: thành công / false: không thanh công
     * @throws Exception lỗi
     */
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = TagAccount.name + TagAuthority.name + "names", key = "#idAccount"),
            @CacheEvict(value = TagAccount.name + TagAuthority.name + "idNames", key = "#idAccount"),
            @CacheEvict(value = TagAccount.name + TagAuthority.name + "enables", key = "#idAccount"),
            @CacheEvict(value = {TagMenuAdministration.name, TagMenuClient.name, TagMenuPartner.name}, allEntries = true)
    })
    public Boolean updateAuthorities(UUID idAccount, UpdateAuthorities updateAuthorities) throws Exception {
        if (updateAuthorities == null) {
            throw new Exception("Thông tin cập nhật không thể rỗng !");
        }
        if (idAccount == null) {
            throw new Exception("Id tài khoản không thể rỗng !");
        }
        if (updateAuthorities.getAuthorities() == null) {
            throw new Exception("Danh sách vai trò không thể rỗng !");
        }
        if (updateAuthorities.getAuthorities().isEmpty()) {
            throw new Exception("Vui lòng nhập tối thiểu một vai trò !");
        }
        try {
            // Xóa tất cả các vai trò cũ
            repository.deleteAllWithIdAccount(idAccount);
            // Lưu mới các vai trò mới
            List<AccountAuthority> a = new ArrayList<>();
            for (BaseEntityDTO entityDTO : updateAuthorities.getAuthorities()) {
                a.add(new AccountAuthority(new Account(idAccount), authorityService.getById(entityDTO.getUuid())));
            }
            repository.saveAll(a);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}