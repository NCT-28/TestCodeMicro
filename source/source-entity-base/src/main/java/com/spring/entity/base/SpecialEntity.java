package com.spring.entity.base;


import com.base.source.models.annotation.ReportFieldName;
import com.spring.entity.base.converts.StringToListConvert;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class SpecialEntity extends BaseEntity {

    // Kiểu dữ liệu enums
    @Column
    @ReportFieldName("thuộc tính kiểu Enum")
    private Boolean isEnum;

    @Column
    @ReportFieldName("Các giá trị của kiểu Enum")
    @Convert(converter = StringToListConvert.class)
    private List<String> enums;

    // Kiểu dữ liệu ngày tháng
    @Column
    @ReportFieldName("thuộc tính kiểu Date")
    private Boolean isDate;

    // Kiểu dữ liệu màu sắc
    @Column
    @ReportFieldName("thuộc tính kiểu Màu")
    private Boolean isColor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialEntity that = (SpecialEntity) o;
        return Objects.equals(isEnum, that.isEnum) &&
                Objects.equals(enums, that.enums) &&
                Objects.equals(isDate, that.isDate) &&
                Objects.equals(isColor, that.isColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEnum, enums, isDate, isColor);
    }
}