package startup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 9090;
    private static final int MAX_CLIENTS = 10;
    private ServerSocket serverSocket;
    private ThreadPool threadPool;

    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            threadPool = new ThreadPool(MAX_CLIENTS);
            System.out.println("Servidor iniciado en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Asignar un hilo del pool para manejar la conexión del cliente
                HandlerClient handler = new HandlerClient(clientSocket);
                threadPool.execute(handler);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
