import Models.Offer;
import Models.Reservation;
import Models.UserSingleton;
import Services.OfferService;
import Services.ReservationService;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowOffersUser extends JFrame {
    Reservation reservation;
    private JList<Offer> offersList;
    private JLabel offerNameField;
    private JButton delButton;
    private JButton backButton;
    private JPanel viewOffers;
    private JTextArea descriptionField;
    private JLabel dateFromField;
    private JLabel dateToField;
    private JLabel priceField;
    private JButton payReservationButton;

    public ShowOffersUser(int userID) {
        setTitle("Twoje oferty podróży");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.viewOffers);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        UiDesigner.applyStyles();

        try {
            setIconImage(ImageIO.read(new File("src/icon.png")));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Wystąpił błąd przy wczytywaniu icon.png.");
        }

        loadOffers();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });

        offersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Offer selectedOffer = offersList.getSelectedValue();
                    if (selectedOffer != null) {
                        String offerName = selectedOffer.getName();
                        offerNameField.setText("Wybrano ogłoszenie: " + offerName);
                        descriptionField.setText("Opis: " + selectedOffer.getDescription());
                        dateFromField.setText("Data od: " + selectedOffer.getDateFrom().toString());
                        dateToField.setText("Data do: " + selectedOffer.getDateTo().toString());
                        priceField.setText("Cena: " + selectedOffer.getPrice() + " PLN");
                    }
                }
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Offer selectedOffer = offersList.getSelectedValue();
                if (selectedOffer != null) {
                    int offerNumber = selectedOffer.getOfferId();
                    removeReservation(offerNumber);
                    loadOffers();
                    offerNameField.setText("Wybrano ogłoszenie: ");
                    descriptionField.setText("Opis: ");
                    dateFromField.setText("Data od: ");
                    dateToField.setText("Data do: ");
                    priceField.setText("Cena: ");
                    JOptionPane.showMessageDialog(ShowOffersUser.this, "Usunięto rezerwację pomyślnie.", "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ShowOffersUser.this, "Wybierz rezerwacje do usunięcia.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        payReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void removeReservation(int offerId) {
        try {
            ReservationService.removeReservation(offerId, UserSingleton.getInstance().getUser().getUserId());
            loadOffers();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas usuwania rezerwacji.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadOffers() {
        DefaultListModel<Offer> listModel = new DefaultListModel<>();
        try {
            List<Offer> offers = OfferService.getUserOffers(UserSingleton.getInstance().getUser().getUserId());
            for (Offer offer : offers) {
                listModel.addElement(offer);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania ofert.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        offersList.setModel(listModel);
    }
}
