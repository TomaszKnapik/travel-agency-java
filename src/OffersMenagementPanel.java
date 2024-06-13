import Models.Offer;
import Models.UserSingleton;
import Services.OfferService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OffersMenagementPanel extends JFrame {
    private JButton backButton;
    private JButton addNewButton;
    private JPanel offersMenagementPanel;
    private JList<Offer> offersList;
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

        loadOffers();

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
                EditOffer editOffer = new EditOffer(-1);
                editOffer.setVisible(true);
                dispose();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Offer selectedOffer = offersList.getSelectedValue();
                if (selectedOffer != null) {
                    int offerNumber = selectedOffer.getOfferId();
                    EditOffer editOffer = new EditOffer(offerNumber);
                    editOffer.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(OffersMenagementPanel.this, "Wybierz ofertę do edycji.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Offer selectedOffer = offersList.getSelectedValue();
                if (selectedOffer != null) {
                    int offerNumber = selectedOffer.getOfferId();
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
                    Offer selectedOffer = offersList.getSelectedValue();
                    if (selectedOffer != null) {
                        int offerNumber = selectedOffer.getOfferId();
                        offerNumberField.setText("Wybrano ogloszenie: " + offerNumber);
                    }
                }
            }
        });
    }

    private void loadOffers() {
        DefaultListModel<Offer> listModel = new DefaultListModel<>();
        try {
            List<Offer> offers = OfferService.getOffers();
            for (Offer offer : offers) {
                listModel.addElement(offer);
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania ofert.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        offersList.setModel(listModel);
    }

    private void deleteOffer(int offerId) {
        try {
            OfferService.removeOffer(offerId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas usuwania oferty.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
