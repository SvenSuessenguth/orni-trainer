package orc.cc.orni.trainer;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class QuizUiService {
    public Category[] getAllCategories() {
        return Category.values();
    }

    public Hint[] getAllHints() {
        return Hint.values();
    }
}
