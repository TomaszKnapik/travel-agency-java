import Models.Offer;
import Models.User;
import Services.OfferService;
import Services.ReservationService;
import Services.UserService;

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

public class ReservationMenagement extends JFrame {
    private JList<Offer> offersList;
    private JButton cancelReservationButton;
    private JButton backButton;
    private JList<User> usersList;
    private JPanel reservationMenu;
    private JButton clearButton;

    private boolean isUserSelected = false;
    private boolean isOfferSelected = false;

    private boolean isSelected() {
        return isUserSelected || isOfferSelected;
    }

    public ReservationMenagement() {
        setTitle("Rezerwacje podróży");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.reservationMenu);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        UiDesigner.applyStyles();

        try {
            setIconImage(ImageIO.read(new File("src/icon.png")));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Wystąpił błąd przy wczytywaniu icon.png.");
        }

        loadOffers();
        loadUsers();
        cancelReservationButton.setEnabled(false);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isOfferSelected = false;
                isUserSelected = false;
                loadOffers();
                loadUsers();
                cancelReservationButton.setEnabled(false);
            }
        });

        usersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    User selectedUser = usersList.getSelectedValue();
                    if (selectedUser != null) {
                        if(!isOfferSelected) {
                            isUserSelected = true;
                            loadUserOffers(selectedUser);
                        }
                    }
                    updateCancelButtonState();
                }
            }
        });

        offersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Offer selectedOffer = offersList.getSelectedValue();
                    if (selectedOffer != null) {
                        if(!isUserSelected) {
                            isOfferSelected = true;
                            loadOfferUsers(selectedOffer);
                        }
                    }
                    updateCancelButtonState();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminDashboard adminDashboard = new AdminDashboard();
                adminDashboard.setVisible(true);
                dispose();
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selectedUser = usersList.getSelectedValue();
                Offer selectedOffer = offersList.getSelectedValue();
                if (selectedUser != null && selectedOffer != null) {
                    int result = JOptionPane.showConfirmDialog(
                            reservationMenu,
                            "Czy na pewno chcesz anulować rezerwację dla użytkownika " +
                                    selectedUser.getName() + " i oferty " + selectedOffer.getName() + "?",
                            "Potwierdzenie",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            ReservationService.removeReservation(selectedOffer.getOfferId(), selectedUser.getUserId());
                            JOptionPane.showMessageDialog(reservationMenu, "Rezerwacja anulowana.", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                            isOfferSelected = false;
                            isUserSelected = false;
                            loadOffers();
                            loadUsers();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(reservationMenu, "Nie udało się anulować rezerwacji", "Błąd", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(reservationMenu, "Proszę wybrać użytkownika i ofertę.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadUsers() {
        DefaultListModel<User> listModel = new DefaultListModel<>();
        try {
            List<User> users = UserService.getUsers();
            for (User user : users) {
                listModel.addElement(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        usersList.setModel(listModel);
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

    private void loadUserOffers(User user) {
        DefaultListModel<Offer> listModel = new DefaultListModel<>();
        try {
            List<Offer> offers = ReservationService.getOffersForUser(user.getUserId());
            for (Offer offer : offers) {
                listModel.addElement(offer);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania ofert użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        offersList.setModel(listModel);
    }

    private void loadOfferUsers(Offer offer) {
        DefaultListModel<User> listModel = new DefaultListModel<>();
        try {
            List<User> users = ReservationService.getUsersForOffer(offer.getOfferId());
            for (User user : users) {
                listModel.addElement(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania użytkowników dla oferty.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        usersList.setModel(listModel);
    }

    private void updateCancelButtonState() {
        boolean userSelected = usersList.getSelectedValue() != null;
        boolean offerSelected = offersList.getSelectedValue() != null;
        cancelReservationButton.setEnabled(userSelected && offerSelected);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReservationMenagement().setVisible(true);
            }
        });
    }
}
