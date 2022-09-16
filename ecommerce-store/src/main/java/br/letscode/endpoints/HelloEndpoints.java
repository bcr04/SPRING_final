package br.letscode.endpoints;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.letscode.services.TimeService;
import br.letscode.services.impl.TimeServiceImpl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class HelloEndpoints {

    // private String horaAtual;

    // @Autowired
    // public void setHoraAtual(){
    //     this.horaAtual = TimeService.retornarHoraAtual();
    // }

    @Autowired
    TimeService timeService;

    Logger log = LoggerFactory.getLogger(HelloEndpoints.class);  

    @RequestMapping(path="/hello/", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        log.info("/hello acessado com sucesso");
        return new ResponseEntity<String>("Hello world!", HttpStatus.OK);
    }

    @RequestMapping(path="/hello/{nome}", method = RequestMethod.GET)
    public ResponseEntity<String> helloNome(@PathVariable String nome) {
        log.info("/hello/{nome} acessado com sucesso");
        return new ResponseEntity<String>("Hello "+nome+"!", HttpStatus.OK);
    }


    @RequestMapping(path="/hello/{nome}/horario", method = RequestMethod.GET)    
    public ResponseEntity<String> helloNomeHorario(@PathVariable String nome) {
        log.info("/hello/{nome}/horario acessado com sucesso");
        return new ResponseEntity<String>("Hello "+nome+"!\n"+ "Agora são " + timeService.retornarHoraAtual() +", não esqueça!", HttpStatus.OK);
    }




}
