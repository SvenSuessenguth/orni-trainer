package orc.cc.orni.trainer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.json.Json;
import jakarta.json.bind.JsonbBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class Data implements Serializable {
    List<Bird> birds = new ArrayList<>();

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) throws Exception {
        var classLoader = Thread.currentThread().getContextClassLoader();
        var birdsFolder = new File(Objects.requireNonNull(classLoader.getResource("/META-INF/resources/birds")).getPath());
        // In jedem subFolder ist eine Art hinterlegt
        var subFolders = birdsFolder.listFiles();

        var fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return "meta-data.json".equals(name);
            }
        };
        for (var subFolder : subFolders) {
            var file = Arrays.stream(subFolder.listFiles(fileNameFilter)).findFirst().orElseThrow();
            var reader = Json.createReader(new FileReader(file));
            var jsonst = reader.read();

            var bird = JsonbBuilder.create().fromJson(jsonst.toString(), Bird.class);
            birds.add(bird);
        }

        System.out.println(birds);
    }
}
