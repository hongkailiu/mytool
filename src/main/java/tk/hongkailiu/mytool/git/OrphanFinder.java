package tk.hongkailiu.mytool.git;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import tk.hongkailiu.mytool.helper.FileHelper;

/**
 * Created by hongkailiu on 2017-01-21.
 */
@Slf4j
public class OrphanFinder {

  public static final List<String> EXTENSIONS = Arrays.asList("idx", "bitmap", "pack");

  private final File folder;
  private final boolean recursive;

  @Inject
  /* package */ FileHelper fileHelper;

  @Inject
  public OrphanFinder(@Assisted @NonNull File folder, @Assisted boolean recursive) {
    this.folder = folder;
    this.recursive = recursive;
  }

  public interface OrphanFinderFactory {

    OrphanFinder create(File folder, boolean recursive);
  }

  public List<File> find() {
    List<File> orphans = new ArrayList();
    List<String> list = fileHelper.listFiles(folder, EXTENSIONS, recursive);
    Collections.sort(list);
    log.debug("list.size(): " + list.size());
    String base = null;
    for (int i = list.size() - 1; i >= 0; i--) {
      log.debug("File " + i + ": " + list.get(i));
      if (EXTENSIONS.get(2).equals(FilenameUtils.getExtension(list.get(i)))) {
        base = FilenameUtils.getBaseName(list.get(i));
      } else {
        if (base == null || !FilenameUtils.getBaseName(list.get(i)).equals(base)) {
          File candidate = new File(folder, list.get(i));
          orphans.add(candidate);
        }
      }
    }
    return orphans;
  }
}
