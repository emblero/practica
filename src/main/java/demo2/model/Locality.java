package demo2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("place")
    String place;

    @JsonProperty("region")
    String region;

    @OneToMany
    List<Locality> localities;

    @JsonProperty("latitude")
    Double latitude;

    @JsonProperty("longitude")
    Double longitude;

    @Override
    public String toString() {
        return "Locality{" +
            "id=" + id +
            ", locality='" + place + '\'' +
            ", region='" + region + '\'' +
            ", localities=" + localities +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }
}
