package com.cibertec.ejercicio2;

public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public String consultarLibro(String idInput) {
        if (idInput == null || idInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar los datos requeridos");
        }

        int id;
        try {
            id = Integer.parseInt(idInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ingrese un ID válido");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("Ingrese un ID válido");
        }

        Libro libro = libroRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        return libro.getTitulo();
    }
}