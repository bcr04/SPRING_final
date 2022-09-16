package br.letscode.services.impl;

import br.letscode.dao.ItemDao;
import br.letscode.dao.PedidoDao;
import br.letscode.dto.ItemDto;
import br.letscode.dto.PedidoDto;
import br.letscode.models.Item;
import br.letscode.models.Pedido;
import br.letscode.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoDao pedidoDao;

    @Autowired
    ItemDao itemDao;

    @Override
    public List<Pedido> listarTodosPedidos() {
        return pedidoDao.findAll();
    }

    // Usando o DTO pq nosso caso (de mentirinha), precisamos de algum trabalho nele
    // antes de chegar a camada de persistencia
    @Override
    public Pedido novoPedido(PedidoDto pedidoDto) {
        
        ArrayList<Long> listaIdItems = new ArrayList<>();

        try {
            // iterar e salvar no banco os dados de item de pedido
            ArrayList<ItemDto> listaItems = pedidoDto.getListItems();
            for (ItemDto it : listaItems) {
                Item i = new Item(it.getIdPedido(), it.getIdProduto(), it.getPrecoUnitario(), it.getQuantidade(),
                        it.getPrecoUnitario());
                Item i2 = itemDao.save(i);
                listaIdItems.add(i2.getIdItem());
            }

            Pedido pedido = new Pedido(pedidoDto.getIdCliente(), listaIdItems);
            pedidoDao.save(pedido);
            return pedido;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean atualizarPedido(Pedido pedido) {
        try {
            pedidoDao.save(pedido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletarPedido(long id) {
        try {
            pedidoDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}