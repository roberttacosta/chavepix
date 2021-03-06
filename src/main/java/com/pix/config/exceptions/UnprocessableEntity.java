package com.pix.config.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
@Getter
@Setter
public class UnprocessableEntity extends BaseRuntimeException {
    private static final long serialVersionUID = -8000326824003042657L;
    private String key;

    public UnprocessableEntity(String key, String value) {
        super(Map.of("key", key, "value", value));
        this.key = key;
    }

    public UnprocessableEntity(String key) {
        super(Map.of("key", key));
        this.key = key;
    }

    @Override
    public String getExceptionKey() {
        return key;
    }
}
