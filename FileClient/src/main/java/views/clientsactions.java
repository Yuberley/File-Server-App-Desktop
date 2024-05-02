package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import startup.Document;

public class clientsactions {
    private JPanel ventana;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JButton añadirArchivoButton;

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
                        Document.upload(selectedFile.getAbsolutePath());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
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
