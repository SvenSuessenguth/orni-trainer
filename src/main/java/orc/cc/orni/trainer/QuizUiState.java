package orc.cc.orni.trainer;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@ViewScoped
@Named
@Data
@Log
public class QuizUiState implements Serializable {
    @Inject
    private Resources resources;

    private Category category = Category.BRONZE;
    private Hint hint;

    private Bird bird;
    private String imageName;
    private String soundName;
    private boolean showResolution = false;

    private Set<Bird> birdsAnsweredNotCorrect = new HashSet<>();

    @PostConstruct
    public void postConstruct() {
        // load resources
        resources.getBirds().stream()
                .filter(b -> b.getCategory() == category)
                .forEach(b -> birdsAnsweredNotCorrect.add(b));

        // select first bird
        selectBird();
    }


    void selectBird() {
        // Bird
        var countBirds = birdsAnsweredNotCorrect.size();
        var birdNumber = ThreadLocalRandom.current().nextInt(0, countBirds);
        log.info("Random Bird number is: " + birdNumber + " out of " + countBirds);
        bird = resources.getBirds().get(birdNumber);

        // Image
        var path = bird.getSpecies().replace(" ", "_");
        var countImages = bird.getImages().size();
        var randomImageNumber = ThreadLocalRandom.current().nextInt(0, countImages);
        log.info("Random image number is: " + randomImageNumber + " out of " + countImages);
        var randomImageName = bird.getImages().get(randomImageNumber).name();
        log.info("randomImageName: " + path.toLowerCase() + "/" + randomImageName);
        imageName = path.toLowerCase() + "/" + randomImageName;

        // Sound
        var countSounds = bird.getSounds().size();
        if (countSounds > 0) {
            var randomSoundNumber = ThreadLocalRandom.current().nextInt(0, countSounds);
            log.info("Random soound number is: " + randomSoundNumber + " out of " + countSounds);
            var randomSoundName = bird.getImages().get(randomSoundNumber).name();
            log.info("randomSoundName: " + path.toLowerCase() + "/" + randomSoundName);
            soundName = path.toLowerCase() + "/" + randomSoundName;
        }else {
            soundName = null;
        }

        showResolution = false;
    }

    public boolean isImageHint() {
        return hint == Hint.IMAGE;
    }

    public boolean isSoundHint() {
        return hint == Hint.SOUND;
    }

    public boolean isSpeciesHint() {
        return hint == Hint.SPECIES;
    }

    public int getCountBirdsLeft() {
        return birdsAnsweredNotCorrect.size();
    }
}
