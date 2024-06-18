package Models;

import java.sql.Date;

public class Offer {
    int offerId;
    String name;
    String description;
    Date dateFrom;
    Date dateTo;
    double price;

    public Offer(int offerId, String name, String description, Date dateFrom, Date dateTo, double price) {
        this.offerId = offerId;
        this.name = name;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
    }

    public int getOfferId() {
        return offerId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Nr: " + offerId + ", " + name ;
    }
}
