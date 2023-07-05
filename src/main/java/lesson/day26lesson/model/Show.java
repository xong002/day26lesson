package lesson.day26lesson.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {
    private Integer id;
    private String name;
    private String language;
    private Integer runtime;
    private Double averageRating;

}
