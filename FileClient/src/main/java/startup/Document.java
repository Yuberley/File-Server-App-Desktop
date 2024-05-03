package startup;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class Document {

    public static void upload(String path) throws IOException {
        System.out.println("Subiendo documento: " + path);

        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        fis.read(buffer);
        fis.close();

        String encodedContent = Base64.getEncoder().encodeToString(buffer);

        // Crear objeto JSON para enviar
        JSONObject json = new JSONObject();
        json.put("accion", "archivo");
        json.put("fileName", file.getName());
        json.put("file", encodedContent);

        Socket socket;

        // Conectar con el servidor y enviar el JSON
        ConnectionServer connectionServer = new ConnectionServer();
        socket = connectionServer.start();
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());

            output.writeUTF(json.toString());
            output.flush();

            // Leer la respuesta del servidor
            String response = input.readUTF();
            System.out.println("Respuesta del servidor: " + response);

            System.out.println("Datos JSON enviados al servidor.");


    }

    public static void getDocuments(String nameFile) throws IOException {
        System.out.println("Listando archivos en el servidor");

        // Crear objeto JSON para enviar
        JSONObject json = new JSONObject();
        json.put("accion", "descargararchivo");
        json.put("fileName", nameFile);

        Socket socket;

        // Conectar con el servidor y enviar el JSON
        ConnectionServer connectionServer = new ConnectionServer();
        socket = connectionServer.start();
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());

        output.writeUTF(json.toString());
        output.flush();

        // Leer la respuesta del servidor
        String response = input.readUTF();
        JSONObject jsonObject = new JSONObject(response);

        // Revisa si hay error
        if (jsonObject.has("error")) {
            System.out.println("Error: " + jsonObject.getString("error"));
            return;
        }

        // Decodificar la información del archivo
        String encodedFile = jsonObject.getString("file");
        byte[] fileData = Base64.getDecoder().decode(encodedFile);
        FileOutputStream fos = new FileOutputStream("client_files/" + jsonObject.getString("filename"));
        fos.write(fileData);
        fos.close();
        System.out.println("Archivo descargado correctamente");
    }

}
