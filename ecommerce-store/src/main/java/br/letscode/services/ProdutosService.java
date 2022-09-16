package br.letscode.services;

import br.letscode.models.Produto;
import java.util.List;



public interface ProdutosService {
    List<Produto> listarTodosProdutos();

    List<String> listarCategorias();

    Produto buscarProduto(Long id);

    Produto novoProduto(Produto produto);

    boolean atualizarProduto(Produto produto);

    void deletarProduto(Long id);

}
