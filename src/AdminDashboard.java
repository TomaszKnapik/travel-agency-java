import Models.User;
import Models.UserSingleton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
    private JButton StatisticButton;

    private User user;

    public AdminDashboard() {
        super("Panel administracyjny");

        UiDesigner.applyStyles();

        this.user = UserSingleton.getInstance().getUser();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.adminPanel);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

        try {
            setIconImage(ImageIO.read(new File("src/icon.png")));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Wystąpił błąd przy wczytywaniu icon.png.");
        }

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
                ReservationMenagement reservationMenagement = new ReservationMenagement();
                reservationMenagement.setVisible(true);
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
        StatisticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatisticPanel statisticPanel = new StatisticPanel();
                statisticPanel.setVisible(true);
                dispose();
            }
        });
    }
}
