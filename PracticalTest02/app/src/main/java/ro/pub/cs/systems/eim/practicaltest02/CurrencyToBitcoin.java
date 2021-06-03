package ro.pub.cs.systems.eim.practicaltest02;

import java.util.Date;

public class CurrencyToBitcoin {
    private String currency;
    private String updatedAt;
    private String price;

    public CurrencyToBitcoin() {
    }

    public CurrencyToBitcoin(String currency, String updatedAt, String price) {
        this.currency = currency;
        this.updatedAt = updatedAt;
        this.price = price;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CurrencyToBitcoin{" +
                "currency='" + currency + '\'' +
                ", updatedAt=" + updatedAt +
                ", price='" + price + '\'' +
                '}';
    }
}
