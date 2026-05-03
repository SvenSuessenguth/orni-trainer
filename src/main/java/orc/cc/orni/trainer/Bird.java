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
  String order;
  String family;
  String species;
  String habitat;
  @EqualsAndHashCode.Exclude
  List<Image> images;
  @EqualsAndHashCode.Exclude
  List<Sound> sounds;
}
