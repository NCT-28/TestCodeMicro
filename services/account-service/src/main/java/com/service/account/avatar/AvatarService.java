package com.service.account.avatar;

import com.database.model.account.Account;
import com.database.model.account.avatar.Avatar;
import com.original.tag.models.account.TagAvatar;
import com.service.account.avatar.repository.AvatarRepository;
import com.spring.jpa.base.repository.IRepository;
import com.spring.jpa.base.service.BaseImplService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AvatarService extends BaseImplService<Avatar> {

    private final AvatarRepository repo;

    protected AvatarService(AvatarRepository repo) {
        super(repo);
        this.repo = repo;
    }

    /**
     * Kiểm tra id hình đại diện có tồn tại với tài khoản
     *
     * @param idAccount id tài khoản
     * @param idAvatar id hình đại diện cần kiểm tra
     * @return true: tồn tại / false: không tồn tại
     */
    @Transactional(readOnly = true)
//    @Cacheable(value = TagAvatar.name + "checkExists", key = "#idAccount")
    public Boolean checkExists(UUID idAccount, UUID idAvatar) {
        return repo.checkExists(idAccount, idAvatar) != null;
    }

    /**
     * Lấy id hình đại diện mặc định của tài khoản
     *
     * @param idAccount id tài khoản
     * @return id hình ảnh đại diện
     */
    @Transactional(readOnly = true)
//    @Cacheable(value = TagAvatar.name + "getDefault", key = "#idAccount")
    public String getDefault(UUID idAccount) {
        return "{\"idUpload\":\"" + repo.findDefault(idAccount) + "\"}";
    }

    /**
     * Tìm danh sách các Avatar đã upload
     *
     * @param idAccount Id tài khoản
     * @return Danh sách các idUpload
     */
    @Transactional(readOnly = true)
//    @Cacheable(value = TagAvatar.name + "getAll", key = "#idAccount")
    public List<?> getAll(UUID idAccount) {
        return repo.findAllByParentID(idAccount, Account.class, IRepository.baseIDName, "idUpload");
    }

    /**
     * Lưu hình đại diện với id tài khoản
     *
     * @param idAccount id tài khoản
     * @param idAvatar  id hình ảnh
     * @return thông tin đã lưu
     */
    @Transactional
    @CacheEvict(value = TagAvatar.name + "getDefault", key = "#idAccount")
    public Avatar saveWithIdAccount(UUID idAccount, String idAvatar) {
        return repo.save(new Avatar(idAvatar, new Account(idAccount), false));
    }

    /**
     * Đặt là hình đại diện mặc định với id tài khoản
     *
     * @param idAccount id tài khoản
     * @param idAvatar  id hình ảnh
     * @return thông tin đã lưu
     */
    @Transactional
    @CacheEvict(value = TagAvatar.name + "getDefault", key = "#idAccount")
    public String saveDefaultByIdAccount(UUID idAccount, UUID idAvatar) {
        // Lưu tất cả các Avartar thành không mặc định
        repo.saveAllAvartarToFalse(idAccount);
        // Lưu Avartar thành mặc định với id
        if (getRepository().updateById(Map.of("macDinh", true), idAvatar)) {
            return repo.findDefault(idAccount);
        }
        return null;
    }

    /**
     * Xóa Avartar
     *
     * @param uuid id avartar
     * @return true: ok / false: éo
     */
    @Override
    @Transactional
    @CacheEvict(value = {TagAvatar.name + "getDefault", TagAvatar.name + "checkExists", TagAvatar.name + "getAll"}, key = "#idAccount")
    public boolean delete(UUID uuid) {
        try {
            repo.deleteById(uuid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}