import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    private JPanel LoginPanel;
    private JPanel titlePanel;
    private JPanel loginFormPanel;
    private JPanel accPanelBack;
    private JTextField emailInput;
    private JLabel emailField;
    private JLabel passwordInput;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton signUpButton;
    private JButton backButton;
    private JPanel PhotoPanel;
    private JLabel PhotoLabel;

    public LoginForm() {
        super("Zaloguj się");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.LoginPanel);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userRole = checkCredentials(emailInput.getText(), new String(passwordField1.getPassword()));
                    if (userRole == 1) {
                        dispose();
                        AdminDashboard adminDashboard = new AdminDashboard();
                        adminDashboard.setVisible(true);
                    } else if (userRole == 0) {
                        dispose();
                        UserDashboard userDashboard = new UserDashboard(); // assuming you have a UserDashboard class
                        userDashboard.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(LoginForm.this, "Nieprawidłowy email lub hasło.", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginForm.this, "Błąd połączenia z bazą danych: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegisterForm registerForm = new RegisterForm();
                registerForm.setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }

    private int checkCredentials(String nazwa_uzytkownika, String haslo) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "SELECT * FROM user WHERE nazwa_uzytkownika = ? AND haslo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nazwa_uzytkownika);
        preparedStatement.setString(2, haslo);
        ResultSet resultSet = preparedStatement.executeQuery();
        int userRole = -1;
        if (resultSet.next()) {
            userRole = resultSet.getInt("Admin");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return userRole;
    }
}
