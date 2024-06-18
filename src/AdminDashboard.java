import Models.User;
import Models.UserSingleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private JPanel titlePanel;
    private JPanel adminPanel;
    private JPanel PhotoPanel;
    private JLabel PhotoLabel;
    private JButton offersMenagementButton;
    private JButton usersMenagementButton;
    private JButton reservationMenagementButton;
    private JButton exitButton;
    private JButton logOutButton;

    private User user;

    public AdminDashboard() {
        super("Panel administracyjny");

        this.user = UserSingleton.getInstance().getUser();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.adminPanel);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

        offersMenagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OffersMenagementPanel offersMenagementPanel = new OffersMenagementPanel();
                offersMenagementPanel.setVisible(true);
                dispose();
            }
        });

        usersMenagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersMenagement usersMenagement = new UsersMenagement();
                usersMenagement.setVisible(true);
                dispose();
            }
        });

        reservationMenagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                UserSingleton.logout();
                menu.setVisible(true);
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
