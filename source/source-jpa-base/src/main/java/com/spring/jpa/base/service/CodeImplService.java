package com.spring.jpa.base.service;

import com.source.base.utils.RandomStringUtils;
import com.spring.entity.base.NaturalIdEntity;
import com.spring.jpa.base.repository.BaseRepository;

import java.util.UUID;

public abstract class CodeImplService<T extends NaturalIdEntity> extends BaseImplService<T> {
    protected CodeImplService(BaseRepository<T, UUID> repo) {
        super(repo);
    }

    public String getCode() {
        String code = RandomStringUtils.randomAlphaNumeric(16);
        T val = getRepository().findByName("code",code);
        if(val != null){
            code = getCode();
        }
        return code;
    }
}