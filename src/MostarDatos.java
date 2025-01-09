// Richard Padilla
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MostarDatos {
    public JButton verInscripcionesButton;
    public JLabel VerDatos; // Keep this as is
    public JPanel Datitos;
    private JButton registrarseButton;

    public MostarDatos() {
        verInscripcionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/Concursos";
                String user = "root";
                String password = "123456";

                try (Connection conexionMySQL = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT * FROM Inscripciones";
                    PreparedStatement statement = conexionMySQL.prepareStatement(query);
                    ResultSet rs = statement.executeQuery();

                    // Construir un String con los resultados
                    StringBuilder resultados = new StringBuilder("<html>");
                    while (rs.next()) {
                        resultados.append("ID: ").append(rs.getString("cod"))
                                .append(",  Nombre: ").append(rs.getString("nombre"))
                                .append(",  Apellido: ").append(rs.getString("apellido"))
                                .append(",  Edad: ").append(rs.getString("edad"))
                                .append(",  Correo: ").append(rs.getString("correo"))
                                .append("<br>");
                    }
                    resultados.append("</html>");

                    // Mostrar resultados en el JLabel
                    VerDatos.setText(resultados.toString());

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos!");
                }
            }
        });


        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Registrarse");
                frame.setContentPane(new Registrarse().Registrisito);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                // Cerrar la ventana de login actual
                JFrame VerFrame = (JFrame) SwingUtilities.getWindowAncestor(Datitos);
                VerFrame.dispose();
            }
        });
    }
}
