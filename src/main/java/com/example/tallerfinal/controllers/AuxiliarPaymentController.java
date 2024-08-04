package com.example.tallerfinal.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/payment")
public class AuxiliarPaymentController {

    /**
     * Valida el pago para un monto específico y un usuario determinado.
     *
     * @param amount El monto del pago a validar.
     * @param userId El ID del usuario para el cual se realiza la validación del pago.
     * @return Un {@link Mono} que contiene un valor booleano indicando el resultado de la validación.
     *         {@code true} si la validación fue exitosa, {@code false} en caso contrario.
     *
     * <p>Este método maneja una solicitud POST a la ruta "/payment".</p>
     */
    @PostMapping()
    public Mono<Boolean> validationPayment(@RequestBody double amount, String userId){
        return Mono.just(true);
    }
}
