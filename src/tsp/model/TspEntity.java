package tsp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tsp", schema = "tsp_db", catalog = "")
public class TspEntity implements Serializable {
    private int id;
    private Double maxDuration = null;
    private String name;

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
    @Column(name = "max_duration")
    public Double getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(Double maxDuration) {
        this.maxDuration = maxDuration;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TspEntity tspEntity = (TspEntity) o;
        return id == tspEntity.id &&
                Objects.equals(maxDuration, tspEntity.maxDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maxDuration);
    }
}
