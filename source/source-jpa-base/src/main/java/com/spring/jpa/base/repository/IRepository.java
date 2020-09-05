package com.spring.jpa.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepository<T, ID> extends JpaRepository<T, ID> {
    String baseIDName = "uuid";
}