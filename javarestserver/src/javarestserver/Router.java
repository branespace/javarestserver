package javarestserver;

import com.sun.net.httpserver.HttpServer;

/**
 *
 * @author branespace
 */
public class Router {
    public static void route(HttpServer server){
        
        server.createContext("/name", new NameHandler());
        server.createContext("/", new DefaultHandler());
        
    }
}
