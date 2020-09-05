package com.spring.entity.base;


import com.base.source.models.IPictureEntity;
import com.base.source.models.annotation.ReportFieldName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class PictureEntity extends BaseEntity implements IPictureEntity<UUID> {

    @Column
    @ReportFieldName("tên file")
    private String idUpload;

    @Column
    @ReportFieldName("đường dẫn hình ảnh")
    private String picture;

    @Column
    @ReportFieldName("là đường dẫn ngoài")
    private Boolean external;

    @ReportFieldName("Mô tả")
    @Column(columnDefinition = "TEXT")
    private String description;
}