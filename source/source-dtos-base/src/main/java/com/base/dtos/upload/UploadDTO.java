package com.base.dtos.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadDTO {
    private String id;
    private String username;
    private Date created;
    private String path;
    private String fileName;
    private Long size;
    private String type;
    private Boolean allowed;
    private Boolean temp;
    private Date expiredTime;
}