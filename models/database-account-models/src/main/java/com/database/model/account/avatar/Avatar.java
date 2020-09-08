package com.database.model.account.avatar;

import com.base.source.models.annotation.ReportFieldJoinName;
import com.base.source.models.annotation.ReportFieldName;
import com.base.source.models.annotation.ReportTableName;
import com.database.model.account.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.original.tag.models.account.TagAccount;
import com.original.tag.models.account.TagAvatar;

import com.spring.entity.base.PictureEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Table
@Entity
@NoArgsConstructor
@ReportTableName(value = TagAvatar.name, name = TagAvatar.description)
public class Avatar extends PictureEntity {

    @ReportFieldJoinName(TagAccount.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Account account;

    @Column
    @ReportFieldName("là mặc định")
    private Boolean macDinh;

    public Avatar(String idUpload, Account account, Boolean macDinh) {
        setIdUpload(idUpload);
        this.account = account;
        this.macDinh = macDinh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return Objects.equals(getPicture(), avatar.getPicture()) &&
                Objects.equals(getExternal(), avatar.getExternal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPicture(), getExternal());
    }
}