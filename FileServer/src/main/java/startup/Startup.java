package startup;

import storage.FileData;
import storage.FileManager;

import javax.swing.*;
import java.util.List;

public class Startup {

    private static int PORT = 9090; // Server port
    private static int MAX_CLIENTS = 10; // Maximum number of clients

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--port")) {
                PORT = Integer.parseInt(args[i + 1]);
            }

            if (args[i].equals("--clients")) {
                MAX_CLIENTS = Integer.parseInt(args[i + 1]);
            }

            if (args[i].equals("--help")) {
                System.out.println("Usage: java Startup.java [--port <port>] [--clients <number of clients>]");
                return;
            }
        }

        Server server = new Server(PORT, MAX_CLIENTS);
        server.start();

//        FileManager fileManager = new FileManager();
//        String json = fileManager.listFilesAsJson();
//        System.out.println(json);

    }

}