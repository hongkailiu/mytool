package tk.hongkailiu.mytool.git;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import java.io.File;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.SubCommand;
import tk.hongkailiu.mytool.Command;
import tk.hongkailiu.mytool.git.OrphanFinder.OrphanFinderFactory;
import tk.hongkailiu.mytool.module.CommonModule;
import tk.hongkailiu.mytool.module.GitModule;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@Slf4j
public class GitCommand implements Command {

  @Option(required=true, name="-extensions",usage="extension, eg, \"idx\" \"bitmap\" \"pack\"")
  public String[] extensions;

  @Option(required=true, name="-folder",usage="folder name")
  public File folder;

  @Inject
  private OrphanFinderFactory orphanFinderFactory;

  @Override
  public void execute() {
    Injector injector = Guice.createInjector(new CommonModule(), new GitModule());
    GitHelper gitHelper = new GitHelper(injector);
    log.info("GitCommand ...");
    gitHelper.findOrphans(folder, Arrays.asList(extensions));
  }
}
