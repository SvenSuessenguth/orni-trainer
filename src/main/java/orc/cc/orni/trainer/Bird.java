package orc.cc.orni.trainer;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Bird implements Serializable {
  @EqualsAndHashCode.Exclude
  Category category;
  String species;
  String ordnung;
  String familie;
  @EqualsAndHashCode.Exclude
  List<Image> images;
  @EqualsAndHashCode.Exclude
  List<Sound> sounds;
}
