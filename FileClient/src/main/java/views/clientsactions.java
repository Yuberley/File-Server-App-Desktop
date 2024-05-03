package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import startup.Document;
import startup.ListFiles;

public class clientsactions {
    private JPanel ventana;
    private JButton añadirArchivoButton;
    private JTextArea listar;
    private JButton archivos;
    private JButton obtenerArchivoButton;
    private JTextField textField1;
    private JButton listarClientesButton;

    public static Socket socket;
    public static DataInputStream input;
    public static DataOutputStream output;

    public clientsactions() {
        añadirArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Archivo seleccionado: " + selectedFile.getAbsolutePath());
                    try {
                        Document.upload(selectedFile.getAbsolutePath(), input, output);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        archivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Listando archivos...");
                    String filesList = ListFiles.listFiles(input, output);
                    listar.setText(filesList);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        obtenerArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = textField1.getText().trim();
                try {
                    Document.getDocuments(fileName, input, output);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("clientsactions");
        frame.setContentPane(new clientsactions().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // Aquí puedes crear y configurar componentes que no son directamente parte de la plantilla del diseñador
    }
}
