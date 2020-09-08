package com.database.dto.menu;

import com.base.dtos.jpa.BaseEntityDTO;
import com.base.dtos.jpa.ParentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuClientDTO extends BaseEntityDTO implements ParentDTO<UUID> {

    public MenuClientDTO(UUID id, String name, String path, String icon, Integer ordinal, UUID uuidParent) {
        this.setUuid(id);
        this.name = name;
        this.path = path;
        this.icon = icon;
        this.ordinal = ordinal;
        this.uuidParent = uuidParent;
        this.children = null;
    }

    private UUID uuidParent;

    private String name;

    private String path;

    private String icon;

    private Integer ordinal;

    private List<MenuClientDTO> children;

    @Override
    public UUID getIDParent() {
        return uuidParent;
    }

    @Override
    public <K extends ParentDTO<UUID>> void setChildren(Collection<K> collection) {
        this.children = (List<MenuClientDTO>) collection;
    }
}