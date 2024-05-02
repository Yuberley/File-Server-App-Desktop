package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import views.clientsactions;

public class login {
    private JTextField textField2; // IP Address
    private JTextField textField3; // Port
    private JButton conectarseButton;
    private JPanel ventana;

    private JFrame frame; // Referencia al JFrame para poder cerrarlo

    public login() {
        frame = new JFrame("Login");
        frame.setContentPane(ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        conectarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
    }

    private void connect() {
        String host = textField2.getText().trim();
        String portString = textField3.getText().trim();
        try {
            int port = Integer.parseInt(portString);
            if (port < 1 || port > 65535) {
                JOptionPane.showMessageDialog(null, "Error: El puerto debe estar entre 1 y 65535.");
                return;
            }
            Socket socket = new Socket(host, port);
            socket.close();
            JOptionPane.showMessageDialog(null, "Conexión establecida exitosamente.");
            frame.dispose();
            clientsactions.main(new String[]{host, portString});
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El puerto debe ser un número entero.");
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Error: Host desconocido.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
