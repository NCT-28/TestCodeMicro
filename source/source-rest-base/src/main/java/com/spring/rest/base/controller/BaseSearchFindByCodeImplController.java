package com.spring.rest.base.controller;

import com.source.base.controller.BaseController;
import com.source.base.controller.BaseFindByCodeControler;
import com.source.base.controller.utils.RequestUtils;
import com.spring.entity.base.IdentifierEntity;
import com.spring.jpa.base.service.WhereServiceSQL;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public abstract class BaseSearchFindByCodeImplController<T extends IdentifierEntity> extends BaseSearchImplController<T> implements BaseFindByCodeControler<T> {
    protected BaseSearchFindByCodeImplController(WhereServiceSQL<T, UUID> service) {
        super(service);
    }
    /**
     * Tìm kiếm theo mã
     *
     * @param code   mã cần tìm
     * @param select chọn các cột cần hiển thị
     * @return Dữ liệu đã tìm kiếm
     */
    @Override
    @Operation(summary = _summary)
    @GetMapping(value = _find, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByCode(@Parameter(description = _id_description) @RequestParam(name = BaseController._code_param) String code,
                                 @Parameter(description = _select_description) @RequestParam(name = _select_param) String select) {
        if (select == null) {
            return ResponseEntity.ok(getService().getByName("code", code));
        }
        return ResponseEntity.ok(getService().getByName("code", code, RequestUtils.parseSelect(select)));
    }
}
