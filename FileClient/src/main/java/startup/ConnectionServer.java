package startup;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionServer {

    public Socket start() throws UnknownHostException, IOException {
        String HOST = "127.0.0.1"; // IP del servidor
        int PORT = 9090; // Puerto del servidor

        System.out.printf("Conectando al servidor en %s:%d\n", HOST, PORT);

        InetAddress IP = InetAddress.getByName(HOST);
        Socket socket = new Socket(IP, PORT);

        System.out.println("Conexión establecida");

        return socket; // Retornamos el socket para ser utilizado fuera de este método
    }
}
