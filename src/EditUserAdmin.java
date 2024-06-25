import Models.User;
import Services.OfferService;
import Services.UserService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class EditUserAdmin extends JFrame {

    User user;
    private JLabel PhotoLabel;
    private JButton backButton;
    private JButton saveButton;
    private JTextField nameInput;
    private JTextField surNameInput;
    private JTextField userNameInput;
    private JTextField passwordInput;
    private JTextField emailInpup;
    private JPanel editUserAdmin;
    private JCheckBox adminCheckBox;
    private JLabel titleLabel;

    private boolean editMode = false;

    public EditUserAdmin(int userID) {
        super("Edycja użytkownika");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.editUserAdmin);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        UiDesigner.applyStyles();

        try {
            setIconImage(ImageIO.read(new File("src/icon.png")));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Wystąpił błąd przy wczytywaniu icon.png.");
        }

        loadUser(userID);
        setTitleLabel();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    user.setUsername(userNameInput.getText());
                    user.setName(nameInput.getText());
                    user.setSureName(surNameInput.getText());
                    user.setEmail(emailInpup.getText());
                    user.setPassword(passwordInput.getText());
                    user.setUserRole(adminCheckBox.isSelected() ? 1 : 0);
                }
                if (editMode) {
                    updateUser();
                } else {
                    createUser();
                }
                setTitleLabel();
            }
        });



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersMenagement usersMenagement = new UsersMenagement();
                usersMenagement.setVisible(true);
                dispose();
            }
        });
    }

    private void setTitleLabel() {
        titleLabel.setText(editMode ? "Edycja użytkownika " + user.getUsername() : "Dodawanie użytkownika");
        saveButton.setText(editMode ? "Zapisz zmiany" : "Dodaj użytkownika");
    }

    private void loadUser(int userID) {
        try {
            if (userID > 0){
                user = UserService.getUser(userID);
            }

            if (user == null) {
                user = new User(0, null, null, null, null, null, 0);
                return;
            }

            editMode = true;

            userNameInput.setText(user.getUsername());
            nameInput.setText(user.getName());
            surNameInput.setText(user.getSureName());
            emailInpup.setText(user.getEmail());
            passwordInput.setText(user.getPassword());
            adminCheckBox.setSelected(user.getUserRole() == 0);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUser() {
        try {
            UserService.updateUser(user.getUserId(), user);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas zapisu użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            JOptionPane.showMessageDialog(this, "Zmiany zostały zapisane", "Edycja", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void createUser() {
        try {
            int id = UserService.createUser(user);
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, "Błąd podczas dodawania użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }
            user.setUserId(id);
            editMode = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas dodawania użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        StringBuilder missingFields = new StringBuilder();

        if (nameInput.getText().trim().isEmpty()) {
            missingFields.append("imie, ");
        }
        if (surNameInput.getText().trim().isEmpty()) {
            missingFields.append("nazwisko, ");
        }
        if (userNameInput.getText() == null) {
            missingFields.append("nazwa użytkownika, ");
        }
        if (emailInpup.getText() == null) {
            missingFields.append("email, ");
        }
        if (passwordInput.getText().trim().isEmpty()) {
            missingFields.append("hasło, ");
        }

        if (missingFields.length() > 0) {
            missingFields.setLength(missingFields.length() - 2);
            JOptionPane.showMessageDialog(this, "Wypełnij brakujące pola: " + missingFields.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
