package com.example.tallerfinal.controllers;

import com.example.tallerfinal.models.Cashout;
import com.example.tallerfinal.services.interfaces.ICashoutService;
import com.example.tallerfinal.services.interfaces.ITransactionHistoryRestClient;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cashouts")
public class CashoutController {
    private final ICashoutService service;
    private final ITransactionHistoryRestClient transactionHistoryRestClient;

    public CashoutController(ICashoutService service, ITransactionHistoryRestClient transactionHistoryRestClient) {
        this.service = service;
        this.transactionHistoryRestClient = transactionHistoryRestClient;
    }

    /**
     * Endpoint para crear un nuevo Cashout.
     *
     * @param cashout el objeto Cashout que se va a crear
     * @return el Cashout creado
     */
    @PostMapping
    public Mono<Cashout> crearCashOut(@Valid @RequestBody Cashout cashout){

        return service.crear(cashout);
    }

    /**
     * Endpoint para obtener todos los Cashouts de un usuario específico.
     *
     * @param userId el ID del usuario
     * @return una lista de Cashouts del usuario
     */
    @GetMapping("/user/{userId}")
    public Flux<Cashout> obtenerPorUserId(@PathVariable String userId){

        return transactionHistoryRestClient.cashOutList(userId);
    }
}
