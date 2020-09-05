package com.spring.rest.base.controller;

import com.source.base.controller.BaseController;
import com.source.base.controller.BaseCrudController;
import com.source.base.service.jpa.JpaService;
import com.source.base.utils.RandomStringUtils;
import com.spring.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public abstract class BaseCrudImplController<T extends BaseEntity> implements BaseCrudController<T, UUID> {

    private final JpaService<T, UUID> service;

    protected BaseCrudImplController(JpaService<T, UUID> service) {
        this.service = service;
    }

    protected JpaService<T, UUID> getService() {
        return this.service;
    }

    protected String getCode() {
        String code = RandomStringUtils.randomAlphaNumeric(16);
        T val = getService().getByName("code",code);
        if(val != null){
            code = getCode();
        }
        return code;
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
    public ResponseEntity<Boolean> delete(@Parameter(description = BaseController._id_param + " cần xóa") @RequestParam(name = BaseController._id_param) UUID uuid) throws Exception {
        return ResponseEntity.ok(getService().delete(uuid));
    }

    @Override
    @PostMapping(value = _add, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<T> postAdd(@Parameter(description = "Thông tin cần thêm") T model) throws Exception;

    @Override
    @PutMapping(value = _update, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<T> putUpdate(@Parameter(description = "Thông tin cần sửa") T model) throws Exception;
}