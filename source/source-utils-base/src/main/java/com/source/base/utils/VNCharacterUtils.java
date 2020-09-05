package com.source.base.utils;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

@UtilityClass
public class VNCharacterUtils {

    private final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự'};

    private final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u'};

    public char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public String removeAccentRegex(String s) {
        return Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(Normalizer.normalize(s, Normalizer.Form.NFD)).replaceAll("");
    }

    /**
     * Chuyển đổi tên thành mã
     *
     * @param name tên cần chuyển đổi
     * @return mã
     */
    private String extraCode(String name) {
        String[] s = name.split(" ");
        StringBuilder p = new StringBuilder();
        for (String a : s) {
            p.append(a.trim());
        }
        p = new StringBuilder(StringUtils.trimAllWhitespace(p.toString()));
        p = new StringBuilder(p.toString().replaceAll(" ", "-"));
        p = new StringBuilder(p.toString().replaceAll(",", ""));
        p = new StringBuilder(p.toString().replaceAll("=", ""));
        p = new StringBuilder(p.toString().replaceAll("_", ""));
        p = new StringBuilder(p.toString().replaceAll("'", ""));
        p = new StringBuilder(p.toString().replaceAll("\"", ""));
        p = new StringBuilder(p.toString().replaceAll("&", ""));
        p = new StringBuilder(p.toString().replaceAll(":", ""));
        p = new StringBuilder(p.toString().replaceAll(";", ""));
        p = new StringBuilder(p.toString().replaceAll("#", ""));
        p = new StringBuilder(p.toString().replaceAll("%", ""));
        p = new StringBuilder(p.toString().replaceAll("-", ""));
        p = new StringBuilder(p.toString().replaceAll("~", ""));
        p = new StringBuilder(p.toString().replaceAll("/", ""));
        p = new StringBuilder(p.toString().replaceAll("!", ""));
        p = new StringBuilder(p.toString().replaceAll("@", ""));
        p = new StringBuilder(p.toString().replaceAll("}", ""));
        p = new StringBuilder(p.toString().replaceAll("<", ""));
        p = new StringBuilder(p.toString().replaceAll(">", ""));
        p = new StringBuilder(p.toString().replaceAll("\\?", ""));
        p = new StringBuilder(p.toString().replaceAll("\\{", ""));
        p = new StringBuilder(p.toString().replaceAll("\\|", ""));
        p = new StringBuilder(p.toString().replaceAll("\\.", ""));
        p = new StringBuilder(p.toString().replaceAll("\\*", ""));
        String codeName = removeAccent(p.toString());
        String cutStr = codeName.length() < 100 ? codeName : codeName.substring(0, 100);
        return cutStr + UUID.randomUUID().toString().replaceAll("-", "");
    }
}
