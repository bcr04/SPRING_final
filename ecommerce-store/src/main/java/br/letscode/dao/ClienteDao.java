package br.letscode.dao;

import br.letscode.models.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByIdOrEmail(long id, String email); // SELECT * FROM CLIENTE WHERE id = {id} OR email = {email ???
    Cliente findByEmail(String email);
}
