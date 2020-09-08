package com.database.model.account.menu;

import com.base.source.models.annotation.ReportFieldName;
import com.spring.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Data
@MappedSuperclass
public abstract class Menu extends BaseEntity {

    @ReportFieldName("tên menu")
    @Column(nullable = false)
    private String name;

    @ReportFieldName("đường dẫn")
    @Column(unique = true, nullable = false)
    private String path;

    @ReportFieldName("icon")
    @Column
    private String icon;

    @ReportFieldName("mô tả")
    @Column
    private String description;

    @ReportFieldName("đã tắt")
    @Column
    private Boolean disable;

    @ReportFieldName("số thứ tự")
    @Column
    private Integer ordinal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(name, menu.name) &&
                path.equals(menu.path) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(description, menu.description) &&
                Objects.equals(disable, menu.disable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path, icon, description, disable);
    }
}