package tsp.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "roundtrip", schema = "tsp_db", catalog = "")
public class RoundtripEntity {
    private int id;
    private Timestamp createDate;
    private String name;
    private double distance;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "distance")
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setMembers(Timestamp create_date, String name, double distance) {
        this.createDate = create_date;
        this.name = name;
        this.distance = distance;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundtripEntity that = (RoundtripEntity) o;
        return id == that.id &&
                Double.compare(that.distance, distance) == 0 &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, name, distance);
    }
}
