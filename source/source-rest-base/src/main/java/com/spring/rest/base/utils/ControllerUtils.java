package com.spring.rest.base.utils;

import com.source.base.controller.utils.RequestUtils;
import com.source.base.service.jpa.JpaService;
import com.source.base.service.jpa.ManyToManyService;
import com.spring.base.exception.NotFoundException;
import com.spring.jpa.base.service.WhereServiceSQL;
import com.spring.jpa.base.sql.ParamsSQL;
import com.spring.jpa.base.sql.WhereSQL;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Nguyễn Đình Tạo
 */
@UtilityClass
public class ControllerUtils {

    /**
     * @param i    id cần tìm
     * @param c    các cột cần chọn
     * @param e    lấy dữ liệu
     * @param <T>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    public <T, ID> ResponseEntity<?> getOne(JpaService<T, ID> e, ID i, String c) throws Exception {
        if (c == null) {
            T a = e.getById(i);
            if (a == null) throw new NotFoundException("Không tìm thấy dữ liệu");
            return ResponseEntity.ok(a);
        }
        Map<String,?> b = e.getById(i, RequestUtils.parseSelect(c));
        if (b == null) throw new NotFoundException("Không tìm thấy dữ liệu");
        return ResponseEntity.ok(b);
    }

    /**
     * @param e    lấy dữ liệu
     * @param h    điều kiện cần tìm
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param p    trang số
     * @param z    số lượng cần lấy
     * @param t    yêu cầu đếm
     * @param <T>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    public <T, ID> ResponseEntity<?> getAll(WhereServiceSQL<T, ID> e, String h, String c, String s, Integer p, Integer z, boolean t) {
        if (h == null) {
            if (p != null && z != null) {
                return getAll(e, RequestUtils.parseSelect(c), RequestUtils.parseSort(s), p, z, t);
            }
            return getAll(e, RequestUtils.parseSelect(c), RequestUtils.parseSort(s));
        }
        if (p != null && z != null) {
            return getAll(e, SearchUtils.parse(h), RequestUtils.parseSelect(c), RequestUtils.parseSort(s), p, z, t);
        }
        return getAll(e, SearchUtils.parse(h), RequestUtils.parseSelect(c), RequestUtils.parseSort(s));
    }

    /**
     * @param e    lấy dữ liệu
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param <T>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    private <T, ID> ResponseEntity<?> getAll(JpaService<T, ID> e, String[] c, Sort s) {
        if (s == null && c == null) {
            return ResponseEntity.ok(e.getAll());
        }
        if (c == null) {
            return ResponseEntity.ok(e.getAll(s));
        }
        if (s == null) {
            return ResponseEntity.ok(e.getAll(c));
        }
        return ResponseEntity.ok(e.getAll(s, c));
    }

    /**
     * @param e    lấy dữ liệu
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param p    trang số
     * @param z    số lượng cần lấy
     * @param t    yêu cầu đếm
     * @param <T>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    private <T, ID> ResponseEntity<?> getAll(JpaService<T, ID> e, String[] c, Sort s, int p, int z, boolean t) {
        if (s == null && c == null) {
            return ResponseEntity.ok(e.getAll(PageRequest.of(p, z), t));
        }
        if (c == null) {
            return ResponseEntity.ok(e.getAll(PageRequest.of(p, z, s), t));
        }
        if (s == null) {
            return ResponseEntity.ok(e.getAll(PageRequest.of(p, z), t, c));
        }
        return ResponseEntity.ok(e.getAll(PageRequest.of(p, z, s), t, c));
    }

    /**
     * @param e    lấy dữ liệu
     * @param h    điều kiện cần tìm
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param p    trang số
     * @param z    số lượng cần lấy
     * @param t    yêu cầu đếm
     * @param g    điều kiện tìm kiếm
     * @param b    các tham số tìm kiếm
     * @param <T>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    public <T, ID> ResponseEntity<?> getAll(WhereServiceSQL<T, ID> e, String h, String c, String s, Integer p, Integer z, boolean t, String g, ParamsSQL... b) {
        if (h == null) {
            if (p != null && z != null) {
                return getAll(e, new WhereSQL(List.of(b), g), RequestUtils.parseSelect(c), RequestUtils.parseSort(s), p, z, t);
            }
            return getAll(e, new WhereSQL(List.of(b), g), RequestUtils.parseSelect(c), RequestUtils.parseSort(s));
        }
        WhereSQL w = SearchUtils.parse(h);
        if (w == null) {
            if (p != null && z != null) {
                return getAll(e, new WhereSQL(List.of(b), g), RequestUtils.parseSelect(c), RequestUtils.parseSort(s), p, z, t);
            }
            return getAll(e, new WhereSQL(List.of(b), g), RequestUtils.parseSelect(c), RequestUtils.parseSort(s));
        }
        w.setParam(b);
        w.setSqlWhere(w.getSqlWhere() + " and " + g);
        if (p != null && z != null) {
            return getAll(e, w, RequestUtils.parseSelect(c), RequestUtils.parseSort(s), p, z, t);
        }
        return getAll(e, w, RequestUtils.parseSelect(c), RequestUtils.parseSort(s));
    }

    /**
     * @param e    lấy dữ liệu
     * @param w    điều kiện cần tìm
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param <T>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    private <T, ID> ResponseEntity<?> getAll(WhereServiceSQL<T, ID> e, WhereSQL w, String[] c, Sort s) {
        if (s == null && c == null) {
            return ResponseEntity.ok(e.getAll(w));
        }
        if (c == null) {
            return ResponseEntity.ok(e.getAll(w, s));
        }
        if (s == null) {
            return ResponseEntity.ok(e.getAll(w, c));
        }
        return ResponseEntity.ok(e.getAll(w, s, c));
    }

    /**
     * @param w    điều kiện cần tìm
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param p    trang số
     * @param z    số lượng cần lấy
     * @param t    yêu cầu đếm
     * @param e    lấy dữ liệu
     * @param <T>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    private <T, ID> ResponseEntity<?> getAll(WhereServiceSQL<T, ID> e, WhereSQL w, String[] c, Sort s, int p, int z, boolean t) {
        if (s == null && c == null) {
            return ResponseEntity.ok(e.getAll(w, PageRequest.of(p, z), t));
        }
        if (c == null) {
            return ResponseEntity.ok(e.getAll(w, PageRequest.of(p, z, s), t));
        }
        if (s == null) {
            return ResponseEntity.ok(e.getAll(w, PageRequest.of(p, z), t, c));
        }
        return ResponseEntity.ok(e.getAll(w, PageRequest.of(p, z, s), t, c));
    }

    /**
     * @param e    lấy dữ liệu
     * @param i    id parent
     * @param k    Class parent
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param p    trang số
     * @param z    số lượng cần lấy
     * @param t    yêu cầu đếm
     * @param <T>  entity được map
     * @param <ID> loại id
     * @param <K>  entity parent
     * @return ResponseEntity
     */
    public <T, ID, K> ResponseEntity<?> getAllByParentID(JpaService<T, ID> e, ID i, Class<K> k, String c, String s, Integer p, Integer z, boolean t) {
        if (p != null && z != null) {
            return getAllByParentID(e, i, k, RequestUtils.parseSelect(c), RequestUtils.parseSort(s), p, z, t);
        }
        return getAllByParentID(e, i, k, RequestUtils.parseSelect(c), RequestUtils.parseSort(s));
    }

