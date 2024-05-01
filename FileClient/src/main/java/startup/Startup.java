package startup;

import org.json.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Base64;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Startup {
    public static void main(String[] args) {
        String HOST = "192.168.1.3"; // IP del servidor
        int PORT = 9090; // Puerto del servidor

        System.out.printf("Conectando al servidor en %s:%d\n", HOST, PORT);

        // Establecer la dirección IP del servidor
        try {
            InetAddress IP = InetAddress.getByName(HOST);

            // Archivo a enviar
            File file = new File("C:\\Users\\yuber\\Desktop\\client-server-architecture\\FileClient\\src\\main\\java\\startup\\information.txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            fis.close();

            // Codificar el contenido del archivo en Base64 para enviarlo como un string JSON
            String encodedContent = Base64.getEncoder().encodeToString(buffer);

            // Crear objeto JSON para enviar
            JSONObject json = new JSONObject();
            json.put("cliente", "Pepito");
            json.put("fileName", file.getName());
            json.put("file", encodedContent);

            // Conectar con el servidor y enviar el JSON
            try (Socket socket = new Socket(IP, PORT);
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                 DataInputStream input = new DataInputStream(socket.getInputStream())) {

                output.writeUTF(json.toString());
                output.flush();

                // Leer la respuesta del servidor
                String response = input.readUTF();
                System.out.println("Respuesta del servidor: " + response);

                System.out.println("Datos JSON enviados al servidor.");
            }
        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }
}