import Models.User;
import Services.UserService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditUser extends JFrame {

    User user;
    private JPanel editUser;
    private JLabel PhotoLabel;
    private JButton backButton;
    private JButton saveButton;
    private JTextField nameInput;
    private JTextField surNameInput;
    private JTextField userNameInput;
    private JPasswordField passwordInput;
    private JTextField emailInput;

    public EditUser(int userID) {
        super("Edycja użytkownika");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.editUser);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        UiDesigner.applyStyles();

        try {
            setIconImage(ImageIO.read(new File("src/icon.png")));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Wystąpił błąd przy wczytywaniu icon.png.");
        }

        loadUser(userID);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    user.setUsername(userNameInput.getText());
                    user.setName(nameInput.getText());
                    user.setSureName(surNameInput.getText());
                    user.setEmail(emailInput.getText());
                    user.setPassword(passwordInput.getText());
                    updateUser();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
                dispose();
            }
        });
    }

    private void loadUser(int userID) {
        try {
            if (userID > 0) {
                user = UserService.getUser(userID);
            }

            if (user == null) {
                user = new User(0, null, null, null, null, null, 0);
                return;
            }

            userNameInput.setText(user.getUsername());
            nameInput.setText(user.getName());
            surNameInput.setText(user.getSureName());
            emailInput.setText(user.getEmail());
            passwordInput.setText(user.getPassword());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUser() {
        try {
            UserService.updateUser(user.getUserId(), user);
            JOptionPane.showMessageDialog(this, "Zmiany zostały zapisane", "Edycja", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas zapisu użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        StringBuilder missingFields = new StringBuilder();

        if (nameInput.getText().trim().isEmpty()) {
            missingFields.append("Imię, ");
        }
        if (surNameInput.getText().trim().isEmpty()) {
            missingFields.append("Nazwisko, ");
        }
        if (userNameInput.getText().trim().isEmpty()) {
            missingFields.append("Nazwa użytkownika, ");
        }
        if (emailInput.getText().trim().isEmpty()) {
            missingFields.append("Email, ");
        }
        if (passwordInput.getText().trim().isEmpty()) {
            missingFields.append("Hasło, ");
        }

        if (missingFields.length() > 0) {
            missingFields.setLength(missingFields.length() - 2);
            JOptionPane.showMessageDialog(this, "Brakujące pola: " + missingFields.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!validateEmail(emailInput.getText())) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy adres email.");
            return false;
        }

        if (!validatePassword(passwordInput.getText())) {
            JOptionPane.showMessageDialog(this, "Hasło musi zawierać 1 znak specjalny, 1 cyfrę, 1 wielką literę i mieć minimum 8 znaków.");
            return false;
        }

        return true;
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
