
package com.cibertec.ejercicio1;

import java.util.Optional;

public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public String consultarProducto(String idTexto) {

        if (idTexto == null || idTexto.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Debe ingresar los datos requeridos");
        }

        int id;

        try {
            id = Integer.parseInt(idTexto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ingrese un ID válido");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("Ingrese un ID válido");
        }

        Optional<Producto> producto = productoRepository.buscarPorId(id);

        if (producto.isEmpty()) {
            throw new IllegalArgumentException("Producto no encontrado");
        }

        return producto.get().getNombre();
    }
}
