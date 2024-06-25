import Models.Offer;
import Services.OfferService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import com.github.lgooddatepicker.components.DatePicker;

public class EditOffer extends JFrame {

    Offer offer;
    private JPanel editOfferPane;
    private JTextField nameLabel;
    private JTextField priceLabel;
    private JPanel datePickerPanelTo;
    private JButton saveButton;
    private JButton backButton;
    private JLabel PhotoLabel;
    private JTextField descLabel;
    private JPanel datePickerPanelFrom;
    private JLabel titleLabel;

    private DatePicker datePickerTo;
    private DatePicker datePickerFrom;

    private boolean editMode = false;

    public EditOffer(int offerId) {
        super("Edycja oferty");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.editOfferPane);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        UiDesigner.applyStyles();


        try {
            setIconImage(ImageIO.read(new File("src/icon.png")));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Wystąpił błąd przy wczytywaniu icon.png.");
        }

        datePickerFrom = new DatePicker();
        datePickerPanelFrom.add(datePickerFrom);

        datePickerTo = new DatePicker();
        datePickerPanelTo.add(datePickerTo);

        loadOffer(offerId);
        setTitleLabel();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    offer.setName(nameLabel.getText());
                    offer.setDescription(descLabel.getText());
                    offer.setPrice(Double.parseDouble(priceLabel.getText()));
                    offer.setDateFrom(DateUtils.dateFromLocalDate(datePickerFrom.getDate()));
                    offer.setDateTo(DateUtils.dateFromLocalDate(datePickerTo.getDate()));

                    if (editMode) {
                        updateOffer();
                    } else {
                        createOffer();
                    }
                    setTitleLabel();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OffersMenagementPanel offersMenagementPanel = new OffersMenagementPanel();
                offersMenagementPanel.setVisible(true);
                dispose();
            }
        });
    }

    private void setTitleLabel() {
        titleLabel.setText(editMode ? "Edycja oferty " + offer.getName() : "Dodawanie oferty");
        saveButton.setText(editMode ? "Zapisz zmiany" : "Dodaj oferte");
    }

    private void loadOffer(int offerId) {
        try {
            if (offerId > 0) {
                offer = OfferService.getOffer(offerId);
            }

            if (offer == null) {
                offer = new Offer(0, null, null, null, null, 0);
                return;
            }

            editMode = true;

            nameLabel.setText(offer.getName());
            descLabel.setText(offer.getDescription());
            priceLabel.setText(Double.toString(offer.getPrice()));
            datePickerFrom.setDate(DateUtils.localDateFromDate(offer.getDateFrom()));
            datePickerTo.setDate(DateUtils.localDateFromDate(offer.getDateTo()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania ofert.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateOffer() {
        try {
            OfferService.updateOffer(offer.getOfferId(), offer);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas zapisu oferty.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createOffer() {
        try {
            int id = OfferService.createOffer(offer);
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, "Błąd podczas tworzenia oferty.", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }
            offer.setOfferId(id);
            editMode = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas tworzenia oferty.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        StringBuilder missingFields = new StringBuilder();

        if (nameLabel.getText().trim().isEmpty()) {
            missingFields.append("nazwa, ");
        }
        if (descLabel.getText().trim().isEmpty()) {
            missingFields.append("opis, ");
        }
        if (datePickerFrom.getDate() == null) {
            missingFields.append("od kiedy, ");
        }
        if (datePickerTo.getDate() == null) {
            missingFields.append("do kiedy, ");
        }
        if (priceLabel.getText().trim().isEmpty()) {
            missingFields.append("cena, ");
        }

        if (missingFields.length() > 0) {
            missingFields.setLength(missingFields.length() - 2);
            JOptionPane.showMessageDialog(this, "Wypełnij brakujące pola: " + missingFields.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
