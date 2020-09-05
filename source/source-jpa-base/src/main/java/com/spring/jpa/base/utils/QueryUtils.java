package com.spring.jpa.base.utils;

import com.spring.jpa.base.repository.IRepository;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Nguyễn Đình Tạo
 */
@UtilityClass
public class QueryUtils {

    /**
     * Chuyển đổi một mảng các cột thành chuổi {@link String} với dấu phân cách
     *
     * @param a mảng các cột
     * @return chuổi được chuyển đổi
     */
    private String arrayToDelimiter(String[] a) {
        return String.join(",", a);
    }

    /**
     * Chuyển đổi một mảng các cột thành chuổi {@link String} với dấu phân cách và tiền tố
     *
     * @param p tiền tố
     * @param a mảng các cột
     * @return chuổi được chuyển đổi
     */
    private String arrayToDelimiter(String p, String[] a) {
        return p + "." + String.join("," + p + ".", a);
    }

    /**
     * Tìm tên biến theo loại Class<K>
     *
     * @param t   Class cần tìm
     * @param k   Loại class của biến cần tìm
     * @param <T> Loại cần tìm
     * @param <K> biến class cần tìm
     * @return tên biến
     */
    public <T, K> String GetFieldNameByTypeClass(Class<T> t, Class<K> k) {
        for (Field f : t.getDeclaredFields()) {
            if (f.getType().equals(k)) {
                return f.getName();
            }
        }
        return null;
    }

    /**
     * lấy package entity
     *
     * @param t   class entity
     * @param <T> entity
     * @return tên package
     */
    public <T> String GetNameEntity(Class<T> t) {
        return t.getPackageName() + "." + t.getSimpleName();
    }

    /**
     * Chuyển đổi {@link Sort} tới chuỗi truy vấn sắp xếp
     *
     * @param s sắp xếp
     * @return chuỗi truy vấn
     */
    private String sortToString(Sort s) {
        return s.stream()
                .map(m -> m.getProperty() + " " + (m.isAscending() ? "ASC" : "DESC"))
                .collect(Collectors.joining(","));
    }

    /**
     * @param c các cột cần chọn
     * @return câu query select các cột cần chọn
     */
    public String querySelect(String c) {
        if (c != null) {
            return "select " + c + " ";
        }
        return "";
    }

    /**
     * @param c các cột cần chọn
     * @return câu query select các cột cần chọn
     */
    public String querySelect(String... c) {
        if (c != null) {
            if (c.length == 1) {
                return querySelect(c[0]);
            }
            if (c.length > 1) {
                return "select " + arrayToDelimiter(c) + " ";
            }
        }
        return "";
    }

    /**
     * @param p (nếu có) tiền tố ứng với bảng cần chọn
     * @param c cột cần chọn
     * @return câu query select các cột cần chọn
     */
    public String querySelect(String p, String c) {
        if (c != null) {
            return "select " + p + "." + c + " ";
        }
        return "select " + p + " ";
    }

    /**
     * @param p (nếu có) tiền tố ứng với bảng cần chọn
     * @param c các cột cần chọn
     * @return câu query select các cột cần chọn
     */
    public String querySelect(String p, String... c) {
        if (c != null) {
            if (c.length == 1) {
                return querySelect(p, c[0]);
            }
            if (c.length > 1) {
                return "select " + arrayToDelimiter(p, c) + " ";
            }
        }
        return "select " + p + " ";
    }

    /**
     * Sinh chuỗi truy vấn chọn theo các cột map với DTO
     *
     * @param d   class DTO
     * @param c   các cột cần chọn
     * @param <D> loại DTO
     * @return chuỗi truy vấn
     */
    public <D> String querySelect(Class<D> d, String... c) {
        if (c != null) {
            if (c.length == 1) {
                return "select new " + GetNameEntity(d) + "(" + c[0] + ") ";
            }
            if (c.length > 1) {
                return "select new " + GetNameEntity(d) + "(" + arrayToDelimiter(c) + ") ";
            }
        }
        return "";
    }

