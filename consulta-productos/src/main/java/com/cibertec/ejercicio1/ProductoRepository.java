
package com.cibertec.ejercicio1;

import java.util.Optional;

public interface ProductoRepository {

    Optional<Producto> buscarPorId(int id);

}
