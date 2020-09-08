package com.database.model.account.menu;

import com.base.source.models.annotation.ReportFieldJoinName;
import com.base.source.models.annotation.ReportTableName;
import com.database.model.account.authority.AuthorityMenuAdministration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.original.tag.models.menu.TagMenuAdministration;
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
        @Index(name = "menu_administration_index", columnList = "uuid,name,icon,ordinal,parent_uuid"),
        @Index(name = "menu_administration_path_index", columnList = "path", unique = true),
})
@ReportTableName(value = TagMenuAdministration.name, name = TagMenuAdministration.description)
public class MenuAdministration extends Menu {

    @ReportFieldJoinName(TagMenuAdministration.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private MenuAdministration parent;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parent")
    private Set<MenuAdministration> children;

    @JsonIgnore
    @OneToMany(targetEntity = AuthorityMenuAdministration.class, cascade = CascadeType.ALL, mappedBy = "menuAdministration")
    private Set<AuthorityMenuAdministration> authorityMenuAdministrations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MenuAdministration that = (MenuAdministration) o;
        return Objects.equals(getPath(), that.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPath());
    }
}