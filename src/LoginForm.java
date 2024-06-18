import Models.User;
import Models.UserSingleton;
import Services.UserService;

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
                    User user = UserService.getUser(emailInput.getText(), new String(passwordField1.getPassword()));
                    if (user == null) {
                        JOptionPane.showMessageDialog(LoginForm.this, "Nieprawidłowa nazwa użytkownika lub hasło.", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int userRole = user.getUserRole();

                    new UserSingleton(user);

                    switch (userRole) {
                        case 0:
                            UserDashboard userDashboard = new UserDashboard(); // assuming you have a UserDashboard class
                            userDashboard.setVisible(true);
                            dispose();
                            break;
                        case 1:
                            AdminDashboard adminDashboard = new AdminDashboard();
                            adminDashboard.setVisible(true);
                            dispose();
                            break;
                        default:
                            JOptionPane.showMessageDialog(LoginForm.this, "Nieprawidłowa konfiguracja uprawnień użytkownika.", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
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
}
