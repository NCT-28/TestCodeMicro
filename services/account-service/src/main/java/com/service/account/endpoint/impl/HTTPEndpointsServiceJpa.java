package com.service.account.endpoint.impl;

import com.base.enums.Role;
import com.database.model.account.endpoint.HTTPEndpoints;
import com.service.account.endpoint.HTTPEndpointsService;
import com.service.account.endpoint.repository.HTTPEndpointsRepository;
import com.spring.jpa.base.service.BaseImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Nguyễn Đình Tạo
 */
@Service
public class HTTPEndpointsServiceJpa extends BaseImplService<HTTPEndpoints> implements HTTPEndpointsService<HTTPEndpoints> {

    private final HTTPEndpointsRepository repo;

    protected HTTPEndpointsServiceJpa(HTTPEndpointsRepository repo) {
        super(repo);
        this.repo = repo;
    }

    /**
     * Kiểm tra đường dẫn có tồn tại trong database
     *
     * @param httpEndpoints endpoint cần tìm
     * @return true / false
     */
    @Override
    @Transactional(readOnly = true)
    public Boolean checkExist(HTTPEndpoints httpEndpoints) {
        return repo.findExistEndpoints(httpEndpoints.getPattern()) != null;
    }

    /**
     * Tìm một đường dẫn trong database
     *
     * @param httpEndpoints đường dẫn cần tìm
     * @return endpoints cần tìm
     */
    @Override
    @Transactional(readOnly = true)
    public HTTPEndpoints findEndpoints(HTTPEndpoints httpEndpoints) {
        return repo.findExistEndpoints(httpEndpoints.getPattern());
    }

    /**
     * Tìm tất cả các URI theo tên Server
     *
     * @param serverName tên Server
     * @return danh sách các URI
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> findAllEndpointByServerName(String serverName) {
        return repo.findAllEndpointByServerName(serverName);
    }

    /**
     * Xóa Endpoint với theo Pattern và Server Name
     *
     * @param pattern    uri của Endpoint
     * @param serverName tên Server
     */
    @Override
    @Transactional
    public boolean deleteWithPatternAndServerName(String pattern, String serverName) {
        return repo.deleteWithPatternAndServerName(pattern, serverName);
    }

    /**
     * Xóa tất cả các Endpoint theo tên server
     *
     * @param name tên server
     */
    @Transactional
    public void deleteAllWithServerName(String name) {
        repo.deleteAllWithServerName(name);
    }

    /**
     * Lưu danh sách các endpoint theo tên server
     *
     * @param serverNames tên server
     * @param listSave    danh sách cần lưu
     * @return thành cồng / không thành công
     */
    @Transactional
    public boolean batchAllWithServerName(List<String> serverNames, List<HTTPEndpoints> listSave) {
        try {
            for (String serverName : serverNames) {
                this.deleteAllWithServerName(serverName);
            }
            this.addAll(listSave);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Tìm danh sách các nhóm theo vai trò
     *
     * @param role loại vai trò
     * @return danh sách các tên nhóm
     */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findAllGroupName(Role role) {
        return repo.findAllGroupName(role);
    }

    /**
     * Tìm danh sách các Endpoint theo tên nhóm và vai trò
     *
     * @param role      vai trò
     * @param groupName tên nhóm
     * @return danh sách các endpoint cần tìm
     */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findAllEndpointByGroupName(Role role, String groupName) {
        return repo.findAllEndpointByGroupName(role, groupName);
    }
}