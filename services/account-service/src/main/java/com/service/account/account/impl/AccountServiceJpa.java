package com.service.account.account.impl;

import com.base.dtos.address.AddressDTO;
import com.base.dtos.others.MiniAccountDTO;
import com.base.dtos.others.NameDTO;
import com.base.dtos.others.PartnerAddEmployeeDTO;
import com.base.dtos.others.UuidCheckDTO;
import com.base.dtos.userinfo.AccountResetPassword;
import com.base.dtos.userinfo.AuthorityInfo;
import com.base.enums.Role;
import com.database.dto.account.AccountDTO;
import com.database.dto.account.AccountDetailsDTO;
import com.database.dto.account.AccountRegisterDTO;
import com.database.model.account.Account;
import com.database.model.account.AccountDetails;
import com.database.model.account.authority.AccountAuthority;

import com.original.tag.models.account.TagAccount;
import com.service.account.account.AccountAuthorityService;
import com.service.account.account.AccountDetailsService;
import com.service.account.account.AccountService;
import com.service.account.account.AuthorityService;
import com.service.account.account.repository.AccountRepository;
import com.service.account.avatar.AvatarService;
import com.source.base.utils.RandomStringUtils;
import com.source.base.utils.ValidationUtils;
import com.spring.jpa.base.repository.IRepository;
import com.spring.jpa.base.service.BaseImplService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class AccountServiceJpa extends BaseImplService<Account> implements AccountService<Account> {

    private final PasswordEncoder passwordEncoder;
   // private final AddressServiceRest addressServiceRest;

    private final AvatarService avatarService;
    private final AccountDetailsService accountDetailsService;
    //private final AccountAddressService accountAddressService;
    private final AuthorityService authorityService;
    private final AccountAuthorityService accountAuthorityService;

    public AccountServiceJpa(RestTemplate restTemplate,
                             PasswordEncoder passwordEncoder,
                             AccountRepository repository,
                             AvatarService avatarService,
                             AccountDetailsService accountDetailsService,
                             AuthorityService authorityService,
                             AccountAuthorityService accountAuthorityService) {
        super(repository);
        this.passwordEncoder = passwordEncoder;

        this.avatarService = avatarService;
        this.accountDetailsService = accountDetailsService;
        this.authorityService = authorityService;
        this.accountAuthorityService = accountAuthorityService;
    }

    /**
     * Lấy tên hiển thị của tài khoản bởi id
     *
     * @param idAccount id Tài khoản
     * @return Tên hiển thị của tài khoản
     */
    @Transactional(readOnly = true)
    @Cacheable(value = TagAccount.name + "getName", key = "#idAccount")
    public String getName(UUID idAccount) {
        return "{\"name\":\"" + getRepository().findById(idAccount, "displayName", String.class) + "\"}";
    }

    /**
     * Cập nhật tên hiển thị của tài khoản
     *
     * @param idAccount id Tài khoản
     * @param nameDTO   thông tin cần cập nhật
     * @return true: thành công / false: không thành công
     * @throws Exception lỗi
     */
    @Transactional
    @CacheEvict(value = TagAccount.name + "getName", key = "#idAccount")
    public Boolean updateDisplayNameById(UUID idAccount, NameDTO nameDTO) throws Exception {
        if (nameDTO.getName() == null) {
            throw new Exception("Tên hiển thị không thể rỗng !");
        }
        if (nameDTO.getName().length() < 6) {
            throw new Exception("Tên hiển thị quá ngắn !");
        }
        return getRepository().updateById(Map.of("displayName", nameDTO.getName()), idAccount);
    }

    /**
     * Lấy thông tin chi tiết của tài khoản
     *
     * @param idAccount id Tài khoản
     * @return thông tin tài khoản
     */
    @Transactional(readOnly = true)
    public AccountDTO getInfoDTO(UUID idAccount) {
        Account account = getRepository().findById(idAccount).orElse(null);
        if (account == null) {
            return null;
        }
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUuid(account.getUuid());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setDisplayName(account.getDisplayName());
        accountDTO.setLocked(account.getLocked());
        accountDTO.setEnabled(account.getEnabled());
        accountDTO.setActivated(account.getActivated());
        accountDTO.setAccountDetailsDTO(accountDetailsService.getInfoDTO(idAccount));
      //  accountDTO.setAccountAddressDTOS(accountAddressService.getInfoDTO(idAccount));
        return accountDTO;
    }

    /**
     * Xóa Tài khoản bởi ID tài khoản
     *
     * @param idAccount id tài khoản
     * @return true: thành công / false: không thành công
     */
    @Transactional
    @CacheEvict(value = TagAccount.name + "getName", key = "#idAccount")
    public boolean deleteById(UUID idAccount) {
        try {
            this.avatarService.deleteAllByParentID(idAccount, Account.class);
          //  this.accountAddressService.deleteAllByParentID(idAccount, Account.class);
            this.accountDetailsService.deleteAllByParentID(idAccount, Account.class);
            this.getRepository().deleteById(idAccount);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Thêm/Chỉnh sửa tài khoản
     *
     * @param dto   thông tin DTO
     * @param isAdd nếu là thêm
     * @return thông tin đã thêm
     * @throws Exception lỗi
     */
    @Transactional
    @CacheEvict(value = TagAccount.name + "getName", key = "#dto." + IRepository.baseIDName)
    public Account saveByDTO(AccountDTO dto, boolean isAdd) throws Exception {
        Account account = this.convertDTO(dto, isAdd);
        if (isAdd) {
            account.setUuid(null);
            account.setUsername(dto.getUsername());
        }
        return getRepository().save(account);
    }

    /**
     * Chuyển đổi DTO tới Model
     *
     * @param accountDTO thông tin DTO
     * @return thông tin Model
     */
    public Account convertDTO(AccountDTO accountDTO, boolean isAdd) throws Exception {
        if (accountDTO.getUsername() == null) {
            throw new Exception("Tên đăng nhập không thể rỗng !");
        }
        if (accountDTO.getUsername().length() < 5) {
            throw new Exception("Tên tài khoản quá ngắn !");
        }
        Account account;
        if (isAdd) {
            account = new Account();
        } else {
            account = getRepository().findById(accountDTO.getUuid()).orElse(null);
        }
        if (account == null) {
            throw new Exception("Khởi tạo dữ liệu lỗi !");
        }
        account.setDisplayName(accountDTO.getDisplayName());
        account.setLocked(accountDTO.getLocked());
        account.setActivated(accountDTO.getActivated());
        account.setEnabled(accountDTO.getEnabled());
        AccountDetailsDTO accountDetailsDTO = accountDTO.getAccountDetailsDTO();
        if (accountDetailsDTO == null) {
            throw new Exception("Thông tin tài khoản không thể rỗng !");
        }
        if (accountDetailsDTO.getName() == null) {
            throw new Exception("Tên không thể rỗng !");
        }
        if (accountDetailsDTO.getEmail() == null) {
            throw new Exception("Email không thể rỗng !");
        }
        if (!ValidationUtils.checkEmail(accountDetailsDTO.getEmail())) {
            throw new Exception("Email không hợp lệ !");
        }
        if (accountDetailsDTO.getPhoneNumber() == null) {
            throw new Exception("Số điện thoại không thể rỗng !");
        }
        if (accountDetailsDTO.getPhoneNumber().length() < 10) {
            throw new Exception("Số điện thoại không hợp lệ !");
        }
        AccountDetails accountDetails;
        if (isAdd) {
            accountDetails = new AccountDetails();
        } else {
            accountDetails = accountDetailsService.getByParentID(accountDTO.getUuid(), Account.class);
        }
        accountDetails.setName(accountDetailsDTO.getName());
        accountDetails.setEmail(accountDetailsDTO.getEmail());
        accountDetails.setPhoneNumber(accountDetailsDTO.getPhoneNumber());
        accountDetails.setAbout(accountDetailsDTO.getAbout());
        // Tạo mối quan hệ
        accountDetails.setAccount(account);
        account.setAccountDetails(accountDetails);
        //
        return account;
    }

    /**
     * Cập nhật lại mật khẩu
     *
     * @param idAccount     id tài khoản
     * @param resetPassword thông tin mật khẩu cần đổi
     * @return true / false
     */
    @Transactional
    public Boolean updatePasswordById(UUID idAccount, AccountResetPassword resetPassword) throws Exception {
        if (resetPassword.getNewPassword() == null) {
            throw new Exception("Mật khẩu mới không thể rỗng !");
        }
        if (resetPassword.getNewPassword().length() < 8) {
            throw new Exception("Mật khẩu quá ngắn !");
        }
        if (resetPassword.getReNewPassword() == null) {
            throw new Exception("Nhập lại mật khẩu không thể rỗng !");
        }
        if (!resetPassword.getReNewPassword().equals(resetPassword.getNewPassword())) {
            throw new Exception("Hai mật khẩu không trùng nhau !");
        }
        return getRepository().updateById(Map.of("password", passwordEncoder.encode(resetPassword.getNewPassword())), idAccount);
    }

    /**
     * Đăng ký thêm tài khoản
     *
     * @param accountRegisterDTO thông tin cần đăng ký
     * @return trả về thông tin tài khoản đã đăng ký
     * @throws Exception lỗi khi không thể đăng ký
     */
    @Transactional
    public Account saveAccountRegister(AccountRegisterDTO accountRegisterDTO) throws Exception {
        if (accountRegisterDTO.getUsername() == null || accountRegisterDTO.getEmail() == null || accountRegisterDTO.getGender() == null ||
                accountRegisterDTO.getPassword() == null || accountRegisterDTO.getRePassword() == null) {
            throw new Exception("Vui lòng nhập đầy đủ thông tin !");
        }
        if (!ValidationUtils.checkEmail(accountRegisterDTO.getEmail())) {
            throw new Exception("Email không hợp lệ !");
        }
        if (accountRegisterDTO.getPhoneNumber().length() < 10) {
            throw new Exception("Số điện thoại không hợp lệ !");
        }
        if (accountRegisterDTO.getUsername().length() < 5) {
            throw new Exception("Tên tài khoản quá ngắn !");
        }
        if (accountRegisterDTO.getPassword().length() < 8) {
            throw new Exception("Mật khẩu quá ngắn !");
        }
        if (!accountRegisterDTO.getPassword().equals(accountRegisterDTO.getRePassword())) {
            throw new Exception("Hai mật khẩu không trùng nhau !");
        }
        AddressDTO addressDTO = accountRegisterDTO.getAddressDTO();
        if (addressDTO == null) {
            throw new Exception("Vui lòng thêm tối thiểu một địa chỉ !");
        }

        Account account = new Account();
        account.setUsername(accountRegisterDTO.getUsername());
        account.setPassword(passwordEncoder.encode(accountRegisterDTO.getPassword()));
        account.setDisplayName(accountRegisterDTO.getName());
        account.setEnabled(true);
        account.setLocked(false);
        account.setActivated(false);
        //
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setName(accountRegisterDTO.getName());
        accountDetails.setEmail(accountRegisterDTO.getEmail());
        accountDetails.setPhoneNumber(accountRegisterDTO.getPhoneNumber());
        accountDetails.setAccount(account);

        // Gán liên kết địa chỉ tài khoản

        // Gán liên kết Thông tin chi tiết của tài khoản
        account.setAccountDetails(accountDetails);
        // Lưu thông tin
        return this.accountAuthorityService.addAccountAndAuthorityInfo(account, Set.of(new AuthorityInfo(Role.USER.name())));
    }

    //================[Vu]===================================

//    /**
//     * Thêm tài khoản cho nhân viên
//     *
//     * @param model thông tin cần lưu
//     * @return thông tin đã lưu {@link UuidCheckDTO}
//     * @author Nguyễn Trần Anh Vũ
//     */
//    @Transactional
//    public MiniAccountDTO saveAccountEmployee(PartnerAddEmployeeDTO model) {
//        Account account = new Account();
//        account.setDisplayName(model.getDisplayName());
//        //account.setUsername(this.getRandomUserName(model.getCodeCompany()));
//        account.setUsername(String.join("_", model.getName(), model.getCodeCompany()));
//        // Generate Random Password
//        String password = RandomStringUtils.randomAlphaNumeric(8);
//        account.setPassword(passwordEncoder.encode(password));
//        // thêm các điều kiện
//        account.setEnabled(true);
//        account.setLocked(false);
//        account.setActivated(true);
//        //
//        AccountDetails accountDetails = new AccountDetails();
//        accountDetails.setEmail(model.getEmail());
//        accountDetails.setName(model.getDisplayName());
//        accountDetails.setPhoneNumber(model.getPhoneNumber());
//        // Tạo liên kết nhiều nhiều
//        AccountAuthority accountAuthority = new AccountAuthority();
//        accountAuthority.setAccount(account);
//        accountAuthority.setAuthority(authorityService.getById(model.getVaiTroUuid()));
//        // TẠo liên kết
//        accountDetails.setAccount(account);
//        account.setAccountDetails(accountDetails);
//        account.setAccountAuthorities(Set.of(accountAuthority));
//        // Lưu thông tin tài khoản
//        Account acc = getRepository().save(account);
//        MiniAccountDTO res = new MiniAccountDTO();
//        res.setId(acc.getUuid());
//        res.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
//        res.setUsername(acc.getUsername());
//        // Tạo dữ liệu cập nhật
//        Map<String, Object> m = Map.of("createdBy", model.getCreatedBy() + "", "createdDate", new Date());
//        // Cập nhật người tạo cho 3 bảng
//        getRepository().updateById(m, acc.getUuid());
//        accountDetailsService.updateById(m, acc.getAccountDetails().getUuid());
//        for (AccountAuthority authority : acc.getAccountAuthorities()) {
//            accountAuthorityService.updateById(m, authority.getUuid());
//        }
//        // Trả về thông tín đã cập nhật
//        return res;
//    }
}