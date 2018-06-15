package me.kursaDarbs.app.model;
import javax.persistence.*;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "Hall")
public class Hall {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema_;

    @Column(name="rows")
    private int rows_;

    @Column(name="cols")
    private int cols_;


    public Hall(){}

    public Integer GetId() {
        return id_;
    }

    public Cinema GetCinema() {
        return cinema_;
    }

    public Integer GetRows() {
        return rows_;
    }

    public Integer GetCols() {
        return cols_;
    }


    @Override
    public String toString() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", id_);
            jsonObj.put("cinema_name", cinema_.GetName());
            jsonObj.put("rows", rows_);
            jsonObj.put("cols", cols_);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }

}
