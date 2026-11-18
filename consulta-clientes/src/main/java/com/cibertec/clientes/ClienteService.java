package com.cibertec.clientes;

public class ClienteService {

    private ClienteRepository clienterepository;

    public ClienteService(ClienteRepository repository) {
        this.clienterepository = repository;
    }

    public String consultarCliente(Integer id) {
        if (id == null) {
           return "Debe ingresar los datos requeridos";
        }
        if (id <= 0) {
            return "Ingrese un ID correcto";
        }

        Cliente cliente = clienterepository.buscarPorId(id);

        if (cliente == null) {
            return "Cliente no registrado";
        }
        if (!cliente.getEstado()) {
            return "Cliente inactivo";
        }
        return cliente.getNombre() + " - " + cliente.getCorreo();
    }
}
