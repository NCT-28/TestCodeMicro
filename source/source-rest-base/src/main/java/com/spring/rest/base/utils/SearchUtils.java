package com.spring.rest.base.utils;

import com.base.enums.Ope;
import com.spring.jpa.base.sql.ParamsSQL;
import com.spring.jpa.base.sql.WhereSQL;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyễn Đình Tạo
 */
@UtilityClass
public class SearchUtils {

    /**
     * Không có tiền tố
     *
     * @param s chuỗi tìm kiếm
     * @return thông tin {@link WhereSQL}
     */
    public WhereSQL parse(String s) {
        return where(null, s);
    }

    /**
     * Có tiền tố
     *
     * @param p tiền tố
     * @param s chuỗi tìm kiếm
     * @return thông tin {@link WhereSQL}
     */
    public WhereSQL parse(String p, String s) {
        return where(p, s);
    }

    /**
     * @param p tiền tố
     * @param s chuỗi tìm kiếm
     * @return thông tin {@link WhereSQL}
     */
    private WhereSQL where(String p, String s) {
        StringBuilder sql = new StringBuilder();
        List<ParamsSQL> k = new ArrayList<>();
        while (s.contains(" and ") || s.contains(" or ")) {
            int and = s.indexOf(" and ");
            int or = s.indexOf(" or ");
            if (or == -1) {
                assign(p, sql, k, s, " and ");
                s = s.substring(s.indexOf(" and ") + 5);
            } else if (and == -1) {
                assign(p, sql, k, s, " or ");
                s = s.substring(s.indexOf(" or ") + 4);
            } else if (and < or) {
                assign(p, sql, k, s, " and ");
                s = s.substring(s.indexOf(" and ") + 5);
            } else {
                assign(p, sql, k, s, " or ");
                s = s.substring(s.indexOf(" or ") + 4);
            }
        }
        ParamsSQL f = operation(p, s.trim());
        if (f == null) {
            return null;
        }
        k.add(f);
        return new WhereSQL(k, sql + f.getSql());
    }

    /**
     * @param p tiền tố
     * @param k sql
     * @param h đối số
     * @param s chuỗi tìm kiếm
     * @param l điều kiện
     */
    private void assign(String p, StringBuilder k, List<ParamsSQL> h, String s, String l) {
        ParamsSQL f = operation(p, s.substring(0, s.indexOf(l)));
        if (f != null) {
            k.append(f.getSql()).append(l);
            h.add(f);
        }
    }

    /**
     * @param p tiền tố
     * @param s chuỗi tìm kiếm
     * @return info {@link ParamsSQL}
     */
    private ParamsSQL operation(String p, String s) {
        if (s.contains(" @like ")) {
            return search(p, s, " @like ", Ope.like, " like ");
        }
        if (s.contains(" @nLike ")) {
            return search(p, s, " @nLike ", Ope.notlike, " not like ");
        }
        if (s.contains(" @eq ")) {
            return search(p, s, " @eq ", Ope.eq, " = ");
        }
        if (s.contains(" @ne ")) {
            return search(p, s, " @ne ", Ope.ne, " <> ");
        }
        if (s.contains(" @gt ")) {
            return search(p, s, " @gt ", Ope.gt, " > ");
        }
        if (s.contains(" @ge ")) {
            return search(p, s, " @ge ", Ope.ge, " >= ");
        }
        if (s.contains(" @lt ")) {
            return search(p, s, " @lt ", Ope.lt, " < ");
        }
        if (s.contains(" @le ")) {
            return search(p, s, " @le ", Ope.le, " <= ");
        }
        return null;
    }

    /**
     * @param p tiền tố
     * @param s chuỗi tìm kiếm
     * @param c chuỗi cần thay thế
     * @param o toán tử
     * @param r chuỗi thay thế toán tử
     * @return info {@link ParamsSQL}
     */
    private ParamsSQL search(String p, String s, String c, Ope o, String r) {
        String[] a = s.split(c);
        String t = a[1].trim();
        if (t.startsWith("(") && t.endsWith(")")) {
            // Thay đổi giá trị của chuổi SQL
            s = s.replace(c, r);
            String val = t.substring(t.indexOf("|") + 1, t.indexOf(")"));
            String type = t.substring(1, t.indexOf("|"));
            if (p == null && type.equals("string")) {
                s = s.replace(a[0].trim(), "LOWER("+a[0].trim()+")");
                val = val.toLowerCase();
            }
            String key = a[0].replace(".", "_").trim();
            // Tạo một lớp ParamsSQL chứa các tham số đã Parse
            return new ParamsSQL(
                    key, o, type, val,
                    (p == null ? "" : p + ".") + s.substring(0, s.indexOf(" (")) + " :" + key
            );
        }
        return null;
    }
}