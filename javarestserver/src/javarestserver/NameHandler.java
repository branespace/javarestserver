package javarestserver;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 *
 * @author branespace
 */
public class NameHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        if (method.equals("GET")) {
            get(exchange);
        } else if (method.equals("POST")) {
            post(exchange);
        } else if (method.equals("PUT")) {
            put(exchange);
        } else if (method.equals("DELETE")) {
            delete(exchange);
        }
    }
    
    public void get(HttpExchange exchange) throws IOException{
        if (exchange.getRequestURI().toString().equals("/name")) {
            Responder.respond(200, NameServer.getNames(), exchange);
        } else {
            Responder.respond(200, NameServer.getName(
                  getId(exchange.getRequestURI().toString())), exchange);                
        }
    }
    
    public void post(HttpExchange exchange) throws IOException{
        int id = NameServer.saveName(exchange.getRequestBody());
        Responder.respond(201, Integer.toString(id), exchange);    
    }    
    
    public void put(HttpExchange exchange) throws IOException{
        int id = NameServer.updateName(exchange.getRequestBody(),
                getId(exchange.getRequestURI().toString()));
        if (id > -1) {
            Responder.respond(202, Integer.toString(id), exchange);  
        } else {
            Responder.respond(500, "Internal Server Error", exchange); 
        }
    }    

    public void delete(HttpExchange exchange) throws IOException{
        int id = NameServer.deleteName(getId(exchange.getRequestURI().toString()));
        if (id > -1) {
            Responder.respond(202, Integer.toString(id), exchange);  
        } else {
            Responder.respond(500, "Internal Server Error", exchange); 
        }       
    }    
    
    public static int getId(String URI) {
        return Integer.parseInt(URI.substring(6), 10);
    }
}
