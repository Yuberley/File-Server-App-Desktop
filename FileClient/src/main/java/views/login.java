package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import views.clientsactions;
import startup.ConnectionServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;

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
            Socket socket = ConnectionServer.start(host, portString);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());


            JOptionPane.showMessageDialog(null, "Conexión establecida exitosamente.");
            frame.dispose();
            clientsactions clients = new clientsactions();
            clients.setSocket(socket);
            clients.setInput(input);
            clients.setOutput(output);
            clients.main(new String[]{host, portString});
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
