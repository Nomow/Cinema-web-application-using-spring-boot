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
    private Integer id_;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session_;

    @ManyToOne
    @JoinColumn(name = "payment_system_id")
    private PaymentSystem paymentSystem_;

    @Column(name="row")
    private int row_;

    @Column(name="col")
    private int col_;

    @Column(name="email")
    private String email_;

    @Column(name="order_number")
    private String orderNumber_;

    @Column(name="phone_number")
    private String phoneNumber_;
            ;
    public BoughtSeats(){}

    public Integer GetId() {
        return id_;
    }

    public Session GetSession() {
        return session_;
    }

    public PaymentSystem GetPaymentSystem() {
        return paymentSystem_;
    }

    public Integer GetRow() {
        return row_;
    }

    public Integer GetCol() {
        return col_;
    }

    public String GetEmail() {
        return email_;
    }

    public String GetOrderNumber() {
        return orderNumber_;
    }

    public String GetPhoneNumber() {
        return phoneNumber_;
    }
}
