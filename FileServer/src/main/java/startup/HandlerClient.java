package startup;
import java.net.Socket;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class HandlerClient implements Runnable {

    private Socket clientSocket;
    private String clientName;

    public HandlerClient(Socket clientSocket, String clientName) {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
    }

//    @Override
//    public void run() {
//        try {
//            System.out.printf("Client connected: %s\n", clientSocket.getInetAddress().getHostAddress());
//
//            // Get the input and output streams
//            InputStream input = clientSocket.getInputStream();
//            OutputStream output = clientSocket.getOutputStream();
//
//            // Read the file name
//            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//            String fileName = reader.readLine();
//            System.out.printf("File name: %s\n", fileName);
//
//            // Read the file size
//            long fileSize = Long.parseLong(reader.readLine());
//            System.out.printf("File size: %d\n", fileSize);
//
//            // Read the file
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            FileOutputStream fileOutput = new FileOutputStream("uploads/" + fileName);
//            while (fileSize > 0 && (bytesRead = input.read(buffer, 0, (int) Math.min(buffer.length, fileSize))) != -1) {
//                fileOutput.write(buffer, 0, bytesRead);
//                fileSize -= bytesRead;
//            }
//            fileOutput.close();
//
//            // Send the response
//            PrintWriter writer = new PrintWriter(output, true);
//            writer.println("File uploaded successfully");
//
//            // Close the client socket
//            clientSocket.close();
//
//        } catch (Exception e) {
//            System.out.printf("Error: %s", e.getMessage());
//        }
//    }


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
