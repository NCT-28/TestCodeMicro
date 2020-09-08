package com.service.account.endpoint.repository;

import com.database.model.account.authority.Authority;
import com.database.model.account.authority.AuthorityHTTPEndpoints;
import com.database.model.account.endpoint.HTTPEndpoints;
import com.service.account.account.repository.AuthorityRepository;
import com.spring.jpa.base.repository.ManyToManyImplRepository;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.utils.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
public class AuthorityHTTPEndpointsRepository extends ManyToManyImplRepository<Authority, AuthorityHTTPEndpoints, HTTPEndpoints> {

    protected AuthorityHTTPEndpointsRepository(EntityManager em, AuthorityRepository t, HTTPEndpointsRepository j) {
        super(Authority.class, AuthorityHTTPEndpoints.class, HTTPEndpoints.class, em, t, j);
    }

    /**
     * Kiểm tra đường dẫn truy cập có tồn tại với các vai trò tài khoản
     *
     * @param p đường dẫn truy cập
     * @param a danh sách các vai trò tài khoản
     * @return true có tồn tại, false không tồn tại
     */
    public Object check(String p, List<?> a) {
        return EMUtils.singleResult(getEM(), getEM().createQuery("select k.createdBy " +
                getQuery("k", "t", "j") + " where j.pattern=:p and t." + getTClassID() + ".authority in :a")
                .setParameter("p", p)
                .setParameter("a", a));
    }

    /**
     * Lấy danh sách các Id Endpoint với Id vai trò tài khoản và tên nhóm
     *
     * @param idAuthority Id vai trò tài khoản
     * @param groupName   tên nhóm
     * @return danh sách các Id Endpoint
     */
    public List<UUID> getListIdEndpoint(UUID idAuthority, String groupName) {
        return EMUtils.listResult(getEM(), getEM().createQuery(
                "select h." + baseIDName + " " + getQuery("k", "h") +
                        " where k." + getTClassID() + "." + baseIDName + "=:q and k." + getJClassID() + ".groupName=:g", UUID.class)
                .setParameter("q", idAuthority)
                .setParameter("g", groupName));
    }

    /**
     * Xóa tất cả các Endpoint với Id vai trò tài khoản và tên nhóm đã được Join
     *
     * @param idAuthority Id vai trò tài khoản
     * @param groupName   Tên nhóm
     * @return true là thành công, false không thành công
     */
    public boolean deleteAllEqualAuthorityAndGroupName(UUID idAuthority, String groupName) {
        return EMUtils.execute(getEM(), getEM().createQuery(
                "delete " + QueryUtils.GetNameEntity(getDomainClass()) +
                        " where " + getTClassID() + "." + baseIDName +
                        " in (select " + baseIDName + " from " + QueryUtils.GetNameEntity(getTClass()) + " where " + baseIDName + "=:i)" +
                        " and " + getJClassID() + "." + baseIDName +
                        " in (select " + baseIDName + " from " + QueryUtils.GetNameEntity(getJClass()) + " where groupName=:g)")
                .setParameter("i", idAuthority)
                .setParameter("g", groupName));
    }
}