package storage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileManager {
    private static final String DIRECTORY_PATH = "server_files"; // Cambiar por la ruta del directorio deseado


    /**
     * Lista todos los archivos en el directorio especificado y devuelve una lista de objetos que representan
     * el nombre del archivo y su tamaño en formato JSON.
     * @return String en formato JSON con los archivos y sus tamaños.
     */
    public static String listFilesAsJson() {
        List<FileData> files = new ArrayList<>();
        Path dirPath = Paths.get(DIRECTORY_PATH);

        // Comprueba si el directorio existe
        if (!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            System.out.println("El directorio no existe o no es un directorio.");
            return new JSONArray().toString(); // Devuelve un array JSON vacío
        }

        File directory = dirPath.toFile();
        File[] fileList = directory.listFiles();

        // Preparar el array JSON
        JSONArray jsonArray = new JSONArray();

        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    JSONObject fileObj = new JSONObject();
                    fileObj.put("file", file.getName());
                    fileObj.put("size", formatSize(file.length()));
                    jsonArray.put(fileObj);
                }
            }
        }

        return jsonArray.toString();
    }

    /**
     * Formatea el tamaño del archivo de bytes a una representación más legible.
     * @param size Tamaño del archivo en bytes.
     * @return Tamaño del archivo formateado como String.
     */
    private static String formatSize(long size) {
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return (size / 1024) + " KB";
        } else {
            return (size / (1024 * 1024)) + " MB";
        }
    }

}
