package br.letscode.endpoints;

import br.letscode.dto.ClienteDto;
import br.letscode.models.Cliente;
import br.letscode.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ClienteEndpoints {
    @Autowired
    ClienteService clienteService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(description = "Esse metodo retorna todos os clientes do sistema, sem filtros. Necessário papel de ADMIN.",
    summary = " ADM - Retorna lista de clientes")
    @RequestMapping(path="/cliente", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getAllClients() {
        List<Cliente> clienteList = clienteService.listarTodosClientes();

        return ResponseEntity.ok(clienteList);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    @Operation(description = "Cria um novo cliente com os dados recebidos, a senha é criptografada e o tipo padrão é cliente. Necessário papel de ADMIN ou CLIENTE.", summary = "ADMIN, CLIENTE - Criar novo cliente")
    @RequestMapping(path="/cliente", method = RequestMethod.POST)
    public ResponseEntity<Cliente> novoCliente(@RequestBody ClienteDto cliente) {
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        Cliente clienteSalvo = clienteService.novoCliente(cliente);


        // boolean sucesso = clienteService.novoCliente(cliente);

        if(clienteSalvo != null) {
            return ResponseEntity.ok(clienteSalvo);
        }
        else {            
            return new ResponseEntity("Criacao do cliente falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(description = "Atualiza os dados do cliente (chave primária é o e-mail) com os dados fornecidos. Necessário papel de ADMIN.", summary = "ADMIN - Atualizar cliente")
    @RequestMapping(path="/cliente", method = RequestMethod.PUT)
    public ResponseEntity<String> atualizarCliente(@RequestBody Cliente cliente) {
        boolean sucesso = clienteService.atualizarCliente(cliente);

        if(sucesso) {
            return new ResponseEntity<String>("Cliente atualizado com sucesso!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Atualizacao do cliente falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    // @RequestMapping(path="/cliente/{id}", method = RequestMethod.DELETE)
    // public ResponseEntity removerCliente(@PathVariable long id) {
    //     boolean sucesso = clienteService.removerCliente(id);

    //     if(sucesso) {
    //         return new ResponseEntity("Cliente deletado com sucesso!", HttpStatus.OK);
    //     }
    //     else {
    //         return new ResponseEntity("Remocao do cliente falhou!", HttpStatus.BAD_REQUEST);
    //     }
    // }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(description = "Remove o cliente a aprtir do e-mail fornecido. Deve ter papel de ADMIN.", summary = "Apenas ADMIN - deletar cliente")
    @RequestMapping(path="/cliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removerCliente(@PathVariable long id) {
        boolean sucesso = clienteService.removerCliente(id);

        if(sucesso) {
            return new ResponseEntity("Cliente deletado com sucesso!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Remocao do cliente falhou!", HttpStatus.BAD_REQUEST);
        }
    }



}
