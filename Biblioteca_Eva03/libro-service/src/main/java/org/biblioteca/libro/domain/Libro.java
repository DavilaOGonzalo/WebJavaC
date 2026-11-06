package org.biblioteca.libro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String autor;

    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoLibro estado;

    @Version
    private Long version;

    protected Libro() {
    }

    public Libro(String isbn, String titulo, String autor, int stock) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        actualizarStock(stock);
    }

    public void actualizar(String isbn, String titulo, String autor, int stock) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        actualizarStock(stock);
    }

    public void actualizarStock(int stock) {
        this.stock = stock;
        this.estado = stock > 0 ? EstadoLibro.DISPONIBLE : EstadoLibro.AGOTADO;
    }

    public Long getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getStock() { return stock; }
    public EstadoLibro getEstado() { return estado; }
}
