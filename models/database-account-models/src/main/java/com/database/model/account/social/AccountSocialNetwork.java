package com.database.model.account.social;

import com.base.source.models.annotation.ReportFieldJoinName;
import com.base.source.models.annotation.ReportFieldName;
import com.base.source.models.annotation.ReportManyToManyTableName;
import com.database.model.account.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.original.tag.models.account.TagAccount;
import com.original.tag.models.account.TagSocialNetwork;
import com.spring.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ReportManyToManyTableName(tEntity = Account.class, jEntity = SocialNetwork.class)
public class AccountSocialNetwork extends BaseEntity {

    private static final long serialVersionUID = 14111324L;

    @ReportFieldJoinName(TagAccount.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Account account;

    @ReportFieldJoinName(TagSocialNetwork.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private SocialNetwork socialNetwork;

    @ReportFieldName("thông tin kết nối")
    @Column(nullable = false)
    private String connectionInformation;

    @ReportFieldName("đã kích hoạt")
    @Column(nullable = false)
    private Boolean activated;
}