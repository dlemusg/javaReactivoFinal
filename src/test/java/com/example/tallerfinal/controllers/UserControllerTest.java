package com.example.tallerfinal.controllers;

import com.example.tallerfinal.models.AmountRequest;
import com.example.tallerfinal.models.User;
import com.example.tallerfinal.services.interfaces.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserController.class})
@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    IUserService service;

    @Test
    void crearUser() {
        var user = new User();
        user.setId("user123");
        user.setName("John Doe");
        user.setBalance(1000.0);

        when(service.crear(refEq(user))).thenReturn(Mono.just(user));

        webTestClient.post()
                .uri("/users")
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("user123")
                .jsonPath("$.name").isEqualTo("John Doe")
                .jsonPath("$.balance").isEqualTo(1000.0);
    }

    @Test
    void obteneTodosLosUser() {
        var user1 = new User();
        user1.setId("user123");
        user1.setName("John Doe");
        user1.setBalance(1000.0);

        var user2 = new User();
        user2.setId("user456");
        user2.setName("Jane Doe");
        user2.setBalance(2000.0);

        when(service.obtener()).thenReturn(Flux.just(user1, user2));

        webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo("user123")
                .jsonPath("$[0].name").isEqualTo("John Doe")
                .jsonPath("$[0].balance").isEqualTo(1000.0)
                .jsonPath("$[1].id").isEqualTo("user456")
                .jsonPath("$[1].name").isEqualTo("Jane Doe")
                .jsonPath("$[1].balance").isEqualTo(2000.0);
    }

    @Test
    void obtenerUser() {
        var user = new User();
        user.setId("user123");
        user.setName("John Doe");
        user.setBalance(1000.0);

        when(service.obtenerPorId("user123")).thenReturn(Mono.just(user));

        webTestClient.get()
                .uri("/users/{id}", "user123")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("user123")
                .jsonPath("$.name").isEqualTo("John Doe")
                .jsonPath("$.balance").isEqualTo(1000.0);
    }

    @Test
    void actualizarUserBalance() {
        var user = new User();
        user.setId("user123");
        user.setName("John Doe");
        user.setBalance(1000.0);

        var amountRequest = new AmountRequest();
        amountRequest.setAmount(500.0);

        var updatedUser = new User();
        updatedUser.setId("user123");
        updatedUser.setName("John Doe");
        updatedUser.setBalance(1500.0);

        when(service.obtenerPorId("user123")).thenReturn(Mono.just(user));
        when(service.crear(refEq(updatedUser))).thenReturn(Mono.just(updatedUser));

        webTestClient.put()
                .uri("/users/{id}/balance", "user123")
                .bodyValue(amountRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("user123")
                .jsonPath("$.name").isEqualTo("John Doe")
                .jsonPath("$.balance").isEqualTo(1500.0);
    }
}
