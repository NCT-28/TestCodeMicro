package com.database.model.account;

import com.base.source.models.annotation.ReportFieldName;
import com.base.source.models.annotation.ReportOneToOneTableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.original.tag.models.account.TagAccount;
import com.original.tag.models.account.TagAccountDetails;
import com.spring.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
@ReportOneToOneTableName(target = Account.class, value = TagAccountDetails.name, name = TagAccountDetails.description,
        description = "Bảng chi tiết tài khoản liên kết với bảng " + TagAccount.name)
public class AccountDetails extends BaseEntity {

    @JsonIgnore
    @ReportFieldName(TagAccount.description)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Account account;

    @Column(columnDefinition = "TEXT")
    @ReportFieldName("Thông tin thêm")
    private String about;

    @Column
    @ReportFieldName("họ và tên")
    private String name;

    @Column
    @ReportFieldName("email")
    private String email;

    @Column
    @ReportFieldName("số điện thoại")
    private String phoneNumber;
}