package br.letscode.services.impl;

import br.letscode.dao.ProdutoDao;
import br.letscode.dto.ProdutoDto;
import br.letscode.models.Produto;
import br.letscode.restclient.FinanceiroRestClient;
import br.letscode.services.ProdutosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutosServiceImpl implements ProdutosService {

    @Autowired
    FinanceiroRestClient financeiroRestClient;

    @Autowired
    ProdutoDao produtoDao;

    public List<String> listarCategorias() {
        // Logging
        List<String> categorias = financeiroRestClient.findAll();

        // O servico retorna as categoraias + o nome dos produtos
        return categorias;
    }

    @Override
    public Produto buscarProduto(Long id) {
        return produtoDao.findById(id).get();
    }

    @Override
    public Produto novoProduto(Produto produto) {
        return produtoDao.save(produto);

    }

    @Override
    public boolean atualizarProduto(Produto produto) {
        try {
            produtoDao.save(produto);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    @Override
    public void deletarProduto(Long id) {
        produtoDao.deleteById(id);
    }

    @Override
    public List<Produto> listarTodosProdutos() {
        return produtoDao.findAll();
    }

}
