package com.tecsup.service;

import com.tecsup.model.DetalleVenta;
import com.tecsup.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository repo;

    public List<DetalleVenta> listar() {
        return repo.findAll();
    }

    public DetalleVenta guardar(DetalleVenta d) {
        return repo.save(d);
    }

    public DetalleVenta obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}