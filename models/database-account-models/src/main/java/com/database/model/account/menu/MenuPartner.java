package com.database.model.account.menu;

import com.base.source.models.annotation.ReportFieldJoinName;
import com.base.source.models.annotation.ReportTableName;
import com.database.model.account.authority.AuthorityMenuPartner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.original.tag.models.menu.TagMenuPartner;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(indexes = {
        @Index(name = "menu_partner_index", columnList = "uuid,name,icon,ordinal,parent_uuid"),
        @Index(name = "menu_partner_path_index", columnList = "path", unique = true),
})
@ReportTableName(value = TagMenuPartner.name, name = TagMenuPartner.description)
public class MenuPartner extends Menu {

    @ReportFieldJoinName(TagMenuPartner.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private MenuPartner parent;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parent")
    private Set<MenuPartner> children;

    @JsonIgnore
    @OneToMany(targetEntity = AuthorityMenuPartner.class, cascade = CascadeType.ALL, mappedBy = "menuPartner")
    private Set<AuthorityMenuPartner> authorityMenuPartners;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MenuPartner that = (MenuPartner) o;
        return Objects.equals(getPath(), that.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPath());
    }
}