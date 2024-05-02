package startup;
import java.net.Socket;
import java.io.*;
import java.util.Base64;
import org.json.JSONObject;
import storage.FileManager;

public class HandlerClient implements Runnable {

    private Socket clientSocket;
    private String clientName;
    private FileManager fileManager;

    public HandlerClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
        fileManager = new FileManager();
    }

    @Override
    public void run() {

        try (
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())
        ) {

            // Leer la cadena JSON completa desde el cliente
            String jsonData = input.readUTF();
            JSONObject jsonObject = new JSONObject(jsonData);

            // Extracción de datos desde el objeto JSON
            String clientName = jsonObject.getString("cliente");
            String fileName = jsonObject.getString("fileName");
            String fileContentBase64 = jsonObject.getString("file");

            // Guardar el archivo en el sistema de archivos
            fileManager.saveFile(fileName, Base64.getDecoder().decode(fileContentBase64));

            // Enviar confirmación al cliente
            output.writeUTF("Archivo recibido correctamente");

        } catch (IOException e) {
            System.err.println("IOException en HandlerClient: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (!clientSocket.isClosed()) {
                    clientSocket.close();
                    System.out.println("Conexión con el cliente cerrada.");
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar socket: " + e.getMessage());
                e.printStackTrace();
            }
        }


//        try (DataInputStream input = new DataInputStream(clientSocket.getInputStream());
//             DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
//
//            while (true) {  // Mantener el bucle hasta que el cliente cierre la conexión
//                try {
//                    String clientName = input.readUTF();  // Espera recibir el nombre del cliente
//                    String fileName = input.readUTF();  // Espera recibir el nombre del archivo
//
//                    System.out.println("Cliente: " + clientName);
//                    System.out.println("fileName: " + fileName);
//
//                    if (fileName == null) break;  // Salir del bucle si el cliente cerró la conexión ordenadamente
//
//                    File file = new File("server_files/" + fileName);
//                    file.getParentFile().mkdirs();
//
//                    try (FileOutputStream fileOutput = new FileOutputStream(file)) {
//                        byte[] buffer = new byte[4096];
//                        int bytesRead;
//                        while ((bytesRead = input.read(buffer)) != -1) {
//                            fileOutput.write(buffer, 0, bytesRead);
//                        }
//                    }
//                    System.out.println("Archivo recibido y guardado: " + file.getPath());
//
//                    // Enviar confirmación al cliente
//                    output.writeUTF("Archivo recibido correctamente");
//
//                } catch (EOFException e) {
//                    System.out.println("Cliente cerró la conexión: " + clientSocket.getInetAddress().getHostAddress());
//                    break;  // Salir del bucle y cerrar el socket
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("IOException en HandlerClient: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            try {
//                if (!clientSocket.isClosed()) {
//                    clientSocket.close();
//                }
//                System.out.println("Conexión con el cliente cerrada.");
//            } catch (IOException e) {
//                System.err.println("Error al cerrar socket: " + e.getMessage());
//                e.printStackTrace();
//            }
//        }
    }
}
