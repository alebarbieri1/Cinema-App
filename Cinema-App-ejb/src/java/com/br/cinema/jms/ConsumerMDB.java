/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.jms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Alexandre Barbieri
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/cinemaApp"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ConsumerMDB implements MessageListener {

    public ConsumerMDB() {
    }

    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            File f = new File("C:\\Users\\SPSILVA\\Documents\\NetBeansProjects\\Cinema-App\\log\\log.txt");
            try {
                FileOutputStream fos = new FileOutputStream(f, true);
                fos.write((tm.getText() + "\n").getBytes());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConsumerMDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConsumerMDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JMSException ex) {
            Logger.getLogger(ConsumerMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
