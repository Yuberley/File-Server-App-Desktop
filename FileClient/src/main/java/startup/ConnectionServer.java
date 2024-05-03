package startup;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionServer {

    public static Socket start(String HOST, String PORT) throws UnknownHostException, IOException {
        int port = Integer.parseInt(PORT);

        System.out.printf("Conectando al servidor en %s:%d\n", HOST, port);

        InetAddress IP = InetAddress.getByName(HOST);
        Socket socket = new Socket(IP, port);

        System.out.println("Conexión establecida");

        return socket; // Retornamos el socket para ser utilizado fuera de este método
    }
}
