package tsp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tsp", schema = "tsp_db", catalog = "")
public class TspEntity implements Serializable {
    private int id;
    private Double maxDuration;
    private int startCityId;
    private int endCityId;
    private int subsequenceId;

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

    @Id
    @Column(name = "start_city_id")
    public int getStartCityId() {
        return startCityId;
    }

    public void setStartCityId(int id) {
        this.startCityId = id;
    }

    @Id
    @Column(name = "end_city_id")
    public int getEndCityId() {
        return id;
    }

    public void setEndCityId(int id) {
        this.endCityId = id;
    }
    @Id
    @Column(name = "subsequence_id")
    public int getSubsequenceId() { return subsequenceId; }

    public void setSubsequenceId(int id) {
        this.subsequenceId = id;
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
