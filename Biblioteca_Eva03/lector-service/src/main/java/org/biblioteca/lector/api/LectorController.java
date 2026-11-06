package org.biblioteca.lector.api;

import jakarta.validation.Valid;
import org.biblioteca.lector.domain.Lector;
import org.biblioteca.lector.service.LectorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lectores")
public class LectorController {
    private final LectorService service;

    public LectorController(LectorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Lector> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Lector obtener(@PathVariable("id") Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lector crear(@Valid @RequestBody LectorRequest request) { return service.crear(request); }

    @PutMapping("/{id}")
    public Lector actualizar(@PathVariable("id") Long id, @Valid @RequestBody LectorRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable("id") Long id) { service.eliminar(id); }
}
