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
        String nazwa_uzytkownika = userNameInput.getText();
        String email = emailInput.getText();
        String haslo = new String(passwordInput1.getPassword());
        String haslo1 = new String(passwordInput2.getPassword());
        String imie = firstnameInput.getText();
        String nazwisko = surnameInput.getText();

        String missingFields = "";
        if (imie.isEmpty()) missingFields += "Imię, ";
        if (nazwisko.isEmpty()) missingFields += "Nazwisko, ";
        if (nazwa_uzytkownika.isEmpty()) missingFields += "Nazwa użytkownika, ";
        if (email.isEmpty()) missingFields += "Email, ";
        if (haslo.isEmpty()) missingFields += "Hasło, ";

        if (!missingFields.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Brakujące pola: " + missingFields.substring(0, missingFields.length() - 2));
            return;
        }

        if (!validateEmail(email)) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy adres email.");
            return;
        }

        if (!validatePassword(haslo)) {
            JOptionPane.showMessageDialog(this, "Hasło musi zawierać 1 znak specjalny, 1 cyfrę, 1 wielką literę i mieć minimum 8 znaków.");
            return;
        }

        if (!haslo.equals(haslo1)) {
            JOptionPane.showMessageDialog(this, "Hasła nie są zgodne.");
            return;
        }


        registerUser(nazwa_uzytkownika, email, haslo, imie, nazwisko);
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

    private void registerUser(String nazwa_uzytkownika, String email, String haslo, String imie, String nazwisko) {
        try (Connection connection = Database.getConnection()) {
            String query = "INSERT INTO user (id_user, nazwa_uzytkownika, email, haslo, imie, nazwisko, Admin) VALUES (null, ?, ?, ?, ?, ?, 0)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nazwa_uzytkownika);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, haslo);
            preparedStatement.setString(4, imie);
            preparedStatement.setString(5, nazwisko);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Rejestracja zakończona sukcesem.");
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
                dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas rejestracji: " + ex.getMessage());
        }
    }
}
