// Richard Padilla
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrarse {
    public JTextField textNombre;
    public JTextField textApellido;
    public JTextField textEdad;
    public JTextField textCorreo;
    public JButton inscribirseButton;
    public JButton verInscripcionesButton;
    public JPanel Registrisito;

    public Registrarse() {
        inscribirseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/Concursos";
                String user = "root";
                String password = "123456";

                try (Connection conexionMYSQL = DriverManager.getConnection(url, user, password)) {

                    // Validar que la edad sea un número entero y esté en el rango permitido
                    try {
                        int edad = Integer.parseInt(textEdad.getText());
                        if (edad < 18 || edad > 40) {
                            JOptionPane.showMessageDialog(null, "Debe ser mayor de 18 años y menor de 40 años para registrarse.");
                            return;
                        }

                        String sql = "INSERT INTO Inscripciones (nombre, apellido, edad, correo) VALUES (?,?,?,?)";
                        PreparedStatement statement = conexionMYSQL.prepareStatement(sql);
                        statement.setString(1, textNombre.getText());
                        statement.setString(2, textApellido.getText());
                        statement.setInt(3, edad);
                        statement.setString(4, textCorreo.getText());

                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "¡Inscripción exitosa!");

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.");
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos.");
                    ex.printStackTrace();
                }
            }
        });

        verInscripcionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Información de Inscripciones");
                frame.setContentPane(new MostarDatos().Datitos);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(600, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                // Cerrar la ventana actual de registro
                JFrame RegistroFrame = (JFrame) SwingUtilities.getWindowAncestor(Registrisito);
                RegistroFrame.dispose();
            }
        });
    }
}
