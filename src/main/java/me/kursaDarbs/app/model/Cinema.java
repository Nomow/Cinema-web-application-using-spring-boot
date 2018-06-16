package me.kursaDarbs.app.model;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "Cinema")
public class Cinema {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;


    @Column(name="address")
    private String address;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "cinema")
    private List<Session> sessions;



    public Cinema(){}

    public Integer GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public City GetCity() {
        return city;
    }

    public String GetAddress() {
        return address;
    }

    public String GetPhoneNumber() {
        return phoneNumber;
    }

    public String GetEmail() {
        return email;
    }

    public double GetLatttidue() {
        return latitude;
    }

    public double GetLongitude() {
        return longitude;
    }

    public List<Session> GetSessions() { return sessions;}

    @Override
    public String toString() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", id);
            jsonObj.put("name", name);
            jsonObj.put("city", city.GetName());
            jsonObj.put("address", address);
            jsonObj.put("phoneNumber", phoneNumber);
            jsonObj.put("email", email);
            jsonObj.put("latitude", latitude);
            jsonObj.put("longitude", longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }

}
