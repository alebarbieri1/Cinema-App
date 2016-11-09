/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.jms;

import javax.ejb.Local;

/**
 *
 * @author Alexandre Barbieri
 */
@Local
public interface ProducerJMSLocal {
    void sendMessage(String text);
}
