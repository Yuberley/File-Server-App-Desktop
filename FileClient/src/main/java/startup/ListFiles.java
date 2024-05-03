package startup;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import org.json.JSONArray;

public class ListFiles {

    public static String listFiles(DataInputStream input, DataOutputStream output) throws IOException {
        System.out.println("Listando archivos en el servidor");

        System.out.println(input);
        System.out.println(output);

        // Crear objeto JSON para enviar
        JSONObject json = new JSONObject();
        json.put("accion", "listararchivo");


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
