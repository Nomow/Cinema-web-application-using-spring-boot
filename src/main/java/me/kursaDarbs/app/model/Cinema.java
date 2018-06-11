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
    @Column(name="latitude")
    private double latitude_;
    @Column(name="longitude")
    private double longitude_;


    public Cinema(){}

    public Integer getId() {
        return id_;
    }



    public String GetName() {
        return name_;
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
            jsonObj.put("latitude", latitude_);
            jsonObj.put("longitude", longitude_);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }

}
