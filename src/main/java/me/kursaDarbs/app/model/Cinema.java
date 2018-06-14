package me.kursaDarbs.app.model;
import javax.persistence.*;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "Cinema")
public class Cinema {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_;

    @Column(name="name")
    private String name_;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city_;

    @Column(name="address")
    private String address_;

    @Column(name="phone_number")
    private String phoneNumber_;

    @Column(name="email")
    private String email_;

    @Column(name="latitude")
    private double latitude_;

    @Column(name="longitude")
    private double longitude_;


    public Cinema(){}

    public Integer GetId() {
        return id_;
    }

    public String GetName() {
        return name_;
    }

    public String GetCity() {
        return city_.GetName();
    }

    public String GetAddress() {
        return address_;
    }

    public String GetPhoneNumber() {
        return phoneNumber_;
    }

    public String GetEmail() {
        return email_;
    }


    public double GetLatttidue() {
        return latitude_;
    }

    public double GetLongitude() {
        return longitude_;
    }

    @Override
    public String toString() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", id_);
            jsonObj.put("name", name_);
            jsonObj.put("city", GetCity());
            jsonObj.put("address", address_);
            jsonObj.put("phoneNumber", phoneNumber_);
            jsonObj.put("email", email_);
            jsonObj.put("latitude", latitude_);
            jsonObj.put("longitude", longitude_);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }

}
