package com.pix.config.exceptions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    private String key;
    private String message;

    public ErrorDTO() {
    }

    public ErrorDTO(String key, String message) {
        this.key = key;
        this.message = message;
    }
}
