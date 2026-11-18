package com.cibertec.clientes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Test
    void clienteExisteYActivo_exito() {
        ClienteRepository mockrepository = mock(ClienteRepository.class);
        ClienteService clienteservice = new ClienteService(mockrepository);

        Cliente mockcliente = new Cliente(1, "Carlos Perez", "carlos@correo.com", true);
        when(mockrepository.buscarPorId(1)).thenReturn(mockcliente);

        String resultado = clienteservice.consultarCliente(1);

        assertEquals("Carlos Perez - carlos@correo.com", resultado);
        verify(mockrepository).buscarPorId(1);
    }

    @Test
    void idInvalido_error() {
        ClienteRepository mockrepository = mock(ClienteRepository.class);
        ClienteService clienteservice = new ClienteService(mockrepository);

        String resultado = clienteservice.consultarCliente(-1);

        assertEquals("Ingrese un ID correcto", resultado);
        verifyNoInteractions(mockrepository);
    }

    @Test
    void clienteNoEncontrado_error() {
        ClienteRepository mockrepository = mock(ClienteRepository.class);
        ClienteService clienteservice = new ClienteService(mockrepository);

        when(mockrepository.buscarPorId(99)).thenReturn(null);

        String resultado = clienteservice.consultarCliente(99);

        assertEquals("Cliente no registrado", resultado);
        verify(mockrepository).buscarPorId(99);
    }

    @Test
    void clienteInactivo() {
        ClienteRepository mockrepository = mock(ClienteRepository.class);
        ClienteService clienteservice = new ClienteService(mockrepository);

        Cliente mockcliente = new Cliente(2, "Ana Torres", "ana@correo.com", false);
        when(mockrepository.buscarPorId(2)).thenReturn(mockcliente);

        String resultado = clienteservice.consultarCliente(2);

        assertEquals("Cliente inactivo", resultado);
        verify(mockrepository).buscarPorId(2);
    }

    @Test
    void idEnBlanco() {
        ClienteRepository mockrepository = mock(ClienteRepository.class);
        ClienteService clienteservice = new ClienteService(mockrepository);

        String resultado = clienteservice.consultarCliente(null);

        assertEquals("Debe ingresar los datos requeridos", resultado);
        verifyNoInteractions(mockrepository);
    }
}
