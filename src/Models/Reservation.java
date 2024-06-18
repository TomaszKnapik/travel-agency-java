package Models;

public class Reservation {
    int reservationID;
    int userID;
    int offerID;
    int active;

    public Reservation(int reservationID, int userID, int offerID, int active) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.offerID = offerID;
        this.active = active;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getOfferID() {
        return offerID;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }
    @Override
    public String toString() {
        return "Numer rezerwacji: " + reservationID;
    }
}
