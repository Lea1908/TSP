package tsp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tsp_startcity", schema = "tsp_db", catalog = "")
public class TspStartCityEntity implements Serializable {
    private int id;
    private int tsp_id;
    private int city_id;
    public TspStartCityEntity() {}
    public TspStartCityEntity(int tsp_id, int city_id) {
        this.tsp_id = tsp_id;
        this.city_id = city_id;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "tsp_id")
    public int getTsp_id() {
        return tsp_id;
    }

    public void setTsp_id(int tsp_id) {
        this.tsp_id = tsp_id;
    }
    @Id
    @Column(name = "city_id")
    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TspStartCityEntity entity = (TspStartCityEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
