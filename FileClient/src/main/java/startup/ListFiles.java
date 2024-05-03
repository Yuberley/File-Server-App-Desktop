package startup;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import org.json.JSONArray;

public class ListFiles {

    public static String listFiles() throws IOException {
        System.out.println("Listando archivos en el servidor");

        // Crear objeto JSON para enviar
        JSONObject json = new JSONObject();
        json.put("accion", "listararchivo");

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

        JSONArray jsonArray = new JSONArray(response);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i); // Trata cada elemento como un JSONObject
            sb.append(item.toString()).append("\n"); // Puedes cambiar esto para manejar los datos del objeto como necesites
        }
        return sb.toString();
    }

}
