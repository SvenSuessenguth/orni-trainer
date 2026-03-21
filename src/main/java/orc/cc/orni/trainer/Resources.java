package orc.cc.orni.trainer;

import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jakarta.faces.view.ViewScoped;
import jakarta.json.Json;
import jakarta.json.bind.JsonbBuilder;
import lombok.Data;
import lombok.extern.java.Log;

@ViewScoped
@Data
@Log
public class Resources implements Serializable {
  private final List<Bird> birds = new ArrayList<>();

  public Resources() {
    var classLoader = Thread.currentThread().getContextClassLoader();
    var birdsFolder = new File(Objects.requireNonNull(classLoader.getResource("/META-INF/resources/birds")).getPath());

    // In jedem subFolder ist eine Art hinterlegt
    var subFolders = birdsFolder.listFiles();
    if (subFolders == null) {
      return;
    }

    // Lesen der Metadaten in jedem SubFolder zu jeder Art
    for (var subFolder : subFolders) {
      // In jedem SubFolder musss es eine Datei mit Metadaten geben
      var metaDataFiles = subFolder.listFiles((dir, name) -> "meta-data.json".equals(name));
      if (metaDataFiles == null) {
        continue;
      }
      var metaDataFile = Arrays.stream(metaDataFiles).findFirst().orElseThrow();

      try (var jsonb = JsonbBuilder.create()) {
        try (var metaDataReader = Json.createReader(new FileReader(metaDataFile))) {
          var jsonObject = metaDataReader.readObject();
          var bird = jsonb.fromJson(jsonObject.toString(), Bird.class);
          birds.add(bird);
        }
      } catch (Exception e) {
        log.severe(e.getMessage());
      }
    }
  }
}
