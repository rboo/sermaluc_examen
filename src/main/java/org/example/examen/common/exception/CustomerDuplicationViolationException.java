package org.example.examen.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerDuplicationViolationException extends RuntimeException{

    String message;

    public CustomerDuplicationViolationException(String message) {
        this.message=message;
    }

}
