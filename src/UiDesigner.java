import javax.swing.*;
import java.awt.*;

public class UiDesigner {
    public static void applyStyles() {
        UIManager.put("Button.background", new Color(18, 18, 18));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Bookman Old Style", Font.PLAIN, 16));

        UIManager.put("TextField.background", new Color(30, 30, 30));
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("TextField.caretForeground", Color.WHITE);
        UIManager.put("TextField.border", BorderFactory.createLineBorder(new Color(179, 179, 179)));
        UIManager.put("TextField.font", new Font("Bookman Old Style", Font.PLAIN, 16));

        UIManager.put("Panel.background", new Color(30, 30, 30));

        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("Label.font", new Font("Bookman Old Style", Font.PLAIN, 16));

        UIManager.put("List.background", new Color(30, 30, 30));
        UIManager.put("List.foreground", Color.WHITE);
        UIManager.put("List.font", new Font("Bookman Old Style", Font.PLAIN, 16));

        UIManager.put("ScrollBar.background", Color.BLACK);
        UIManager.put("ScrollBar.thumb", new Color(80, 80, 80));
        UIManager.put("ScrollBar.thumbDarkShadow", Color.BLACK);
        UIManager.put("ScrollBar.track", Color.BLACK);
        UIManager.put("ScrollBar.trackHighlight", Color.BLACK);

        UIManager.put("TextArea.background", new Color(30, 30, 30));
        UIManager.put("TextArea.foreground", Color.WHITE);
        UIManager.put("TextArea.caretForeground", Color.WHITE);
        UIManager.put("TextArea.border", BorderFactory.createLineBorder(new Color(179, 179, 179)));
        UIManager.put("TextArea.font", new Font("Bookman Old Style", Font.PLAIN, 16));
        UIManager.put("TextArea.inactiveForeground", Color.WHITE);

        UIManager.put("PasswordField.background", new Color(30, 30, 30));
        UIManager.put("PasswordField.foreground", Color.WHITE);

        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("OptionPane.foreground", Color.WHITE);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        UIManager.put("CheckBox.background", new Color(30, 30, 30));
        UIManager.put("CheckBox.foreground", Color.WHITE);
        UIManager.put("CheckBox.font", new Font("Bookman Old Style", Font.PLAIN, 16));
    }
}
