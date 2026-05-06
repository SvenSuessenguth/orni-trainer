package orc.cc.orni.trainer;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class QuizUiService {
    @Inject
    private QuizUiState quizUiState;

    public Category[] getAllCategories() {
        return Category.values();
    }

    public Hint[] getAllHints() {
        return Hint.values();
    }

    public String correct() {
        var bird = quizUiState.getBird();
        quizUiState.getBirdsAnsweredNotCorrect().remove(bird);
        quizUiState.selectBird();
        return null;
    }

    public String incorrect() {
        quizUiState.selectBird();
        return null;
    }

    public String showSolution() {
        quizUiState.setShowResolution(true);
        return null;
    }

    public String selectHint(AjaxBehaviorEvent event) {
        quizUiState.selectBird();
        return null;
    }
}
