package startup;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Startup {
    private static InetAddress IP;
    private static Socket socket;

    public static void main(String[] args) {
        String HOST = "192.168.1.3"; //args[1]; // "localhost";
        int PORT = 9090; //Integer.parseInt(args[3]); // 8080;

        System.out.printf("This application will open a connection for socker as server");
        System.out.printf("Host: %s\n", HOST);
        System.out.printf("Port: %d\n", PORT);

        try {

            System.out.printf("Starting server on %s:%d\n", HOST, PORT);
            IP = InetAddress.getByName(HOST);
            socket = new Socket(IP, PORT);
            System.out.printf("Server started on %s:%d\n", HOST, PORT);


        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return;
        }
    }
}