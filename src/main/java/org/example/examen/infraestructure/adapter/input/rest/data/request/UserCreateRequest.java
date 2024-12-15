package org.example.examen.infraestructure.adapter.input.rest.data.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.examen.domain.model.annotation.ValidPassword;
import org.example.examen.domain.model.dto.PhoneDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @NotEmpty(message = "Debe ingresar un nombre")
    private String name;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Debe ingresar un correo válido")
    private String email;
    @ValidPassword
    private String password;
    private List<PhoneDTO> phones;
}
