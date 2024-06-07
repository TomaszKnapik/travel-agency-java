import javax.swing.*;

public class UserDashboard extends JFrame {
    private JPanel userPanel;

    public UserDashboard() {
        super("Zaloguj się");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.userPanel);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
    }
}
