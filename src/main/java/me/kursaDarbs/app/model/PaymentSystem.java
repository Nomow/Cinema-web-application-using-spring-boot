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
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;

    public PaymentSystem(){}

    public Integer GetId() {
        return id;
    }
    public String GetName() {
        return name;
    }
    public String GetDescription() {
        return description;
    }
}