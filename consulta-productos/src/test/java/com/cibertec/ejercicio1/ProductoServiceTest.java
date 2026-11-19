
package com.cibertec.ejercicio1;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    private ProductoRepository productoRepository;
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        productoRepository = mock(ProductoRepository.class);
        productoService = new ProductoService(productoRepository);
    }

    @Test
    public void consultarProducto_CuandoExiste_RetornaNombre() {
        Producto producto = new Producto(1, "Laptop");

        when(productoRepository.buscarPorId(1))
                .thenReturn(Optional.of(producto));

        String resultado = productoService.consultarProducto("1");

        assertEquals("Laptop", resultado);
        verify(productoRepository).buscarPorId(1);
    }

    @Test
    public void consultarProducto_CuandoIdEsCero_LanzaError() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> productoService.consultarProducto("0"));

        assertEquals("Ingrese un ID válido", ex.getMessage());
        verifyNoInteractions(productoRepository);
    }

    @Test
    public void consultarProducto_CuandoNoExiste_LanzaError() {
        when(productoRepository.buscarPorId(2))
                .thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> productoService.consultarProducto("2"));

        assertEquals("Producto no encontrado", ex.getMessage());
    }

    @Test
    public void consultarProducto_CuandoCampoEstaEnBlanco_LanzaError() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> productoService.consultarProducto(" "));

        assertEquals("Debe ingresar los datos requeridos", ex.getMessage());
        verifyNoInteractions(productoRepository);
    }

    @Test
    public void consultarProducto_CuandoIdEsNegativo_LanzaError() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> productoService.consultarProducto("-1"));

        assertEquals("Ingrese un ID válido", ex.getMessage());
        verifyNoInteractions(productoRepository);
    }
}
