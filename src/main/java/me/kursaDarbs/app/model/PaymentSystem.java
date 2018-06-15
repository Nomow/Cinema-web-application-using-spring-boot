package me.kursaDarbs.app.model;
import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "PaymentSystem")
public class PaymentSystem {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_;
    @Column(name="name")
    private String name_;
    @Column(name="description")
    private String description_;

    public PaymentSystem(){}

    public Integer GetId() {
        return id_;
    }
    public String GetName() {
        return name_;
    }
    public String GetDescription() {
        return description_;
    }
}