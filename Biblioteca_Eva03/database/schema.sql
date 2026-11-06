CREATE DATABASE IF NOT EXISTS bd_libro CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS bd_lector CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS bd_prestamo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE bd_libro;
CREATE TABLE IF NOT EXISTS libros (
    id BIGINT NOT NULL AUTO_INCREMENT,
    isbn VARCHAR(20) NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    stock INT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    version BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT uk_libros_isbn UNIQUE (isbn)
);

USE bd_lector;
CREATE TABLE IF NOT EXISTS lectores (
    id BIGINT NOT NULL AUTO_INCREMENT,
    documento VARCHAR(30) NOT NULL,
    nombres VARCHAR(160) NOT NULL,
    contacto VARCHAR(160) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_lectores_documento UNIQUE (documento)
);

USE bd_prestamo;
CREATE TABLE IF NOT EXISTS prestamos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    lector_id BIGINT NOT NULL,
    libro_id BIGINT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_limite DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
