package com.base.dtos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class PageT<T> implements Page<T> {

    private final List<T> content;
    private final long total;

    public PageT(List<T> content) {
        this.content = content;
        this.total = content.size();
    }

    public PageT(List<T> content, long total) {
        this.content = content;
        this.total = total;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public int getNumberOfElements() {
        return content.size();
    }

    @Override
    public long getTotalElements() {
        return total;
    }

    @Override
    public boolean hasContent() {
        return !content.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    @Override
    public int getTotalPages() {
        return 1;
    }

    @Override
    public int getNumber() {
        return 1;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public boolean isLast() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    @Override
    public Pageable nextPageable() {
        return Pageable.unpaged();
    }

    @Override
    public Pageable previousPageable() {
        return Pageable.unpaged();
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return null;
    }
}