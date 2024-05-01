package storage;

public class FileData {
    private String file;
    private String size;

    public FileData(String file, String size) {
        this.file = file;
        this.size = size;
    }

    @Override
    public String toString() {
        return "{file: \"" + file + "\", size: \"" + size + "\"}";
    }
}