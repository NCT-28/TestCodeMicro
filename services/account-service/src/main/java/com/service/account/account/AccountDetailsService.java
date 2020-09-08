package com.service.account.account;

import com.database.dto.account.AccountDetailsDTO;
import com.database.model.account.Account;
import com.database.model.account.AccountDetails;
import com.original.tag.models.account.TagAccountDetails;
import com.service.account.account.repository.AccountDetailsRepository;
import com.source.base.utils.ValidationUtils;
import com.spring.jpa.base.repository.IRepository;
import com.spring.jpa.base.service.BaseImplService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Nguyễn Đình Tạo
 */
@Service
public class AccountDetailsService extends BaseImplService<AccountDetails> {

    private final AccountDetailsRepository repo;

    protected AccountDetailsService(AccountDetailsRepository repo) {
        super(repo);
        this.repo = repo;
    }

    /**
     * Lấy thông tin tài khoản DTO
     *
     * @param idAccount id tài khoản
     * @return thông tin chi tiết tài khoản
     */
    @Transactional(readOnly = true)
    public AccountDetailsDTO getInfoDTO(UUID idAccount) {
        AccountDetails accountDetails = repo.findByParentID(idAccount, Account.class);
        if (accountDetails == null) {
            return null;
        }
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setUuid(accountDetails.getUuid());
        accountDetailsDTO.setName(accountDetails.getName());
        accountDetailsDTO.setEmail(accountDetails.getEmail());
        accountDetailsDTO.setPhoneNumber(accountDetails.getPhoneNumber());
        accountDetailsDTO.setAbout(accountDetails.getAbout());
        return accountDetailsDTO;
    }

    /**
     * Lấy thông tin chi tiết của tài khoản
     *
     * @param idAccount id tài khoản
     * @return thông tin chi tiết
     */
    @Transactional(readOnly = true)
    @Cacheable(value = TagAccountDetails.name + "details", key = "#idAccount")
    public AccountDetails getDetails(UUID idAccount) {
        return repo.findByParentID(idAccount, Account.class);
    }

    /**
     * Cập nhật thông tin tài khoản
     *
     * @param accountDetails thông tin tài khoản cần cập nhật
     * @return trạng thái cập nhật
     * @throws Exception lỗi khi không thể cập nhật
     */
    @Transactional
    @CacheEvict(value = TagAccountDetails.name + "details", key = "#idAccount")
    public boolean updateInfo(UUID idAccount, AccountDetails accountDetails) throws Exception {
        // Tìm Id của Record trong bảng AccountDetails
        UUID id = repo.findByParentID(idAccount, Account.class, IRepository.baseIDName, UUID.class);
        if (id == null) {
            throw new Exception("Id " + TagAccountDetails.description + " không tìm thấy !");
        }
        // Set id đã tìm cho đối tượng cần lưu
        accountDetails.setUuid(id);
        // Cập nhật các thông tin
        Map<String, Object> parameters = new HashMap<>();
        if (accountDetails.getName() != null) {
            if (accountDetails.getName().length() < 6) {
                throw new Exception("Tên tài khoản quá ngắn !");
            }
            parameters.put("name", accountDetails.getName());
        }
        if (accountDetails.getEmail() != null) {
            if (!ValidationUtils.checkEmail(accountDetails.getEmail())) {
                throw new Exception("Email không hợp lệ !");
            }
            parameters.put("email", accountDetails.getEmail());
        }
        if (accountDetails.getPhoneNumber() != null) {
            if (accountDetails.getPhoneNumber().length() < 8) {
                throw new Exception("Số điện thoại không hợp lệ !");
            }
            parameters.put("phoneNumber", accountDetails.getPhoneNumber());
        }
        if (accountDetails.getAbout() != null) {
            parameters.put("about", accountDetails.getAbout());
        }
        return repo.updateById(parameters, accountDetails.getUuid());
    }
}