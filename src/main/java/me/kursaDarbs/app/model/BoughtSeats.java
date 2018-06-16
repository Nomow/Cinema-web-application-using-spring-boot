package me.kursaDarbs.app.model;
import javax.persistence.*;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "BoughtSeats")
public class BoughtSeats {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "payment_system_id")
    private PaymentSystem paymentSystem;

    @Column(name="row")
    private int row;

    @Column(name="col")
    private int col;

    @Column(name="email")
    private String email;

    @Column(name="order_number")
    private String orderNumber;

    @Column(name="phone_number")
    private String phoneNumber;
            ;
    public BoughtSeats(){}

    public Integer GetId() {
        return id;
    }

    public Session GetSession() {
        return session;
    }

    public PaymentSystem GetPaymentSystem() {
        return paymentSystem;
    }

    public Integer GetRow() {
        return row;
    }

    public Integer GetCol() {
        return col;
    }

    public String GetEmail() {
        return email;
    }

    public String GetOrderNumber() {
        return orderNumber;
    }

    public String GetPhoneNumber() {
        return phoneNumber;
    }
}
