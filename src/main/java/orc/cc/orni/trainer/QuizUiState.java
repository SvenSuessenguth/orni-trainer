package orc.cc.orni.trainer;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@ViewScoped
@Named
@Data
@Log
public class QuizUiState implements Serializable {
    @Inject
    private Resources resources;

    private Category category;
    private List<Bird> birdsInCategory;
    private List<Bird> birdsAnsweredCorrectly;
    private List<Bird> birdsAnsweredWrongly;

    public String getRandomBirdRandomImage() {
        var countBirds = resources.getBirds().size();
        var randomBirdNumber = ThreadLocalRandom.current().nextInt(0, countBirds);
        log.info("Random Bird number is: " + randomBirdNumber);
        var randomBird = resources.getBirds().get(randomBirdNumber);

        var path = randomBird.getArt().replace(" ", "_");
        var countImages = randomBird.getImages().size();
        var randomImageNumber = ThreadLocalRandom.current().nextInt(0, countImages);
        log.info("Random image number is: " + randomImageNumber);
        var randomImageName = randomBird.getImages().get(randomImageNumber).name();

        log.info("randomImageName: " + path.toLowerCase() + "/" + randomImageName);
        return path.toLowerCase() + "/" + randomImageName;
    }
}
