package startup;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Startup {

    private static ServerSocket serverSocket;
    private static int MAX_CLIENTS = 5;
    private static int PORT = 9090;

    private ServerSocket serverSocket2;
    private ThreadPool threadPool;


    public static void main(String[] args) {

        // if (args.length != 4) {
        //     System.out.printf("Usage: java -jar server.jar --host <host> --port <port>");
        //     return;
        // }

        Server server = new Server();
        server.start();

    }

}