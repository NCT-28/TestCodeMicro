//package com.service.account.account.impl;
//
//import com.base.dtos.others.MiniAccountDTO;
//import com.database.dto.constants.Account;
//import com.database.dto.partner.PartnerAddEmployeeDTO;
//import com.original.rest.api.init.AccountServer;
//import com.original.tag.models.account.TagAccount;
//import com.source.base.controller.constants.Path;
//import com.source.base.service.rest.BaseImplRestService;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.UUID;
//
//public class AccountServiceRest extends BaseImplRestService<Account, UUID> {
//    public AccountServiceRest(RestTemplate r) {
//        super(r, Account.class, AccountServer.host + Path.EXCHANGE_URL + "/" + TagAccount.name, AccountServer.user, AccountServer.pass);
//    }
//
//    public MiniAccountDTO addAccount(PartnerAddEmployeeDTO dto) {
//        return this.postForEntity("/add", dto, MiniAccountDTO.class);
//    }
//
//    public void deleteAccount(UUID idAccount) {
//        this.delete(idAccount);
//    }
//}