package orc.cc.orni.trainer;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Bird implements Serializable {
  String art;
  String ordnung;
  String familie;
  List<Image> images;
}
