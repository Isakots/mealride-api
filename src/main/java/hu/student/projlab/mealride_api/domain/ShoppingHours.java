package hu.student.projlab.mealride_api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Time;

@Getter
@Setter
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
}
