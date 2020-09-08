package com.service.account.endpoint.impl;

import com.base.dtos.http.AuthorityHTTPEndpointsService;
import com.database.model.account.authority.Authority;
import com.database.model.account.authority.AuthorityHTTPEndpoints;
import com.database.model.account.endpoint.HTTPEndpoints;
import com.original.tag.models.menu.TagMenuAdministration;
import com.original.tag.models.menu.TagMenuClient;
import com.original.tag.models.menu.TagMenuPartner;
import com.service.account.endpoint.repository.AuthorityHTTPEndpointsRepository;
import com.spring.jpa.base.service.ManyToManyImplService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Nguyễn Đình Tạo
 */
@Service
public class AuthorityHTTPEndpointsServiceJpa extends ManyToManyImplService<Authority, AuthorityHTTPEndpoints, HTTPEndpoints> implements AuthorityHTTPEndpointsService {

    private final AuthorityHTTPEndpointsRepository repository;

    protected AuthorityHTTPEndpointsServiceJpa(AuthorityHTTPEndpointsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Kiểm tra đường dẫn truy cập có tồn tại với các vai trò tài khoản
     *
     * @param p đường dẫn truy cập
     * @param a danh sách các vai trò tài khoản
     * @return true có tồn tại, false không tồn tại
     */
    @Transactional(readOnly = true)
    public boolean check(String p, List<?> a) {
        return repository.check(p, a) == null;
    }

    /**
     * Lấy danh sách các Id Endpoint với Id vai trò tài khoản và tên nhóm
     *
     * @param idAuthority Id vai trò tài khoản
     * @param groupName   tên nhóm
     * @return danh sách các Id Endpoint
     */
    @Transactional(readOnly = true)
    public List<UUID> getListIdEndpoint(UUID idAuthority, String groupName) {
        return repository.getListIdEndpoint(idAuthority, groupName);
    }

    /**
     * Lưu danh sách các Endpoint với Id vai trò tài khoản
     *
     * @param idAuthority   Id vai trò tài khoản
     * @param groupName     Tên nhóm
     * @param endpointsList danh sách các Endpoint cần lưu
     * @return true là thành công, false không thành công
     */
    @Transactional
    @Caching(evict = {@CacheEvict(value = {TagMenuAdministration.name, TagMenuClient.name, TagMenuPartner.name}, allEntries = true)})
    public boolean saveAllWithAuthorityAndGroupName(UUID idAuthority, String groupName, List<AuthorityHTTPEndpoints> endpointsList) {
        if (repository.deleteAllEqualAuthorityAndGroupName(idAuthority, groupName)) {
            try {
                if (endpointsList != null) {
                    if (!endpointsList.isEmpty()) {
                        repository.saveAll(endpointsList);
                        return true;
                    }
                }
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }
}