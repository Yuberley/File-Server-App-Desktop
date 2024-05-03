package startup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int MAX_CLIENTS;
    private static int PORT;
    private ServerSocket serverSocket;
    private ThreadPool threadPool;
    private volatile boolean isRunning;

    public Server(int port, int maxClients) {
        PORT = port;
        MAX_CLIENTS = maxClients;
        isRunning = true;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            threadPool = new ThreadPool(MAX_CLIENTS);
            System.out.println("Servidor iniciado en el puerto " + PORT);

            while (isRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                    // Asignar un hilo del pool para manejar la conexión del cliente
                    HandlerClient handler = new HandlerClient(clientSocket);
                    threadPool.execute(handler);
                } catch (IOException e) {
                    if (!isRunning) break; // salir del bucle si el servidor se ha detenido intencionalmente
                    System.out.println("Error aceptando conexión: " + e.getMessage());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopServer();
        }
    }

    public void stopServer() {
        isRunning = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error cerrando el servidor: " + e.getMessage());
        }
    }

}
