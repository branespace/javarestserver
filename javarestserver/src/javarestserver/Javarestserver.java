package javarestserver;

import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.lang.Exception;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 *
 * @author branespace
 */
public class Javarestserver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        InetAddress SERVER_ADDRESS;
        int SERVER_PORT;
        
        //Try to set server variables
        try{
            SERVER_ADDRESS = InetAddress.getLoopbackAddress();
            SERVER_PORT = Integer.parseInt(args[0], 10);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        //Start server
        HttpServer server = HttpServer.create(
                new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT), 0);
        System.out.println("Server running at " + Integer.toString(SERVER_PORT));
        Router.route(server);
        
        server.setExecutor(null);
        server.start();
    }
    
}
