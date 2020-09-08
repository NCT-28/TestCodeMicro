package com.database.model.account.authority;

import com.base.source.models.annotation.ReportFieldJoinName;
import com.base.source.models.annotation.ReportManyToManyTableName;
import com.database.model.account.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.original.tag.models.account.TagAccount;
import com.original.tag.models.account.TagAuthority;

import com.spring.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ReportManyToManyTableName(tEntity = Account.class, jEntity = Authority.class)
public class AccountAuthority extends BaseEntity {

    @ReportFieldJoinName(TagAccount.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Account account;

    @ReportFieldJoinName(TagAuthority.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Authority authority;

    public AccountAuthority(Account account, Authority authority) {
        this.account = account;
        this.authority = authority;
    }
}