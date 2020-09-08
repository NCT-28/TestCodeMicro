package com.service.account.avatar.repository;

import com.database.model.account.avatar.Avatar;
import com.spring.jpa.base.repository.BaseImplRepository;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.utils.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
public class AvatarRepository extends BaseImplRepository<Avatar> {

    protected AvatarRepository(EntityManager em) {
        super(Avatar.class, em);
    }

    /**
     * Lấy id hình đại diện mặc định của tài khoản
     *
     * @param idAccount id tài khoản
     * @return id hình ảnh đại diện
     */
    public String findDefault(UUID idAccount) {
        return EMUtils.singleResult(getEM(), getEM().createQuery(
                "select idUpload from " + QueryUtils.GetNameEntity(getDomainClass()) + " where account." + baseIDName + "=:i and macDinh=:p", String.class)
                .setParameter("i", idAccount)
                .setParameter("p", true));
    }

    /**
     * Kiểm tra id hình đại diện có tồn tại với tài khoản
     *
     * @param idAccount id tài khoản
     * @param idAvartar id hình đại diện cần kiểm tra
     * @return true: tồn tại / false: không tồn tại
     */
    public String checkExists(UUID idAccount, UUID idAvartar) {
        return EMUtils.singleResult(getEM(), getEM().createQuery(
                "select idUpload from " + QueryUtils.GetNameEntity(getDomainClass()) + " where account." + baseIDName + "=:i and " + baseIDName + "=:h", String.class)
                .setParameter("i", idAccount)
                .setParameter("h", idAvartar));
    }

    /**
     * Lưu tất cả các Avartar thành không mặc định
     *
     * @param id id tài khoản
     */
    public void saveAllAvartarToFalse(UUID id) {
        EMUtils.execute(getEM(), getEM().createQuery("UPDATE " + QueryUtils.GetNameEntity(getDomainClass()) + " SET macDinh=:m WHERE account." + baseIDName + "=:i")
                .setParameter("i", id)
                .setParameter("m", false)
        );
    }
}