package startup;
import java.net.Socket;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class HandlerClient implements Runnable {

    private Socket clientSocket;
    private String clientName;

    public HandlerClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            // Leer el nombre del archivo enviado
            String fileName = input.readUTF();
            File file = new File("server_files/" + fileName);

            // Crear directorio si no existe
            file.getParentFile().mkdirs();

            // Escribir el contenido del archivo recibido
            FileOutputStream fileOutput = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, bytesRead);
            }
            fileOutput.close();
            System.out.println("Archivo recibido y guardado: " + file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
