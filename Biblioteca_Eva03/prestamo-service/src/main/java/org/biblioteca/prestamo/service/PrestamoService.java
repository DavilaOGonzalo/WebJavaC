package org.biblioteca.prestamo.service;

import org.biblioteca.prestamo.api.PrestamoRequest;
import org.biblioteca.prestamo.domain.EstadoPrestamo;
import org.biblioteca.prestamo.domain.Prestamo;
import org.biblioteca.prestamo.repository.PrestamoRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Service
public class PrestamoService {
    private final PrestamoRepository repository;
    private final RestClient libroClient;
    private final RestClient lectorClient;

    public PrestamoService(PrestamoRepository repository,
                           @Qualifier("libroRestClient") RestClient libroRestClient,
                           @Qualifier("lectorRestClient") RestClient lectorRestClient) {
        this.repository = repository;
        this.libroClient = libroRestClient;
        this.lectorClient = lectorRestClient;
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listar() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Prestamo obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Préstamo " + id + " no existe"));
    }

    @Transactional
    public Prestamo crear(PrestamoRequest request) {
        consultarLector(request.lectorId());
        reservarLibro(request.libroId());
        try {
            return repository.saveAndFlush(new Prestamo(request.lectorId(), request.libroId(),
                    request.fechaPrestamo(), request.fechaLimite()));
        } catch (DataAccessException ex) {
            liberarLibro(request.libroId());
            throw ex;
        }
    }

    @Transactional
    public Prestamo devolver(Long id) {
        Prestamo prestamo = obtener(id);
        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            throw new ConflictoException("El préstamo " + id + " ya fue devuelto");
        }
        liberarLibro(prestamo.getLibroId());
        prestamo.devolver();
        try {
            return repository.saveAndFlush(prestamo);
        } catch (DataAccessException ex) {
            reservarLibro(prestamo.getLibroId());
            throw ex;
        }
    }

    private void consultarLector(Long lectorId) {
        llamarServicio(() -> lectorClient.get().uri("/api/lectores/{id}", lectorId).retrieve().toBodilessEntity(),
                "lector", lectorId);
    }

    private void reservarLibro(Long libroId) {
        llamarServicio(() -> libroClient.post().uri("/api/libros/{id}/reservas", libroId).retrieve().toBodilessEntity(),
                "libro", libroId);
    }

    private void liberarLibro(Long libroId) {
        llamarServicio(() -> libroClient.post().uri("/api/libros/{id}/liberaciones", libroId).retrieve().toBodilessEntity(),
                "libro", libroId);
    }

    private void llamarServicio(RunnablePeticion peticion, String tipo, Long id) {
        try {
            peticion.ejecutar();
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                throw new RecursoNoEncontradoException("El " + tipo + " " + id + " no existe");
            }
            if (ex.getStatusCode().value() == HttpStatus.CONFLICT.value()) {
                throw new ConflictoException(ex.getResponseBodyAsString());
            }
            throw new ServicioExternoException("El servicio de " + tipo + " rechazó la operación", ex);
        } catch (RuntimeException ex) {
            if (ex instanceof RecursoNoEncontradoException || ex instanceof ConflictoException) {
                throw ex;
            }
            throw new ServicioExternoException("No fue posible comunicarse con el servicio de " + tipo, ex);
        }
    }

    @FunctionalInterface
    private interface RunnablePeticion {
        Object ejecutar();
    }
}
