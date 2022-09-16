package br.letscode.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.letscode.services.TimeService;

@Service
public class TimeServiceImpl implements TimeService {

    Logger log = LoggerFactory.getLogger(TimeServiceImpl.class);  

    public String retornarHoraAtual(){
        LocalTime dataHora = LocalTime.now();        
        String retorno = dataHora.truncatedTo(ChronoUnit.SECONDS).toString();
        log.info("Hora atual: "+ retorno);
        return retorno; 
    }

    

}
