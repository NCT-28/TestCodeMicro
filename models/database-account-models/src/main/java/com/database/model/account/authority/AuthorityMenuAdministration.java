package com.database.model.account.authority;

import com.base.source.models.annotation.ReportFieldJoinName;
import com.base.source.models.annotation.ReportManyToManyTableName;
import com.database.model.account.menu.MenuAdministration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.original.tag.models.account.TagAuthority;
import com.original.tag.models.menu.TagMenuAdministration;
import com.spring.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ReportManyToManyTableName(tEntity = Authority.class, jEntity = MenuAdministration.class)
public class AuthorityMenuAdministration extends BaseEntity {

    @ReportFieldJoinName(TagAuthority.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Authority authority;

    @ReportFieldJoinName(TagMenuAdministration.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private MenuAdministration menuAdministration;
}