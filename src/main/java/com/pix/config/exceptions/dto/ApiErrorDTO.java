package com.pix.config.exceptions.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class ApiErrorDTO {
    private Date timestamp;
    private Integer status;
    private String code;
    private Set<ErrorDTO> errors;

    public ApiErrorDTO() {
    }

    public ApiErrorDTO(Date timestamp, Integer status, String code, Set<ErrorDTO> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
        this.errors = errors;
    }
}
