package com.example.tallerfinal.controllers;

import com.example.tallerfinal.models.Cashout;
import com.example.tallerfinal.services.interfaces.ICashoutService;
import com.example.tallerfinal.services.interfaces.IPaymentRestClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/transaction-history/user/{userId}")
public class AuxiliarController {

    private final ICashoutService service;

    public AuxiliarController(ICashoutService service) {
        this.service = service;
    }

    /**
     * Obtiene el historial de transacciones (cashouts) para un usuario específico.
     *
     * @param userId El ID del usuario para el cual se desea obtener el historial de transacciones.
     * @return Un {@link Flux} de {@link Cashout} que contiene todas las transacciones asociadas al usuario.
     *
     * Este método maneja una solicitud GET a la ruta "/transaction-history/user/{userId}".
     * En caso de que no se encuentren transacciones para el usuario, se devolverá un {@code Flux} vacío.
     */
    @GetMapping()
    public Flux<Cashout> obtenerPorUserId(@PathVariable String userId){
        return service.obtenerPorUserId(userId);
    }
}
