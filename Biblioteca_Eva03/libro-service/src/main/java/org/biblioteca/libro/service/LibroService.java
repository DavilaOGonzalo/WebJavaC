package org.biblioteca.libro.service;

import org.biblioteca.libro.api.LibroRequest;
import org.biblioteca.libro.domain.Libro;
import org.biblioteca.libro.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository repository;

    public LibroService(LibroRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Libro> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Libro obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Libro " + id + " no existe"));
    }

    @Transactional
    public Libro crear(LibroRequest request) {
        return repository.save(new Libro(limpiarIsbn(request.isbn()), request.titulo().trim(),
                request.autor().trim(), request.stock()));
    }

    @Transactional
    public Libro actualizar(Long id, LibroRequest request) {
        Libro libro = obtener(id);
        libro.actualizar(limpiarIsbn(request.isbn()), request.titulo().trim(),
                request.autor().trim(), request.stock());
        return repository.save(libro);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    @Transactional
    public Libro reservar(Long id) {
        Libro libro = obtener(id);
        if (libro.getStock() == 0) {
            throw new ConflictoException("No hay ejemplares disponibles para el libro " + id);
        }
        libro.actualizarStock(libro.getStock() - 1);
        return repository.save(libro);
    }

    @Transactional
    public Libro liberar(Long id) {
        Libro libro = obtener(id);
        libro.actualizarStock(libro.getStock() + 1);
        return repository.save(libro);
    }

    private String limpiarIsbn(String isbn) {
        return isbn.replace("-", "").trim().toUpperCase();
    }
}
