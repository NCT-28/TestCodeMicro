package com.service.account.menu;

import com.base.dtos.jpa.BaseEntityDTO;
import com.base.dtos.jpa.ParentDTO;
import com.service.account.menu.repository.AbstractManyToManyMenuRepository;
import com.spring.entity.base.BaseEntity;
import com.spring.jpa.base.service.ManyToManyImplService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public abstract class AbstractManyToManyMenuService<T extends BaseEntity, TS extends BaseEntity, J extends BaseEntity, DT extends BaseEntityDTO & ParentDTO<UUID>>
        extends ManyToManyImplService<T, TS, J> {

    private final AbstractManyToManyMenuRepository<T, TS, J, DT> repo;

    protected AbstractManyToManyMenuService(AbstractManyToManyMenuRepository<T, TS, J, DT> repo) {
        super(repo);
        this.repo = repo;
    }

    /**
     * Danh sách Menu với vai trò tài khoản
     *
     * @param a danh sách các vai trò
     * @return Danh sách Menu
     */
    @Transactional(readOnly = true)
    public List<DT> getByAuthorities(List<?> a) {
        return repo.getByAuthorities(a);
    }

    /**
     * Cập nhật các menu với id vai trò tài khoản
     *
     * @param idAuthority id vai trò tài khoản
     * @param menus       danh sách menu cần cập nhật
     * @return true: thành công
     */
    @Transactional
    public boolean setAllMenuWithAuthority(UUID idAuthority, List<TS> menus) {
        if (repo.deleteAllWithAuthority(idAuthority)) {
            try {
                if (menus != null) {
                    if (!menus.isEmpty()) {
                        getRepository().saveAll(menus);
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