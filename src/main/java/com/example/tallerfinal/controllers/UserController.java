package com.example.tallerfinal.controllers;

import com.example.tallerfinal.exceptions.UserNoFoundException;
import com.example.tallerfinal.repositories.UserRepository;
import com.example.tallerfinal.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.tallerfinal.models.User;
import com.example.tallerfinal.models.AmountRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param user El usuario a crear.
     * @return Un {@link Mono} que contiene el usuario creado.
     */
    @PostMapping
    public Mono<User> crearUsere(@Valid @RequestBody User user){

        return service.crear(user);
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return Un {@link Flux} que contiene una lista de todos los usuarios.
     */
    @GetMapping
    public Flux<User> obteneTodosLosUser(){
        return service.obtener();
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario a obtener.
     * @return Un {@link Mono} que contiene el usuario correspondiente al ID proporcionado.
     * @throws UserNoFoundException Si el usuario con el ID especificado no se encuentra.
     */
    @GetMapping("/{id}")
    public Mono<User> obtenerUser(@PathVariable String id){
        return service.obtenerPorId(id);
    }

    /**
     * Actualiza el saldo de un usuario.
     *
     * @param id El ID del usuario cuyo saldo se actualizará.
     * @param amount La cantidad a agregar al saldo del usuario.
     * @return Un {@link Mono} que contiene el usuario con el saldo actualizado.
     * @throws UserNoFoundException Si el usuario con el ID especificado no se encuentra.
     */
    @PutMapping("/{id}/balance")
    public Mono<User> actualizarUserBalance(@PathVariable String id,@RequestBody AmountRequest amount){
        return service.obtenerPorId(id)
                .switchIfEmpty(Mono.error(new UserNoFoundException("Usuario no encontrado")))
                .flatMap(existUser ->{
                    existUser.setBalance(existUser.getBalance() + amount.getAmount());
                    return service.crear(existUser);
                });
    }
}
