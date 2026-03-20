package orc.cc.orni.trainer;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
@Data
public class QuizUiState implements Serializable {
    private Category category;

    private List<Bird> birdsInCategory;
    private List<Bird> birdsAnsweredCorrectly;
    private List<Bird> birdsAnsweredWrongly;
    private Bird currentBird;
}
