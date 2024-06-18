import Models.User;
import Models.UserSingleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard extends JFrame {
    private JPanel userPanel;
    private JLabel PhotoLabel;
    private JButton logOutButton;
    private JButton exitButton;
    private JButton viewOffersButton;
    private JButton accountMenagementButton;
    private JButton viewUserOffersButton;

    private User user;

    public UserDashboard() {
        super("Panel użytkownika");

        this.user = UserSingleton.getInstance().getUser();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.userPanel);
        this.setSize(800, 600);
        setLocationRelativeTo(null);


        exitButton.addActionListener(new ActionListener() {
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
        accountMenagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = user.getUserId();
                EditUser editUser = new EditUser(userId);
                editUser.setVisible(true);
                dispose();
            }
        });
        viewOffersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = user.getUserId();
//                BookOffer bookOffer = new BookOffer(userId);
//                bookOffer.setVisible(true);
                dispose();
            }
        });
        viewOffersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = user.getUserId();
                ViewOffersPanel viewOffersPanel = new ViewOffersPanel(userId);
                viewOffersPanel.setVisible(true);
                dispose();
            }
        });
    }
}
