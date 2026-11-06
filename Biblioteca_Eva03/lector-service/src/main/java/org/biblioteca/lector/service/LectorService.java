package org.biblioteca.lector.service;

import org.biblioteca.lector.api.LectorRequest;
import org.biblioteca.lector.domain.Lector;
import org.biblioteca.lector.repository.LectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LectorService {
    private final LectorRepository repository;

    public LectorService(LectorRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Lector> listar() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Lector obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Lector " + id + " no existe"));
    }

    @Transactional
    public Lector crear(LectorRequest request) {
        return repository.save(new Lector(request.documento().trim(), request.nombres().trim(), request.contacto().trim()));
    }

    @Transactional
    public Lector actualizar(Long id, LectorRequest request) {
        Lector lector = obtener(id);
        lector.actualizar(request.documento().trim(), request.nombres().trim(), request.contacto().trim());
        return repository.save(lector);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }
}
