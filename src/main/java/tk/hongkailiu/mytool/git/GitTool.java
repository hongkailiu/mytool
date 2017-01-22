package tk.hongkailiu.mytool.git;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import java.io.File;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import tk.hongkailiu.mytool.git.OrphanFinder;
import tk.hongkailiu.mytool.git.OrphanFinder.OrphanFinderFactory;
import tk.hongkailiu.mytool.module.CommonModule;
import tk.hongkailiu.mytool.module.GitModule;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@Slf4j
public class GitTool {
  @Inject
  private OrphanFinderFactory orphanFinderFactory;



  public static void main(String[] args){
    Injector injector = Guice.createInjector(new CommonModule(), new GitModule());
    GitHelper gitHelper = new GitHelper(injector);
    log.info("GitTool ...");
    //gitHelper.findOrphans()
  }

}
