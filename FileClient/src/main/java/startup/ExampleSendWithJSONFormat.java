package startup;

public class ExampleSendWithJSONFormat {

    // Establecer la dirección IP del servidor
//        try {
//        InetAddress IP = InetAddress.getByName(HOST);
//
//        // Archivo a enviar
//        File file = new File("C:\\Users\\yuber\\Desktop\\client-server-architecture\\FileClient\\src\\main\\java\\startup\\information.txt");
//        FileInputStream fis = new FileInputStream(file);
//        byte[] buffer = new byte[(int) file.length()];
//        fis.read(buffer);
//        fis.close();
//
//        // Codificar el contenido del archivo en Base64 para enviarlo como un string JSON
//        String encodedContent = Base64.getEncoder().encodeToString(buffer);
//
//        // Crear objeto JSON para enviar
//        JSONObject json = new JSONObject();
//        json.put("cliente", "Pepito");
//        json.put("fileName", file.getName());
//        json.put("file", encodedContent);
//
//        // Conectar con el servidor y enviar el JSON
//        try (Socket socket = new Socket(IP, PORT);
//             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
//             DataInputStream input = new DataInputStream(socket.getInputStream())) {
//
//            output.writeUTF(json.toString());
//            output.flush();
//
//            // Leer la respuesta del servidor
//            String response = input.readUTF();
//            System.out.println("Respuesta del servidor: " + response);
//
//            System.out.println("Datos JSON enviados al servidor.");
//            Thread.sleep(10000); // Esperar 10 minutos antes de cerrar la conexión
//
//        }
//    } catch (Exception e) {
//        System.out.printf("Error: %s\n", e.getMessage());
//    }
}
