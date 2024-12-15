package org.example.examen.infraestructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "usr")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 20)
    private String password;

    @Column
    private LocalDate created;

    @Column
    private LocalDate modified;

    @Column(name = "last_login")
    private LocalDate lastLogin;

    @Column(length = 50)
    private String token;

    @Column
    private boolean active;

    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones;*/

}
