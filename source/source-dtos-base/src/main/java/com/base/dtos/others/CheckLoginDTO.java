package com.base.dtos.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckLoginDTO {
    private UUID id;
    private String password;
    private Boolean enabled;
    private Boolean locked;
}