    /**
     * @param e    lấy dữ liệu
     * @param i    id parent
     * @param k    Class parent
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param <T>  entity được map
     * @param <ID> loại id
     * @param <K>  entity parent
     * @return ResponseEntity
     */
    private <T, ID, K> ResponseEntity<?> getAllByParentID(JpaService<T, ID> e, ID i, Class<K> k, String[] c, Sort s) {
        if (c == null && s == null) {
            return ResponseEntity.ok(e.getAllByParentID(i, k));
        }
        if (c == null) {
            return ResponseEntity.ok(e.getAllByParentID(i, k, s));
        }
        if (s == null) {
            return ResponseEntity.ok(e.getAllByParentID(i, k, c));
        }
        return ResponseEntity.ok(e.getAllByParentID(i, k, s, c));
    }

    /**
     * @param e    lấy dữ liệu
     * @param i    id parent
     * @param k    Class parent
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param p    trang số
     * @param z    số lượng cần lấy
     * @param t    yêu cầu đếm
     * @param <T>  entity được map
     * @param <ID> loại id
     * @param <K>  entity parent
     * @return ResponseEntity
     */
    private <T, ID, K> ResponseEntity<?> getAllByParentID(JpaService<T, ID> e, ID i, Class<K> k, String[] c, Sort s, int p, int z, boolean t) {
        if (c == null && s == null) {
            return ResponseEntity.ok(e.getAllByParentID(i, k, PageRequest.of(p, z), t));
        }
        if (c == null) {
            return ResponseEntity.ok(e.getAllByParentID(i, k, PageRequest.of(p, z, s), t));
        }
        if (s == null) {
            return ResponseEntity.ok(e.getAllByParentID(i, k, PageRequest.of(p, z), t, c));
        }
        return ResponseEntity.ok(e.getAllByParentID(i, k, PageRequest.of(p, z, s), t, c));
    }

