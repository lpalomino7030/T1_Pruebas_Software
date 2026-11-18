package com.cibertec.ejercicio2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    @Test
    void consultarLibro_Exito() {
        Libro libro = new Libro(10, "Clean Code");
        when(libroRepository.buscarPorId(10)).thenReturn(Optional.of(libro));

        String resultado = libroService.consultarLibro("10");

        assertEquals("Clean Code", resultado);
        verify(libroRepository).buscarPorId(10);
    }

    @Test
    void consultarLibro_IdInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> libroService.consultarLibro("0"));

        assertEquals("Ingrese un ID válido", ex.getMessage());
        verifyNoInteractions(libroRepository);
    }

    @Test
    void consultarLibro_NoEncontrado() {
        when(libroRepository.buscarPorId(50)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> libroService.consultarLibro("50"));

        assertEquals("Libro no encontrado", ex.getMessage());
        verify(libroRepository).buscarPorId(50);
    }

    @Test
    void consultarLibro_CampoEnBlanco() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> libroService.consultarLibro("   "));

        assertEquals("Debe ingresar los datos requeridos", ex.getMessage());
        verifyNoInteractions(libroRepository);
    }
}