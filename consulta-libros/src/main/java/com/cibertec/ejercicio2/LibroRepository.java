package com.cibertec.ejercicio2;

import java.util.Optional;

public interface LibroRepository {
    Optional<Libro> buscarPorId(Integer id);
}
