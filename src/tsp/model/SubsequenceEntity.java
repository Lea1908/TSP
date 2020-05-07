package tsp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "subsequence", schema = "tsp_db", catalog = "")
public class SubsequenceEntity implements Serializable {
    private int id;
    private int tspId;

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
    public int getTspId() {
        return tspId;
    }

    public void setTspId(int id) {
        this.tspId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubsequenceEntity that = (SubsequenceEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
