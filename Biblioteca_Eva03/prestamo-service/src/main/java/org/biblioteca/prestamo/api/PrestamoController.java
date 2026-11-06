package org.biblioteca.prestamo.api;

import jakarta.validation.Valid;
import org.biblioteca.prestamo.domain.Prestamo;
import org.biblioteca.prestamo.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Prestamo> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Prestamo obtener(@PathVariable("id") Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prestamo crear(@Valid @RequestBody PrestamoRequest request) { return service.crear(request); }

    @PostMapping("/{id}/devolucion")
    public Prestamo devolver(@PathVariable("id") Long id) { return service.devolver(id); }
}
