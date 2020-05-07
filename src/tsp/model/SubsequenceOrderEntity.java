package tsp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "subsequence_order", schema = "tsp_db", catalog = "")
public class SubsequenceOrderEntity implements Serializable {
    private int id;
    private int subsequenceId;
    private String cityOrder;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }


    @Id
    @Column(name = "subsequence_id")
    public int getSubsequenceId() { return subsequenceId;}

    public void setSubsequenceId(int id) {
        this.subsequenceId = id;
    }

    @Id
    @Column(name = "city_order")
    public String getCityOrder() { return cityOrder; }

    public void setCityOrder(String cityOrder) {
        this.cityOrder = cityOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubsequenceOrderEntity that = (SubsequenceOrderEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