    /**
     * Sinh chuỗi truy vấn chọn theo các cột map với DTO
     *
     * @param p   (nếu có) tiền tố ứng với bảng cần chọn
     * @param d   class DTO
     * @param c   các cột cần chọn
     * @param <D> loại DTO
     * @return chuỗi truy vấn
     */
    public <D> String querySelect(String p, Class<D> d, String... c) {
        if (c != null) {
            if (c.length == 1) {
                return "select new " + GetNameEntity(d) + "(" + p + "." + c[0] + ") ";
            }
            if (c.length > 1) {
                return "select new " + GetNameEntity(d) + "(" + arrayToDelimiter(p, c) + ") ";
            }
        }
        return "";
    }

    /**
     * Sinh chuỗi Xắp sếp
     *
     * @param s sắp xếp
     * @return chuỗi truy vấn
     */
    private String querySort(Sort s) {
        if (s != null) {
            if (s.isSorted()) {
                return " order by " + sortToString(s);
            }
        }
        return "";
    }

    /**
     * Sinh chuỗi Xắp sếp
     *
     * @param p tiền tố
     * @param s sắp xếp
     * @return chuỗi truy vấn
     */
    private String querySort(String p, Sort s) {
        if (s != null) {
            if (s.isSorted()) {
                return " order by " + p + "." + sortToString(s);
            }
        }
        return "";
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp
     *
     * @param q câu lệnh điều kiện
     * @param s sắp xếp
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String q, Sort s) {
        return q + querySort(s);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp
     *
     * @param q câu lệnh điều kiện
     * @param p phân trang
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String q, Pageable p) {
        return generateStringQuerySort(q, p.getSort());
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp
     *
     * @param q câu lệnh điều kiện
     * @param s sắp xếp
     * @param c cột cần chọn
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String q, Sort s, String c) {
        return querySelect(c) + generateStringQuerySort(q, s);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp
     *
     * @param q câu lệnh điều kiện
     * @param p phân trang
     * @param c cột cần chọn
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String q, Pageable p, String c) {
        return querySelect(c) + generateStringQuerySort(q, p);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp
     *
     * @param q câu lệnh điều kiện
     * @param s sắp xếp
     * @param c các cột cần chọn
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String q, Sort s, String... c) {
        return querySelect(c) + generateStringQuerySort(q, s);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp
     *
     * @param q câu lệnh điều kiện
     * @param p phân trang
     * @param c các cột cần chọn
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String q, Pageable p, String... c) {
        return querySelect(c) + generateStringQuerySort(q, p);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp có tiền tố
     *
     * @param t tiền tố
     * @param q câu lệnh điều kiện
     * @param s sắp xếp
     * @param c cột cần chọn
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String t, String q, Sort s, String c) {
        return querySelect(t, c) + q + querySort(t, s);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp có tiền tố
     *
     * @param t tiền tố
     * @param q câu lệnh điều kiện
     * @param s sắp xếp
     * @param c các cột cần chọn
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String t, String q, Sort s, String... c) {
        return querySelect(t, c) + q + querySort(t, s);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp có tiền tố
     *
     * @param t tiền tố
     * @param q câu lệnh điều kiện
     * @param p phân trang
     * @param c cột cần chọn
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String t, String q, Pageable p, String c) {
        return querySelect(t, c) + q + querySort(t, p.getSort());
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp có tiền tố
     *
     * @param t tiền tố
     * @param q câu lệnh điều kiện
     * @param p phân trang
     * @param c các cột cần chọn
     * @return chuỗi truy vấn
     */
    public String generateStringQuerySort(String t, String q, Pageable p, String... c) {
        return querySelect(t, c) + q + querySort(t, p.getSort());
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp
     *
     * @param q   câu lệnh điều kiện
     * @param s   sắp xếp
     * @param d   class DTO
     * @param c   các cột cần chọn
     * @param <D> loại DTO
     * @return chuỗi truy vấn
     */
    public <D> String generateStringQuerySort(String q, Sort s, Class<D> d, String... c) {
        return querySelect(d, c) + generateStringQuerySort(q, s);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp
     *
     * @param q   câu lệnh điều kiện
     * @param p   phân trang
     * @param d   class DTO
     * @param c   các cột cần chọn
     * @param <D> loại DTO
     * @return chuỗi truy vấn
     */
    public <D> String generateStringQuerySort(String q, Pageable p, Class<D> d, String... c) {
        return generateStringQuerySort(q, p.getSort(), d, c);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp có tiền tố
     *
     * @param t   tiền tố
     * @param q   câu lệnh điều kiện
     * @param s   sắp xếp
     * @param d   class DTO
     * @param c   các cột cần chọn
     * @param <D> loại DTO
     * @return chuỗi truy vấn
     */
    public <D> String generateStringQuerySort(String t, String q, Sort s, Class<D> d, String... c) {
        return querySelect(t, d, c) + q + querySort(t, s);
    }

    /**
     * Sinh chuỗi chọn các Cột và Xắp sếp có tiền tố
     *
     * @param t   tiền tố
     * @param q   câu lệnh điều kiện
     * @param p   phân trang
     * @param d   class DTO
     * @param c   các cột cần chọn
     * @param <D> loại DTO
     * @return chuỗi truy vấn
     */
    public <D> String generateStringQuerySort(String t, String q, Pageable p, Class<D> d, String... c) {
        return generateStringQuerySort(t, q, p.getSort(), d, c);
    }

    /**
     * Sinh chuỗi truy vấn đếm tổng số kết quả
     *
     * @param q câu lệnh điều kiện
     * @return câu truy vấn
     */
    public String queryCount(String q) {
        return "select count(" + IRepository.baseIDName + ") " + q;
    }

    /**
     * Sinh chuỗi truy vấn đếm tổng số kết quả có tiền tố
     *
     * @param p tiền tố
     * @param q câu lệnh điều kiện
     * @return câu truy vấn
     */
    public String queryCount(String p, String q) {
        return "select count(" + p + "." + IRepository.baseIDName + ") " + q;
    }

    /**
     * Gán phân trang cho Query Hibernate
     *
     * @param q Query
     * @param p phần trang
     */
    public Query setPageable(Query q, Pageable p) {
        if (p.isPaged()) {
            q.setFirstResult((int) p.getOffset()).setMaxResults(p.getPageSize());
        }
        return q;
    }

    /**
     * Gán phân trang cho Query Hibernate
     *
     * @param q Query
     * @param p phần trang
     */
    public <T> TypedQuery<T> setPageable(TypedQuery<T> q, Pageable p) {
        if (p.isPaged()) {
            q.setFirstResult((int) p.getOffset()).setMaxResults(p.getPageSize());
        }
        return q;
    }

    /**
     * @param q Query truy vấn
     * @param c cột ứng với kết quả trong Query
     * @return đối tượng JSON chứa dữ liệu các cột
     */
    public Map<String, ?> queryToObject(Query q, String c) {
        return Map.of(c.trim(), q.getSingleResult());
    }

    /**
     * @param q Query truy vấn
     * @param c các cột ứng với kết quả trong Query
     * @return đối tượng JSON chứa dữ liệu các cột
     */
    public Map<String, ?> queryToObject(Query q, String... c) {
        if (c.length == 1) {
            return queryToObject(q, c[0]);
        }
        Map<String, Object> m = new HashMap<>();
        for (int i = 0; i < c.length; i++) {
            m.put(c[i].trim(), ((Object[]) q.getSingleResult())[i]);
        }
        return m;
    }

    /**
     * @param q Query truy vấn
     * @param c cột ứng với kết quả trong Query
     * @return danh sách đối tượng JSON chứa dữ liệu các cột
     */
    public List<Map<String, Object>> queryToList(Query q, String c) {
        List<Map<String, Object>> l = new ArrayList<>();
        for (Object x : q.getResultList()) {
            l.add(Map.of(c.trim(), x));
        }
        return l;
    }

    /**
     * @param q Query truy vấn
     * @param c các cột ứng với kết quả trong Query
     * @return danh sách đối tượng JSON chứa dữ liệu các cột
     */
    public List<Map<String, Object>> queryToList(Query q, String... c) {
        if (c.length == 1) {
            return queryToList(q, c[0]);
        }
        List<Map<String, Object>> l = new ArrayList<>();
        for (Object x : q.getResultList()) {
            Map<String, Object> m = new HashMap<>();
            for (int i = 0; i < c.length; i++) {
                m.put(c[i].trim(), ((Object[]) x)[i]);
            }
            l.add(m);
        }
        return l;
    }
}