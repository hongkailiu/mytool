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

  public List<File> findOrphans(File folder, boolean recursive){
    OrphanFinder orphanFinder = orphanFinderFactory.create(folder, recursive);
    return orphanFinder.find();
  }
}
