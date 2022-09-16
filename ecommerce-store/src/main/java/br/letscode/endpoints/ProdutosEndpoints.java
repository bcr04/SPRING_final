package br.letscode.endpoints;

import br.letscode.models.Produto;
import br.letscode.services.impl.ProdutosServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ProdutosEndpoints {

    @Autowired
    ProdutosServiceImpl produtosService;
    
    @Operation(description = "Exibe todas as categorias cadastradas", summary = "listar categorias")
    @RequestMapping(path = "/produtos/categorias", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllCategories() {

        // TODO outras acoes da API

        return ResponseEntity.ok(produtosService.listarCategorias());
    }

    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(description = "Deletar o produto cuja id foi fornecida. Necessário papel de ADMIN.", summary = "ADMIN - deletar produto")
    @RequestMapping(path = "/produtos/{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletarComID(@PathVariable Long id) {
        try {
            produtosService.deletarProduto(id);
            return new ResponseEntity<String>("Produto deletado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(description = "Alterar os dados do produto. Necessário papel de ADMIN.", summary = "ADMIN - alterar produto")
    @RequestMapping(path="/produtos", method = RequestMethod.PUT)
    public ResponseEntity<String> atualizarCliente(@RequestBody Produto produto) {
        boolean sucesso = produtosService.atualizarProduto(produto);

        if(sucesso) {
            return new ResponseEntity<String>("Produto atualizado com sucesso!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Atualizacao do produto falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(description = "Criar novo produto. Necessário papel de ADMIN.", summary = "ADMIN - criar produto")
    @RequestMapping(path = "/produtos", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtosService.novoProduto(produto));
    }

    @Operation(description = "Retorna todos os dados do produto cuja id foi fornecida na url.", summary = "Retorna dados de um produto específico")
    @RequestMapping(path = "/produtos/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> recuperarProdutoComID(@PathVariable Long id) {
        return ResponseEntity.ok(produtosService.buscarProduto(id));
    }

    @Operation(description = "Listar todos os produtos.", summary = "listar todos os produtos")
    @RequestMapping(path = "/produtos", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        return new ResponseEntity<List<Produto>>(produtosService.listarTodosProdutos(), HttpStatus.OK);
    }


}
