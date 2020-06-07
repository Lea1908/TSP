package tsp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tsp_roundtrips", schema = "tsp_db", catalog = "")
public class TspRoundtripsEntity implements Serializable {
    private int id;
    private int tsp_id;
    private int roudtrip_id;
    @JoinColumn(name = "roundtrip_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private RoundtripEntity roundtrip;

    @JoinColumn(name = "tsp_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private TspEntity tsp;

    public TspRoundtripsEntity() {}
    public TspRoundtripsEntity(int tsp_id, int roudtrip_id) {
        setTsp_id(tsp_id);
        setRoudtrip_id(roudtrip_id);
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
    @Column(name = "roundtrip_id")
    public int getRoudtrip_id() {
        return roudtrip_id;
    }

    public void setRoudtrip_id(int roudtrip_id) {
        this.roudtrip_id = roudtrip_id;
    }
    @Id
    @Column(name = "tsp_id")
    public int getTsp_id() {
        return tsp_id;
    }

    public void setTsp_id(int tsp_id) {
        this.tsp_id = tsp_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TspRoundtripsEntity that = (TspRoundtripsEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
