package com.tecsup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecsup.model.Venta;
import com.tecsup.service.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping
    public List<Venta> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta venta) {
        return ResponseEntity.status(201).body(service.guardar(venta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtener(@PathVariable Long id) {
        Venta v = service.obtener(id);

        if (v == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(v);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(
            @PathVariable Long id,
            @RequestBody Venta v) {

        Venta existente = service.obtener(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setFecha(v.getFecha());
        existente.setTotal(v.getTotal());

        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Venta v = service.obtener(id);

        if (v == null) {
            return ResponseEntity.notFound().build();
        }

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}