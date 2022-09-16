package br.letscode.dao;

import br.letscode.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDao extends JpaRepository<Produto, Long> {

}

