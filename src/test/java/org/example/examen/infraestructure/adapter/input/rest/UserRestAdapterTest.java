package org.example.examen.infraestructure.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.examen.application.mapper.PhoneDTOMapper;
import org.example.examen.application.port.input.UserUseCase;
import org.example.examen.domain.model.User;
import org.example.examen.domain.model.dto.UserDTO;
import org.example.examen.infraestructure.adapter.input.rest.data.request.UserCreateRequest;
import org.example.examen.infraestructure.adapter.input.rest.mapper.UserRestMapper;
import org.example.examen.infraestructure.adapter.output.persistence.mapper.PhoneDboMapper;
import org.example.examen.infraestructure.adapter.output.persistence.mapper.UserDboMapper;
import org.example.examen.infraestructure.adapter.output.persistence.repository.PhoneRepository;
import org.example.examen.infraestructure.adapter.output.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestAdapter.class)
class UserRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserUseCase userUseCase;

    @MockBean
    private UserRestMapper userRestMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserDboMapper userDboMapper;

    @MockBean
    private PhoneRepository phoneRepository;

    @MockBean
    private PhoneDboMapper phoneDboMapper;

    @MockBean
    private PhoneDTOMapper phoneDTOMapper;


    @Test
    void testCreateUser_Success() throws Exception {
        UserCreateRequest userRequest = new UserCreateRequest();
        userRequest.setName("test");
        userRequest.setEmail("rbalbis2001@example.com");
        userRequest.setPassword("Abc123456");

        User user = new User();
        UserDTO userDTO = UserDTO.builder()
                .id(UUID.randomUUID())
                .created(LocalDate.now())
                .modified(LocalDate.now())
                .lastLogin(LocalDate.now())
                .token(UUID.randomUUID().toString())
                .active(true)
                .build();

        when(userRestMapper.toUser(userRequest)).thenReturn(user);
        when(userUseCase.createUser(any(User.class))).thenReturn(userDTO);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());
    }
}