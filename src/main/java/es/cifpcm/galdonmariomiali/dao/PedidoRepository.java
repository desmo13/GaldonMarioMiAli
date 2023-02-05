package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Pedido;
import es.cifpcm.galdonmariomiali.model.Productoffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    ArrayList<Pedido> findByIdUsuario(String username);
}
