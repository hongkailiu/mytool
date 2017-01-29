package tk.hongkailiu.mytool.git;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

/**
 * Created by hongkailiu on 2017-01-21.
 */
@Slf4j
public class OrphanFinder {

  private File folder;
  private List<String> extensions = Arrays.asList("idx", "bitmap", "pack");

  @Inject
  public OrphanFinder(@Assisted @NonNull File folder) {
    this.folder = folder;
  }

  public interface OrphanFinderFactory {

    OrphanFinder create(File folder);
  }

  public List<File> find() {
    List<File> orphans = new ArrayList();
    Path packDir = Paths.get(folder.getAbsolutePath());

    String[] list = packDir.toFile().list((file, name) ->
        extensions.stream().anyMatch(ext -> name.endsWith("." + ext))
    );
    if (list == null) {
      return orphans;
    }
    Arrays.sort(list);

    String base = null;
    for (int i = list.length - 1; i >= 0; i--) {
      if (list[i].endsWith(extensions.get(2))) {
        base = FilenameUtils.getBaseName(list[i]);
      } else {
        if (base == null || !list[i].startsWith(base)) {
          File candidate = new File(packDir.toFile(), list[i]);
          if (!candidate.isDirectory()) {
            orphans.add(candidate);
          }
        }
      }
    }
    return orphans;
  }
}
