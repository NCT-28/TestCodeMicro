package com.spring.rest.base.controller;

import com.source.base.controller.BaseController;
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
public abstract class BaseSearchImplController<T> implements BaseController<T, UUID> {

    private final WhereServiceSQL<T, UUID> service;

    protected BaseSearchImplController(WhereServiceSQL<T, UUID> service) {
        this.service = service;
    }

    protected WhereServiceSQL<T, UUID> getService() {
        return this.service;
    }

    /**
     * Tìm kiếm
     *
     * @param search     điều kiện tìm kiếm
     * @param select     chọn các cột cần hiển thị
     * @param sort       sắp xếp cột đã chọn
     * @param pageNumber trang số
     * @param pageSize   số lượng muốn lấy
     * @param count      yêu cầu đếm tổng số lượng
     * @return danh sách đã tìm kiếm
     */
    @Override
    @Operation(summary = "Tìm kiếm")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(
            @Parameter(description = _search_description) @RequestParam(name = _search_param, required = false) String search,
            @Parameter(description = _select_description) @RequestParam(name = _select_param) String select,
            @Parameter(description = _orderby_description) @RequestParam(name = _orderby_param, required = false) String sort,
            @Parameter(description = _page_description) @RequestParam(name = _page_param) Integer pageNumber,
            @Parameter(description = _size_description) @RequestParam(name = _size_param) Integer pageSize,
            @Parameter(description = _count_description) @RequestParam(name = _count_param, defaultValue = "true") Boolean count) {
        return ControllerUtils.getAll(service, search, select, sort, pageNumber, pageSize, count);
    }
}