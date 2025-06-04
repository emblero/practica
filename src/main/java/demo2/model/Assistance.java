package demo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import demo2.model.enums.AssistanceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Assistance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @JsonProperty("assistanceType")
    private AssistanceType assistanceType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("executor")
    private String executor;

    @OneToMany(mappedBy = "assistance")
    @JsonIgnore
    private List<Attraction> attractionList;

    @Override
    public String toString() {
        return "Assistance{" +
            "id=" + id +
            ", assistanceType=" + assistanceType +
            ", description='" + description + '\'' +
            ", executor='" + executor + '\'' +
            ", attractionList=" + attractionList +
            '}';
    }
}