    /**
     * @param e    lấy dữ liệu
     * @param id   id cần tìm
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param p    trang số
     * @param z    số lượng cần lấy
     * @param t    yêu cầu đếm
     * @param <J>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    public <J, ID> ResponseEntity<?> getAllJoinTableByID(ManyToManyService<?, ?, J, ID> e, ID id, String c, String s, Integer p, Integer z, boolean t) {
        // Kiểm tra có phân trang hay không
        if (p != null && z != null) {
            return getAllJoinTableByID(e, id, RequestUtils.parseSelect(c), RequestUtils.parseSort(s), p, z, t);
        }
        return getAllJoinTableByID(e, id, RequestUtils.parseSelect(c), RequestUtils.parseSort(s));
    }

    /**
     * @param e    lấy dữ liệu
     * @param i    id cần tìm
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param <J>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    private <J, ID> ResponseEntity<?> getAllJoinTableByID(ManyToManyService<?, ?, J, ID> e, ID i, String[] c, Sort s) {
        if (c == null && s == null) {
            return ResponseEntity.ok(e.getAllJoinTableByID(i));
        }
        if (c == null) {
            return ResponseEntity.ok(e.getAllJoinTableByID(i, s));
        }
        if (s == null) {
            return ResponseEntity.ok(e.getAllJoinTableByID(i, c));
        }
        return ResponseEntity.ok(e.getAllJoinTableByID(i, s, c));
    }

    /**
     * @param e    lấy dữ liệu
     * @param i    id cần tìm
     * @param c    các cột cần chọn
     * @param s    sắp xếp
     * @param p    trang số
     * @param z    số lượng cần lấy
     * @param t    yêu cầu đếm
     * @param <J>  entity được map
     * @param <ID> loại id
     * @return ResponseEntity
     */
    private <J, ID> ResponseEntity<?> getAllJoinTableByID(ManyToManyService<?, ?, J, ID> e, ID i, String[] c, Sort s, int p, int z, boolean t) {
        if (c == null && s == null) {
            return ResponseEntity.ok(e.getAllJoinTableByID(i, PageRequest.of(p, z), t));
        }
        if (c == null) {
            return ResponseEntity.ok(e.getAllJoinTableByID(i, PageRequest.of(p, z, s), t));
        }
        if (s == null) {
            return ResponseEntity.ok(e.getAllJoinTableByID(i, PageRequest.of(p, z), t, c));
        }
        return ResponseEntity.ok(e.getAllJoinTableByID(i, PageRequest.of(p, z, s), t, c));
    }
}