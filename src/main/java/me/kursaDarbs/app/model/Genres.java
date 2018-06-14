package me.kursaDarbs.app.model;
import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "Genres")
public class Genres {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_;
    @Column(name="name")
    private String name_;

    public Genres(){}

    public Integer GetId() {
        return id_;
    }

    public String GetName() {
        return name_;
    }

    @Override
    public String toString() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", id_);
            jsonObj.put("name", name_);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }

}