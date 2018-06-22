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
    private Integer id;
    @Column(name="name")
    private String name;

    public Genres(){}

    public Integer GetId() {
        return id;
    }
    public void SetId(Integer id) { this.id = id; }

    public String GetName() {
        return name;
    }
    public void SetName(String name) { this.name = name; }

    @Override
    public String toString() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", id);
            jsonObj.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }

}