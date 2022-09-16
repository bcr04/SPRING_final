package br.letscode.endpoints;

import br.letscode.dto.PedidoDto;
import br.letscode.models.Item;
import br.letscode.models.Pedido;
import br.letscode.models.Produto;
import br.letscode.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



@RestController
public class PedidosEndpoints {
    @Autowired
    PedidoService pedidoService;

    @PreAuthorize("hasAnyRole('CLIENTE')")
    @Operation(description = "Grava um novo pedido no banco nas tabelas PEDIDOS e ITEM. Na tabela pedidos há o ID do cliente, lista de IDs de Itens do pedido. De forma simultânea, grava na tabela ITEM os dados de cada item no momento da compra (ID_PEDIDO, ID_PRODUTO, PRECO_UNITARIO, QUANTIDADE e VALOR_TOTAL) para permitir formar o produto.", summary = "CLIENTE - novo Pedido")
    @RequestMapping(path = "/pedidos", method = RequestMethod.POST)
    public ResponseEntity<Pedido> novoPedido(@RequestBody PedidoDto pedido) {

        Pedido pedidoSalvo = pedidoService.novoPedido(pedido);

        if (pedidoSalvo != null) {
            return ResponseEntity.ok(pedidoSalvo);
        } else {
            return new ResponseEntity("Criacao do Pedido falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")    
    @Operation(description = "Altera um pedido no banco nas tabelas PEDIDOS e ITEM. Na tabela pedidos há o ID do cliente, lista de IDs de Itens do pedido. De forma simultânea, grava na tabela ITEM os dados de cada item no momento da compra (ID_PEDIDO, ID_PRODUTO, PRECO_UNITARIO, QUANTIDADE e VALOR_TOTAL) para permitir formar o produto. Necessário acesso ADMIN.", summary = "ADMIN - alterar Pedido")
    @RequestMapping(path = "/pedidos", method = RequestMethod.PUT)
    public ResponseEntity<String> atualizarPedido(@RequestBody Pedido pedido) {

        boolean sucesso = pedidoService.atualizarPedido(pedido);

        if(sucesso) {
            return new ResponseEntity<String>("Pedido atualizado com sucesso!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Atualizacao do pedido falhou!", HttpStatus.BAD_REQUEST);
        }
    }



    // @Operation(description = "", summary = "")
    // @RequestMapping(path = "/pedido/{id}", method = RequestMethod.GET)
    // public ResponseEntity<Pedido> listarPedidoComID(@PathVariable Long id) {
    //     return ResponseEntity.ok(pedidoService.buscarPedido(id));
    // }

    @PreAuthorize("hasAnyRole('ADMIN')")    
    @Operation(description = "Listar todos os pedidos. Necessário acesso de ADMIN.", summary = "ADMIN - Listar pedidos")
    @RequestMapping(path = "/pedidos", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> listarPedidoComID() {
        return ResponseEntity.ok(pedidoService.listarTodosPedidos());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(description = "Deletar o pedido cuja id foi fornecida. Necessário papel de ADMIN.", summary = "ADMIN - deletar pedido")
    @RequestMapping(path = "/pedidos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarComID(@PathVariable Long id) {
        try {
            pedidoService.deletarPedido(id);
            return new ResponseEntity<String>("Pedido deletado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    

}
