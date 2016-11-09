/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.jms;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Alexandre Barbieri
 */
@LocalBean
@Stateless
public class ProducerJMS implements ProducerJMSLocal {

    // Recurso criado no console do Glassfish (Fila)
    @Resource(mappedName = "jms/cinemaApp")
    private Queue lp3;

    // Connection Factory gerada no Glassfish (Fábrica de Conexões)
    @Inject
    @JMSConnectionFactory("jms/cinemaAppFactory")
    private JMSContext context;

    private void sendJMSMessageToLp3(String messageData) {
        // Envia a mensagem para a fila especificada (lp3)
        context.createProducer().send(lp3, messageData);
    }

    @Override
    public void sendMessage(String text) {
        StringBuilder register = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        register.append(sdf.format(new Date()));
        register.append(" - ");
        register.append(text);
        sendJMSMessageToLp3(register.toString());
    }
    
    
}
