package com.source.base.controller;

import org.springframework.http.ResponseEntity;

public interface BaseController<T, ID> {

    String _id_param = "id";
    String _code_param = "code";

    String _search_param = "search";
    String _search_description = "Tìm môt cột của bảng với các điều kiện: \n" +
            " + Mẫu điều kiện '?1 @?2 (?3|?4) ' '\n" +
            " + Trong đó ?1 là tên trường cần tìm kiếm\n" +
            " + Trong đó ?2 là điều kiện cần lọc\n" +
            " + Trong đó ?3 là kiểu dữ liệu\n" +
            " + Trong đó ?4 là giá trị cần lọc\n" +
            " + Các điều kiện cần lọc (like, 'like'), (nLike, 'not like'), (eq, '='), (ne, '<>'), (gt, '>'), (ge, '>='), (lt, '<'), (le, '<=').\n" +
            " + Các kiểu dữ liệu (string, 'string'), (bool, 'boolean'), (int, 'integer'), (long, 'long'), (float, 'float'), (double, 'double'), (date, 'date'), (uuid, 'UUID').\n" +
            " + Ví dụ 1: name @like (string|Koi) and uuid @eq (uuid|9049e227-19f2-469e-81c6-66a434de8acd) \n" +
            " + Ví dụ 2: name @like (string|alo) or uuid @gt (int|5) \n" +
            " + Ví dụ 3: birthday @gt (date|1995-05-25T00:00:00.000+00:00)";

    String _select_param = "select";
    String _select_description = "Chọn các cột cần hiển thị. Ví Dụ: name,code ";

    String _orderby_param = "orderby";
    String _orderby_description = "Sắp xếp theo tên một cột ('ASC' là mặc định không cần nhập). Ví Dụ: name desc ";

    String _page_param = "page";
    String _page_description = "Chọn số trang cho kết quả được gọi (Trang đầu tiên mặc định là 0). Ví Dụ: 0 ";

    String _size_param = "size";
    String _size_description = "Chọn số lượng kết quả cho một trang cho kết quả được gọi. Ví Dụ: 10 ";

    String _count_param = "count";
    String _count_description = "Yêu cầu Server không cần đếm tổng kết quả (mặc định là true)";

    /**
     * Tìm kiếm
     * @param search     điều kiện tìm kiếm
     * @param select     chọn các cột cần hiển thị
     * @param sort       sắp xếp cột đã chọn
     * @param pageNumber trang số
     * @param pageSize   số lượng muốn lấy
     * @param count      yêu cầu đếm tổng số lượng
     *
     * @return danh sách đã tìm kiếm
     * @throws Exception lỗi khi không tìm kiếm được
     */
    ResponseEntity<?> getAll(String search, String select, String sort, Integer pageNumber, Integer pageSize, Boolean count) throws Exception;
}
