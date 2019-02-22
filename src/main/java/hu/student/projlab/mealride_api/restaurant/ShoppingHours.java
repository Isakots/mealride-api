package hu.student.projlab.mealride_api.restaurant;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Time;

@Embeddable
public class ShoppingHours {

    @Column(name="OPENING_TIME")
    private Time open;
    @Column(name="CLOSING_TIME")
    private Time close;

    public ShoppingHours() {
    }

    public ShoppingHours(Time open, Time close) {
        this.open = open;
        this.close = close;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }
}
