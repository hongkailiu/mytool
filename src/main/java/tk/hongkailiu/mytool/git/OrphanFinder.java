package tk.hongkailiu.mytool.git;

import java.io.File;
import java.util.ArrayList;
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
  private String[] extensions;

  public OrphanFinder(@NonNull File folder, @NonNull String[] extensions) {
    this.folder = folder;
    this.extensions = extensions;
  }

  public List<File> find() {
    List<File> orphans = new ArrayList();
    File[] files = folder.listFiles();

    if (files == null) {
      return orphans;
    }

    for (File file : files) {
      if (file.isFile() && isOrphan(file)) {
        orphans.add(file);
      }
    }

    return orphans;
  }

  private boolean isOrphan(File file) {
    String absolutePath = file.getAbsolutePath();
    String basename = FilenameUtils.getBaseName(absolutePath);
    String extension = FilenameUtils.getExtension(absolutePath);
    log.debug("basename: " + basename);
    log.debug("extension " + extension);
    for (String myExtension : extensions) {
      if (!myExtension.equals(extension) && !new File(folder, basename + "." + myExtension)
          .exists()) {
        return true;
      }
    }
    return false;
  }
}
