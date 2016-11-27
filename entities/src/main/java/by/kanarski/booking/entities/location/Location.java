package by.kanarski.booking.entities.location;

import by.kanarski.booking.utils.Formulas;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long locationId;
    private String contry;
    private String city;
    private Map<Long, LocationTranslation> locationTranslationMap = new HashMap<>();
    private String locationStatus;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Formula(Formulas.COUNTRY_FORMULA)
    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    @Formula(Formulas.CITY_FORMULA)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "LOCATION_ID",
            foreignKey = @ForeignKey(name = "LOCATION_TRANSLATIONS")
    )
    @MapKeyColumn(name = "LANGUAGE_ID")
    public Map<Long, LocationTranslation> getLocationTranslationMap() {
        return locationTranslationMap;
    }

    public void setLocationTranslationMap(Map<Long, LocationTranslation> locationTranslationList) {
        this.locationTranslationMap = locationTranslationList;
    }

    @Column
    public String getLocationStatus() {
        return locationStatus;
    }

    public void setLocationStatus(String locationStatus) {
        this.locationStatus = locationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!locationId.equals(location.locationId)) return false;
        return locationStatus.equals(location.locationStatus);

    }

    @Override
    public int hashCode() {
        int result = locationId.hashCode();
        result = 31 * result + locationStatus.hashCode();
        return result;
    }
}