package demo2.model.enums;

import lombok.AllArgsConstructor;


public enum AssistanceType {
    GID("Гит"),
    AUTOTOUR("Авто Экскурсия"),
    FOOD("Питание");
    private String description;
    AssistanceType(String description){
        this.description = description;
    }
}
