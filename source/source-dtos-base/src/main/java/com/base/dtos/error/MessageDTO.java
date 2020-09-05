package com.base.dtos.error;

import com.base.enums.ApprovalStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageDTO extends ErrorDTO {

    private String message;

    public MessageDTO(ApprovalStatus status, String message) {
        super(status);
        this.message = message;
    }
}