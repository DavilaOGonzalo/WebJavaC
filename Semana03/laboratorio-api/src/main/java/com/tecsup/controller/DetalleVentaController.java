package com.tecsup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecsup.model.DetalleVenta;
import com.tecsup.service.DetalleVentaService;

@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService service;

    @GetMapping
    public List<DetalleVenta> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> guardar(@RequestBody DetalleVenta detalle) {
        return ResponseEntity.status(201).body(service.guardar(detalle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> obtener(@PathVariable Long id) {
        DetalleVenta d = service.obtener(id);

        if (d == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> actualizar(
            @PathVariable Long id,
            @RequestBody DetalleVenta d) {

        DetalleVenta existente = service.obtener(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setCantidad(d.getCantidad());
        existente.setPrecio(d.getPrecio());
        existente.setVenta(d.getVenta());
        existente.setProducto(d.getProducto());

        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        DetalleVenta d = service.obtener(id);

        if (d == null) {
            return ResponseEntity.notFound().build();
        }

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}