package tsp.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "city", schema = "tsp_db", catalog = "")
public class CityEntity {
    private int id;
    private double xCoordinate;
    private double yCoordinate;
    private String name;
    public CityEntity() { }
    public CityEntity(double xCoordinate, double yCoordinate, String name) {
        setName(name);
        setxCoordinate(xCoordinate);
        setyCoordinate(yCoordinate);
    }
    public CityEntity(String name, double xCoordinate, double yCoordinate) {
        setName(name);
        setxCoordinate(xCoordinate);
        setyCoordinate(yCoordinate);
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

    @Basic
    @Column(name = "x_coordinate")
    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    @Basic
    @Column(name = "y_coordinate")
    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
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
        CityEntity that = (CityEntity) o;
        return id == that.id &&
                Double.compare(that.xCoordinate, xCoordinate) == 0 &&
                Double.compare(that.yCoordinate, yCoordinate) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, xCoordinate, yCoordinate, name);
    }

    public static CityEntity findCityByName(List<CityEntity> cities, String name) {
        for (CityEntity city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }
}
