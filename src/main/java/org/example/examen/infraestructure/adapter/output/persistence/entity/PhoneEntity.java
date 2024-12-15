package org.example.examen.infraestructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "phone")
@Data
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String number;

    @Column(length = 1)
    private String cityCode;

    @Column(length = 2)
    private String countryCode;

    @Column(name = "user_id")
    private Long userId;

    /*@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;*/
}
