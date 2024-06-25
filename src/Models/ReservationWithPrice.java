package Models;

import java.util.Date;

public class ReservationWithPrice {
    private Date date;
    private float price;

    public ReservationWithPrice(Date date, float price) {
        this.date = date;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
