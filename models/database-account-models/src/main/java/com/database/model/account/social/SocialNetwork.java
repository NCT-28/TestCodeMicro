package com.database.model.account.social;

import com.base.source.models.annotation.ReportTableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.original.tag.models.account.TagSocialNetwork;
import com.spring.entity.base.DescriptionEntity;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@Data
@ReportTableName(value = TagSocialNetwork.name, name = TagSocialNetwork.description)
public class SocialNetwork extends DescriptionEntity {

    @JsonIgnore
    @OneToMany(targetEntity = AccountSocialNetwork.class, cascade = CascadeType.ALL, mappedBy = "socialNetwork")
    private Set<AccountSocialNetwork> accountSocialNetworks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialNetwork socialNetwork = (SocialNetwork) o;
        return getName().equals(socialNetwork.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}