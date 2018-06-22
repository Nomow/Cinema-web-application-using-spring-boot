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
    public void SetId(Integer id) { this.id = id; }

    public String GetName() {
        return name;
    }
    public void SetName(String name) { this.name = name; }

    public String GetDescription() {
        return description;
    }
    public void SetDescription(String description) { this.description = description; }

}