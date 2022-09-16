package br.letscode.dao;

import br.letscode.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDao extends JpaRepository<Pedido, Long> {

}

