package org.example.examen.domain.model;


import lombok.*;
import org.example.examen.domain.model.dto.PhoneDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate created;
    private LocalDate modified;
    private LocalDate lastLogin;
    private String token;
    private boolean active;
    private List<PhoneDTO> phones;
}
