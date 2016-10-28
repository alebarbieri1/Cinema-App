/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.json;

import com.br.cinema.model.entities.Serie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Alexandre Barbieri
 */
public class JSONParser {

    public static String openURL(String uri) {
        String content = "";
        try {
            URL url = new URL(uri);
            // Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.128.1.70", 8080));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int code = con.getResponseCode();
            if (code == 407) {
                System.out.println("Falha na autenticação do proxy");
            } else if (code == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                con.disconnect();
                content = sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(JSONParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return content;
    }

    public static Serie parseSerieDetails(String content) {
        List<Serie> series = new ArrayList();

        // Leitor JSON
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();

        String name = root.getString("name");
        Long id = root.getJsonNumber("id").longValue();
        Integer number_of_episodes = root.getInt("number_of_episodes");
        String poster_path;
        try {
         poster_path = root.getString("poster_path");
        } catch (ClassCastException  ex){
            poster_path = "";
        }

        Serie s = new Serie();
        s.setNomeSerie(name);
        s.setIdSerie(id);
        s.setEpisodios(number_of_episodes);
        s.setPoster_path(poster_path);

        return s;
    }
}
