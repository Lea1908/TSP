package tsp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "roundtrip_cities", schema = "tsp_db", catalog = "")
public class RoundtripCitiesEntity implements Serializable {
    private int id;

    @JoinColumn(name = "roundtrip_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private RoundtripEntity roundtrip;

    @JoinColumn(name = "city_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private CityEntity city;

    private int roundtripId;
    private int cityId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Id
    @Column(name = "roundtrip_id")
    public int getRoundtripId() { return roundtripId;}
    public void setRoundtripId(int id) { this.roundtripId = id; }

    @Id
    @Column(name = "city_id")
    public int getCityId() { return cityId; }
    public void setCityId(int id) { this.cityId = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundtripCitiesEntity that = (RoundtripCitiesEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
