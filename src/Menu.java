import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame {
    private JPanel MenuPanel;
    private JButton exitButton;
    private JButton logInButton;
    private JButton signUpButton;
    private JLabel PhotoLabel;
    private JPanel exitPanel;
    private JPanel logInSignInPanel;
    private JPanel PhotoPanel;
    private JPanel titlePanel;

    public Menu(){
        super("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.MenuPanel);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

        try {
            setIconImage(ImageIO.read(new File("src/icon.png")));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Wystąpił błąd przy wczytywaniu icon.png.");
        }


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegisterForm registerForm = new RegisterForm();
                registerForm.setVisible(true);
            }
        });
    }
}
