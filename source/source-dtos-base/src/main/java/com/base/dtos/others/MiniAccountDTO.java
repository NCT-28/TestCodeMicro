package com.base.dtos.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiniAccountDTO {
    private UUID id;
    private String username;
    private String password;
}
