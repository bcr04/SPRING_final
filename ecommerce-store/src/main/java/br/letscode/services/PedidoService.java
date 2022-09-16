package br.letscode.services;

import br.letscode.dto.PedidoDto;
import br.letscode.models.Pedido;
import java.util.List;

public interface PedidoService {
    List<Pedido> listarTodosPedidos();

    Pedido novoPedido(PedidoDto pedido);

    boolean atualizarPedido(Pedido pedido);

    boolean deletarPedido(long id);

}
