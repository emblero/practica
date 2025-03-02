package demo2.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AttractionType {
    CASTLE("Дворец"),
    PARK("Парк"),
    MUSEUM("Музей"),
    RESERVE("Заповедник");
    private String description;
    AttractionType(String description) {
        this.description = description;
    }
    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static AttractionType fromValue(String value) {
        for (AttractionType type : values()) {
            if (type.description.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown attraction type: " + value);
    }
}
