package startup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int MAX_CLIENTS;
    private static int PORT;
    private ServerSocket serverSocket;
    private ThreadPool threadPool;

    public Server(int port, int maxClients) {
        PORT = port;
        MAX_CLIENTS = maxClients;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            threadPool = new ThreadPool(MAX_CLIENTS);
            System.out.println("Servidor iniciado en el puerto " + PORT);

            do {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Asignar un hilo del pool para manejar la conexión del cliente
                HandlerClient handler = new HandlerClient(clientSocket);
                threadPool.execute(handler);
            } while (true);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
