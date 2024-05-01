package startup;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Startup {

    private static ServerSocket serverSocket;
    private static int MAX_CONNECTIONS = 5;
    private static int PORT = 9090;


    public static void main(String[] args) {

        // if (args.length != 4) {
        //     System.out.printf("Usage: java -jar server.jar --host <host> --port <port>");
        //     return;
        // }

        System.out.printf("This application will open a connection for socker as server");
        System.out.printf("Port: %d\n", PORT);

        try {

            System.out.printf("Starting server on port %d\n", PORT);
            serverSocket = new ServerSocket(PORT);

            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();
            System.out.printf("Client connected: %s\n", clientSocket.getInetAddress().getHostAddress());

//            // Close the client socket
//            clientSocket.close();
//
//            // Close the server socket
//            serverSocket.close();

        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return;
        }

    }

}