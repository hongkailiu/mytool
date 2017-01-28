package tk.hongkailiu.mytool.git;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.FileConverter;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import tk.hongkailiu.mytool.Command;
import tk.hongkailiu.mytool.git.OrphanFinder.OrphanFinderFactory;
import tk.hongkailiu.mytool.module.CommonModule;
import tk.hongkailiu.mytool.module.GitModule;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@Slf4j
@Parameters(commandDescription = "Git command")
public class GitCommand implements Command {

  public static final String FIND_ORPHANS = "findOrphans";

  @Getter
  @Parameter(names = "-extensions", description = "The extensions, e.g., pack,bitmap,idx")
  private List<String> extensions = new ArrayList();

  @Getter
  @Parameter(names = "-folder", description = "The folder name", converter = FileConverter.class)
  private File folder;

  @Getter
  @Parameter(names = "-action", description = "Action, e.g., " + FIND_ORPHANS, required = true)
  private String action;

  @Inject
  private OrphanFinderFactory orphanFinderFactory;

  @Override
  public void execute() {
    Injector injector =
        Guice.createInjector(new CommonModule(), new GitModule());
    GitHelper gitHelper = new GitHelper(injector);
    log.info("GitCommand ...");

    validate();

    System.out.println("GitCommand ...");
    System.out.println("folder: " + folder.getAbsolutePath());
    System.out.println("extensions: " + Arrays.toString(extensions.toArray()));
    List<File> files = gitHelper.findOrphans(folder, extensions);
    System.out.println("orphan number: " + files.size());
    files.forEach(f -> System.out.println("orphan file: " + f.getAbsolutePath()));
  }

  private void validate() {
    if (StringUtils.isBlank(action)) {
      throw new ParameterException("empty action");
    }

    switch (action) {  // $COVERAGE-IGNORE$ https://github.com/jacoco/jacoco/wiki/FilteringOptions
      case FIND_ORPHANS:
        if (folder == null || !folder.exists() || !folder
            .isDirectory()) {
          throw new ParameterException("no such a folder: " + folder.getAbsolutePath());
        }
        break;
      default:
        throw new ParameterException("not supported git action: " + action);
    }
  }
}
