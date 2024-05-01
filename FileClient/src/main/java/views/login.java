package views;

import javax.swing.*;

public class login {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton conectarseButton;
    private JPanel ventana;

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
