package com.source.base.controller;

import org.springframework.http.ResponseEntity;

public interface BaseCrudController<T, ID> {

    String _add = "add";
    String _update = "update";
    String _delete = "delete";

    ResponseEntity<T> postAdd(T model) throws Exception;

    ResponseEntity<T> putUpdate(T model) throws Exception;

    ResponseEntity<Boolean> delete(ID uuid) throws Exception;
}