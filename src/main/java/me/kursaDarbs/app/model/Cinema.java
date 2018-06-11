package me.kursaDarbs.app.model;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Cinema")
public class Cinema {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_;
    @Column(name="name")
    private String name_;
    @Column(name="latitude")
    private double latitude_;
    @Column(name="longitude")
    private double longitude_;


    public Cinema(){}

    public Long getId() {
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
        return String.format(
                "Customer[id=%d, name='%s']",
                id_, name_);
    }
}
