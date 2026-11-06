package com.tecsup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecsup.model.Cliente;
import com.tecsup.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(service.guardar(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Long id) {
        Cliente c = service.obtener(id);

        if (c == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(
            @PathVariable Long id,
            @RequestBody Cliente c) {

        Cliente existente = service.obtener(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNombre(c.getNombre());
        existente.setApellido(c.getApellido());
        existente.setTelefono(c.getTelefono());
        existente.setEmail(c.getEmail());

        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Cliente c = service.obtener(id);

        if (c == null) {
            return ResponseEntity.notFound().build();
        }

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}