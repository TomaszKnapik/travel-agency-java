import Models.Offer;
import Models.Reservation;
import Models.User;
import Models.UserSingleton;
import Services.OfferService;
import Services.ReservationService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ViewOffersPanel extends JFrame {
    Reservation reservation;
    private JList<Offer> offersList;
    private JLabel offerNameField;
    private JButton bookButton;
    private JButton backButton;
    private JPanel viewOffers;
    private JTextArea descriptionField;
    private JLabel dateFromField;
    private JLabel dateToField;
    private JLabel priceField;
    private JLabel reservationStatus;

    public ViewOffersPanel(int userID) {
        setTitle("Dostępne oferty podróży");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.viewOffers);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

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
                        checkIfReserved(selectedOffer.getOfferId());
                    }
                }
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Offer selectedOffer = offersList.getSelectedValue();
                if (selectedOffer != null) {
                    int offerNumber = selectedOffer.getOfferId();
                    try {
                        reservation = new Reservation(
                                -1,
                                UserSingleton.getInstance().getUser().getUserId(),
                                selectedOffer.getOfferId(),
                                1);
                        ReservationService.createReservation(reservation);
                        JOptionPane.showMessageDialog(ViewOffersPanel.this, "Rezerwacja została pomyślnie dokonana.", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                        checkIfReserved(selectedOffer.getOfferId());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(ViewOffersPanel.this, "Błąd podczas dokonywania rezerwacji.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ViewOffersPanel.this, "Wybierz ofertę do rezerwacji.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void checkIfReserved(int offerId) {
        try {
            boolean reserved = ReservationService.userHasReservation(UserSingleton.getInstance().getUser().getUserId(), offerId);
            reservationStatus.setText(reserved ? "Zarezerwowano" : "Brak rezerwacji");
            bookButton.setEnabled(!reserved);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas sprawdzania statusu oferty.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadOffers() {
        DefaultListModel<Offer> listModel = new DefaultListModel<>();
        try {
            List<Offer> offers = OfferService.getOffers();
            for (Offer offer : offers) {
                listModel.addElement(offer);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania ofert.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        offersList.setModel(listModel);
    }
}
