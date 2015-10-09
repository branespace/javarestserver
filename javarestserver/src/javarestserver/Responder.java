package javarestserver;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

/**
 *
 * @author branespace
 */
public class Responder {
    public static void respond(int status, String text, HttpExchange response) 
            throws IOException {
        response.sendResponseHeaders(status, text.length());
        OutputStream stream = response.getResponseBody();
        stream.write(text.getBytes());
        stream.close();
    }
}
