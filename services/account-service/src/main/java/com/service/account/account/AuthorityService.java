package com.service.account.account;

import com.base.dtos.userinfo.AuthorityInfo;
import com.database.model.account.authority.Authority;
import com.service.account.account.repository.AuthorityRepository;
import com.spring.jpa.base.service.BaseImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ToanNC
 */
@Service
public class AuthorityService extends BaseImplService<Authority> {

    private final AuthorityRepository repository;

    protected AuthorityService(AuthorityRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Lưu vai trò
     *
     * @param authority vai trò cần lưu
     * @return vai trò khi lưu thành công
     * @throws Exception lỗi không hợp lệ
     */
    @Transactional
    public Authority save(Authority authority) throws Exception {
        if (authority.getAuthority() == null) {
            throw new Exception("Mã vai trò không thể rỗng !");
        }
        if (authority.getAuthority().length() < 3) {
            throw new Exception("Mã vai trò quá ngắn !");
        }
        if (authority.getAuthority().length() > 150) {
            throw new Exception("Mã vai trò quá dài !");
        }
        if (authority.getName() == null) {
            throw new Exception("Tên vai trò không thể rỗng !");
        }
        if (authority.getName().length() < 5) {
            throw new Exception("Tên vai trò quá ngắn !");
        }
        return repository.save(authority);
    }

    /**
     * Lưu danh sách các vai trò
     *
     * @param authorityInfos các vai trò cần lưu
     * @return danh sách các vai trò đã lưu
     */
    @Transactional
    public Set<Authority> saveAuthorityInfo(Set<AuthorityInfo> authorityInfos) {
        Set<Authority> authorities = new HashSet<>();
        for (AuthorityInfo authorityInfo : authorityInfos) {
            Authority authority = repository.findByName("authority", authorityInfo.getAuthority());
            if (authority != null) {
                authorities.add(authority);
            } else {
                authorities.add(this.add(new Authority(authorityInfo)));
            }
        }
        return authorities;
    }
}