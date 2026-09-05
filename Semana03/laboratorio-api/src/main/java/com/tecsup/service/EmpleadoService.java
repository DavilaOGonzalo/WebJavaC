package com.tecsup.service;

import com.tecsup.model.Empleado;
import com.tecsup.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository repo;

    public List<Empleado> listar() {
        return repo.findAll();
    }

    public Empleado guardar(Empleado e) {
        return repo.save(e);
    }

    public Empleado obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}