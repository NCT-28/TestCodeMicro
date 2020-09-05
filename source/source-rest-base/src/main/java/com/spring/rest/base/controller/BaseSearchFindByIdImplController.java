package com.spring.rest.base.controller;

import com.source.base.controller.BaseController;
import com.source.base.controller.BaseFindByIdController;
import com.spring.entity.base.BaseEntity;
import com.spring.jpa.base.service.WhereServiceSQL;
import com.spring.rest.base.utils.ControllerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * @param <T>
 * @author Nguyễn Đình Tạo
 */
public abstract class BaseSearchFindByIdImplController<T extends BaseEntity> extends BaseSearchImplController<T> implements BaseFindByIdController<T, UUID> {

    protected BaseSearchFindByIdImplController(WhereServiceSQL<T, UUID> service) {
        super(service);
    }

    /**
     * Tìm kiếm theo id
     *
     * @param uuid   id cần tìm
     * @param select chọn các cột cần hiển thị
     * @return Dữ liệu đã tìm kiếm
     */
    @Override
    @Operation(summary = _summary)
    @GetMapping(value = _find, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@Parameter(description = _id_description) @RequestParam(name = BaseController._id_param) UUID uuid,
                                 @Parameter(description = _select_description) @RequestParam(name = _select_param) String select) throws Exception {
        return ControllerUtils.getOne(getService(), uuid, select);
    }
}