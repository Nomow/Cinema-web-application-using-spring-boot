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

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname
            ;         ;
    public BoughtSeats(){}

    public Integer GetId() {
        return id;
    }
    public void SetId(Integer id) { this.id = id; }

    public Session GetSession() {
        return session;
    }
    public void SetSession(Session session) { this.session = session;}

    public PaymentSystem GetPaymentSystem() {
        return paymentSystem;
    }
    public void SetPaymentSystem(PaymentSystem paymentSystem) { this.paymentSystem = paymentSystem;}


    public Integer GetRow() {
        return row;
    }
    public void SetRow(Integer row) { this.row = row; }

    public Integer GetCol() {
        return col;
    }
    public void SetCol(Integer col) { this.col = col; }

    public String GetEmail() {
        return email;
    }
    public void SetEmail(String email) { this.email = email; }


    public String GetOrderNumber() {
        return orderNumber;
    }
    public void SetOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }


    public String GetName() {
        return name;
    }
    public void SetName(String name) { this.name = name; }

    public String GetSurname() {
        return surname;
    }
    public void SetSurname(String surname) { this.surname = surname; }


}
