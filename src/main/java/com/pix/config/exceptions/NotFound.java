package com.pix.config.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Setter
public class NotFound extends BaseRuntimeException {
    private static final long serialVersionUID = 3363461526683247402L;
    private String key;

    public NotFound(String key, String value) {
        super(Map.of("key", key, "value", value));
        this.key = key;
    }

    public NotFound(String key) {
        super(Map.of("key", key));
        this.key = key;
    }

    @Override
    public String getExceptionKey() {
        return key;
    }
}
