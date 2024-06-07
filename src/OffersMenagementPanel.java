import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OffersMenagementPanel extends JFrame {
    private JButton backButton;
    private JButton addNewButton;
    private JPanel offersMenagementPanel;
    private JList<String> offersList;
    private JPanel titlePanel;
    private JButton editButton;
    private JButton delButton;
    private JLabel offerNumberField;

    public OffersMenagementPanel() {
        super("Zarządzanie ofertami podróży");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.offersMenagementPanel);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

        loadOffers(); // Load offers from the database

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
                dispose();
//                AddOffer addOffer = new AddOffer();
//                addOffer.setVisible(true);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOffer = offersList.getSelectedValue();
                if (selectedOffer != null) {
                    int offerNumber = Integer.parseInt(selectedOffer.split(",")[0].split(":")[1].trim());
                    dispose();
//                    EditOffer editOffer = new EditOffer(offerNumber);
//                    editOffer.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(OffersMenagementPanel.this, "Wybierz ofertę do edycji.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOffer = offersList.getSelectedValue();
                if (selectedOffer != null) {
                    int offerNumber = Integer.parseInt(selectedOffer.split(",")[0].split(":")[1].trim());
                    deleteOffer(offerNumber);
                    loadOffers();
                } else {
                    JOptionPane.showMessageDialog(OffersMenagementPanel.this, "Wybierz ofertę do usunięcia.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        offersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedOffer = offersList.getSelectedValue();
                    if (selectedOffer != null) {
                        String offerNumber = selectedOffer.split(",")[0].split(":")[1].trim();
                        offerNumberField.setText(offerNumber);
                    }
                }
            }
        });
    }

    private void loadOffers() {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        try (Connection connection = Database.getConnection()) {
            String query = "SELECT id_ogloszenia, tytul, cena FROM ogloszenia";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int numerOgłoszenia = resultSet.getInt("id_ogloszenia");
                String tytulOgłoszenia = resultSet.getString("tytul");
                double cenaOgłoszenia = resultSet.getDouble("cena");

                String offer = "Nr: " + numerOgłoszenia + ", Tytuł: " + tytulOgłoszenia + ", Cena: " + cenaOgłoszenia;
                listModel.addElement(offer);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania ofert.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        offersList.setModel(listModel);
    }

    private void deleteOffer(int offerNumber) {
        try (Connection connection = Database.getConnection()) {
            String query = "DELETE FROM ogloszenia WHERE id_ogloszenia = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, offerNumber);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas usuwania oferty.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OffersMenagementPanel frame = new OffersMenagementPanel();
                frame.setVisible(true);
            }
        });
    }
}
