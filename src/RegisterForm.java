import Models.User;
import Services.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterForm extends JFrame {
    private JPanel titlePanel;
    private JPanel accPanelBack;
    private JButton logInButton;
    private JButton backButton;
    private JPanel loginFormPanel;
    private JLabel emailField;
    private JTextField emailInput;
    private JLabel passwordInput;
    private JPasswordField passwordInput1;
    private JButton zarejestujButton;
    private JTextField firstnameInput;
    private JTextField surnameInput;
    private JPasswordField passwordInput2;
    private JPanel signUpPanel;
    private JPanel PhotoPanel;
    private JLabel PhotoLabel;
    private JTextField userNameInput;

    public RegisterForm() {
        super("Rejestracja");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.signUpPanel);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            }
        });

        zarejestujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegistration();
            }
        });
    }

    private void handleRegistration() {
        String password = new String(passwordInput1.getPassword());
        String password2 = new String(passwordInput2.getPassword());

        if(!password.equals(password2)) {
            JOptionPane.showMessageDialog(this, "Hasła nie są zgodne.");
            return;
        }

        User user = new User(-1, userNameInput.getText(), emailInput.getText(), password, firstnameInput.getText(), surnameInput.getText(), 0);

        String missingFields = "";
        if (user.getName().isEmpty()) missingFields += "Imię, ";
        if (user.getSureName().isEmpty()) missingFields += "Nazwisko, ";
        if (user.getUsername().isEmpty()) missingFields += "Nazwa użytkownika, ";
        if (user.getEmail().isEmpty()) missingFields += "Email, ";
        if (user.getPassword().isEmpty()) missingFields += "Hasło, ";

        if (!missingFields.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Brakujące pola: " + missingFields.substring(0, missingFields.length() - 2));
            return;
        }

        if (!validateEmail(user.getEmail())) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy adres email.");
            return;
        }

        if (!validatePassword(password)) {
            JOptionPane.showMessageDialog(this, "Hasło musi zawierać 1 znak specjalny, 1 cyfrę, 1 wielką literę i mieć minimum 8 znaków.");
            return;
        }

        try {
            UserService.createUser(user);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas rejestracji: " + ex.getMessage());
        }
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        if (password.length() < 8) return false;
        String specialChars = "(.*[!@#$%^&*].*)";
        String digits = "(.*[0-9].*)";
        String upperCaseChars = "(.*[A-Z].*)";
        return password.matches(specialChars) && password.matches(digits) && password.matches(upperCaseChars);
    }
}
