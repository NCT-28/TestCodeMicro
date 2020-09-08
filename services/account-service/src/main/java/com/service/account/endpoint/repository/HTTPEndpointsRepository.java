package com.service.account.endpoint.repository;

import com.base.enums.Role;
import com.database.model.account.authority.Authority;
import com.database.model.account.authority.AuthorityHTTPEndpoints;
import com.database.model.account.endpoint.HTTPEndpoints;
import com.spring.jpa.base.repository.BaseImplRepository;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.utils.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
public class HTTPEndpointsRepository extends BaseImplRepository<HTTPEndpoints> {

    protected HTTPEndpointsRepository(EntityManager em) {
        super(HTTPEndpoints.class, em);
    }

    /**
     * Tìm Endpoint theo điều kiện
     *
     * @param pattern uri cần kiểm tra
     * @return Endpoint theo điều kiện cần tìm
     */
    public HTTPEndpoints findExistEndpoints(String pattern) {
        return EMUtils.singleResult(getEM(), getEM().createQuery("from " + QueryUtils.GetNameEntity(getDomainClass()) + " where pattern=:p", HTTPEndpoints.class)
                .setParameter("p", pattern));
    }

    /**
     * Tìm tất cả các Endpoint theo vai trò của tài khoản đang đăng nhập
     *
     * @param role vai trò của tài khoản đang đăng nhập
     * @return danh sách các Endpoint theo các Column đã đưa vào
     */
    public List<Map<String, Object>> findAllGroupName(Role role) {
        StringBuilder s = new StringBuilder("select h.groupDescription,h.groupName,count(h." + baseIDName + ") FROM ");
        s.append(QueryUtils.GetNameEntity(getDomainClass())).append(" h ");
        if (role.equals(Role.ADMIN)) {
            s.append(" WHERE ");
        } else {
            s.append(" join ").append(QueryUtils.GetNameEntity(AuthorityHTTPEndpoints.class)).append(" ah");
            s.append(" on ah.httpEndpoints." + baseIDName + "=h." + baseIDName);
            s.append(" join ").append(QueryUtils.GetNameEntity(Authority.class)).append(" a");
            s.append(" on ah.authority." + baseIDName + "=a." + baseIDName);
            s.append(" WHERE a.authority=:r and ");
        }
        s.append(" h.isPublic=:p GROUP BY h.groupDescription,h.groupName ORDER BY h.groupDescription asc");
        TypedQuery<Object[]> q = getEM().createQuery(s.toString(), Object[].class);
        if (!role.equals(Role.ADMIN)) {
            q.setParameter("r", role.toString());
        }
        q.setParameter("p", false);
        return EMUtils.listResult(getEM(), q, "description", "name", "count");
    }

    /**
     * Tìm tất cả các Endpoint theo tên nhóm và kiểm tra vai trò của tài khoản đang đăng nhập
     *
     * @param role      vai trò của tài khoản đang đăng nhập
     * @param groupName tên nhóm Endpoint
     * @return danh sách các Endpoint theo các Column đã đưa vào
     */
    public List<Map<String, Object>> findAllEndpointByGroupName(Role role, String groupName) {
        StringBuilder s = new StringBuilder("select h." + baseIDName + ",h.summary,h.pattern,h.method from ");
        s.append(QueryUtils.GetNameEntity(getDomainClass())).append(" h ");
        if (role.equals(Role.ADMIN)) {
            s.append(" where ");
        } else {
            s.append(" join ").append(QueryUtils.GetNameEntity(AuthorityHTTPEndpoints.class)).append(" ah");
            s.append(" on ah.httpEndpoints." + baseIDName + "=h." + baseIDName);
            s.append(" join ").append(QueryUtils.GetNameEntity(Authority.class)).append(" a");
            s.append(" on ah.authority." + baseIDName + "=a." + baseIDName);
            s.append(" where a.authority=:role and ");
        }
        s.append(" h.groupName=:g and h.isPublic=:p ORDER BY h.summary asc");
        TypedQuery<Object[]> q = getEM().createQuery(s.toString(), Object[].class);
        if (!role.equals(Role.ADMIN)) {
            q.setParameter("role", role.toString());
        }
        q.setParameter("g", groupName);
        q.setParameter("p", false);
        return EMUtils.listResult(getEM(), q, baseIDName, "summary", "pattern", "method");
    }

    /**
     * Tìm tất cả các {@link HTTPEndpoints} với tên Server
     *
     * @param serverName tên Server
     */
    public List<String> findAllEndpointByServerName(String serverName) {
        return EMUtils.listResult(getEM(), getEM().createQuery(
                "select pattern from " + QueryUtils.GetNameEntity(getDomainClass()) + " where serverName=:t", String.class)
                .setParameter("t", serverName));
    }

    /**
     * Xóa tất cá các Endpoint với theo tên Server
     *
     * @param name tên Server
     */
    public void deleteAllWithServerName(String name) {
        EMUtils.execute(getEM(), getEM().createQuery("delete " + QueryUtils.GetNameEntity(getDomainClass()) + " where serverName=:p")
                .setParameter("p", name));
    }

    /**
     * Xóa Endpoint với theo Pattern và Server Name
     *
     * @param pattern    uri của Endpoint
     * @param serverName tên Server
     */
    public boolean deleteWithPatternAndServerName(String pattern, String serverName) {
        return EMUtils.execute(getEM(), getEM().createQuery("delete " + QueryUtils.GetNameEntity(getDomainClass()) + " where pattern=:p and serverName=:k")
                .setParameter("p", pattern)
                .setParameter("k", serverName));
    }
}