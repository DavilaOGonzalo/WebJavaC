package org.biblioteca.lector.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lectores")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String documento;

    @Column(nullable = false, length = 160)
    private String nombres;

    @Column(nullable = false, length = 160)
    private String contacto;

    protected Lector() {
    }

    public Lector(String documento, String nombres, String contacto) {
        actualizar(documento, nombres, contacto);
    }

    public void actualizar(String documento, String nombres, String contacto) {
        this.documento = documento;
        this.nombres = nombres;
        this.contacto = contacto;
    }

    public Long getId() { return id; }
    public String getDocumento() { return documento; }
    public String getNombres() { return nombres; }
    public String getContacto() { return contacto; }
}
