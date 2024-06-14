import Models.User;
import Services.UserService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class UsersMenagement extends JFrame {
    private JPanel titlePanel;
    private JList<User> usersList;
    private JLabel userNumberField;
    private JButton editButton;
    private JButton delButton;
    private JButton addNewButton;
    private JButton backButton;
    private JPanel userMenagement;


    public UsersMenagement() {
        super("Zarządzanie użytkonikami");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.userMenagement);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

        loadUsers();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminDashboard adminDashboard = new AdminDashboard();
                adminDashboard.setVisible(true);
            }
        });

        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                EditUser editUser = new EditUser();
//                editUser.setVisible(true);
//                dispose();
            }
            //TODO Dodać EditUser
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selectedUser = (User) usersList.getSelectedValue();
                if (selectedUser != null) {
                    int userNumber = selectedUser.getUserId();
//                    EditUser editUser = new EditUser(userNumber);
//                    editUser.setVisible(true);
//                    dispose();
                } else {
                    JOptionPane.showMessageDialog(UsersMenagement.this, "Wybierz użytkownika do edycji.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selectedUser = usersList.getSelectedValue();
                if (selectedUser != null) {
                    int userNumber = selectedUser.getUserId();
                    deleteUser(userNumber);
                    loadUsers();
                } else {
                    JOptionPane.showMessageDialog(UsersMenagement.this, "Wybierz użytkownika do usunięcia.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        usersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    User selectedUser = usersList.getSelectedValue();
                    if (selectedUser != null) {
                        int userNumber = selectedUser.getUserId();
                        userNumberField.setText("Wybrano użytkownika o numerze ID: " + userNumber);
                    }
                }
            }
        });
    }

    private void loadUsers() {
        DefaultListModel<User> listModel = new DefaultListModel<>();
        try {
            List<User> users = UserService.getUsers();
            for (User user : users) {
                listModel.addElement(user);
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        usersList.setModel(listModel);
    }

    private void deleteUser(int userId) {
        try {
            UserService.removeUser(userId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas usuwania użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
