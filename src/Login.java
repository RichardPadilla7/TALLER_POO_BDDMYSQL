import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    public JTextField textField1;
    public JPasswordField passwordField1;
    public JButton iniciarSesionButton;
    public JPanel loginsito;
    public JLabel titulo;

    public Login() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/Concursos";
                String user = "root";
                String password = "1234";

                try (Connection conexionMYSQL = DriverManager.getConnection(url, user, password)) {

                    String query = "SELECT * FROM Usuarios WHERE correo = ? AND pass = ?";
                    PreparedStatement statement = conexionMYSQL.prepareStatement(query);

                    statement.setString(1, textField1.getText());
                    statement.setString(2, passwordField1.getText());

                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {

                        JOptionPane.showMessageDialog(null, "Login Correcto! Bienvenid@ " + textField1.getText());

                        JFrame frame = new JFrame("LOGIN");

                        frame.setContentPane(new Login().loginsito);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(500, 300);
                        frame.setPreferredSize(new Dimension(300, 300));
                        frame.setLocationRelativeTo(null);
                        frame.pack();
                        frame.setVisible(true);

                        // Cerrar la ventana de login actual
                        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(loginsito);
                        loginFrame.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error de conexión en la base de datos!");
                    ex.printStackTrace();
                }
            }
        });
    }
}


