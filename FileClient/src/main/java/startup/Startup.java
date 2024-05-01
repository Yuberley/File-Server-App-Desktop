package startup;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
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


            File file = new File("C:\\Users\\yuber\\Desktop\\client-server-architecture\\FileClient\\src\\main\\java\\startup\\information.txt");
            byte[] buffer = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(buffer, 0, buffer.length);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(file.getName());
            output.write(buffer, 0, buffer.length);
            output.flush();
            socket.close();

            System.out.println("Archivo enviado al servidor.");


        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return;
        }
    }
}