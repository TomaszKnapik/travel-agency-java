import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public RegisterForm(){
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
    }
}
