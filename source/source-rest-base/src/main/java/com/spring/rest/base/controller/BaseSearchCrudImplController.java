package com.spring.rest.base.controller;

import com.source.base.controller.BaseController;
import com.source.base.controller.BaseCrudController;
import com.spring.entity.base.BaseEntity;
import com.spring.jpa.base.service.WhereServiceSQL;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public abstract class BaseSearchCrudImplController<T extends BaseEntity> extends BaseSearchImplController<T> implements BaseCrudController<T, UUID> {

    protected BaseSearchCrudImplController(WhereServiceSQL<T, UUID> service) {
        super(service);
    }

    protected ResponseEntity<T> addDefault(T model) {
        model.setUuid(null);
        return ResponseEntity.ok(getService().add(model));
    }

    protected ResponseEntity<T> updateDefault(T model) throws Exception {
        if (model.getUuid() == null) {
            throw new Exception("Id không thể rỗng !");
        }
        return ResponseEntity.ok(getService().update(model));
    }

    @Override
    @Operation(summary = "Xóa")
    @DeleteMapping(value = _delete, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> delete(@Parameter(description = BaseController._id_param + " cần xóa") @RequestParam(name = BaseController._id_param) UUID uuid) {
        return ResponseEntity.ok(getService().delete(uuid));
    }

    @Override
    @Operation(summary = "Thêm mới")
    @PostMapping(value = _add, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<T> postAdd(@Parameter(description = "Thông tin cần thêm") T model) throws Exception;

    @Override
    @Operation(summary = "Cập nhật")
    @PutMapping(value = _update, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<T> putUpdate(@Parameter(description = "Thông tin cần sửa") T model) throws Exception;
}