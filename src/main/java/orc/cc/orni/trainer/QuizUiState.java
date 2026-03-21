package orc.cc.orni.trainer;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

@ViewScoped
@Named
@Data
public class QuizUiState implements Serializable {
  @Inject
  private Resources resources;

  private Category category;
  private List<Bird> birdsInCategory;
  private List<Bird> birdsAnsweredCorrectly;
  private List<Bird> birdsAnsweredWrongly;

  public String getRandomBirdRandomImage() {
    var countBirds = resources.getBirds().size();
    var randomBirdNumber = ThreadLocalRandom.current().nextInt(0, countBirds + 1);
    var randomBird = resources.getBirds().get(randomBirdNumber);

    var path = randomBird.getArt().replace(" ", "_");
    var countImages = randomBird.getImages().size();
    var randomImageNumber = ThreadLocalRandom.current().nextInt(0, countImages + 1);
    var randomImageName = randomBird.getImages().get(randomImageNumber).name();

    return path.toLowerCase() + "/" + randomImageName;
  }
}
