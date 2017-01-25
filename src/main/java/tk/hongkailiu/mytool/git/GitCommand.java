package tk.hongkailiu.mytool.git;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.io.File;
import java.util.Arrays;

import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import tk.hongkailiu.mytool.Command;
import tk.hongkailiu.mytool.git.OrphanFinder.OrphanFinderFactory;
import tk.hongkailiu.mytool.module.CommonModule;
import tk.hongkailiu.mytool.module.GitModule;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@Slf4j
public class GitCommand implements Command {

  @Option(required = true, name = "-extensions", usage = "extension, eg, \"idx\" \"bitmap\" \"pack\"", handler = StringArrayOptionHandler.class)
  @Getter
  private String[] extensions;

  @Option(required = true, name = "-folder", usage = "folder name")
  @Getter
  private File folder;

  @Inject
  private OrphanFinderFactory orphanFinderFactory;

  @Override
  public void execute() {
    Injector injector =
        Guice.createInjector(new CommonModule(), new GitModule());
    GitHelper gitHelper = new GitHelper(injector);
    log.info("GitCommand ...");
    System.out.println("GitCommand ...");
    System.out.println("folder: " + folder.getAbsolutePath());
    System.out.println("extensions: " + Arrays.toString(extensions));
    List<File> files = gitHelper.findOrphans(folder, Arrays.asList(extensions));
    System.out.println("orphan number: " + files.size());
    files.forEach(f -> System.out.println("orphan file: " + f.getAbsolutePath()));
  }
}
