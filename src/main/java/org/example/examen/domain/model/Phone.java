package org.example.examen.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;
    private Long userId;
}
