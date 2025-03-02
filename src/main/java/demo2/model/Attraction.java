package demo2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import demo2.model.enums.AttractionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import lombok.Setter;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;


    @JsonProperty("name")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;

    @JsonProperty("description")
    private String description;

    @JsonProperty("attractionType")
    @Enumerated(EnumType.STRING)
    private AttractionType attractionType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "locality_id")
    @JsonProperty("locality")
    private Locality locality;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "assistance_id")
    @JsonProperty("assistance")
    private Assistance assistance; // Добавлено поле для связи с Assistance

    public Attraction(long l, String eiffelTowerRide, LocalDate of, String s) {
        this.id = l;
        this.name = eiffelTowerRide;
        this.dateCreation = of;
        this.description = s;
    }


    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Attraction{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", dateCreation=" + dateCreation +
            ", description='" + description + '\'' +
            ", attractionType=" + attractionType +
            ", locality=" + locality +
            ", assistance=" + assistance +
            '}';
    }
}