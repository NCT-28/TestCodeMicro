package com.spring.entity.base;

import com.base.source.models.annotation.ReportFieldName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class CatalogEntity extends BaseEntity {

    // Nếu là kiểu dữ liệu danh mục
    @Column(nullable = false)
    @ReportFieldName(value = "Server lưu trữ bảng danh mục", description = "Nếu thuộc tính là danh mục")
    private String serverName;

    @Column(nullable = false)
    @ReportFieldName(value = "tên bảng danh mục", description = "Nếu thuộc tính là danh mục")
    private String nameTable;

    @Column(nullable = false)
    @ReportFieldName(value = "cột làm id", description = "Nếu thuộc tính là danh mục")
    private String columnId;

    @Column(nullable = false)
    @ReportFieldName(value = "cột để hiển thị", description = "Nếu thuộc tính là danh mục")
    private String columnDisplay;

    @Column
    @ReportFieldName(value = "cột để hiển thị mã", description = "Nếu thuộc tính là danh mục")
    private String columnCode;

    @Column
    @ReportFieldName(value = "cột để hiển thị thêm 1", description = "Nếu thuộc tính là danh mục")
    private String columnDisplayOther1;

    @Column
    @ReportFieldName(value = "cột để hiển thị thêm 2", description = "Nếu thuộc tính là danh mục")
    private String columnDisplayOther2;

    @Column
    @ReportFieldName(value = "cột để hiển thị thêm", description = "Nếu thuộc tính là danh mục dạng cây")
    private String columnParentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogEntity that = (CatalogEntity) o;
        return Objects.equals(serverName, that.serverName) &&
                Objects.equals(nameTable, that.nameTable) &&
                Objects.equals(columnId, that.columnId) &&
                Objects.equals(columnDisplay, that.columnDisplay) &&
                Objects.equals(columnCode, that.columnCode) &&
                Objects.equals(columnDisplayOther1, that.columnDisplayOther1) &&
                Objects.equals(columnDisplayOther2, that.columnDisplayOther2) &&
                Objects.equals(columnParentId, that.columnParentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverName, nameTable, columnId, columnDisplay, columnCode, columnDisplayOther1, columnDisplayOther2, columnParentId);
    }
}