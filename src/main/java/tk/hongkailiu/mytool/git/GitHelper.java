package tk.hongkailiu.mytool.git;

import com.google.inject.Injector;
import java.io.File;
import java.util.List;
import tk.hongkailiu.mytool.git.OrphanFinder.OrphanFinderFactory;

/**
 * Created by hongkailiu on 2017-01-22.
 */
public class GitHelper {
  private final OrphanFinderFactory orphanFinderFactory;

  public GitHelper(Injector injector) {
    this.orphanFinderFactory = injector.getInstance(OrphanFinderFactory.class);
  }

  public List<File> findOrphans(File folder, List<String> extensions){
    OrphanFinder orphanFinder = orphanFinderFactory.create(folder, extensions);
    return orphanFinder.find();
  }
}
