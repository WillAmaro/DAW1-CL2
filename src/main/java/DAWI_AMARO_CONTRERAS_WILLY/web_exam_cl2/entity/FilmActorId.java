package DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.entity;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FilmActorId implements Serializable {
    private Integer actorId;
    private Integer filmId;
}
